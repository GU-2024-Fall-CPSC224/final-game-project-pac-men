package edu.gonzaga.pacman.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import edu.gonzaga.pacman.Game;

public class SuperObject {
	public int[][] pelletMapNum;
	SuperObject[] pelletTypes= new SuperObject[3];
	public BufferedImage image;
	public String name;
	public boolean collison = false;
	public int worldX,worldY;
	public Rectangle solidArea = new Rectangle(32,32,25,25);
	public int solidAreaDefaultX=0;
	public int solidAreaDefaultY=0;
	public SuperObject() {
		name = "pellet";

	}
	public void loadPelletMap(Game gm) {
		pelletMapNum = new int[gm.columnCount][gm.rowCount];
		try {
		pelletTypes[1] = new OBJ_Pellet();
		pelletTypes[1].image=ImageIO.read(getClass().getResourceAsStream("/pacman/objects/Pellet-S.png"));
		pelletTypes[2] = new OBJ_Power_Pellet();
		pelletTypes[2].image=ImageIO.read(getClass().getResourceAsStream("/pacman/objects/Pellet-L.png"));
		}
		catch(IOException e) {
			e.printStackTrace();		}
		
	
		try {
			InputStream is = getClass().getResourceAsStream("/pacman/maps/pelletmap.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while(col < gm.columnCount && row < gm.rowCount) {
				String line = br.readLine();
				while(col < gm.columnCount) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					pelletMapNum[col][row]= num;
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
	public void draw(Graphics2D g2,Game gm) {

			g2.drawImage(image, this.worldX, this.worldY, gm.TILESIZE, gm.TILESIZE,null);

			
		
}
}
