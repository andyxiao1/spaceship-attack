/**
 * HighScore
 *
 * This class represents a high score with a name, score, and quote
 */
public class HighScore {

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
}
