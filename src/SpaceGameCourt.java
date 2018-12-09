/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
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
    
    // the state of the game logic
    private Spaceship ship;
    private List<CollisionProjectile> projectilesOnScreen;
    private Queue<CollisionProjectile> remainingProjectiles;

    public boolean playing = false;
    private JLabel health;
    private JLabel coins;

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
    public static final int SHIP_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public SpaceGameCourt(JLabel health, JLabel coins) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // setup timer
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
                
        Timer projTimer = new Timer(333, new ActionListener() {
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
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        projectilesOnScreen = new LinkedList<CollisionProjectile>();
        remainingProjectiles = new LinkedList<CollisionProjectile>();
        loadProjectiles();
        ship = new Spaceship(COURT_WIDTH, COURT_HEIGHT, Color.RED, this);
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
            }
            
            ship.move();
            for (int i = 0; i < projectilesOnScreen.size(); i++) {
                
                CollisionProjectile nextProj = projectilesOnScreen.get(i);
                
                nextProj.move();
                if (nextProj.collidesWith(ship)) {
                    nextProj.collisionEffect(ship);
                    projectilesOnScreen.remove(i);
                    i--;
                    if (ship.getHealth() == 0) {
                        playing = false;
                    }
                } else if (nextProj.finishedCourse()) {
                    projectilesOnScreen.remove(i);
                    i--;
                }
            }
            
            repaint();
        }
    }
    
    public void loadProjectiles() {
        for (int i = 0; i < 20; i++) {
            remainingProjectiles.add(new Asteroid(COURT_WIDTH, COURT_HEIGHT));
            remainingProjectiles.add(new EnemyShip(COURT_WIDTH, COURT_HEIGHT, this));
            remainingProjectiles.add(new Coin(COURT_WIDTH, COURT_WIDTH));
        }
    }
    
    public void addProjectile(CollisionProjectile p) {
        projectilesOnScreen.add(p);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        for (CollisionProjectile nextProjectile : projectilesOnScreen) {
            nextProjectile.draw(g);
        }
        
        health.setText("Health: " + ship.getHealth() + "/" + ship.MAX_HEALTH);
        coins.setText("Coins: " + ship.getCoins());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}