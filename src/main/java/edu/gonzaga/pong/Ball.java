package edu.gonzaga.pong;

import java.awt.Graphics;

public class Ball {
    private int x;
    private int y;
    private final int size = 20;
    private int speedX;
    private int speedY;
    
    public Ball(int x, int y, GameSettings settings) {
        reset(x, y, settings);
    }
    
    public void reset(int x, int y, GameSettings settings) {
        this.x = x;
        this.y = y;
        this.speedX = settings.getBallSpeed();
        this.speedY = settings.getBallSpeed();
    }
    
    public void move() {
        x += speedX;
        y += speedY;
    }
    
    public void reverseX() {
        speedX =- speedX;
    }
    
    public void reverseY() {
        speedY =- speedY;
    }
    
    public void draw(Graphics g) {
        g.fillOval(x, y, size, size);
    }
    
    public int getX() { 
        return x; 
    }
    public int getY() { 
        return y; 
    }
    public int getSize() { 
        return size; 
    }
} 