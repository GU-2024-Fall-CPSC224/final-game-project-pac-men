package edu.gonzaga.pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Power_Pellet extends SuperObject{
	public OBJ_Power_Pellet() {
		name = "Power Pellet";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Pellet-L.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
