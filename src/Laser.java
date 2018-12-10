import java.awt.Color;
import java.awt.Graphics;

/**
 * Laser
 * 
 * This class represents a laser that is fired by ships. It does damage on collision.
 */
public class Laser extends CollisionProjectile {

    public static final int SIZE_X = 5;
    public static final int SIZE_Y = 10;
    public static final int INIT_VEL_X = 0;
    public static final int DEALT_DAMAGE = 20;
    
    private Color color;

    public Laser(int px, int py, int vy, Color color) {
        super(INIT_VEL_X, vy, px, py, SIZE_X, SIZE_Y, DEALT_DAMAGE);
        this.color = color;
    }

    /**
     * deals damage to the passed in Damageable projectile
     * @param proj
     */
    public void hitProjectile(Damageable proj) {
        proj.takeDamage(DEALT_DAMAGE);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(getPx(), getPy(), getWidth(), getHeight());
    }
}
