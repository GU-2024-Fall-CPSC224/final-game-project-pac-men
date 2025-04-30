package edu.gonzaga.NewPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    private GameData gameData;
    private UILogic uiLogic;
    private GameController gameController;
    private UserInput userInput;
    private Timer timer;

    public GamePanel() {
        setLayout(null);
        
        // Initialize components
        gameData = new GameData();
        uiLogic = new UILogic(this);
        gameController = new GameController(this, gameData);
        userInput = new UserInput();
        
        // Setup UI
        uiLogic.setupStartButton(this);
        
        // Make it 60 FPS
        timer = new Timer(16, this);
        timer.start();

        // Keyboard control
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(userInput);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameData.isIntroScreen()) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            if (uiLogic.getImage() != null) {
                g.drawImage(uiLogic.getImage(), 150, 50, 500, 300, this);
            } else {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.drawString("PONG", 320, 200);
            }
        } 
        else if (gameData.isSetUpScreen()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Setup Your Game", 250, 100);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Ball Speed:", 250, 290);
            g.drawString("Points to Win:", 250, 370);
        }
        else if (gameData.isGamePlayScreen()) {
            setBackground(Color.BLACK);

            gameController.getLeftPaddle().draw(g);
            gameController.getRightPaddle().draw(g);
            gameController.getBall().draw(g);

            // Center dotted line
            g.setColor(Color.WHITE);
            for (int y = 0; y < getHeight(); y += 40) {
                g.fillRect(getWidth() / 2 - 5, y, 10, 20);
            }

            // Draw scores
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("" + gameData.getPlayer1Score(), getWidth() / 2 - 100, 50);
            g.drawString("" + gameData.getPlayer2Score(), getWidth() / 2 + 70, 50);
        }
        else if (gameData.isEndScreen()) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            String winner = gameData.getPlayer1Score() > gameData.getPlayer2Score() ? 
                          gameData.getPlayer1Name() : gameData.getPlayer2Name();
            g.drawString(winner + " Wins!", 220, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uiLogic.getStartButton()) {
            gameData.setIntroScreen(false);
            gameData.setSetUpScreen(true);
            uiLogic.removeStartButton();
            uiLogic.setupGameComponents();
            repaint();
        } 
        else if (e.getSource() == uiLogic.getStartGameButton()) {
            // Save settings
            gameData.setPlayer1Name(uiLogic.getPlayer1Name());
            gameData.setPlayer2Name(uiLogic.getPlayer2Name());
            gameData.setBallSpeed(uiLogic.getSelectedBallSpeed());
            gameData.setPointsToWin(uiLogic.getSelectedPointsLimit());

            // Initialize ball with selected speed
            gameController.setBallSpeed(gameData.getBallSpeed());

            gameData.setSetUpScreen(false);
            gameData.setGamePlayScreen(true);

            uiLogic.removeGameComponents();
            repaint();
        }

        if (gameData.isGamePlayScreen()) {
            gameController.updateGame(
                userInput.isWPressed(),
                userInput.isSPressed(),
                userInput.isUpPressed(),
                userInput.isDownPressed()
            );
            repaint();
        }
    }
}
