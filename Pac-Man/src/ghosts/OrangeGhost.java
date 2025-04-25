package ghosts;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import entity.Entity;
import pacManProject.Game;

public class OrangeGhost extends Entity{
	public OrangeGhost(Game gm) {
		
		super(gm);
		orangeGhost = true;
		speed = 2;
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
		color = "orange";
		goalCol = gm.player.x / 32;
		goalRow = gm.player.y/32;
	}
	public void getImage() {
		try {
		up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostVertical-1.png.png"));
		down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostVertical-2.png.png"));
		right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostRightV2-1.png.png"));
		right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostRightV2-2.png.png"));
		right3 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostRightV2-3.png.png"));
		left1 =ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostLeftV2-1.png.png"));
		left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostLeftV2-2.png.png"));
		left3 = ImageIO.read(getClass().getResourceAsStream("/ghosts/Sprite-OrangeGhostV2/OrangeGhostLeftV2-3.png.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void release() {
		new Timer(7000, _ -> {
			if(scatterCounter<4) {
		    scatter = !scatter;}
			else {scatter = false;}
			scatterCounter++;
		}).start();
		released = true;
	}
	public void scatter() {
			if(spot1==false) {goalCol = 7; 	goalRow = 29;}
			if(x/32 == 7 && y/32 == 29) {spot1 = true;}
			if(spot1 == true) {goalCol = 12; 	goalRow = 26;}
			if(x/32 == 12 && y/32 == 26) {spot2 = true;}
			if(spot2 == true) {goalCol = 18; 	goalRow = 26;}
			if(x/32 == 18 && y/32 == 26) {spot3 = true;}
			if(spot3 == true) {goalCol = 7;	goalRow = 29;
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
