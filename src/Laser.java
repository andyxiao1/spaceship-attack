import java.awt.Color;
import java.awt.Graphics;

public class Laser extends CollisionProjectile {

    public static final int SIZE_X = 5;
    public static final int SIZE_Y = 10;
    public static final int INIT_VEL_X = 0;
    public static final int DEALT_DAMAGE = 20;
    
    private Color color;

    public Laser(int px, int py, int vy, int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, vy, px, py, SIZE_X, SIZE_Y, courtWidth, courtHeight, DEALT_DAMAGE);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    public void hitProjectile(Damageable proj) {
        proj.takeDamage(DEALT_DAMAGE);
    }
}
