package edu.gonzaga.pong;

import java.awt.Graphics;

public class Paddle {

    // Where paddle is
    private int x; 
    private int y;

    private final int width = 20;
    private final int height = 100;
    private final int speed;
    
    public Paddle(int x, int y, GameSettings settings) {
        this.x = x;
        this.y = y;
        this.speed = settings.getBallSpeed() * 3;
    }
    
    // Move paddle up or down
    public void move(int direction, int gameHeight) {
        // Check if paddle is at top
        if (direction < 0 && y > 0) {
            y -= speed;
        } 
        // Check if paddle is at bottom
        else if (direction > 0 && y < gameHeight - height) {
            y += speed;
        }
    }
    
    // Draw paddle
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
    
    // GETTERS

    public int getX() { 
        return x; 
    }
    
    public int getY() { 
        return y; 
    }
    
    public int getWidth() { 
        return width; 
    }
    
    public int getHeight() { 
        return height; 
    }
} 