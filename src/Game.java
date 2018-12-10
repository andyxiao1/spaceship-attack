import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    
    public static final int NUM_LEVELS = 3;
    
    private JPanel container;
    private JFrame frame;
    private BufferedImage spaceship;
    private List<String> instructions;
    private HighScoreKeeper scoreKeeper;
    
    public void run() {

        // setup frame
        frame = new JFrame("Spaceship Attack");
        frame.setLocation(300, 300);
        
        container = new JPanel();
        frame.add(container);
        
        // default to menu and user can move to other screens from menu's buttons
        loadData();
        showMenu();

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Preloads spaceship picture, instructions, and highscores
     */
    private void loadData() {
        
        // loads spaceship picture
        spaceship = null;
        try {
            spaceship = ImageIO.read(new File("files/spaceship.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        // loads instructions
        instructions = new ArrayList<String>();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader("files/instructions.txt"));
            String line = br.readLine();
            
            while (line != null) {
                instructions.add(line);
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
        
        // loads highscores
        scoreKeeper = new HighScoreKeeper();
    }
    
    /**
     * Redraws frame and container
     */
    private void redraw() {
        container.revalidate();
        container.repaint();
        frame.pack();
    }
    
    /**
     * Displays the menu
     */
    public void showMenu() {
        
        // reset container
        container.removeAll();
        container.setLayout(new BorderLayout());
        
        // display header with title and picture
        JPanel header = new JPanel();
        container.add(header, BorderLayout.NORTH);
        
        JLabel titleLabel = new JLabel("Spaceship Attack");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.darkGray);
        header.add(titleLabel);
        
        JLabel picLabel = new JLabel(new ImageIcon(spaceship));
        header.add(picLabel);
        
        // display menu options
        JPanel menu = new JPanel();
        container.add(menu, BorderLayout.CENTER);
                
        JButton levelSelect = new JButton("Select Level");
        levelSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelSelect();
            }
        });
        menu.add(levelSelect);

        JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });
        menu.add(instructions);
        
        JButton highScores = new JButton("High Scores");
        highScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showHighScores();
            }
        });
        menu.add(highScores);
        
        redraw();
    }
    
    /**
     * Displays the levels available
     */
    private void levelSelect() {
        
        // reset container
        container.removeAll();
        container.setLayout(new BorderLayout());
        
        // display header with title and menu button
        JPanel header = new JPanel();
        container.add(header, BorderLayout.NORTH);
        
        JLabel titleLabel = new JLabel("Select A Level");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.darkGray);
        header.add(titleLabel);
        
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        header.add(menuButton);
        
        // display levels
        JPanel levels = new JPanel();
        levels.setLayout(new GridLayout(1, NUM_LEVELS));
        container.add(levels);
        
        for (int i = 0; i < NUM_LEVELS; i++) {
            JPanel levelPanel = new JPanel();
            levels.add(levelPanel);
            
            JButton levelButton = new JButton("Level " + (i + 1));
            final int levelNum = i + 1;
            levelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showGame(levelNum);
                }
            });
            levelPanel.add(levelButton);
        }
        
        redraw();
    }
    
    /**
     * Creates game depending on the level
     * @param level
     */
    private void showGame(int level) {
        
        // reset container
        container.removeAll();
        container.setLayout(new BorderLayout());

        // display status
        JPanel statusPanel = new JPanel();
        container.add(statusPanel, BorderLayout.NORTH);
        
        final JLabel coins = new JLabel("Coins: ");
        statusPanel.add(coins);
        final JLabel health = new JLabel("Health: ");
        statusPanel.add(health);
        JLabel levelLabel = new JLabel("Level: " + level);
        statusPanel.add(levelLabel);
        
        // display main playing area
        SpaceGameCourt court = new SpaceGameCourt(health, coins, level, this);
        container.add(court, BorderLayout.CENTER);
        
        // Start game
        court.startGame();
        redraw();
    }
    
    /**
     * Displays instructions for the game
     */
    private void showInstructions() {
        
        // reset container
        container.removeAll();
        container.setLayout(new BorderLayout());
        
        // display title
        JPanel header = new JPanel();
        container.add(header, BorderLayout.NORTH);
        
        JLabel titleLabel = new JLabel("Instructions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.darkGray);
        header.add(titleLabel);
        
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        header.add(menuButton);
        
        // display instructions
        JPanel instructionsPanel = new JPanel();
        container.add(instructionsPanel, BorderLayout.CENTER);
        instructionsPanel.setLayout(new GridLayout(0, 1));
        
        for (String nextString : instructions) {
            JLabel instructionsLabel = new JLabel(nextString);
            instructionsPanel.add(instructionsLabel);
            JLabel blank = new JLabel("");
            instructionsPanel.add(blank);
        }
        
        redraw();
    }
    
    /**
     * Displays highscores for the game
     */
    private void showHighScores() {
        
        // reset container
        container.removeAll();
        container.setLayout(new BorderLayout());
        
        // display title
        JPanel header = new JPanel();
        container.add(header, BorderLayout.NORTH);
        
        JLabel titleLabel = new JLabel("HighScores (Level 3)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.darkGray);
        header.add(titleLabel);
        
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });
        header.add(menuButton);
        
        // display highscores
        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new GridLayout(0, 1));
        container.add(highScorePanel, BorderLayout.CENTER);
        
        List<HighScore> scores = scoreKeeper.getScores();
        
        for (int i = 0; i < scores.size(); i++) {
            JLabel scoreLabel = new JLabel((i + 1) + ": " + scores.get(i));
            highScorePanel.add(scoreLabel);
        }
        
        redraw();
    }
    
    /**
     * getter for scoreKeeper
     */
    public HighScoreKeeper getScoreKeeper() {
        return scoreKeeper;
    }
    
    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}