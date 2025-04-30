package edu.gonzaga.flappybird;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FlappyBirdTests {

    @Test
    void testBirdGravity() {
        Bird bird = new Bird(null);
        int startY = bird.y;

        int velocityY = 0;
        int gravity = 1;

        velocityY += gravity;
        bird.y += velocityY;

        assertTrue(bird.y > startY, "Bird should fall down when gravity is acting on bird");
    }

    @Test
    void testPipeMovement() {
        Pipe pipe = new Pipe(null);
        int originalX = pipe.x;

        int velocityX = -4;
        pipe.x += velocityX;

        assertEquals(originalX - 4, pipe.x, "Pipe should have moved left by 4 units");
    }

    @Test
    void testScoreIncreasesWhenPipePassed() {
        Pipe pipe = new Pipe(null);
        pipe.x = 10;
        pipe.passed = false;

        Bird bird = new Bird(null);
        bird.x = pipe.x + Pipe.width + 1;

        //  Bird is going through pipe
        double score = 0;
        if (!pipe.passed && bird.x > pipe.x + Pipe.width) {
            score += 0.5;
            pipe.passed = true;
        }

        assertEquals(0.5, score, "Score should have increased by 0.5 after passing pipe");
        assertTrue(pipe.passed, "Pipe should be marked as passed");
    }
} 