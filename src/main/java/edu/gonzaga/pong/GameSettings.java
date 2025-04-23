package edu.gonzaga.pong;

import java.awt.Color;

public class GameSettings {
    private String player1Name;
    private String player2Name;
    private int scoreLimit;
    private int ballSpeed;
    private Color backgroundColor;
    
    public GameSettings() {
        this.player1Name = "Player 1";
        this.player2Name = "Player 2";
        this.scoreLimit = 5;
        this.ballSpeed = 5;
        this.backgroundColor = Color.BLACK;
    }

    // GETTERS
    
    public String getPlayer1Name() { 
        return player1Name; 
    }
    
    public String getPlayer2Name() { 
        return player2Name; 
    }
    
    public int getScoreLimit() { 
        return scoreLimit; 
    }
    
    public int getBallSpeed() { 
        return ballSpeed; 
    }
    
    public Color getBackgroundColor() { 
        return backgroundColor; 
    }
    

    // SETTERS


    public void setPlayer1Name(String name) { 
        this.player1Name = name; 
    }
    
    public void setPlayer2Name(String name) { 
        this.player2Name = name; 
    }
    
    public void setScoreLimit(int limit) { 
        this.scoreLimit = limit; 
    }
    
    public void setBallSpeed(int speed) { 
        this.ballSpeed = speed; 
    }
    
    public void setBackgroundColor(Color color) { 
        this.backgroundColor = color; 
    }
} 