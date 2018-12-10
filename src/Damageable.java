/**
 * Damageable
 * 
 * This interface represents a flying obj that has health and can take damage. When health is
 * 0 the flying obj is considered dead.
 */
public interface Damageable {

    public void takeDamage(int dmg);
    public boolean isDead();
}
