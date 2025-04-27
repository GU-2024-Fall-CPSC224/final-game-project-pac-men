package edu.gonzaga.pacman.ghosts;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import edu.gonzaga.pacman.entity.Entity;
import edu.gonzaga.pacman.Game;

public class PinkGhost extends Entity{

public PinkGhost(Game gm) {
		
		super(gm);
		speed = 2;
		collisionArea.x =1;
		collisionArea.y =1;
		collisionArea.width = 30;
		collisionArea.height = 30;
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
		direction = "left";
		getImage();
		StartingX =32*21;
		StartingY = 32*14;
		color = "pink";
	}
	public void getImage() {
		try {
		up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostUp-1.png.png"));
		down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostDown-1.png.png"));
		right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostRight-1.png.png"));
		right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostRight-2.png.png"));
		right3 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostRight-3.png.png"));
		left1 =ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostLeft-1.png.png"));
		left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostLeft-2.png.png"));
		left3 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-PinkGhost/PinkGhostLeft-3.png.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void release() {
		released = true;
		new Timer(7000, e -> {
			if(scatterCounter<4) {
		    scatter = !scatter;}
			else {scatter = false;}
			scatterCounter++;
		}).start();
	}
	public void scatter() {
		if(spot1==false) {goalCol = 32; 	goalRow = 1;}
		if(x/32 == 32 && y/32 == 1) {spot1 = true;}
		if(spot1 == true) {goalCol = 27; 	goalRow = 5;}
		if(x/32 == 27 && y/32 == 5) {spot2 = true;}
		if(spot2 == true) {goalCol = 27; 	goalRow = 1;}
		if(x/32 == 27 && y/32 == 1) {spot3 = true;}
		if(spot3 == true) {goalCol = 32;	goalRow = 1;
		spot1 = false;
		spot2 = false;
		spot3 = false;
		}
		searchPath(goalCol,goalRow);
		
}
public void chase() {
	goalCol = gm.player.x / 32;
	goalRow = gm.player.y/32;
	searchPath(goalCol,goalRow);
}
public void setAction() {
	
	if(scatter) {
	scatter();}
	else {
		chase();
	}}
}
