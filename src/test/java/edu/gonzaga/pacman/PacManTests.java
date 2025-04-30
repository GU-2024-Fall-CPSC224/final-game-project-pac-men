package edu.gonzaga.pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.gonzaga.pacman.entity.Player;
import edu.gonzaga.pacman.objects.SuperObject;

public class PacManTests {
    private Game game;
    private UserInput userInput;
    private Player player;

    // Dont touch otherwise everything breaks 
    @BeforeEach
    void setUp() {
        game = new Game();
        game.setupGame();
        game.startGame();
        userInput = new UserInput(game);
        player = new Player(game, userInput);
        
        // Make a ghost for testing
        game.ghosts[0] = new edu.gonzaga.pacman.ghosts.OrangeGhost(game);
        
        // Make a dot for testing
        SuperObject dot = new SuperObject();
        dot.name = "Dot";
        game.obj[0] = dot;
    }
    // Dont touch otherwise everything breaks

    @Test
    void testPlayerStartingPosition() {
        // Starting positions from setDefaultValues
        assertEquals(32*20-16, player.x, "Player should start at correct X position");
        assertEquals(32*23, player.y, "Player should start at correct Y position");
    }

    @Test
    void testPlayerMovement() {
        // Known position 
        player.x = 100;
        player.y = 100;
        
        // Test movement 
        player.direction = "up";
        int startY = player.y;
        player.update();
        assertTrue(player.y < startY, "Player should move up");
        
        player.direction = "right";
        int startX = player.x;
        player.update();
        assertTrue(player.x > startX, "Player should move right");
    }

    @Test
    void testDotCollection() {
        int initialScore = game.score;

        // Eating a dot
        SuperObject dot = new SuperObject();
        dot.name = "Pellet";
        game.obj[0] = dot;
        player.collectPellet(0); 
        assertTrue(game.score > initialScore, "Score should increase when collecting a dot");
    }

    @Test
    void testGhostCollision() {
        int initialLives = player.currentLives;

        // hit ghost 
        player.contactGhost(0); 
        assertEquals(initialLives - 1, player.currentLives, "Player should lose a life when you hit a ghost");
    }

    @Test
    void testPowerUp() {
        int initialScore = game.score;

        // Create power up
        SuperObject powerPellet = new SuperObject();
        powerPellet.name = "Power Pellet";
        game.obj[0] = powerPellet;
        
        // Collect the power up first 
        player.collectPellet(0);
        assertTrue(player.invincible, "Player should be invincible after collecting power pellet");
        
        // ghost touching 
        player.contactGhost(0); 
        
        assertTrue(game.score > initialScore, "Score should increase when eating a ghost with power up");
    }
} 