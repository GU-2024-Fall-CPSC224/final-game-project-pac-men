package edu.gonzaga.NewPong;

import javax.swing.JFrame;

public class Pong {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Simple Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}

