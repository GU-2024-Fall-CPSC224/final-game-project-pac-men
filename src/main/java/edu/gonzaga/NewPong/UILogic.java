package edu.gonzaga.NewPong;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class UILogic {
    private JButton startButton;
    private JButton startGameButton;
    private Image image;
    
    private JTextField player1Field;
    private JTextField player2Field;
    private JComboBox<String> ballSpeedBox;
    private JComboBox<String> pointsLimitBox;
    
    private GamePanel panel;  // Reference to main panel for adding/removing components

    public UILogic(GamePanel panel) {
        this.panel = panel;
        image = Toolkit.getDefaultToolkit().getImage("setUpImage.png");
    }

    public void setupStartButton(ActionListener listener) {
        startButton = new JButton("Start Game");
        startButton.setBounds(300, 400, 200, 50);
        startButton.addActionListener(listener);
        panel.add(startButton);
    }

    public void removeStartButton() {
        panel.remove(startButton);
    }

    public void setupGameComponents() {
        // Player 1 Stuff
        player1Field = new JTextField("Player 1");
        player1Field.setBounds(250, 150, 300, 30);
        panel.add(player1Field);

        // Player 2 Stuff
        player2Field = new JTextField("Player 2");
        player2Field.setBounds(250, 200, 300, 30);
        panel.add(player2Field);

        // Ball Speed 
        String[] speeds = {"5", "10", "15"};
        ballSpeedBox = new JComboBox<>(speeds);
        ballSpeedBox.setSelectedIndex(0);
        ballSpeedBox.setBounds(250, 300, 300, 30);
        ballSpeedBox.setBackground(java.awt.Color.WHITE);
        ballSpeedBox.setForeground(java.awt.Color.BLACK);
        panel.add(ballSpeedBox);

        // Points Limit 
        String[] points = {"5", "10", "15"};
        pointsLimitBox = new JComboBox<>(points);
        pointsLimitBox.setSelectedIndex(0);
        pointsLimitBox.setBounds(250, 380, 300, 30);
        pointsLimitBox.setBackground(java.awt.Color.WHITE);
        pointsLimitBox.setForeground(java.awt.Color.BLACK);
        panel.add(pointsLimitBox);

        // Create start game button
        startGameButton = new JButton("Start Playing!");
        startGameButton.setBounds(300, 450, 200, 50);
        startGameButton.addActionListener(panel);
        panel.add(startGameButton);

        panel.revalidate();
        panel.repaint();
    }

    public void removeGameComponents() {
        panel.remove(player1Field);
        panel.remove(player2Field);
        panel.remove(ballSpeedBox);
        panel.remove(pointsLimitBox);
        panel.remove(startGameButton);
    }

    public String getPlayer1Name() {
        if (player1Field.getText().isEmpty()) {
            return "Player 1";
        } else {
            return player1Field.getText();
        }
    }

    public String getPlayer2Name() {
        if (player2Field.getText().isEmpty()) {
            return "Player 2";
        } else {
            return player2Field.getText();
        }
    }

    public int getSelectedBallSpeed() {
        String selectedSpeed = (String) ballSpeedBox.getSelectedItem();
        return Integer.parseInt(selectedSpeed);
    }

    public int getSelectedPointsLimit() {
        String selectedPoints = (String) pointsLimitBox.getSelectedItem();
        return Integer.parseInt(selectedPoints);
    }

    public JButton getStartButton() { 
        return startButton;
    }

    public JButton getStartGameButton() { 
        return startGameButton; 
    }

    public Image getImage() { 
        return image; 
    }
} 