package edu.gonzaga.NewPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    private boolean introScreen = true;

    // Setup Later
    private boolean setUpScreen = false;
    private boolean gamePlayScreen = false;
    private boolean endScreen = false;

    private JButton startButton;
    private JButton startGameButton;
    private Timer timer;
    private Image image;

    // Setup fields
    private JTextField player1Field;
    private JTextField player2Field;
    private JComboBox<String> ballSpeedBox;
    private JComboBox<String> pointsLimitBox;

    // Settings to store
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";
    private int ballSpeed = 5;
    private int pointsToWin = 5;

    public GamePanel() {
        setLayout(null); 

        image = Toolkit.getDefaultToolkit().getImage("setUpImage.png");

        // Make Start Button
        startButton = new JButton("Start Game");
        startButton.setBounds(300, 400, 200, 50);
        startButton.addActionListener(this);
        add(startButton);

        // Make it 60 FPS
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (introScreen) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            if (image != null) {
                g.drawImage(image, 150, 50, 500, 300, this);
            } else {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.drawString("PONG", 320, 200);
            }
        } 
        else if (setUpScreen) {

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Setup Your Game", 250, 100);

            // Labels
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Ball Speed:", 250, 290);
            g.drawString("Points to Win:", 250, 370);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Switch from intro to setup
            introScreen = false;
            setUpScreen = true;
            remove(startButton);
            setUpSetupScreen();
            repaint();
        } 
        else if (e.getSource() == startGameButton) {
            
            if (player1Field.getText().isEmpty()) {
                player1Name = "Player 1";
            } else {
                player1Name = player1Field.getText();
            }
            
            if (player2Field.getText().isEmpty()) {
                player2Name = "Player 2";
            } else {
                player2Name = player2Field.getText();
            }
            
            // Save ball speed
            String selectedSpeed = (String) ballSpeedBox.getSelectedItem();
            switch (selectedSpeed) {
                case "5":
                    ballSpeed = 5;
                    break;
                case "10":
                    ballSpeed = 10;
                    break;
                case "15":
                    ballSpeed = 15;
                    break;
            }

            // Save points to win
            String selectedPoints = (String) pointsLimitBox.getSelectedItem();
            switch (selectedPoints) {
                case "5":
                    pointsToWin = 5;
                    break;
                case "10":
                    pointsToWin = 10;
                    break;
                case "15":
                    pointsToWin = 15;
                    break;
            }

            // Move to gameplay
            setUpScreen = false;
            gamePlayScreen = true;

            // Remove setup fields
            remove(player1Field);
            remove(player2Field);
            remove(ballSpeedBox);
            remove(pointsLimitBox);
            remove(startGameButton);

            repaint();
        }
    }

    private void setUpSetupScreen() {
        // Player 1 Stuff
        player1Field = new JTextField("Player 1");
        player1Field.setBounds(250, 150, 300, 30);
        add(player1Field);

        // Player 2 Stuff
        player2Field = new JTextField("Player 2");
        player2Field.setBounds(250, 200, 300, 30);
        add(player2Field);

        // Ball Speed 
        String[] speeds = {"5", "10", "15"};
        ballSpeedBox = new JComboBox<>(speeds);
        ballSpeedBox.setSelectedIndex(0); // Default 5
        ballSpeedBox.setBounds(250, 300, 300, 30);
        add(ballSpeedBox);

        // Points Limit 
        String[] points = {"5", "10", "15"};
        pointsLimitBox = new JComboBox<>(points);
        pointsLimitBox.setSelectedIndex(0); // Default 5
        pointsLimitBox.setBounds(250, 380, 300, 30);
        add(pointsLimitBox);

        // Create start game button
        startGameButton = new JButton("Start Playing!");
        startGameButton.setBounds(300, 450, 200, 50);
        startGameButton.addActionListener(this);
        add(startGameButton);

        revalidate();
        repaint();
    }
}
