import java.util.*;

/**
 * HighScore
 *
 * This class represents a high score with a name, score, and quote
 */
public class HighScore implements Comparable<HighScore>{

    private int score;
    private String name;
    private String quote;
    
    public HighScore(int score, String name, String quote) {
        this.score = score;
        this.name = name;
        this.quote = quote;
    }

    /* Getters */
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getQuote() {
        return quote;
    }
    
    @Override
    public int compareTo(HighScore that) {
        return that.score - this.score;
    }
    
    @Override
    public String toString() {
        return "Score: " + score + " | User: " + name + " | Quote: " + quote;
    }
    
    // allows sorting of highscores for display
    public static Comparator<HighScore> HighScoreComparator = new Comparator<HighScore>() {
        public int compare(HighScore first, HighScore second) {
            return first.compareTo(second);
        }
    };
}