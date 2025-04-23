package edu.gonzaga.pong;

import javax.swing.JFrame;

public class PongGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Make game settings
        GameSettings settings = new GameSettings();
        
        // Make main menu
        MainMenuScreen mainMenu = new MainMenuScreen(settings);
        frame.add(mainMenu);
        
        // Make sure it is centerd
        frame.pack();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
} 