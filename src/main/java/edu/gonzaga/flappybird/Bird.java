package edu.gonzaga.flappybird;

import java.awt.*;

public class Bird {
    public int x = 0;
    public int y = 0;
    public static final int width = 34;
    public static final int height = 24;
    Image img;

    Bird(Image img) {
        this.img = img;
        x = FlappyBird.boardWidth/8;
        y = FlappyBird.boardHeight/2;
    }
}