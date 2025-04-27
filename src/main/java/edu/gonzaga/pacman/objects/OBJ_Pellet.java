package edu.gonzaga.pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;


public class OBJ_Pellet extends SuperObject {

	public OBJ_Pellet() {
		name = "Pellet";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Pellet-S.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
