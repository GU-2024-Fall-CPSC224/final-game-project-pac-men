package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int x,y;
	public int speed;
	public BufferedImage up1, down1, left1, left2, left3, right1, right2, right3;
	public String direction = "up";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle collisionArea;
	public int collisionAreaDefaultX, collisionAreaDefaultY;
	public boolean collisionOn = false;
}
