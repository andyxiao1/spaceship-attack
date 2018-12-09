
public abstract class CollisionProjectile extends FlyingObj{
    
    private int collisionDamage;
    
    public CollisionProjectile(int vx, int vy, int px, int py, int width, int height, int courtWidth, 
            int courtHeight, int dmg) {
        super(vx, vy, px, py, width, height, courtWidth, courtHeight);
        this.collisionDamage = dmg;
    }
    
    public void hitShip(Spaceship ship) {
        ship.takeDamage(collisionDamage);
    }
    
}
