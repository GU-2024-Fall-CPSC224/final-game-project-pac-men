package edu.gonzaga.NewPong;

public class GameController {
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private GamePanel panel;  // Reference to get panel dimensions
    private GameData gameState;

    public GameController(GamePanel panel, GameData gameState) {
        this.panel = panel;
        this.gameState = gameState;
        initializeGameObjects();
    }

    private void initializeGameObjects() {
        leftPaddle = new Paddle(50, 250);
        rightPaddle = new Paddle(700, 250);
        ball = new Ball(390, 290);
    }

    public void updateGame(boolean wPressed, boolean sPressed, boolean upPressed, boolean downPressed) {
        // Paddle movement 
        if (wPressed && leftPaddle.y > 0) {
            leftPaddle.moveUp();
        }
        if (sPressed && leftPaddle.y + leftPaddle.height < panel.getHeight()) {
            leftPaddle.moveDown();
        }
        if (upPressed && rightPaddle.y > 0) {
            rightPaddle.moveUp();
        }
        if (downPressed && rightPaddle.y + rightPaddle.height < panel.getHeight()) {
            rightPaddle.moveDown();
        }

        ball.move();

        // Bounce ball on walls
        if (ball.y <= 0 || ball.y + ball.height >= panel.getHeight()) {
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
            gameState.setPlayer2Score(gameState.getPlayer2Score() + 1);
            resetBall();
        }
        if (ball.x > panel.getWidth()) {
            gameState.setPlayer1Score(gameState.getPlayer1Score() + 1);
            resetBall();
        }

        // Check if someone wins
        if (gameState.getPlayer1Score() >= gameState.getPointsToWin() || 
            gameState.getPlayer2Score() >= gameState.getPointsToWin()) {
            gameState.setGamePlayScreen(false);
            gameState.setEndScreen(true);
        }
    }

    public void resetBall() {
        ball.x = panel.getWidth() / 2 - ball.width / 2;
        ball.y = panel.getHeight() / 2 - ball.height / 2;
        ball.speedX = gameState.getBallSpeed();
        ball.speedY = gameState.getBallSpeed();
        if (Math.random() < 0.5) {
            ball.speedX = -ball.speedX;
        }
        if (Math.random() < 0.5) {
            ball.speedY = -ball.speedY;
        }
    }

    public void setBallSpeed(int speed) {
        ball.speedX = speed;
        ball.speedY = speed;
    }

    public Ball getBall() { 
        return ball; 
    }

    public Paddle getLeftPaddle() { 
        return leftPaddle; 
    }

    public Paddle getRightPaddle() { 
        return rightPaddle; 
    }
} 