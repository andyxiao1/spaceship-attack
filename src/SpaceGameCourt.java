import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class SpaceGameCourt extends JPanel {

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 350;
    public static final int SHIP_VELOCITY = 4;
    public static final int ENEMY_LASER_SPEED = 7;
    public static final Color ENEMY_LASER_COLOR = Color.RED;
    public static final int USER_LASER_SPEED = -7;
    public static final Color USER_LASER_COLOR = Color.GREEN;

    public static final int TICK_INTERVAL = 35;
    public static final int PROJECTILE_INTERVAL = 400;
    
    // the state of the game logic
    private Spaceship ship;
    private List<CollisionProjectile> projectilesOnScreen;
    private Queue<CollisionProjectile> remainingProjectiles;
    private List<Laser> userLasers;

    public boolean playing = false;
    private JLabel health;
    private JLabel coins;
    private int level;
    private Game game;
    
    private Timer gameTimer;
    private Timer projTimer;

    public SpaceGameCourt(JLabel health, JLabel coins, int level, Game game) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // setup timers
        gameTimer = new Timer(TICK_INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        gameTimer.start();
                
        projTimer = new Timer(PROJECTILE_INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!remainingProjectiles.isEmpty()) {
                    projectilesOnScreen.add(remainingProjectiles.remove());
                }
            }
        });
        projTimer.start();

        // setup keyboard functionality
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ship.setVx(-SHIP_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ship.setVx(SHIP_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    ship.setVy(SHIP_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    ship.setVy(-SHIP_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ship.fire();
                }
            }

            public void keyReleased(KeyEvent e) {
                ship.setVx(0);
                ship.setVy(0);
            }
        });

        // initialize state
        this.health = health;
        this.coins = coins;
        this.level = level;
        this.game = game;
    }

    /**
     * sets the game to its initial state.
     */
    public void startGame() {
        projectilesOnScreen = new ArrayList<CollisionProjectile>();
        remainingProjectiles = LevelReader.getLevel(level, this);
        userLasers = new ArrayList<Laser>();
        ship = new Spaceship(this);
        playing = true;

        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            if (projectilesOnScreen.isEmpty() && remainingProjectiles.isEmpty()) {
                playing = false;
                gameOver(true);
            }
            
            ship.move();
            updateProjectilesOnScreen();
            updateUserLasers();
            
            repaint();
        }
    }
    
    /**
     * Called by tick(), moves projectiles on screen and implements proper game logic
     */
    private void updateProjectilesOnScreen() {
        for (int i = 0; i < projectilesOnScreen.size(); i++) {
            CollisionProjectile nextProj = projectilesOnScreen.get(i);
            nextProj.move();
            
            if (nextProj.collidesWith(ship)) {
                nextProj.hitShip(ship);
                projectilesOnScreen.remove(i);
                i--;
                if (ship.isDead()) {
                    playing = false;
                    gameOver(false);
                }
            } else if (nextProj.finishedCourse()) {
                projectilesOnScreen.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Called by tick(), moves user lasers and implements proper game logic
     */
    private void updateUserLasers() {
        for (int i = 0; i < userLasers.size(); i++) {
            Laser nextLaser = userLasers.get(i);
            nextLaser.move();
            
            for (int j = 0; j < projectilesOnScreen.size(); j++) {
                CollisionProjectile nextProj = projectilesOnScreen.get(j);

                if (nextProj instanceof Damageable && nextProj.collidesWith(nextLaser)) {
                    Damageable proj = (Damageable) nextProj;
                    nextLaser.hitProjectile(proj);
                    
                    if (proj.isDead()) {
                        projectilesOnScreen.remove(j);
                        j--;
                    }
                    userLasers.remove(i);
                    i--;
                }
            }
            
            if (nextLaser.finishedCourse()) {
                userLasers.remove(i);
                i--;
            }
        }
    }
    
    /**
     * ends game by stopping timers, outputs whether or not they won, and checks if high score
     * @param didWin
     */
    public void gameOver(boolean didWin) {
        gameTimer.stop();
        projTimer.stop();
        int coins = ship.getCoins();
        
        if (didWin && isHighScore()) {
            JOptionPane.showMessageDialog(null, "HIGHSCORE!" + "\nCoins: " + coins);
            String name = JOptionPane.showInputDialog("Name: ");
            String quote = JOptionPane.showInputDialog("Quote: ");
            
            name = (name == null || name.equals("")) ? "Guest" : name;
            quote = (quote == null || quote.equals("")) ? "No Comment" : quote;
            
            game.getScoreKeeper().addScore(new HighScore(coins, name, quote));
        } else {
            String output = (didWin ? "You Won!" : "You Lost :(") + "\nCoins: " + coins;
            JOptionPane.showMessageDialog(null, output);
        }
        
        game.showMenu();
    }
    
    private boolean isHighScore() {
        return level == 3 && game.getScoreKeeper().isHighScore(ship.getCoins());
    }
    
    /**
     * adds enemy laser to projectiles on screen at specified location
     * @param px
     * @param py
     */
    public void addEnemyLaser(int px, int py) {
        Laser toAdd = new Laser(px, py, ENEMY_LASER_SPEED, ENEMY_LASER_COLOR);
        projectilesOnScreen.add(toAdd);
    }
    
    /**
     * adds user laser on screen at specified location
     * @param px
     * @param py
     */
    public void addUserLaser(int px, int py) {
        Laser toAdd = new Laser(px, py, USER_LASER_SPEED, USER_LASER_COLOR);
        userLasers.add(toAdd);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        
        for (CollisionProjectile nextProjectile : projectilesOnScreen) {
            nextProjectile.draw(g);
        }
        
        for (Laser nextLaser : userLasers) {
            nextLaser.draw(g);
        }
        
        health.setText("Health: " + ship.getHealth() + "/" + Spaceship.MAX_HEALTH);
        coins.setText("Coins: " + ship.getCoins());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}