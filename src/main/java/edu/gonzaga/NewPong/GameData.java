package edu.gonzaga.NewPong;

public class GameData {
    private boolean introScreen = true;
    private boolean setUpScreen = false;
    private boolean gamePlayScreen = false;
    private boolean endScreen = false;
    
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";
    private int ballSpeed = 5;
    private int pointsToWin = 5;
    
    private int player1Score = 0;
    private int player2Score = 0;

    public boolean isIntroScreen() { 
        return introScreen; 
    }
    
    public boolean isSetUpScreen() { 
        return setUpScreen; 
    }
    
    public boolean isGamePlayScreen() { 
        return gamePlayScreen; 
    }
    
    public boolean isEndScreen() { 
        return endScreen; 
    }
    
    public void setIntroScreen(boolean value) { 
        introScreen = value; 
    }
    
    public void setSetUpScreen(boolean value) { 
        setUpScreen = value; 
    }
    
    public void setGamePlayScreen(boolean value) { 
        gamePlayScreen = value; 
    }
    
    public void setEndScreen(boolean value) { 
        endScreen = value; 
    }
    
    public String getPlayer1Name() { 
        return player1Name; 
    }
    
    public String getPlayer2Name() { 
        return player2Name; 
    }
    
    public void setPlayer1Name(String name) { 
        player1Name = name; 
    }
    
    public void setPlayer2Name(String name) { 
        player2Name = name; 
    }
    
    public int getBallSpeed() { 
        return ballSpeed; 
    }
    
    public void setBallSpeed(int speed) { 
        ballSpeed = speed; 
    }
    
    public int getPointsToWin() { 
        return pointsToWin; 
    }
    
    public void setPointsToWin(int points) { 
        pointsToWin = points; 
    }
    
    public int getPlayer1Score() { 
        return player1Score; 
    }
    
    public int getPlayer2Score() { 
        return player2Score; 
    }
    
    public void setPlayer1Score(int score) { 
        player1Score = score; 
    }
    
    public void setPlayer2Score(int score) { 
        player2Score = score; 
    }
} 