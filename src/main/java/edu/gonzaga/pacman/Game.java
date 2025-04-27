package edu.gonzaga.pacman;
import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

import edu.gonzaga.pacman.ai.PathFinder;
import edu.gonzaga.pacman.entity.Entity;
import edu.gonzaga.pacman.entity.Player;
import edu.gonzaga.pacman.objects.SuperObject;
import edu.gonzaga.pacman.tile.MapManager;
	@SuppressWarnings("serial")
	public class Game extends JPanel implements Runnable{
		public int score = 0;
		public int ghostRelease = 0;
		public boolean reset = false;
		public int rowCount = 31;
		public int columnCount = 40;
		int scale = 2;
		public final int TILESIZE = 16*scale;
		public int boardStartCol = 6;
		public int boardEndCol = 33;
		public int boardWidth = columnCount * TILESIZE;
		public int boardHeight = rowCount * TILESIZE;
		public MapManager tileM = new MapManager(this);
		public boolean hardMode = false;
		
		final int GHOSTFPS = 48;
		final int PLAYERFPS = 120;
		public JPanel pausePanel = new JPanel();

		UserInput userInput = new UserInput(this);
		Thread gameThread; // allows time to exist in the game
		public PathFinder pFinder = new PathFinder(this);
		//Entities
		public Player player = new Player(this,userInput);
		public Entity ghosts[] = new Entity[10];
		
		public EventHandler eHand = new EventHandler(this);
		public CollisionDetection cDet = new CollisionDetection(this);
		public SuperObject so = new SuperObject();
		public SuperObject obj[] = new SuperObject[boardWidth*boardHeight];
		public ObjectPlacer oPlacer = new ObjectPlacer(this);
		public UI ui = new UI(this);
		public static JFrame frame = new JFrame("Pac Man");
		
		// Game State (Start,End, Pause)
		public int gameState;
		public final int startScreenState = 1;
		public final int playState = 2;
		public final int pauseState = 3;
		public final int endScreenState = 4;
		public final int deathState = 5;
		
		Game(){
			this.setPreferredSize(new Dimension(boardWidth,boardHeight));
			this.setBackground(Color.black);
			this.setDoubleBuffered(true);
			this.addKeyListener(userInput);
			this.setFocusable(true);
			new Timer(2500, e -> {
				if(ghostRelease<4) {
			    ghosts[ghostRelease].release();}
				ghostRelease++;
			}).start();
			
		}
		
		public void setupGame() {
			gameState = startScreenState;
			so.loadPelletMap(this);
			
			
			
		}
		public void startGame() {
			tileM.reloadMap();
			oPlacer.placeObject();
			oPlacer.placeGhost();
		}
		
		private void buildFrame() {
		
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pausePanel);
		
		}
		
		public void buildGame() {
			this.buildFrame();
			frame.add(this);
			frame.pack();
			
			
			frame.setVisible(true);
			this.setupGame();
		
		}
		public void startGameThread() {
			gameThread = new Thread(this);
			gameThread.start();  // automatically calls run method
		}
		@Override
		public void run() { // creates game loop (core of game)
			
			double ghostDrawInterval = 1000000000/(GHOSTFPS);
			double playerDrawInterval = 1000000000/(PLAYERFPS);
			double delta = 0;
			double delta2 = 0;
			long currentTime;
			long lastTime = System.nanoTime();
			
			while(gameThread!=null) {
				currentTime = System.nanoTime();
				delta += (currentTime - lastTime) / ghostDrawInterval;
				delta2 += (currentTime - lastTime) / playerDrawInterval;
				lastTime = currentTime;
				
				if(delta>=1) {
				// update character positions
				eHand.checkEvent(player);
				update();
				
				// re-draw screen
				repaint(); // calls paint component (Required by swing in order to not constantly paint over itself)
				delta--;}
				
				if(delta2>=1) {
					// update character positions
					eHand.checkEvent(player);
					animationUpdate();
					
					// re-draw screen
					repaint(); // calls paint component (Required by swing in order to not constantly paint over itself)
					delta2--;
				}
				
			}

		}
		public void update() {
			if(gameState == playState) {
			player.update();
			for(int i =0; i<ghosts.length;i++) {
				if(ghosts[i]!=null) {
					ghosts[i].update();
				}
			}
			}
			if(player.currentLives == 0) {
				gameState = endScreenState;
			}
			
			for(int i=0; i<obj.length;i++) {
				if(obj[i]!=null) {
					reset=false;
				}}
			if(reset == true&&score>=50) {
				player.x = player.StartingX;
				player.y = player.StartingY;
				so.loadPelletMap(this);
				startGame();
				
						}
			reset = true;
			
		}
		public void animationUpdate() {
				if(gameState == playState) {
				player.animationUpdate();}
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)(g);
			if(gameState == playState) {
				tileM.draw(g2);
				for(int i=0; i<obj.length;i++) {
				if(obj[i]!=null) {
				obj[i].draw(g2,this);}}
				player.draw(g2);
				ui.draw(g2);
				for(int i=0; i<ghosts.length;i++) {
					if(ghosts[i] != null) {
						ghosts[i].draw(g2);
						
					}
				} }
			
			if(gameState == pauseState) {
					tileM.draw(g2);
					for(int i=0; i<obj.length;i++) {
					if(obj[i]!=null) {
					obj[i].draw(g2,this);}}
					player.draw(g2);
					ui.draw(g2);}
				if(gameState == startScreenState) {ui.draw(g2);}
				if(gameState == endScreenState) {ui.draw(g2);}
				
				
					
			

			g2.dispose(); // like .close()
		}
		
	
	
}