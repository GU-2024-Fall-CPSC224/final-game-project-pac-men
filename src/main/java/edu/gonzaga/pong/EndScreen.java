package edu.gonzaga.pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class EndScreen extends JPanel {

    private GameSettings settings;
    private int player1Score;
    private int player2Score;
    
    public EndScreen(GameSettings settings, int player1Score, int player2Score) {

        this.settings = settings;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        
        // Black background
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        
        // Game over text
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gameOverLabel.setForeground(Color.WHITE);
        add(gameOverLabel, BorderLayout.NORTH);
        
        // Scores panel
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new GridLayout(2, 1));
        scoresPanel.setBackground(Color.BLACK);
        
        // Figure out who won
        String winner;
        if (player1Score > player2Score) {
            winner = settings.getPlayer1Name();
        } else {
            winner = settings.getPlayer2Name();
        }
        
        // Winner text
        JLabel winnerLabel = new JLabel(winner + " wins!", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        winnerLabel.setForeground(Color.WHITE);
        scoresPanel.add(winnerLabel);
        
        // Scores text
        JLabel scoresLabel = new JLabel(
            settings.getPlayer1Name() + ": " + player1Score + "  |  " + 
            settings.getPlayer2Name() + ": " + player2Score, 
            SwingConstants.CENTER
        );
        scoresLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoresLabel.setForeground(Color.WHITE);
        scoresPanel.add(scoresLabel);
        
        add(scoresPanel, BorderLayout.CENTER);
        
        // Play again 
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 24));
        playAgainButton.setBackground(Color.WHITE);
        playAgainButton.setForeground(Color.BLACK);
        
        // go back to main menu
        playAgainButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            ScreenManager.switchScreen(frame, new MainMenuScreen(settings));
        });
        
        add(playAgainButton, BorderLayout.SOUTH);
        
        // let him cook
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    }
} 