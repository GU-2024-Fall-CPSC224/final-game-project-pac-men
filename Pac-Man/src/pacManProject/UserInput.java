package pacManProject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener{
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
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
