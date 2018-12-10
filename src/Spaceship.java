import java.awt.*;

/**
 * Spaceship
 * 
 * This class represents the users spaceship that can take damage and shoot lasers
 */
public class Spaceship extends FlyingObj implements Damageable {
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 140;
    public static final int INIT_POS_Y = 270;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int MAX_HEALTH = 200;
    public static final Color SHIP_COLOR = Color.RED;

    private int coins;
    private SpaceGameCourt court;
    private int health;
    
    public Spaceship(SpaceGameCourt court) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE);

        this.coins = 0;
        this.court = court;
        this.health = MAX_HEALTH;
    }
    
    /**
     * shoots user laser at its current location
     */
    public void fire() {
        court.addUserLaser(getPx() + 8, getPy() - 21);
    }   
    
    /**
     * adds coinVal to the total coin count
     * @param coinVal
     */
    public void addCoins(int coinVal) {
        coins += coinVal;
    }
    
    /**
     * getter for health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * getter for health
     */
    public int getCoins() {
        return coins;
    }
    
    @Override
    public boolean isDead() {
        return health == 0;
    }
    
    @Override
    public void takeDamage(int dmg) {
        health = Integer.max(0, health - dmg);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(SHIP_COLOR);
        g.fillRect(getPx(), getPy(), getWidth(), getHeight());
    }
}