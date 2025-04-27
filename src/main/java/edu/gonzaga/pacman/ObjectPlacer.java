package edu.gonzaga.pacman;
import edu.gonzaga.pacman.ghosts.*;
import edu.gonzaga.pacman.objects.*;
public class ObjectPlacer {
	Game gm;
	int index = 0;
	public ObjectPlacer(Game gm) {
		this.gm = gm;
	}
	public void placeObject() {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while(col < gm.columnCount && row < gm.rowCount) {
			
			int tileNumber = gm.so.pelletMapNum[col][row];
			
			
				if(tileNumber ==1) {
					gm.obj[index] = new OBJ_Pellet();
					gm.obj[index].worldX = x;
					gm.obj[index].worldY = y;
				}
				if(tileNumber ==2) {
					gm.obj[index] = new OBJ_Power_Pellet();
					gm.obj[index].worldX = x;
					gm.obj[index].worldY = y;
					
				}
			
			
			col++;
			x+=gm.TILESIZE;
			
			if(col == gm.columnCount) {
				col = 0;
				x=0;
				row++;
				y+=gm.TILESIZE;
			}
			

		index++;
	}
	
}
	public void placeGhost() {
		if(gm.hardMode) {
			gm.ghosts[0] = new HardModeGhost(gm).resetPlacement();
			gm.ghosts[1] = new HardModeGhost(gm).resetPlacement();
			gm.ghosts[2] = new HardModeGhost(gm).resetPlacement();
			gm.ghosts[3] = new HardModeGhost(gm).resetPlacement();
			gm.ghosts[1].x+=32;
			gm.ghosts[2].x+=64;
			gm.ghosts[3].x+=96;
		}
		else {
		gm.ghosts[0] = new OrangeGhost(gm).resetPlacement();
		gm.ghosts[1] = new CyanGhost(gm).resetPlacement();
		gm.ghosts[2] = new PurpleGhost(gm).resetPlacement();
		gm.ghosts[3] = new PinkGhost(gm).resetPlacement();}
		
	}
	
}