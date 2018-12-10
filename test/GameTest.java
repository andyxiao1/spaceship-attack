import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void test() {
        assertTrue(true);
    }
    
    // FlyingObj Tests
    @Test
    public void testCollidesWithCenter() {
        Asteroid a1 = new Asteroid(10, 10);
        a1.setPx(20);
        a1.setPy(20);
        Asteroid a2 = new Asteroid(10, 10);
        a2.setPx(20);
        a2.setPy(20);
        
        assertTrue(a1.collidesWith(a2)); 
    }
    
    @Test
    public void testCollidesWithEdge() {
        Asteroid a1 = new Asteroid(10, 10);
        a1.setPx(0);
        a1.setPy(0);
        Asteroid a2 = new Asteroid(10, 10);
        a2.setPx(20);
        a2.setPy(20);
        
        assertTrue(a1.collidesWith(a2)); 
    }
    
    @Test
    public void testCollidesWithFail() {
        Asteroid a1 = new Asteroid(10, 10);
        a1.setPx(0);
        a1.setPy(0);
        Asteroid a2 = new Asteroid(10, 10);
        a2.setPx(21);
        a2.setPy(21);
        
        assertFalse(a1.collidesWith(a2)); 
    }
 
    @Test
    public void testFinishedCourseTop() {
        Asteroid a = new Asteroid(10, 10);
        a.setPx(100);
        a.setPy(0);
        a.setVy(-1);
        
        assertTrue(a.finishedCourse());
    }
    
    @Test
    public void testFinishedCourseBottom() {
        Asteroid a = new Asteroid(10, 10);
        a.setPx(100);
        a.setPy(400);
        
        assertTrue(a.finishedCourse());
    }
    
    @Test
    public void testFinishedCourseSide() {
        Asteroid a = new Asteroid(10, 10);
        a.setPx(400);
        a.setPy(100);
        
        assertFalse(a.finishedCourse());
    }
    
    // Damageable Tests
    // take damage
    // isDead
    
    // SpaceShip Tests
    
    // add coins
    // isDead
    // takeDamage - never < 0 health
    // fire
    
    // CollisionProjectile Tests
    // hitShip
    
    // EnemyShip Tests
    // fire
    // take damage - health never < 0
    // isdead
    
    // Coin Tests
    // hitShip dynamic dispatch
    
    // Laser Tests
    // hitProjectile
    
    
    
    // Asteroid Tests
    // take Daamge
    // is dead
    
}