import java.awt.*;

/**
 * Asteroid
 * 
 * This class represents an asteroid that can collide
 * with the user and take damage
 */
public class Asteroid extends CollisionProjectile implements Damageable {
    public static final int SIZE = 20;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final Color ASTEROID_COLOR = Color.BLACK;
    
    private int health;

    public Asteroid(int damage, int health) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * SpaceGameCourt.COURT_WIDTH - SIZE), INIT_POS_Y, 
               SIZE, SIZE, damage);
        this.health = health;
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
        g.setColor(ASTEROID_COLOR);
        g.fillOval(getPx(), getPy(), getWidth(), getHeight());
    }
}