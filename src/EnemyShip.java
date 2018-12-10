import java.awt.Color;
import java.awt.Graphics;

/**
 * EnemyShip
 * 
 * This class represents an enemy ship that can fire lasers at the user and collide
 * with the user and take damage.
 */
public class EnemyShip extends CollisionProjectile implements Damageable {

    // enemy ship constants
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 30;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final int LASER_SPEED = 7;
    public static final Color ENEMY_COLOR = Color.BLUE;
    
    private int health;
    private int fireCounter;
    private SpaceGameCourt court;

    public EnemyShip(SpaceGameCourt court, int damage, int health) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * SpaceGameCourt.COURT_WIDTH - SIZE_X),
                INIT_POS_Y, SIZE_X, SIZE_Y, damage);
        this.fireCounter = 0;
        this.court = court;
        this.health = health;
    }
    
    @Override
    public void move() {
        super.move();
        fire();
    }
    
    /**
     * shoots a laster every 10 moves
     */
    public void fire() {
        if (fireCounter <= 0) {
            court.addEnemyLaser(getPx() + 8, getPy() + 30);
            fireCounter = 10;
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
        g.setColor(ENEMY_COLOR);
        g.fillOval(getPx(), getPy(), getWidth(), getHeight());
    }
}
