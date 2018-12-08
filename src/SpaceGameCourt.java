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
    private List<Asteroid> asteroids;

    public boolean playing = false;
    private JLabel health;
    private JLabel score;

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
    public static final int SHIP_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public SpaceGameCourt(JLabel health, JLabel score) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // setup timer
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
        
        Timer asteroidTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                asteroids.add(new Asteroid(50, 0, COURT_WIDTH, COURT_HEIGHT, Color.BLACK));
            }
        });
        asteroidTimer.start();

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
                }
            }

            public void keyReleased(KeyEvent e) {
                ship.setVx(0);
                ship.setVy(0);
            }
        });

        // initialize state
        this.health = health;
        this.score = score;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        asteroids = new LinkedList<Asteroid>();
        ship = new Spaceship(COURT_WIDTH, COURT_HEIGHT, Color.RED);
        playing = true;
        updateHealthLabel();
        score.setText("Score: 0");

        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            ship.move();
            for (Iterator<Asteroid> iterator = asteroids.iterator(); iterator.hasNext();) {
                
                Asteroid nextAsteroid = iterator.next();
                
                nextAsteroid.move();
                if (nextAsteroid.intersects(ship)) {
                    ship.takeDamage(10);
                    updateHealthLabel();
                    iterator.remove();
                    if (ship.getHealth() == 0) {
                        playing = false;
                    }
                } else if (nextAsteroid.hitWall() != null) {
                    iterator.remove();
                }
            }
            
            repaint();
        }
    }
    
    private void updateHealthLabel() {
        health.setText("Health: " + ship.getHealth() + "/" + ship.MAX_HEALTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        for (Asteroid nextAsteroid : asteroids) {
            nextAsteroid.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}