package edu.gonzaga.pacman.ghosts;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.gonzaga.pacman.entity.Entity;
import edu.gonzaga.pacman.Game;

public class DeadGhost extends Entity{
	
public DeadGhost(Game gm) {
		super(gm);
		released = true;
		speed = 2;
		collisionArea.x =1;
		collisionArea.y =1;
		collisionArea.width = 24;
		collisionArea.height = 24;
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
		direction = "left";
		getImage();
		StartingX =32*20;
		StartingY = 32*14;
}
public void getImage() {
	try {
	up1 = 		ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-4.png.png"));
	down1 =	 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-3.png.png"));
	right1 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-2.png.png"));
	right2 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-2.png.png"));
	right3 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-2.png.png"));
	left1 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-1.png.png"));
	left2 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-1.png.png"));
	left3 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-DeadGhost/DeadGhost-1.png.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setAction() {
	onPath = true;
	if(onPath==true) {
		int goalCol = 20;
		int goalRow = 14;
		
		
		if(this.x/32 == StartingX/32 && this.y == StartingY) {
			if(gm.ghosts[0]==this) {
				if(gm.hardMode) {
					gm.ghosts[0] = new HardModeGhost(gm).resetPlacement();
				}
				else {
				gm.ghosts[0] = new OrangeGhost(gm).resetPlacement();
				}}
			else if(gm.ghosts[1]==this) {
				if(gm.hardMode) {
					gm.ghosts[1] = new HardModeGhost(gm).resetPlacement();
				}
				else {
				gm.ghosts[1] = new CyanGhost(gm).resetPlacement();
				}}
			else if(gm.ghosts[2]==this) {
				if(gm.hardMode) {
					gm.ghosts[2] = new HardModeGhost(gm).resetPlacement();
				}
				else {
				gm.ghosts[2] = new PurpleGhost(gm).resetPlacement();
				}}
			else if(gm.ghosts[3]==this) {
				if(gm.hardMode) {
					gm.ghosts[3] = new HardModeGhost(gm).resetPlacement();
				}
				else {
				gm.ghosts[3] = new PinkGhost(gm).resetPlacement();
			}}
		}
		for(int i=0; i< gm.ghosts.length;i++) {
			if(gm.ghosts[i]!=null) {
			gm.ghosts[i].released = true;}
		}
		searchPath(goalCol,goalRow);
	}
	
	else {
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
	}}
	
	
}
}
