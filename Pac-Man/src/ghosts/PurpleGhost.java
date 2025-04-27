package ghosts;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import entity.Entity;
import pacManProject.Game;

public class PurpleGhost extends Entity{
	public PurpleGhost(Game gm) {
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
		StartingX =32*19;
		StartingY = 32*14;
		color = "purple";
		
	}
	
	public void getImage() {
		try {
		up1 = 		ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostUp-1.png.png"));
		down1 =	 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostDown-2.png.png"));
		right1 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostRight-1.png.png"));
		right2 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostRight-2.png.png"));
		right3 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostRight-3.png.png"));
		left1 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostLeft-1.png.png"));
		left2 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostLeft-2.png.png"));
		left3 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-PurpleGhost/PurpleGhostLeft-3.png.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void release() {
		released = true;
		new Timer(7000, _ -> {
			if(scatterCounter<4) {
		    scatter = !scatter;}
			else {scatter = false;}
			scatterCounter++;
		}).start();
	}
	public void scatter() {
		goalCol = 7;
		goalRow = 1;
		if(spot1==false) {goalCol = 7; 	goalRow = 1;}
		if(x/32 == 7 && y/32 == 1) {spot1 = true;}
		if(spot1 == true) {goalCol = 12; 	goalRow = 5;}
		if(x/32 == 12 && y/32 == 5) {spot2 = true;}
		if(spot2 == true) {goalCol = 12; 	goalRow = 1;}
		if(x/32 == 12 && y/32 == 1) {spot3 = true;}
		if(spot3 == true) {goalCol = 7;	goalRow = 1;
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