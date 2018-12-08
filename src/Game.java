/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("Spaceship Attack");
        frame.setLocation(300, 300);
        
        // Status panel
        final JPanel statusPanel = new JPanel();
        frame.add(statusPanel, BorderLayout.NORTH);
        final JLabel score = new JLabel("Score: ");
        statusPanel.add(score);
        final JLabel health = new JLabel("Health: ");
        statusPanel.add(health);
        
        // Main playing area
        final SpaceGameCourt court = new SpaceGameCourt(health, score);
        //final GameCourt court = new GameCourt(health);
        frame.add(court, BorderLayout.CENTER);

        /*
        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);
        */

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}