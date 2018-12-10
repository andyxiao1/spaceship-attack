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

    public static final String HIGHSCORE_FILE = "files/highscores.txt";
    
    private List<HighScore> highScores;
    
    public HighScoreKeeper() {
        loadScores();
        writeScores();
    }
    
    /**
     * loads scores from highscore textfile into highscore list
     */
    private void loadScores() {
        BufferedReader br = null;
        highScores = new LinkedList<HighScore>();
        
        try {
            br = new BufferedReader(new FileReader(HIGHSCORE_FILE));
            String line = br.readLine();
            
            // reads in every line and creates a highscore obj to add to highscore list
            while (line != null) {
                String[] arr = line.split("\\|\\|");
                
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].trim();
                }
                
                highScores.add(new HighScore(Integer.parseInt(arr[0]), arr[1], arr[2]));
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
        Collections.sort(highScores, HighScore.HighScoreComparator);
    }
    
    /**
     * writes scores to highscore textfile in order from highscore list
     */
    private void writeScores() {
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(HIGHSCORE_FILE);
            for (HighScore nextScore : highScores) {
                pr.println(nextScore.getScore() + " || " + nextScore.getName() + " || " + nextScore.getQuote());
            }
            pr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * adds score to highScores, call isHighScore first
     * @param score
     */
    public void addScore(HighScore score) {
        highScores.add(score);
        Collections.sort(highScores, HighScore.HighScoreComparator);
        if (highScores.size() > 3) {
            highScores.remove(highScores.size() - 1);
        }
        writeScores();
    }
    
    /**
     * checks if score is a highscore
     * @param score
     * @return true if highscore false if not
     */
    public boolean isHighScore(int score) {
        return highScores.isEmpty() || score > highScores.get(highScores.size() - 1).getScore();
    }
    
    /**
     * returns sorted list of scores
     * @return
     */
    public List<HighScore> getScores() {
        return highScores;
    }
 }
