package edu.gonzaga.pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MainMenuScreen extends JPanel {

    private GameSettings settings;
    
    public MainMenuScreen(GameSettings settings) {
        this.settings = settings;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        
        // Title
        JLabel titleLabel = new JLabel("PONG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);
        
        // Settings panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(4, 2, 10, 10));
        settingsPanel.setBackground(Color.BLACK);
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Player 1 name
        JTextField player1Field = new JTextField(settings.getPlayer1Name());
        // Player 2 name
        JTextField player2Field = new JTextField(settings.getPlayer2Name());
        
        // Player 1 name
        settingsPanel.add(makeLabel("Player 1 Name:"));
        settingsPanel.add(player1Field);
        // Player 2 name
        settingsPanel.add(makeLabel("Player 2 Name:"));
        settingsPanel.add(player2Field);
        
        // Ball speed
        String[] speeds = {"Slow", "Medium", "Fast", "Very Fast"};
        JComboBox<String> speedBox = new JComboBox<>(speeds);
        speedBox.setSelectedIndex(1); // Start with Medium
        settingsPanel.add(makeLabel("Ball Speed:"));
        settingsPanel.add(speedBox);
        
        // Score limit
        String[] limits = {"3 Points", "5 Points", "7 Points", "10 Points"};
        JComboBox<String> limitBox = new JComboBox<>(limits);
        limitBox.setSelectedIndex(1); // Start with 5 Points
        settingsPanel.add(makeLabel("Score Limit:"));
        settingsPanel.add(limitBox);
        
        add(settingsPanel, BorderLayout.CENTER);
        
        // Start button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.BLACK);
        
        startButton.addActionListener(e -> {
            // Save player names
            settings.setPlayer1Name(player1Field.getText());
            settings.setPlayer2Name(player2Field.getText());
            
            // Set ball speed
            int speed = 3;
            if (speedBox.getSelectedIndex() == 1) speed = 5;
            if (speedBox.getSelectedIndex() == 2) speed = 7;
            if (speedBox.getSelectedIndex() == 3) speed = 9;
            settings.setBallSpeed(speed);
            
            // Set score limit
            int limit = 3;
            if (limitBox.getSelectedIndex() == 1) limit = 5;
            if (limitBox.getSelectedIndex() == 2) limit = 7;
            if (limitBox.getSelectedIndex() == 3) limit = 10;
            settings.setScoreLimit(limit);
            
            // Game screen
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            ScreenManager.switchScreen(frame, new GamePanel(settings));
        });
        
        add(startButton, BorderLayout.SOUTH);
        
        // Make pretty
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    
    // Maybe get rid of later
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }
} 