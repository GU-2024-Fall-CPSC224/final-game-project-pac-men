package edu.gonzaga.NewPong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInput extends KeyAdapter {
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }

    public boolean isWPressed() { 
        return wPressed; 
    }
    
    public boolean isSPressed() { 
        return sPressed; 
    }

    public boolean isUpPressed() { 
        return upPressed; 
    }

    public boolean isDownPressed() { 
        return downPressed;
    }
} 