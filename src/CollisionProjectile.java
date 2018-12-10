/**
 * CollisionProjectile
 * 
 * This class represents a projectile that can collide with the users ship.
 */
public abstract class CollisionProjectile extends FlyingObj{
    
    private int collisionDamage;
    
    public CollisionProjectile(int vx, int vy, int px, int py, int width, int height, int damage) {
        super(vx, vy, px, py, width, height);
        this.collisionDamage = damage;
    }
    
    /**
     * by default, this method does damage to the passed in ship
     * @param ship
     */
    public void hitShip(Spaceship ship) {
        ship.takeDamage(collisionDamage);
    }
}
