package pacManProject;
import objects.*;
public class ObjectPlacer {
	Game gm;
	int index = 0;
	public ObjectPlacer(Game gm) {
		this.gm = gm;
	}
	public void placeObject() {
		gm.obj[0] = new OBJ_Pellet();
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
	
}}
