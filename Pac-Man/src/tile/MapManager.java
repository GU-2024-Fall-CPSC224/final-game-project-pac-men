package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import pacManProject.Game;

// TODO maybe rename this to level manager 

public class MapManager {
	Game gm;
	public MapTile[] mapTile;
	public int mapTileNum[][];
	public MapManager(Game gm) {
		this.gm = gm;
		mapTile = new MapTile[15]; // just wall tile for now
		mapTileNum = new int[gm.columnCount][gm.rowCount];
		getTileImage();
		loadMap();
		
	}
	public void getTileImage() {
		try {
			mapTile[0] = new MapTile();
			mapTile[0].image= ImageIO.read(getClass().getResourceAsStream("/tile/BackgroundBlack.png"));
			
			mapTile[1] = new MapTile();
			mapTile[1].image= ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			
			mapTile[3] = new MapTile();
			mapTile[3].image= ImageIO.read(getClass().getResourceAsStream("/tile/UpWall-L.png"));
			mapTile[4] = new MapTile();
			mapTile[4].image= ImageIO.read(getClass().getResourceAsStream("/tile/UpWall-R.png"));
			mapTile[5] = new MapTile();
			mapTile[5].image= ImageIO.read(getClass().getResourceAsStream("/tile/Corner-UpR.png"));
			mapTile[6] = new MapTile();
			mapTile[6].image= ImageIO.read(getClass().getResourceAsStream("/tile/Corner-UpL.png"));
			mapTile[7] = new MapTile();
			mapTile[7].image= ImageIO.read(getClass().getResourceAsStream("/tile/Corner-DownR.png"));
			mapTile[8] = new MapTile();
			mapTile[8].image= ImageIO.read(getClass().getResourceAsStream("/tile/Corner-DownL.png"));
			mapTile[9] = new MapTile();
			mapTile[9].image= ImageIO.read(getClass().getResourceAsStream("/tile/Sideways-U.png"));
			mapTile[2] = new MapTile();
			mapTile[2].image= ImageIO.read(getClass().getResourceAsStream("/tile/Sideways-D.png"));
			
			mapTile[11] = new MapTile();
			mapTile[11].image= ImageIO.read(getClass().getResourceAsStream("/tile/InnerCorner-DownLeft.png"));
			mapTile[12] = new MapTile();
			mapTile[12].image= ImageIO.read(getClass().getResourceAsStream("/tile/InnerCorner-DownRight.png"));
			mapTile[13] = new MapTile();
			mapTile[13].image= ImageIO.read(getClass().getResourceAsStream("/tile/InnerCorner-UpLeft.png"));
			mapTile[14] = new MapTile();
			mapTile[14].image= ImageIO.read(getClass().getResourceAsStream("/tile/InnerCorner-UpRight.png"));
			mapTile[1].collision = false;
			mapTile[2].collision = true;
			mapTile[3].collision = true;
			mapTile[4].collision = true;
			mapTile[5].collision = true;
			mapTile[6].collision = true;
			mapTile[7].collision = true;
			mapTile[8].collision = true;
			mapTile[9].collision = true;
			mapTile[11].collision = true;
			mapTile[12].collision = true;
			mapTile[13].collision = true;
			mapTile[14].collision = true;
			
			
			
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while(col < gm.columnCount && row < gm.rowCount) {
				String line = br.readLine();
				while(col < gm.columnCount) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row]= num;
					col++;
				}
				if(col == gm.columnCount) {
					col =0;
					row++;
				}
				
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while(col < gm.columnCount && row < gm.rowCount) {
			int tileNumber = mapTileNum[col][row];
			g2.drawImage(mapTile[tileNumber].image, x, y, gm.TILESIZE, gm.TILESIZE,null);
			col++;
			x+=gm.TILESIZE;
			if(col == gm.columnCount) {
				col = 0;
				x=0;
				row++;
				y+=gm.TILESIZE;
			}
		}
		

	
}
	
}
