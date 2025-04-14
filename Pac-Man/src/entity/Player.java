package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import pacManProject.*;

public class Player extends Entity {
	Game gm;
	UserInput userInput;
	String oldDirection;
	public Player(Game gm, UserInput userInput) {
		this.gm = gm;
		this.userInput = userInput;
		direction = "right";
		
		collisionArea = new Rectangle(0,0,30,30);
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
		setDefaultValues();
		getPlayerImage();
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostUp-1.png.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostDown-2.png.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostRight-1.png.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostRight-2.png.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostRight-3.png.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostLeft-1.png.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostLeft-2.png.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/PurpleGhostLeft-3.png.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setDefaultValues() {
		x = 32;
		y = 32;
		speed = 4;
	}
	public void update() {
		
		if(userInput.upPressed) {
			collisionOn = false;
			gm.cDet.checkUp(this);
			if(!collisionOn) {
				direction = "up";}
			else {direction = oldDirection;}
		}
			
		
		else if(userInput.downPressed) {
			collisionOn = false;
			gm.cDet.checkDown(this);
			if(!collisionOn) {
				direction = "down";}
			else {direction = oldDirection;}
		}
			
		
		else if(userInput.rightPressed) {
			collisionOn = false;
			gm.cDet.checkRight(this);
			if(!collisionOn) {
				direction = "right";}
			else {direction = oldDirection;}
		}
		else if(userInput.leftPressed) {
			collisionOn = false;
			gm.cDet.checkLeft(this);
			if(!collisionOn) {
				direction = "left";}
			else {direction = oldDirection;}
		
		}
		collisionOn = false;
		gm.cDet.checkTile(this);
		int objIndex =gm.cDet.checkObject(this,true);
		collectPellet(objIndex);
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":
				oldDirection =direction;
				
				y-=speed;
				break;
			
			case "down":
				oldDirection =direction;
				y+=speed;
				break;
			case "left":
				oldDirection =direction;
				x-=speed;
				break;
			case "right":
				oldDirection =direction;
				x+=speed;
				break;
		}}
			
			
			



		spriteCounter++;
		if(spriteCounter >10) {
			if(spriteNum == 1) {
				spriteNum = 2;
				
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			image = up1;
			
			break;
		
		
		case "down":
				image = down1;
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
				}
			if(spriteNum == 2) {
				image = left2;
				}
			if(spriteNum == 3) {
				image = left3;
				}
			
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
				}
			if(spriteNum == 2) {
				image = right2;
				}
			if(spriteNum == 3) {
				image = right3;
				}
			break;
		
		}
		g2.drawImage(image,x,y,gm.TILESIZE,gm.TILESIZE,null);
		
		
	}
	public void collectPellet(int index) {
		if(index!=999) {
			gm.obj[index]=null;
			gm.score+=10;
		}
		
		
	}
}
