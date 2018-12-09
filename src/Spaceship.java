/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * square of a specified color.
 */
public class Spaceship extends FlyingObj implements Damageable {
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 148;
    public static final int INIT_POS_Y = 250;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int INIT_COINS = 0;
    public static final int MAX_HEALTH = 200;

    private int coins;
    private Color color;
    private SpaceGameCourt court;
    private int courtWidth;
    private int courtHeight;
    private int health;
    
    /**
    * Note that, because we don't need to do anything special when constructing a Square, we simply
    * use the superclass constructor called with the correct parameters.
    */
    public Spaceship(int courtWidth, int courtHeight, Color color, SpaceGameCourt court) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
        this.coins = INIT_COINS;
        this.court = court;
        this.courtWidth = courtWidth;
        this.courtHeight = courtHeight;
        this.health = MAX_HEALTH;
    }
    
    public void fire() {
        court.addUserLaser(new Laser(this.getPx() + 8, this.getPy() - 21, -7, courtWidth, courtHeight, Color.GREEN));
    }   
    
    public int getHealth() {
        return health;
    }
    
    public boolean isDead() {
        return health == 0;
    }
    
    public int getCoins() {
        return coins;
    }
    
    public void addCoins(int coinVal) {
        coins += coinVal;
    }
    
    @Override
    public void takeDamage(int dmg) {
        health = Integer.max(0, health - dmg);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}