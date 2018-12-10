import java.util.*;
import java.io.*;

/**
 * LevelReader
 * 
 * This class loads in a level from a text file
 * The format of the text file goes
 * type || health || dealtDamage <-- for coins there is no dealt damage
 */
public class LevelReader {

    public static final String[] LEVEL_NAMES = {"files/levels/level-one.txt", "files/levels/level-two.txt",
    "files/levels/level-three.txt"};
    
    /**
     * returns a level from a court and level number
     * @param level
     * @param court
     * @return
     * @throws IOException
     */
    public static Queue<CollisionProjectile> getLevel(int level, SpaceGameCourt court) {

        BufferedReader br = null;
        Queue<CollisionProjectile> projectiles = new LinkedList<CollisionProjectile>();
        
        try {
            br = new BufferedReader(new FileReader(LEVEL_NAMES[level - 1]));
            String line = br.readLine();
            
            // reads in every line and creates a projectile obj and adds it to the projectiles Queue
            while (line != null) {
                String[] arr = line.split("\\|\\|");
                
                if (arr.length < 2 || arr.length > 3) {
                    throw new IOException("Broken Level");
                }
                
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].trim();
                }
                
                CollisionProjType proj = CollisionProjType.valueOf(arr[0]);
                
                if (arr.length == 2 && proj == CollisionProjType.COIN) {
                    projectiles.add(new Coin(Integer.parseInt(arr[1])));
                } else if (arr.length == 3) {
                    int health = Integer.parseInt(arr[1]);
                    int dmg = Integer.parseInt(arr[2]);
                    
                    if (proj == CollisionProjType.ASTEROID) {
                        projectiles.add(new Asteroid(dmg, health));
                    } else if (proj == CollisionProjType.ENEMY) {
                        projectiles.add(new EnemyShip(court, dmg, health));
                    } else {
                        throw new IOException("Level Broken");
                    }
                } else {
                    throw new IOException("Level Broken");
                }
                
                line = br.readLine();
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return projectiles;
    }
}
