package edu.gonzaga.pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener{
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	Game gm;
	public UserInput(Game gm) {
		this.gm = gm;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(gm.gameState== gm.pauseState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { // if W or ^ is pressed
				gm.ui.selectedButton -= 1;
				if(gm.ui.selectedButton <0) {
					gm.ui.selectedButton = 2;
				}
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { // if S or down arrow is pressed
				
				gm.ui.selectedButton += 1;
				if(gm.ui.selectedButton >2) {
					gm.ui.selectedButton = 0;
				}}
			
			if(code == KeyEvent.VK_ENTER) { // if S or down arrow is pressed
				
				if(gm.ui.selectedButton ==0 ) {
					gm.gameState = gm.playState;}
				else if (gm.ui.selectedButton ==1) {
					gm.gameState = gm.startScreenState;
				}
				else if (gm.ui.selectedButton ==2 ) {
					System.exit(0);
				}
				}

			}
		if(gm.gameState== gm.playState) {
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { // if W or ^ is pressed
			upPressed = true;
			downPressed = false;
			rightPressed = false;
			leftPressed = false;
			
		}
		
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { // if A or < is pressed
			leftPressed = true;
			rightPressed = false;
			upPressed = false;
			downPressed = false;
					
				}
		
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { // if S or down arrow is pressed
			
			downPressed = true;
			upPressed = false;
			rightPressed = false;
			leftPressed = false;
			
		}
		
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { // if D or > is pressed
			rightPressed = true;
			leftPressed = false;
			upPressed = false;
			downPressed = false;
			
		}}
		if(code == KeyEvent.VK_ESCAPE) { // if D or > is pressed
			if(gm.gameState == gm.pauseState) {
				gm.gameState = gm.playState;
			}
			else if(gm.gameState == gm.playState){gm.gameState=gm.pauseState;}
			
		}
		if(gm.gameState == gm.startScreenState) {
			
			
			
			if(code == KeyEvent.VK_ESCAPE) {
				System.exit(0);
				}
			if(code == KeyEvent.VK_ENTER) { // if S or down arrow is pressed
				gm.gameState = gm.playState;
				gm.startGame();}
			if(code == KeyEvent.VK_SPACE) {
				
				gm.gameState = gm.playState;
				gm.hardMode = true; 
				gm.startGame();
			}
				
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		int code = e.getKeyCode();
//		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { // if W or ^ is pressed
//			upPressed = false;
//			
//		}
//		
//		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { // if A or < is pressed
////			leftPressed = false;
//					
//				}
//		
//		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { // if S or down arrow is pressed
//			downPressed = false;
//			
//		}
//		
//		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { // if D or > is pressed
////			rightPressed = false;
//			
//		}
		
	}

}
