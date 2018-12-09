
public abstract class CollisionProjectile extends FlyingProjectile{
    
    private int collisionDamage;
    
    public CollisionProjectile(int vx, int vy, int px, int py, int width, int height, int courtWidth, 
            int courtHeight, int dmg) {
        super(vx, vy, px, py, width, height, courtWidth, courtHeight);
        this.collisionDamage = dmg;
    }
    
    public void collisionEffect(Spaceship ship) {
        ship.dealDamage(collisionDamage);
    }    
}
