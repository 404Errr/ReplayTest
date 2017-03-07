package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import client.level.Level;
import client.level.pathfinding.PathfindingTile.CurrentList;
import data.TileData;

public class PathFinder implements TileData {
	private List<PathfindingTile> openList;//, closedList;
	private PathfindingTile[][] tiles;

	public PathFinder() {
		tiles = new PathfindingTile[Level.getHeightP()][Level.getWidthP()];
		for (int r = 0;r<Level.getHeightP();r++) {
			for (int c = 0;c<Level.getWidthP();c++) {
				tiles[r][c] = new PathfindingTile(c, r);
			}
		}
	}

	public List<Point> getPath(int x1, int y1, int x2, int y2) {
		List<Point> pathPoints = new LinkedList<>();
		if (!Level.tileExists(x1, y1)||!Level.tileExists(x2, y2)) return pathPoints;
		if (!Level.getTile(x1, y1).isUsable()||!Level.getTile(x2, y2).isUsable()) {
			return pathPoints;
		}
		List<PathfindingTile> path = findPath(x1, y1, x2, y2);
		for (PathfindingTile tileNode:path) {
			pathPoints.add(new Point(tileNode.getX(), tileNode.getY()));
		}
		return pathPoints;
	}

	public List<PathfindingTile> findPath(int iC, int iR, int fC, int fR) {
		for (int r = 0;r<Level.getHeightP();r++) {
			for (int c = 0;c<Level.getWidthP();c++) {
				tiles[r][c].setCurrentList(CurrentList.NONE);
			}
		}
		openList = new LinkedList<>();
		openList.add(tiles[iR][iC]);//add first
		tiles[iR][iC].setCurrentList(CurrentList.OPEN);//add first

		PathfindingTile currentPathfindingTile;
		while (openList.size()>0) {
			currentPathfindingTile = getLowestCombinedInOpen();//get node with lowest combined Costs from openList
			openList.remove(currentPathfindingTile);
			currentPathfindingTile.setCurrentList(CurrentList.CLOSED);
			if ((currentPathfindingTile.getX()==fC)&&(currentPathfindingTile.getY()==fR)) {//found goal
				return calcPath(tiles[iR][iC], currentPathfindingTile);
			}
			List<PathfindingTile> adjacentTiles = getAdjacents(currentPathfindingTile);
			for (int i = 0;i<adjacentTiles.size();i++) {
				PathfindingTile currentAdjacent = adjacentTiles.get(i);
				if (currentAdjacent.getCurrentList()!=CurrentList.OPEN) {//node is not in openList
					currentAdjacent.setPrevious(currentPathfindingTile);//set current node as previous for this node
					currentAdjacent.setDistanceCost(tiles[fR][fC]);//set distance
					currentAdjacent.setTotalCost(currentPathfindingTile);//set total
					currentAdjacent.setCurrentList(CurrentList.OPEN);
					openList.add(currentAdjacent);//add node to openList
				}
				else {//node is in openList
					if (currentAdjacent.getTotalCost()>currentAdjacent.calculateTotalCost(currentPathfindingTile)) {//costs from current node are cheaper than previous costs
						currentAdjacent.setPrevious(currentPathfindingTile);//set current node as previous for this node
						currentAdjacent.setTotalCost(currentPathfindingTile);//set total
					}
				}
			}
		}
		return new LinkedList<>();//no path exists; return empty list
	}

	private List<PathfindingTile> calcPath(PathfindingTile start, PathfindingTile goal) {//starts at goal, doesnt include start
		LinkedList<PathfindingTile> path = new LinkedList<>();
		PathfindingTile curr = goal;
		while (true) {
			path.addFirst(curr);
			if (curr==start) break;
			curr = curr.getPrevious();
		}
		return path;
	}

	private PathfindingTile getLowestCombinedInOpen() {
		PathfindingTile min = openList.get(0);
		for (int i = 0;i<openList.size();i++) {
			if (openList.get(i).getCombinedCosts()<min.getCombinedCosts()) {
				min = openList.get(i);
			}
		}
		return min;
	}

	private List<PathfindingTile> getAdjacents(PathfindingTile tile) {
		int x = tile.getX(), y = tile.getY();
		List<PathfindingTile> adjacent = new LinkedList<>();
		PathfindingTile temp;
		if (y>Level.getHeightN()) {
			temp = tiles[y-1][x];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (y<Level.getHeightP()-1) {
			temp = tiles[y+1][x];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (x>Level.getWidthN()) {
			temp = tiles[y][x-1];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (x<Level.getWidthP()) {
			temp = tiles[y][x+1];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (x<Level.getWidthP()&&y<Level.getHeightP()) {
			temp = tiles[y+1][x+1];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (x>Level.getWidthN()&&y>Level.getHeightN()) {
			temp = tiles[y-1][x-1];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (x<Level.getWidthP()&&y>Level.getHeightN()) {
			temp = tiles[y-1][x+1];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (x>Level.getWidthN()&&y<Level.getHeightP()) {
			temp = tiles[y+1][x-1];
			if (temp.isUsable()&&temp.getCurrentList()!=CurrentList.CLOSED) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		return adjacent;
	}
}
