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
		
		}}
		public void checkLeft(Entity entity) {

			int entityTopY = entity.y + entity.collisionArea.y;
			int entityBottomY = entity.y + entity.collisionArea.y+entity.collisionArea.height;
			int entityLeftX = entity.x + entity.collisionArea.x;
			int entityLeftCol = entityLeftX / gm.TILESIZE;
			int entityTopRow = entityTopY / gm.TILESIZE;
			int entityBottomRow = entityBottomY / gm.TILESIZE;
			
			int tileNum1, tileNum2;
			switch(entity.direction) {

			case "left":
				entityLeftCol = (entityLeftX - entity.speed)/gm.TILESIZE;
				tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
					entity.collisionOn = true;
					
				}
				break;
			
			}
	}
		public void checkRight(Entity entity) {
			int entityRightX = entity.x + entity.collisionArea.x + entity.collisionArea.width;
			int entityTopY = entity.y + entity.collisionArea.y;
			int entityBottomY = entity.y + entity.collisionArea.y+entity.collisionArea.height;
			
			
			int entityRightCol = entityRightX / gm.TILESIZE;
			int entityTopRow = entityTopY / gm.TILESIZE;
			int entityBottomRow = entityBottomY / gm.TILESIZE;
			
			int tileNum1, tileNum2;
			switch(entity.direction) {

			case "right":
				entityRightCol = (entityRightX + entity.speed)/gm.TILESIZE;
				tileNum1 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
					entity.collisionOn = true;
					
				}
				break;
			
			}}
		
		
		public void checkUp(Entity entity) {
			int entityLeftX = entity.x + entity.collisionArea.x;
			int entityRightX = entity.x + entity.collisionArea.x + entity.collisionArea.width;
			int entityTopY = entity.y + entity.collisionArea.y;
			
			
			
			int entityLeftCol = entityLeftX / gm.TILESIZE;
			int entityRightCol = entityRightX / gm.TILESIZE;
			int entityTopRow = entityTopY / gm.TILESIZE;
			
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
			
			}}
		public void checkDown(Entity entity) {
			int entityLeftX = entity.x + entity.collisionArea.x;
			int entityRightX = entity.x + entity.collisionArea.x + entity.collisionArea.width;
			int entityBottomY = entity.y + entity.collisionArea.y+entity.collisionArea.height;
			
			
			
			int entityLeftCol = entityLeftX / gm.TILESIZE;
			int entityRightCol = entityRightX / gm.TILESIZE;
			int entityBottomRow = entityBottomY / gm.TILESIZE;
			
			int tileNum1, tileNum2;
			switch(entity.direction) {
			case "down":
				entityBottomRow = (entityBottomY + entity.speed)/gm.TILESIZE;
				tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gm.tileM.mapTile[tileNum1].collision == true || gm.tileM.mapTile[tileNum2].collision == true) {
					entity.collisionOn = true;
					
				}}}
		public int checkObject(Entity entity, boolean player) {
			int index = 999;
			for(int i = 0; i <gm.obj.length;i++) {
				if(gm.obj[i]!=null) {
					
					entity.collisionArea.x = entity.x + entity.collisionArea.x;
					entity.collisionArea.y = entity.y + entity.collisionArea.y;
					gm.obj[i].solidArea.x = gm.obj[i].worldX + gm.obj[i].solidArea.x;
					gm.obj[i].solidArea.y = gm.obj[i].worldY + gm.obj[i].solidArea.y;
					switch(entity.direction) {
					case "up":
						entity.collisionArea.y-=entity.speed;
						if(entity.collisionArea.intersects(gm.obj[i].solidArea)) {
							if(player) {
								index = i;
							}
						}
						
						break;
					case "right":
						entity.collisionArea.x+=entity.speed;
						if(entity.collisionArea.intersects(gm.obj[i].solidArea)) {
							if(player) {
								index = i;
							}
						}
						
						
						break;
					case "down":
						entity.collisionArea.y+=entity.speed;
						if(entity.collisionArea.intersects(gm.obj[i].solidArea)) {
							if(player) {
								index = i;
							}
						}
						break;
					case "left":
						entity.collisionArea.x-=entity.speed;
						if(entity.collisionArea.intersects(gm.obj[i].solidArea)) {
							if(player) {
								index = i;
							}
						}
						break;
					}
					entity.collisionArea.x = entity.collisionAreaDefaultX;
					entity.collisionArea.y = entity.collisionAreaDefaultY;
					gm.obj[i].solidArea.x = gm.obj[i].solidAreaDefaultX;
					gm.obj[i].solidArea.y = gm.obj[i].solidAreaDefaultY;
				}
			}
			
			return index;
		}
}
