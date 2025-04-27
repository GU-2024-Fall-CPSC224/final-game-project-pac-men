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
		mapTile = new MapTile[24]; // just wall tile for now
		mapTileNum = new int[gm.columnCount][gm.rowCount];
		getTileImage();
		loadMap();
		
	}
	public void getTileImage() {
		try {
			for(int i =0;i<23;i++) {
				mapTile[i] = new MapTile();
			}
			for(int i =2;i<23;i++) {
				if(i!=10) {
				mapTile[i].collision = true;}
			}
			if(gm.hardMode) {
				mapTile[0].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/BackgroundBlack.png"));
				mapTile[1].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/BackgroundBlack.png")); // teleporter to opposite side
				mapTile[2].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-10.png.png"));
				mapTile[3].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-3.png.png"));
				mapTile[4].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-4.png.png"));
				mapTile[5].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-5.png.png"));
				mapTile[6].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-6.png.png"));
				mapTile[7].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-8.png.png"));
				mapTile[8].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-7.png.png"));
				mapTile[9].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-9.png.png"));
				
				mapTile[11].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-11.png.png"));
				mapTile[12].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-12.png.png"));
				mapTile[13].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-13.png.png"));
				mapTile[14].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTile-14.png.png"));
				
				mapTile[15].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-1.png.png"));
				mapTile[16].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-2.png.png"));
				mapTile[17].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-3.png.png"));
				mapTile[18].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-4.png.png"));
				mapTile[19].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-5.png.png"));
				mapTile[20].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-6.png.png"));
				mapTile[21].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-7.png.png"));
				mapTile[22].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sprite-HardModeWalls/HardModeTileSkinny-8.png.png"));
			}
			else {
				mapTile[0].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/BackgroundBlack.png"));
				mapTile[1].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/BackgroundBlack.png")); // teleporter to opposite side
				mapTile[2].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sideways-D.png"));
				mapTile[3].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/UpWall-L.png"));
				mapTile[4].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/UpWall-R.png"));
				mapTile[5].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Corner-UpR.png"));
				mapTile[6].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Corner-UpL.png"));
				mapTile[7].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Corner-DownR.png"));
				mapTile[8].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Corner-DownL.png"));
				mapTile[9].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/Sideways-U.png"));
				
				mapTile[11].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/InnerCorner-DownLeft.png"));
				mapTile[12].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/InnerCorner-DownRight.png"));
				mapTile[13].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/InnerCorner-UpLeft.png"));
				mapTile[14].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/InnerCorner-UpRight.png"));
				
				mapTile[15].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-1.png.png"));
				mapTile[16].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-2.png.png"));
				mapTile[17].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-3.png.png"));
				mapTile[18].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-4.png.png"));
				mapTile[19].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-5.png.png"));
				mapTile[20].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-6.png.png"));
				mapTile[21].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-7.png.png"));
				mapTile[22].image= ImageIO.read(getClass().getResourceAsStream("/pacman/tile/TileSkinny-8.png.png"));
			}
			
			
			
			
			
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void reloadMap() {
		getTileImage();
		loadMap();
	}
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/pacman/maps/map01.txt");
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
