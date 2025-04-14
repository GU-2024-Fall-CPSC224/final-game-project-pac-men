package pacManProject;
import java.awt.*;

import javax.swing.*;

import entity.Player;
import objects.SuperObject;
import tile.MapManager;
	@SuppressWarnings("serial")
	public class Game extends JPanel implements Runnable{
		public int score = 0;
		public int rowCount = 31;
		public int columnCount = 28;
		int scale = 2;
		public final int TILESIZE = 16*scale; // 32 by 32 
		
		public int boardWidth = columnCount * TILESIZE;
		public int boardHeight = rowCount * TILESIZE;
		
		final int FPS = 48;
		
		MapManager tileM = new MapManager(this);
		UserInput userInput = new UserInput();
		Thread gameThread; // allows time to exist in the game
		public Player player = new Player(this,userInput);
		public CollisionDetection cDet = new CollisionDetection(this);
		public SuperObject so = new SuperObject();
		public SuperObject obj[] = new SuperObject[28*32];
		public ObjectPlacer oPlacer = new ObjectPlacer(this);
		
		public static JFrame frame = new JFrame("Pac Man");
		Game(){
			this.setPreferredSize(new Dimension(boardWidth,boardHeight));
			this.setBackground(Color.black);
			this.setDoubleBuffered(true);
			this.addKeyListener(userInput);
			this.setFocusable(true);
		}
		
		public void setupPellets() {
			so.loadPelletMap(this);
			oPlacer.placeObject();
		}
		
		private void buildFrame() {
		
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public void buildGame() {
			this.buildFrame();
			frame.add(this);
			frame.pack();
			
			
			frame.setVisible(true);
			this.setupPellets();
		
		}
		public void startGameThread() {
			gameThread = new Thread(this);
			gameThread.start();  // automatically calls run method
		}
		@Override
		public void run() { // creates game loop (core of game)
			
			double drawInterval = 1000000000/(FPS);
			double delta = 0;
			long currentTime;
			long lastTime = System.nanoTime();
			
			while(gameThread!=null) {
				currentTime = System.nanoTime();
				delta += (currentTime - lastTime) / drawInterval;
				
				lastTime = currentTime;
				
				if(delta>=1) {
				// update character positions
				update();
				
				// re-draw screen
				repaint(); // calls paint component (Required by swing in order to not constantly paint over itself)
				delta--;
				}
				
			}

		}
		public void update() {
			player.update();
			
		}
		public void paintComponent(Graphics g) {
			int oldScore = score;
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D)(g);
			tileM.draw(g2);
			for(int i=0; i<obj.length;i++) {
			if(obj[i]!=null) {
			obj[i].draw(g2,this);}}
			player.draw(g2);
//			if(score>oldScore) {
				System.out.println("Score: "+score);
//			}
			
			g2.dispose(); // like .close()
		}
		
	
	
}