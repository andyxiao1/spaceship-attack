import java.util.*;
import java.io.*;

/**
 * HighScoreKeeper
 * 
 * This class reads and writes from highscore text file
 * It loads the data in using a level reader and allows for new highscores to be added
 * The format for the highscores.txt
 * score || name || quote
 */
public class HighScoreKeeper {

    public static final String HIGHSCORE_FILE = "files/highscore.txt";
    
    private List<HighScore> highscores;
    
    public HighScoreKeeper() {
        
        // load in highscores
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(HIGHSCORE_FILE));
            String line = br.readLine();
            
            // reads in every line and creates a highscore obj to add to highscore list
            while (line != null) {
                String[] arr = line.split("\\|\\|");
                
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].trim();
                }
                
                highscores.add(new HighScore(Integer.parseInt(arr[0]), arr[1], arr[2]));
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
    }
        
        
        
 }
