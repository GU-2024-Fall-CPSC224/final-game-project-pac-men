package edu.gonzaga.NewPong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public int x, y;
    public int width = 20;
    public int height = 20;
    public int speedX = 5;
    public int speedY = 5;
    private int hitCount = 0;  // Tracks # of hits

    public Ball(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public void bounceVertical() {
        speedY = -speedY;
    }

    public void bounceHorizontal() {
        speedX = -speedX;
        hitCount++;
        
        // Increase ball speed every 2 hits (makes it slower than having the increase just be 1)
        if (hitCount % 2 == 0) {
            if (speedX > 0) {
                speedX += 1;
            } else {
                speedX -= 1;
            }
            if (speedY > 0) {
                speedY += 1;
            } else {
                speedY -= 1;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
