package ghosts;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import pacManProject.Game;

public class HardModeGhost extends Entity{
public HardModeGhost(Game gm) {
	super(gm);
	speed = 3;
	collisionArea.x =1;
	collisionArea.y =1;
	collisionArea.width = 30;
	collisionArea.height = 30;
	collisionAreaDefaultX = collisionArea.x;
	collisionAreaDefaultY = collisionArea.y;
	direction = "left";
	getImage();
	StartingX =32*20-16;
	StartingY = 32*14;
	color = "black";
}
public void getImage() {
	try {
	up1 = 		ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-1.png.png"));
	down1 =	 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-2.png.png"));
	right1 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-1.png.png"));
	right2 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-2.png.png"));
	right3 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-3.png.png"));
	right4 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-4.png.png"));
	left1 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-1.png.png"));
	left2 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-2.png.png"));
	left3 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-3.png.png"));
	left4 = 	ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-HardModeGhost/HardModeGhostRight-4.png.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
public void setAction() {
	onPath = true;
	if(onPath==true) {
		int goalCol = gm.player.x / 32;
		int goalRow = gm.player.y/32;
		
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
