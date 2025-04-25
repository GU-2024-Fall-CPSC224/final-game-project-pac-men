package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import pacManProject.Game;

public class Entity {
	public boolean released = false;
	public boolean spot1,spot2,spot3 = false;
	public boolean scatter = true;
	public int scatterCounter=0;
	public int goalCol;
	public int goalRow;
	public int x,y;
	public int speed;
	public boolean orangeGhost = false;
	public BufferedImage up1, down1, left1, left2, left3, left4, right1, right2, right3, right4;
	public String direction = "up";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int actionLockCounter = 0;
	public boolean onPath = false;
	public int StartingX,StartingY;
	public boolean returned = false;
	public Rectangle collisionArea = new Rectangle();
	public int collisionAreaDefaultX, collisionAreaDefaultY;
	public boolean collisionOn = false;
	public Game gm;
	public String color;
	public Entity(Game gm) {
		this.gm = gm;
	}
	public void setAction() {}
	public void checkCollision() {
		collisionOn = false;
		gm.cDet.checkTile(this);
		
	}
 	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(orangeGhost) {
			if(spriteNum%2==1) {
			image = up1;}
			else{
				image = down1;
			}}
			else {
				image = up1;}
			
			break;
		
		
		case "down":
			if(orangeGhost) {
				if(spriteNum%2==1) {
				image = up1;}
				else{
					image = down1;
				}}
				else {
					image = down1;}
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
			if((spriteNum == 4)) {
				image = right4;
			}
			break;
		
		}
		g2.drawImage(image,x,y,gm.TILESIZE,gm.TILESIZE,null);
		
		
		
	}
	public void update() {
		if(released) {
		setAction();}
		if(!released) {
			setRandomAction();
		}
		checkCollision();
		
		
		
		// Check For Teleportation
		gm.eHand.checkEvent(this);
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":
				
				y-=speed;
				break;
			
			case "down":
				y+=speed;
				break;
			case "left":
				x-=speed;
				break;
			case "right":
				x+=speed;
				break;
		}
		spriteCounter++;
		if(spriteCounter >10) {
			if(spriteNum == 1) {
				spriteNum = 2;
				
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			
			else if((spriteNum == 3 && left4!=null)) {
				spriteNum = 4;
			}
			else if(spriteNum == 3) {
				spriteNum = 1;
			}
			else if(spriteNum == 4) {
				spriteNum =1;
			}
			
			spriteCounter = 0;
		}}
	}
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (x+ collisionArea.x)/gm.TILESIZE;
		int startRow = (y+ collisionArea.y)/gm.TILESIZE;
		
		gm.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		if(gm.pFinder.search() == true) {
			
			int nextX = gm.pFinder.pathList.get(0).col * gm.TILESIZE;
			int nextY = gm.pFinder.pathList.get(0).row * gm.TILESIZE;
			
			
			int enLeftX = x+collisionArea.x;
			int enRightX = x + collisionArea.x + collisionArea.width;
			int enTopY = y + collisionArea.y;
			int enBottomY = y + collisionArea.y + collisionArea.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gm.TILESIZE ) {
				direction = "up";
				
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gm.TILESIZE ) {
				direction = "down";
				
			}
			else if(enTopY >= nextY && enBottomY < nextY +gm.TILESIZE) {
				
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
				
			}
			else if (enTopY > nextY && enLeftX > nextX) {
				
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if (enTopY > nextY && enLeftX < nextX) {
				
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if (enTopY < nextY && enLeftX > nextX) {
				
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
		}
		if(goalCol == (x+ collisionArea.x)/gm.TILESIZE && goalRow == (y+ collisionArea.y)/gm.TILESIZE) {
			returned = true;
		}
		else {
			returned = false;
		}
	}
	public Entity resetPlacement() {
		this.x = StartingX;
		this.y = StartingY;
		return this;
	}
	public void release() {
		// TODO Auto-generated method stub
		
	}
	public void setRandomAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 60) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction ="up";
			}
			if(i>25 && i<=50) {
				direction ="right";
			}
			if(i>50 && i<=75) {
				direction ="down";
			}
			if(i>75) {
				direction ="left";
			}
			actionLockCounter=0;
		}
		
		
	}
}
