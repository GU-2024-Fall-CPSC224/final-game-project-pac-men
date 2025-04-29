package edu.gonzaga.NewPong;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
    public int x, y;
    public int width = 20;
    public int height = 100;
    public int speed = 10;

    public Paddle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
