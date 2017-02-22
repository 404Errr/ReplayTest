package client.level.pathfinding;

import java.util.LinkedList;
import java.util.List;

import client.level.Level;
import data.TileData;

public class NavMap implements TileData {
	private static boolean CAN_MOVE_DIAGONALY = true;

	private TileNode[][] tileNodes;
	public final int width, height;

	public NavMap(int width, int height, int[][] map) {
		tileNodes = new TileNode[width][height];
		for (int i = 0;i<width;i++) {
			for (int j = 0;j<height;j++) {
				tileNodes[i][j] = new TileNode(i, j, !Level.getTile(i, j).isSolid(SOLID_WALLS));
			}
		}
		this.width = width-1;
		this.height = height-1;
	}

	public final TileNode getTileNode(int r, int c) {
		return tileNodes[r][c];
	}

	public TileNode[][] getTileNodes() {
		return tileNodes;
	}

	private List<TileNode> openList;
	private List<TileNode> closedList;
	private boolean done = false;


	public final List<TileNode> findPath(int iX, int iY, int nX, int nY) {//initial and new
		openList = new LinkedList<>();
		closedList = new LinkedList<>();
		openList.add(tileNodes[iX][iY]);

		done = false;
		TileNode currentTileNode;
		while (!done) {
			currentTileNode = lowestFInOpen();//get node with lowest fCosts from openList
			closedList.add(currentTileNode);//add current node to closed
			openList.remove(currentTileNode); //delete current node from open
			if ((currentTileNode.getX()==nX)&&(currentTileNode.getY()==nY)) {//found goal
				return calcPath(tileNodes[iX][iY], currentTileNode);
			}
			List<TileNode> adjacentNodes = getAdjacent(currentTileNode);
			for (int i = 0;i<adjacentNodes.size();i++) {
				TileNode currentAdj = adjacentNodes.get(i);
				if (!openList.contains(currentAdj)) {//node is not in openList
					currentAdj.setPrevious(currentTileNode);//set current node as previous for this node
					currentAdj.sethCosts(tileNodes[nX][nY]);//set h costs of this node (estimated costs to goal)
					currentAdj.setgCosts(currentTileNode);//set g costs of this node (costs from start to this node)
					openList.add(currentAdj);//add node to openList
				} else {//node is in openList
					if (currentAdj.getgCosts()>currentAdj.calculategCosts(currentTileNode)) {//costs from current node are cheaper than previous costs
						currentAdj.setPrevious(currentTileNode);//set current node as previous for this node
						currentAdj.setgCosts(currentTileNode);//set g costs of this node (costs from start to this node)
					}
				}
			}
			if (openList.isEmpty()) {//no path exists
				return new LinkedList<>();//return nothing
			}
		}
		return null;
	}

	private List<TileNode> calcPath(TileNode start, TileNode goal) {//starts at goal, doesnt include start
		LinkedList<TileNode> path = new LinkedList<>();
		TileNode curr = goal;
		boolean done = false;
		while (!done) {
			path.addFirst(curr);
			curr = curr.getPrevious();
			if (curr.equals(start)) {
				done = true;
			}
		}
		return path;
	}

	private TileNode lowestFInOpen() {
		TileNode min = openList.get(0);
		for (int i = 0;i<openList.size();i++) {
			if (openList.get(i).getfCosts()<min.getfCosts()) {
				min = openList.get(i);
			}
		}
		return min;
	}

	private List<TileNode> getAdjacent(TileNode tileNode) {
		int x = tileNode.getX();
		int y = tileNode.getY();
		List<TileNode> adjacent = new LinkedList<>();
		TileNode temp;
		if (x>0) {
			temp = this.getTileNode(x-1, y);
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (x<width) {
			temp = this.getTileNode(x+1, y);
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (y>0) {
			temp = this.getTileNode(x, y-1);
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (y<height) {
			temp = this.getTileNode(x, y+1);
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (CAN_MOVE_DIAGONALY) {
			if (x<width&&y<height) {
				temp = this.getTileNode(x+1, y+1);
				if (temp.isUseable()&&!closedList.contains(temp)) {
					temp.setIsDiagonal(true);
					adjacent.add(temp);
				}
			}
			if (x>0&&y>0) {
				temp = this.getTileNode(x-1, y-1);
				if (temp.isUseable()&&!closedList.contains(temp)) {
					temp.setIsDiagonal(true);
					adjacent.add(temp);
				}
			}
			if (x>0&&y<height) {
				temp = this.getTileNode(x-1, y+1);
				if (temp.isUseable()&&!closedList.contains(temp)) {
					temp.setIsDiagonal(true);
					adjacent.add(temp);
				}
			}
			if (x<width&&y>0) {
				temp = this.getTileNode(x+1, y-1);
				if (temp.isUseable()&&!closedList.contains(temp)) {
					temp.setIsDiagonal(true);
					adjacent.add(temp);
				}
			}
		}
		return adjacent;
	}
}
