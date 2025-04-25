package ai;

import java.util.ArrayList;

import pacManProject.Game;

public class PathFinder {
	Game gm;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode,goalNode,currentNode;
	public boolean goalReached = false;
	int step = 0;
	
	public PathFinder(Game gm) {
		this.gm = gm;
		buildNodes();
	}
	public void buildNodes() {
		
		node = new Node[gm.boardEndCol][gm.rowCount];
		
		int col = 0;
		int row = 0;
		while(col<gm.boardEndCol && row<gm.rowCount) {
			node[col][row] = new Node (col,row);
			col++;
			if(col == gm.boardEndCol) {
				col = 0;
				row++;
			}
			
		}
		
	}
	
	public void resetNodes() {
		int col = 0;
		int row = 0;
		
		while(col<gm.boardEndCol && row<gm.rowCount) {
			
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if(col == gm.boardEndCol) {
				col = 0;
				row++;
			}
			
			openList.clear();
			pathList.clear();
			goalReached = false;
			step = 0;
			
		}
	}
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
		
		resetNodes();
		
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		
		int col = 6;
		int row = 0;
		
		while(col<gm.columnCount && row<gm.rowCount) {
			int tileNum = gm.tileM.mapTileNum[col][row];
			if (gm.tileM.mapTile[tileNum].collision == true) {
				node[col][row].solid = true;
			}
			
		
		
		getCost(node[col][row]);
		
		col++;
		if(col == gm.boardEndCol) {
			col = 0;
			row++;
		}}
		
	}
	public void getCost(Node node) {
		// G cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		// H cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		// F cost
		node.fCost = node.hCost + node.gCost;
	}
	
	public boolean search() {
		
		while(goalReached == false && step < 500) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			if(row-1>= 0) {
				openNode(node[col][row-1]);
			}

			if(row+1 <  gm.rowCount) {
				openNode(node[col][row+1]);
			}
			if(col-1>= 0) {
				openNode(node[col-1][row]);
			}

			if(col+1 < gm.boardEndCol) {
				openNode(node[col+1][row]);
			}
			
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			for(int i= 0; i < openList.size(); i++) {
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
					
				}
				else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
				
			}
			if(openList.size()==0) {
				break;
			}
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
				
			}
			step++;
		}
		return goalReached;
	}
	public void trackThePath() {
		Node current = goalNode;
		while(current!= startNode) {
			pathList.add(0,current);
			current = current.parent;
		}
		
	}
	public void openNode(Node node) {
		if(node.open == false && node.checked == false  && node.solid == false) {
			
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
			
		}
		
	}
	
}
