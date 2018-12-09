import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class EnemyShip extends CollisionProjectile implements Damageable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 30;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final int DEALT_DAMAGE = 40;
    public static final int MAX_HEALTH = 60;

    
    public static final Color COLOR = Color.BLUE;
    
    // to change
    private int courtWidth;
    private int courtHeight;
    private int health;
    private int fireCounter;
    
    // bad
    private SpaceGameCourt court;

    public EnemyShip(int courtWidth, int courtHeight, SpaceGameCourt court) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * 250), INIT_POS_Y, SIZE_X, SIZE_Y, courtWidth, 
                courtHeight, DEALT_DAMAGE);
        this.fireCounter = 0;
        this.courtWidth = courtWidth;
        this.courtHeight = courtHeight;
        this.court = court;
        this.health = MAX_HEALTH;
    }
    
    @Override
    public void move() {
        super.move();
        fire();
    }
    
    public void fire() {
        if (fireCounter <= 0) {
            fireCounter = 10;
            court.addEnemyLaser(new Laser(this.getPx() + 8, this.getPy() + 30, 7, courtWidth, courtHeight,
                   Color.RED));
        } else {
            fireCounter--;
        }
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
