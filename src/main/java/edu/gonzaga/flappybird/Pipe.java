package edu.gonzaga.flappybird;

import java.awt.*;

public class Pipe {
    public int x = 0;
    public int y = 0;
    public static final int width = 64;
    public static final int height = 512;
    public Image img;
    public boolean passed = false;

    Pipe(Image img) {
        this.img = img;
        x = FlappyBird.boardWidth;
    }
}
