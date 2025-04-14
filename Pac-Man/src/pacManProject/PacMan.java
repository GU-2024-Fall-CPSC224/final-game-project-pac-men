package pacManProject;


public class PacMan{
	public static void main(String[] args) {
		System.out.println("STARTED");
		Game gm = new Game();

		gm.buildGame();
	
		gm.startGameThread();
	}}