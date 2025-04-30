package edu.gonzaga.NewPong;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PongTests {

    @Test
    void testBallMovement() {
        Ball ball = new Ball(100, 100);
        int oldX = ball.x;
        int oldY = ball.y;

        ball.move();

        // the balls x or y should have changed if you call move
        assertNotEquals(oldX, ball.x, "Ball X should change after move()");
        assertNotEquals(oldY, ball.y, "Ball Y should change after move()");
    }

    @Test
    void testBallWallBounce() {
        Ball ball = new Ball(100, 0); // Top of screen
        ball.speedY = -5;

        ball.bounceVertical();

        assertEquals(5, ball.speedY, "Ball Y speed should be reversed after bouncing off the top of the screen");
    }

    @Test
    void testPaddleMovement() {
        Paddle paddle = new Paddle(50, 100);
        int originalY = paddle.y;

        paddle.moveUp();
        assertTrue(paddle.y < originalY, "Paddle should move up");

        paddle.moveDown();
        paddle.moveDown();
        assertTrue(paddle.y > originalY, "Paddle should move down");
    }

    @Test
    void testPointScoring() {
        GameData data = new GameData();
        data.setPlayer1Score(0);
        data.setPlayer2Score(0);

        data.setPlayer2Score(data.getPlayer2Score() + 1);
        assertEquals(1, data.getPlayer2Score(), "Player 2 should have 1 point");
    }

    @Test
    void testWinCondition() {
        GameData data = new GameData();
        data.setPointsToWin(3);
        data.setPlayer1Score(3);

        boolean player1Won = data.getPlayer1Score() >= data.getPointsToWin();
        assertTrue(player1Won, "Player 1 should win when reaching pointsToWin");
    }
} 