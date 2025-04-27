package edu.gonzaga.pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class UI {
	public boolean showStartText = true;
	BufferedImage lives = null;
	BufferedImage image = null;
	BufferedImage button1,button2;
	public int selectedButton = 0;
	Game gm;
	Font ft;
	Font tscreenFt;
	Graphics2D g2;
	public UI (Game gm) {
		try {
			 ft = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Joystix.TTF")).deriveFont(30f).deriveFont(Font.BOLD);
			 tscreenFt = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("/fonts/CrackMan.TTF")).deriveFont(30f).deriveFont(Font.BOLD);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Joystix.TTF")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/CrackMan.TTF")));
		}
		catch(IOException | FontFormatException e) {
			System.out.print("FAILED: ");
			System.out.println(e.getMessage());
			System.out.println("Loading Basic Fonts");
			// Fallback fonts in case of failure
			ft = new Font("Arial", Font.BOLD, 30);
			tscreenFt = new Font("Arial", Font.BOLD, 30);
		}
		new Timer(500, e -> {
		    showStartText = !showStartText;
		}).start();
		this.gm = gm;
		getImages();
		// Create Hud Object
		
	}
	public void getImages() {
		try {
			button1 = ImageIO.read(getClass().getResourceAsStream("/objects/Button.png"));
			button2 = ImageIO.read(getClass().getResourceAsStream("/objects/Button(selected).png"));
			lives = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Up1.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(ft);
		g2.setColor(Color.white);
		switch (gm.gameState){
		case 1:
			drawStartScreen();
			break;
		case 2:
			drawPlayState();
			break;
		case 3:
			drawPauseScreen();
			break;
		case 4:
			drawEndScreen();
			break;
		case 5:
			drawDeathAnimation();
			break;}
}

	private void drawPauseScreen() {
		
		g2.setFont(ft.deriveFont(60f));
		int length = (int) g2.getFontMetrics().getStringBounds("Paused", g2).getWidth();
		int x = gm.boardWidth/2 - length/2;
		int y = gm.boardHeight/6;
		
		
		g2.setColor(new Color(50,50,50,125));
		g2.fillRect(32*6, 0, gm.boardWidth-32*12, gm.boardHeight);
		
		
		g2.setColor(Color.GRAY);
		g2.drawString("Paused", x+3, y+3);
		g2.setColor(Color.white);
		g2.drawString("Paused", x, y);
		g2.setFont(ft);
		length = (int) g2.getFontMetrics().getStringBounds("SCORE: " + gm.score, g2).getWidth();
		y = gm.boardHeight/4-32;
		x = gm.boardWidth/2 - length/2;
		g2.setColor(Color.GRAY);
		g2.drawString("SCORE: " + gm.score, x+2, y+2);
		g2.setColor(Color.white);
		g2.drawString("SCORE: " + gm.score, x,y);
		
		
		// Buttons
		image = button1;
		y+=48;
		x = gm.boardWidth/2 - length/2;
		String text = "";
		for(int i = 0; i<3;i++) {

		if(selectedButton ==i) {
			image = button2;
			g2.setColor(Color.GRAY);}
		else {image = button1;
		g2.setColor(Color.WHITE);}
		switch(i) {
		case 0:
			text = "Resume";
			break;
		case 1:
			text = "Settings";
			break;
		case 2:
			text = "Exit";
			break;
		}
		
		x = gm.boardWidth/2;
		
		g2.drawImage(image, x-gm.TILESIZE*10, y, gm.TILESIZE*20,gm.TILESIZE*5, null);
		g2.setFont(ft.deriveFont(60f));
		length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int fontx = x - length/2;
		g2.drawString(text, fontx,y+100);
		y+= 240;}
		
		
		
	}

	private void drawPlayState() {
		int x = 10;
		g2.drawString("SCORE: " + gm.score, x,50);
		for(int i =0; i<gm.player.currentLives;i++ ) {
			x+=32;
		g2.drawImage(lives, x,100,32,32,null);
		}
		
	}

	private void drawStartScreen() {
		
		

		g2.setFont(tscreenFt.deriveFont(120f));
		g2.setColor(Color.yellow);
		int length = (int) g2.getFontMetrics().getStringBounds("Pac-Man", g2).getWidth();
		int x = gm.boardWidth/2 - length/2;
		int y = gm.boardHeight/6;
		g2.drawString("Pac-Man", x, y);
		g2.setFont(tscreenFt.deriveFont(30f));
		length = (int) g2.getFontMetrics().getStringBounds("By Carson, Tommy, and Xavier", g2).getWidth();;
		y += 40;
		x = gm.boardWidth/2 - length/2;
		g2.drawString("By Carson, Tommy, and Xavier", x, y);
		
		if(showStartText) {
		
		g2.setFont(ft);
		length = (int) g2.getFontMetrics().getStringBounds("Press Enter to Start", g2).getWidth();;
		y = gm.boardHeight/2;
		x = gm.boardWidth/2 - length/2;
		g2.drawString("Press Enter to Start", x, y);
		
		g2.setFont(ft.deriveFont(20f).deriveFont(Font.PLAIN));
		g2.setColor(new Color( 66, 12, 9 ));
		length = (int) g2.getFontMetrics().getStringBounds("Press Space for Hard Mode", g2).getWidth();;
		y += 70;
		x = gm.boardWidth/2 - length/2;
		g2.drawString("Press Space for Hard Mode", x, y);}
		
		
	}

	private void drawEndScreen() {
		// TODO Auto-generated method stub
		
	}
//	private void drawSettingsScreen() {
//		// TODO Auto-generated method stub
//		
//	}
	private void drawDeathAnimation() {
		
	}
	
}
