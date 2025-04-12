package pacManProject;


public class PacMan{
	public static void main(String[] args) {
		System.out.println("STARTED");
		Game bg = new Game();

		bg.buildBackground();
		bg.startGameThread();
	}}