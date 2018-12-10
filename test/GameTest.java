import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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
    
    public static Game game = new Game();
    public static SpaceGameCourt court = new SpaceGameCourt(new JLabel(), new JLabel(), 1, game);
    
    // SpaceShip Tests
    @Test
    public void testAddCoins() {
        Spaceship s = new Spaceship(court);
        s.addCoins(4);
        assertEquals(s.getCoins(), 4); 
    }
    
    @Test
    public void testSpaceshipIsDead() {
        Spaceship s = new Spaceship(court);
        assertFalse(s.isDead());
        s.takeDamage(200);
        assertTrue(s.isDead()); 
    }
    
    @Test
    public void testSpaceshipTakeDamage() {
        Spaceship s = new Spaceship(court);
        assertEquals(s.getHealth(), 200);
        s.takeDamage(100);
        assertEquals(s.getHealth(), 100); 
    }
    
    
    @Test
    public void testSpaceshipTakeDamageAlt() {
        Spaceship s = new Spaceship(court);
        assertEquals(s.getHealth(), 200);
        s.takeDamage(300);
        assertEquals(s.getHealth(), 0); 
    }
    
    // CollisionProjectile Tests
    @Test
    public void testCollisionProjHitShip() {
        Spaceship s = new Spaceship(court);
        assertEquals(s.getHealth(), 200);
        Asteroid a = new Asteroid(10, 10);
        a.hitShip(s);
        assertEquals(s.getHealth(), 190); 
    }
    
    // EnemyShip Tests
    @Test
    public void testEnemyTakeDamage() {
        EnemyShip s = new EnemyShip(court, 10, 100);
        assertEquals(s.getHealth(), 100);
        s.takeDamage(10);
        assertEquals(s.getHealth(), 90); 
    }
    
    @Test
    public void testEnemyTakeDamageAlt() {
        EnemyShip s = new EnemyShip(court, 10, 100);
        assertEquals(s.getHealth(), 100);
        s.takeDamage(100);
        assertEquals(s.getHealth(), 0); 
    }
    
    @Test
    public void testEnemyIsDead() {
        EnemyShip s = new EnemyShip(court, 10, 100);
        s.takeDamage(100);
        assertTrue(s.isDead()); 
    }
    
    // Coin Tests
    @Test
    public void testCoinHitShip() {
        Spaceship s = new Spaceship(court);
        assertEquals(s.getHealth(), 200);
        Coin c = new Coin(10);
        c.hitShip(s);
        assertEquals(s.getHealth(), 200);
        assertEquals(s.getCoins(), 10); 
    }
    
    // Laser Tests
    @Test
    public void testLaserHitProjectile() {
        Asteroid a = new Asteroid(10, 40);
        Laser l = new Laser(10, 10, 10, Color.RED);
        l.hitProjectile(a);
        assertEquals(a.getHealth(), 20);
    }
    
    // Asteroid Tests
    @Test
    public void testAsteroidTakeDamage() {
        Asteroid a = new Asteroid(10, 10);
        assertEquals(a.getHealth(), 10);
        a.takeDamage(5);
        assertEquals(a.getHealth(), 5);
    }
    
    @Test
    public void testAsteroidIsDead() {
        Asteroid a = new Asteroid(10, 10);
        assertEquals(a.getHealth(), 10);
        a.takeDamage(10);
        assertTrue(a.isDead());
    }    
}