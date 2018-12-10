import java.awt.Color;
import java.awt.Graphics;

/**
 * Coin
 * 
 * This class represents a coin that adds to the ships coin value on collision.
 */
public class Coin extends CollisionProjectile {
    
    public static final int SIZE = 20;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final Color COLOR = Color.YELLOW;
    public static final int DEALT_DAMAGE = 0;
    
    private int coinValue;
    
    public Coin(int coinValue) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * SpaceGameCourt.COURT_WIDTH - SIZE), 
                INIT_POS_Y, SIZE, SIZE, DEALT_DAMAGE);
        this.coinValue = coinValue;
    }
    
    @Override
    public void hitShip(Spaceship ship) {
        ship.addCoins(coinValue);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(getPx(), getPy(), getWidth(), getHeight());
    }
}
