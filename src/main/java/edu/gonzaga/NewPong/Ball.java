package edu.gonzaga.NewPong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public int x, y;
    public int width = 20;
    public int height = 20;
    public int speedX = 5;
    public int speedY = 5;

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
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
