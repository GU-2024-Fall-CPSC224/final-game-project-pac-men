package ghosts;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import entity.Entity;
import pacManProject.Game;

public class CyanGhost extends Entity{
	int goalCol;
	int goalRow;
	public CyanGhost(Game gm) {
		
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
		StartingX =32*20;
		StartingY = 32*14;
		color = "cyan";
		goalCol = (gm.player.x-4) / 32;
		goalRow = gm.player.y/32;
		onPath = true;
	}
	public void getImage() {
		try {
		up1 = 		ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostUp-1.png.png"));
		down1 =	 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostDown-1.png.png"));
		right1 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostRight-1.png.png"));
		right2 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostRight-2.png.png"));
		right3 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostRight-3.png.png"));
		left1 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostLeft-1.png.png"));
		left2 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostLeft-2.png.png"));
		left3 = 	ImageIO.read(getClass().getResourceAsStream("/pacman/ghosts/Sprite-CyanGhost/CyanGhostLeft-3.png.png"));
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
		if(spot1==false) {goalCol = 32; 	goalRow = 29;}
		if(x/32 == 32 && y/32 == 29) {spot1 = true;}
		if(spot1 == true) {goalCol = 27; 	goalRow = 26;}
		if(x/32 == 27 && y/32 == 26) {spot2 = true;}
		if(spot2 == true) {goalCol = 21; 	goalRow = 26;}
		if(x/32 == 21 && y/32 == 26) {spot3 = true;}
		if(spot3 == true) {goalCol = 32;	goalRow = 29;
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
