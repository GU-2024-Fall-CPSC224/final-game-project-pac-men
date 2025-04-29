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

    // Game Objects
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;

    // Controls
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    // Scores
    private int player1Score = 0;
    private int player2Score = 0;

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

        // Game objects setup
        leftPaddle = new Paddle(50, 250);
        rightPaddle = new Paddle(700, 250);
        ball = new Ball(390, 290);

        // Keyboard control
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_W) {
                    wPressed = true;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
                    sPressed = true;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
                    upPressed = true;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
                    downPressed = true;
                }
            }

            public void keyReleased(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_W) {
                    wPressed = false;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_S) {
                    sPressed = false;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
                    upPressed = false;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
                    downPressed = false;
                }
            }
        });
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

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Ball Speed:", 250, 290);
            g.drawString("Points to Win:", 250, 370);
        }

        else if (gamePlayScreen) {
            setBackground(Color.BLACK);

            leftPaddle.draw(g);
            rightPaddle.draw(g);
            ball.draw(g);

            // Center dotted line
            g.setColor(Color.WHITE);
            for (int y = 0; y < getHeight(); y += 40) {
                g.fillRect(getWidth() / 2 - 5, y, 10, 20);
            }

            // Draw scores
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("" + player1Score, getWidth() / 2 - 100, 50);
            g.drawString("" + player2Score, getWidth() / 2 + 70, 50);
        }
        else if (endScreen) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            String winner = player1Score > player2Score ? player1Name : player2Name;
            g.drawString(winner + " Wins!", 220, 300);
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
            // Save settings
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

            ball.speedX = ballSpeed;
            ball.speedY = ballSpeed;

            setUpScreen = false;
            gamePlayScreen = true;

            remove(player1Field);
            remove(player2Field);
            remove(ballSpeedBox);
            remove(pointsLimitBox);
            remove(startGameButton);

            repaint();
        }

        if (gamePlayScreen) {
            // Paddle movement 
            if (wPressed && leftPaddle.y > 0) {
                leftPaddle.moveUp();
            }
            if (sPressed && leftPaddle.y + leftPaddle.height < getHeight()) {
                leftPaddle.moveDown();
            }
            if (upPressed && rightPaddle.y > 0) {
                rightPaddle.moveUp();
            }
            if (downPressed && rightPaddle.y + rightPaddle.height < getHeight()) {
                rightPaddle.moveDown();
            }

            ball.move();

            // Bounce ball on walls
            if (ball.y <= 0 || ball.y + ball.height >= getHeight()) {
                ball.bounceVertical();
            }

            // Bounce ball off paddles
            if (ball.x <= leftPaddle.x + leftPaddle.width &&
                ball.y + ball.height >= leftPaddle.y &&
                ball.y <= leftPaddle.y + leftPaddle.height) {
                ball.bounceHorizontal();
            }

            if (ball.x + ball.width >= rightPaddle.x &&
                ball.y + ball.height >= rightPaddle.y &&
                ball.y <= rightPaddle.y + rightPaddle.height) {
                ball.bounceHorizontal();
            }

            // Check if ball goes off screen
            if (ball.x < 0) {
                player2Score++;
                resetBall();
            }
            if (ball.x > getWidth()) {
                player1Score++;
                resetBall();
            }

            // Check if someone wins
            if (player1Score >= pointsToWin || player2Score >= pointsToWin) {
                gamePlayScreen = false;
                endScreen = true;
            }

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
        ballSpeedBox.setSelectedIndex(0);
        ballSpeedBox.setBounds(250, 300, 300, 30);
        add(ballSpeedBox);

        // Points Limit 
        String[] points = {"5", "10", "15"};
        pointsLimitBox = new JComboBox<>(points);
        pointsLimitBox.setSelectedIndex(0);
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

    private void resetBall() {
        ball.x = getWidth() / 2 - ball.width / 2;
        ball.y = getHeight() / 2 - ball.height / 2;
        ball.speedX = ballSpeed * (Math.random() < 0.5 ? 1 : -1);
        ball.speedY = ballSpeed * (Math.random() < 0.5 ? 1 : -1);
    }
}
