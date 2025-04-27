package edu.gonzaga.pacman.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.gonzaga.pacman.ghosts.DeadGhost;
import edu.gonzaga.pacman.*;

public class Player extends Entity {
	BufferedImage right4,left4,down2,down3,down4,up2,up3,up4,death1,death2,death3,death4,death5,death6,death7,death8,death9,death10,death11,death12,death13;
	UserInput userInput;
	String oldDirection;
	public int ghostScore = 200;
	public int currentLives;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public boolean deathAnimation = false;
	public Player(Game gm, UserInput userInput) {
		super(gm);
		this.userInput = userInput;
		direction = "up";
		
		collisionArea = new Rectangle(0,0,30,30);
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
		setDefaultValues();
		getPlayerImage();
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Up1.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Up2.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Up1.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Right1.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Right2.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Right1.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Left1.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Left2.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Left1.png"));
			
			death1 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-1.png"));
			death2 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Up1.png"));
			death3 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death3.png"));
			death4 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death4.png"));
			death5 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death5.png"));
			death6 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death6.png"));
			death7 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death7.png"));
			death8 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death8.png"));
			death9 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death9.png"));
			death10 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death10.png"));
			death11 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death11.png"));
			death12 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death12.png"));
			death13 =ImageIO.read(getClass().getResourceAsStream("/player/Sprite-PacMan/PacMan-Death13.png"));
			
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setDefaultValues() {
		x = 32*20-16;
		y = 32*23;
		speed = 4;
		currentLives = 3;
		StartingX = 32*20-16;
		StartingY = 32*23;
	}
//	public void deathAnimation() {
//		spriteCounter++;
//		
//			if(spriteNum == 1) {
//				spriteNum = 2;
//				
//			}
//			else if(spriteNum == 2) {
//				spriteNum = 3;
//			}
//			else if(spriteNum == 3) {
//				spriteNum = 4;
//			}
//			else if(spriteNum == 4) {
//				spriteNum = 5;
//			}
//			
//			else if(spriteNum == 5) {
//				spriteNum = 6;
//				
//			}
//			else if(spriteNum == 6) {
//				spriteNum = 7;
//			}
//			else if(spriteNum == 7) {
//				spriteNum = 8;
//			}
//			else if(spriteNum == 8) {
//				spriteNum = 9;
//			}
//			if(spriteNum == 9) {
//				spriteNum = 10;
//				
//			}
//			else if(spriteNum == 10) {
//				spriteNum = 11;
//			}
//			else if(spriteNum == 11) {
//				spriteNum = 12;
//			}
//			else if(spriteNum == 12) {
//				spriteNum = 13;
//			}
//			else if(spriteNum == 13) {
//				spriteNum = 14;
//			}
//			else if(spriteNum == 14) {
//				spriteNum = 15;
//			}
//			else if(spriteNum == 15) {
//				spriteNum = 16;
//			}
//			else if(spriteNum == 16) {
//				spriteNum = 17;
//			}
//			else if(spriteNum == 17) {
//				deathAnimation = false;
//				spriteNum = 1;
//				for(int i =0; i<gm.ghosts.length;i++) {
//					if(gm.ghosts[i]!=null) {
//						gm.ghosts[i].speed=2;
//					}}
//			}
//		spriteCounter= 0;
//		
//		
//		
//	}
	public void animationUpdate() {
		spriteCounter++;
		if(spriteCounter >10) {
			if(spriteNum == 1) {
				spriteNum = 2;
				
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 4;
			}
			else if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
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
		
		// Check for wall
		collisionOn = false;
		gm.cDet.checkTile(this);
		
		// Check For Pellet
		int objIndex =gm.cDet.checkObject(this,true);
		collectPellet(objIndex);
		int ghostIndex = gm.cDet.checkGhost(this,gm.ghosts);
		contactGhost(ghostIndex);
		// Check For Teleportation
		gm.eHand.checkEvent(this);
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
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > 480) {
				invincible = false;
				invincibleCounter = 0;
				ghostScore = 200;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
				}
			if(spriteNum == 2) {
				image = up2;
				}
			if(spriteNum == 3) {
				image = up3;
				}
			if(spriteNum == 4) {
				image = up4;
				}
			break;
		
		
		case "down":
			if(spriteNum == 1) {
				image = down1;
				}
			if(spriteNum == 2) {
				image = down2;
				}
			if(spriteNum == 3) {
				image = down3;
				}
			if(spriteNum == 4) {
				image = down4;
				}
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
			if(spriteNum == 4) {
				image = left4;
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
			if(spriteNum == 4) {
				image = right4;
				}
			break;
		
		case "death":
			if(spriteNum == 1) {
				image = death1;
				
			}
			else if(spriteNum == 2) {
				image = death2;
			}
			else if(spriteNum == 3) {
				image = death3;
			}
			else if(spriteNum == 4) {
				image = death4;
			}
			
			else if(spriteNum == 5) {
				image = death5;
				
			}
			else if(spriteNum == 6) {
				image = death6;
			}
			else if(spriteNum == 7) {
				image = death7;
			}
			else if(spriteNum == 8) {
				image = death8;
			}
			if(spriteNum == 9) {
				image = death9;
				
			}
			else if(spriteNum == 10) {
				image = death10;
			}
			else if(spriteNum == 11) {
				image = death11;
			}
			else if(spriteNum == 12) {
				image = death12;
			}
			else if(spriteNum == 13) {
				image = death13;
			}
			else if(spriteNum == 14) {
				image = death13;
			}
			else if(spriteNum == 15) {
				image = death13;
			}
			else if(spriteNum == 16) {
				image = death13;
			}
			else if(spriteNum == 17) {
				image = death13;
			}
			
			break;
		}
		
		g2.drawImage(image,x,y,gm.TILESIZE,gm.TILESIZE,null);
		
		
	}
	public void contactGhost(int i) {
		if(i != 999) {
			if(invincible) {
				
				eatGhost(i);
				
			}
			else {
				ghostScore = 200;
				gm.ghostRelease = 0;
				loseLife();
				
//				direction = "death";
//				deathAnimation = true;
				
			}
		}
		
	}
	private void loseLife() {
		currentLives-=1;
//		gm.gameState = gm.deathState;
		for(int i = 0; i < gm.ghosts.length; i++) {
			gm.ghosts[i] = null;
			gm.oPlacer.placeGhost();
			x=StartingX;
			y=StartingY;
		}
		
		
	}
	private void eatGhost(int index) {
		if(gm.ghosts[index].color !=null) {
		gm.score+=ghostScore;
		ghostScore = ghostScore*2;}
		Entity temp = gm.ghosts[index];
		gm.ghosts[index] = new DeadGhost(gm);
		gm.ghosts[index].x = temp.x;
		gm.ghosts[index].y = temp.y;
		
		          
		
	}
	public void collectPellet(int index) {
		if(index!=999) {
			if(gm.obj[index].name == "Power Pellet") {
				gm.score+=40;
				invincible = true;
			}
			else {gm.score+=10;}
			gm.obj[index]=null;
			
			
		}
		
		
	}
}
