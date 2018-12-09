/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * circle of a specified color.
 */
public class Asteroid extends CollisionProjectile implements Damageable {
    public static final int SIZE = 20;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final int DEALT_DAMAGE = 20;
    public static final Color COLOR = Color.BLACK;
    public static final int MAX_HEALTH = 40;
    
    private int health;

    public Asteroid(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * 250), INIT_POS_Y, SIZE, SIZE, courtWidth, 
                courtHeight, DEALT_DAMAGE);
        this.health = MAX_HEALTH;
    }

    @Override
    public void takeDamage(int dmg) {
        health = Integer.max(0, health - dmg);
    }
    
    @Override
    public boolean isDead() {
        return health == 0;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}