import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class EnemyShip extends CollisionProjectile {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 30;
    public static final int INIT_POS_X = (int) (Math.random() * 250);
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final int DEALT_DAMAGE = 40;
    public static final Color COLOR = Color.BLUE;
    
    
    // to change
    private int courtWidth;
    private int courtHeight;
    
    // bad
    private SpaceGameCourt court;
    
    private int fireCounter;

    public EnemyShip(int courtWidth, int courtHeight, SpaceGameCourt court) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * 250), INIT_POS_Y, SIZE_X, SIZE_Y, courtWidth, courtHeight, DEALT_DAMAGE);
        this.fireCounter = 0;
        this.courtWidth = courtWidth;
        this.courtHeight = courtHeight;
        this.court = court;
    }
    
    @Override
    public void move() {
        super.move();
        if (fireCounter <= 0) {
            fireCounter = 10;
            court.addProjectile(new Laser(this.getPx() + 8, this.getPy() + 30, 7, courtWidth, courtHeight, Color.RED));
        } else {
            fireCounter--;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
