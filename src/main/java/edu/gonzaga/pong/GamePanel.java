package edu.gonzaga.pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    // 700
    // 750
    // 780
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_MARGIN = 50;
    
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Timer timer;
    private GameSettings settings;
    
    // Score tracker
    private int player1Score = 0;
    private int player2Score = 0;
    
    // Track which keys are pressed
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    
    public GamePanel(GameSettings settings) {
        this.settings = settings;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        
        ball = new Ball(WIDTH/2, HEIGHT/2, settings);
        leftPaddle = new Paddle(PADDLE_MARGIN, HEIGHT/2 - 50, settings);
        rightPaddle = new Paddle(WIDTH - PADDLE_MARGIN - 20, HEIGHT/2 - 50, settings);
    
        // Make sure keyboard works
        setFocusable(true);
        setRequestFocusEnabled(true);
        
        // Set up keyboard controls
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) wPressed = true;
                if (key == KeyEvent.VK_S) sPressed = true;
                if (key == KeyEvent.VK_UP) upPressed = true;
                if (key == KeyEvent.VK_DOWN) downPressed = true;
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) wPressed = false;
                if (key == KeyEvent.VK_S) sPressed = false;
                if (key == KeyEvent.VK_UP) upPressed = false;
                if (key == KeyEvent.VK_DOWN) downPressed = false;
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {

            }
            
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        
        // Game timer
        timer = new Timer(16, this);
        timer.start();
        
        // How tf does this make the whole game work
        Timer focusTimer = new Timer(100, e -> {
            requestFocusInWindow();
            ((Timer)e.getSource()).stop();
        });
        focusTimer.setRepeats(false);
        focusTimer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        
        // CENTER DASHED LINE
        for (int y = 0; y < HEIGHT; y += 30) {
            g.fillRect(WIDTH/2 - 5, y, 10, 20);
        }
        
        // Ball +Paddles
        ball.draw(g);
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        
        // Scores
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString(String.valueOf(player1Score), WIDTH/4, 50);
        g.drawString(String.valueOf(player2Score), 3*WIDTH/4, 50);
        
        // Player Names
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(settings.getPlayer1Name(), WIDTH/4 - 50, 80);
        g.drawString(settings.getPlayer2Name(), 3*WIDTH/4 - 50, 80);
    }
    
    // Let him cook
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move paddles if w s up down pressed
        if (wPressed) leftPaddle.move(-1, HEIGHT);
        if (sPressed) leftPaddle.move(1, HEIGHT);
        if (upPressed) rightPaddle.move(-1, HEIGHT);
        if (downPressed) rightPaddle.move(1, HEIGHT);
        
        // Move ball and check for hit
        ball.move();
        checkCollisions();
        repaint();
    }
    
    // Check if ball hits stuff
    private void checkCollisions() {
        // Hit top or bottom
        if (ball.getY() <= 0 || ball.getY() >= HEIGHT - ball.getSize()) {
            ball.reverseY();
        }
        
        // Hit paddles
        if (checkPaddleCollision(leftPaddle) || checkPaddleCollision(rightPaddle)) {
            ball.reverseX();
        }
        
        // Score points
        if (ball.getX() <= 0) {
            player2Score++;
            resetBall();
        } else if (ball.getX() >= WIDTH) {
            player1Score++;
            resetBall();
        }
        
        // Check if game over
        if (player1Score >= settings.getScoreLimit() || player2Score >= settings.getScoreLimit()) {
            endGame();
        }
    }
    
    // Check if ball hits paddle
    private boolean checkPaddleCollision(Paddle paddle) {
        return ball.getX() <= paddle.getX() + paddle.getWidth() &&
               ball.getX() >= paddle.getX() &&
               ball.getY() + ball.getSize() >= paddle.getY() &&
               ball.getY() <= paddle.getY() + paddle.getHeight();
    }
    
    // Game over
    private void endGame() {
        timer.stop();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        ScreenManager.switchScreen(frame, new EndScreen(settings, player1Score, player2Score));
    }
    
    // Reset ball
    private void resetBall() {
        ball = new Ball(WIDTH/2, HEIGHT/2, settings);
    }
} 