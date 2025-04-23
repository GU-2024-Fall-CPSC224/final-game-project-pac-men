package edu.gonzaga.pong;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenManager {
    public static void switchScreen(JFrame frame, JPanel newPanel) {
        
        // Remove old screen
        frame.getContentPane().removeAll();
        // Add new screen
        frame.add(newPanel);
        
        frame.pack();
        frame.setVisible(true);
    }
} 