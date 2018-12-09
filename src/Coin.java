import java.awt.Color;
import java.awt.Graphics;

public class Coin extends CollisionProjectile {
    
    public static final int SIZE = 20;
    public static final int INIT_POS_X = (int) (Math.random() * 250);
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 3;
    public static final Color COLOR = Color.YELLOW;
    public static final int COIN_VALUE = 1;
    public static final int DEALT_DAMAGE = 0;

    public Coin(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * 250), INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight, DEALT_DAMAGE);
    }
    
    @Override
    public void collisionEffect(Spaceship ship) {
        ship.addCoins(COIN_VALUE);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
