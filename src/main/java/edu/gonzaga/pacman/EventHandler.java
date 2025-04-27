package edu.gonzaga.pacman;

import java.awt.Rectangle;

import edu.gonzaga.pacman.entity.Entity;

public class EventHandler {
 Game gm;
 Rectangle eventRect;
 int eventRectDefaultX, eventRectDefaultY;
 
 public EventHandler(Game gm) {
	 this.gm = gm;
	 eventRect = new Rectangle();
	 eventRect.x = 15;
	 eventRect.y = 15;
	 eventRect.width = 2;
	 eventRect.height = 2;
	 eventRectDefaultX = eventRect.x;
	 eventRectDefaultY = eventRect.y;
	 
 }
 public void checkEvent(Entity e) {

	 if(hit(6,14,e)) {
		 e.x = 32*32;
		 e.y = 14*32;
		 e.direction = "left";
	 }
	  if(hit(33,14,e)) {
		 e.x = 7*32;
		 e.y = 14*32;
		 e.direction = "right";
	 }
	 
 }
 public boolean hit(int eventCol, int eventRow,Entity e) {
	 boolean hit = false;
	 
	 e.collisionArea.x += e.x;
	 e.collisionArea.y += e.y;
	 eventRect.x += eventCol*gm.TILESIZE;
	 
	 eventRect.y += eventRow*gm.TILESIZE;
	 if(e.collisionArea.intersects(eventRect)) {
		 hit = true;
	 }
	 e.collisionArea.y = e.collisionAreaDefaultY;
	 e.collisionArea.x = e.collisionAreaDefaultX;
	 eventRect.x = eventRectDefaultX;
	 eventRect.y = eventRectDefaultY;
	 return hit;
 }
}
