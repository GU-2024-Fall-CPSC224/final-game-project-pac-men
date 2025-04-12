package pacManProject;

import entity.Entity;

public class CollisionDetection {
	Game gm;
	public CollisionDetection(Game gm) {
		this.gm = gm;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftX = entity.x + entity.collisionArea.x;
		int entityRightX = entity.x + entity.collisionArea.x + entity.collisionArea.width;
		int entityTopY = entity.y + entity.collisionArea.y;
		int entityBottomY = entity.y + entity.collisionArea.y+entity.collisionArea.height;
		
		
		
		int entityLeftCol = entityLeftX / gm.TILESIZE;
		int entityRightCol = entityRightX / gm.TILESIZE;
		int entityTopRow = entityTopY / gm.TILESIZE;
		int entityBottomRow = entityBottomY / gm.TILESIZE;
		
		int tileNum1, tileNum2;
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopY - entity.speed) / gm.TILESIZE;
			tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.speed)/gm.TILESIZE;
			tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			break;
		case "left":
			entityLeftCol = (entityLeftX - entity.speed)/gm.TILESIZE;
			tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			break;
		case "right":
			entityRightCol = (entityRightX + entity.speed)/gm.TILESIZE;
			tileNum1 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			break;
		
		}
	}
}
