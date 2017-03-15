package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import util.Util;

public class AStarPathFinder extends PathFinder {
	public static final int BASIC_MOVEMENT_COST = 10;//10
	public static final int DIAGONAL_MOVEMENT_COST = 14;//14
	public static final int WALL_MOVEMENT_COST = 12;//12 additional cost if near a wall
	public static final int WALL_DISTANCE = 2;//2 max distance to walls it checks

	private List<PathfindingTile> openList, closedList;
	private PathfindingTile[][] tiles;
	boolean[][] useableTiles;

	private List<Point> currentPath;

	public AStarPathFinder() {
		this.currentPath = new LinkedList<>();
	}

	public void setPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles) {
		this.useableTiles = useableTiles;
		Thread finder = new Thread(new FinderThread(x1, y1, x2, y2), "AStar");
		finder.start();
	}

	@Override
	public synchronized LinkedList<Point> getPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles) {
		this.useableTiles = useableTiles;
		List<Point> pathPoints = new LinkedList<>();
		if (!Util.inArrayBounds(x1, y1, useableTiles)||!Util.inArrayBounds(x2, y2, useableTiles)||!useableTiles[y1][x1]||!useableTiles[y2][x2]) {
			return (LinkedList<Point>) pathPoints;
		}
		tiles = new PathfindingTile[useableTiles.length][useableTiles[0].length];
		for (int r = 0;r<useableTiles.length;r++) {
			for (int c = 0;c<useableTiles[0].length;c++) {
				tiles[r][c] = new PathfindingTile(c, r, useableTiles);
			}
		}
		List<PathfindingTile> path = findPath(x1, y1, x2, y2);
		for (PathfindingTile tileNode:path) {
			pathPoints.add(new Point(tileNode.getC(), tileNode.getR()));
		}
		return (LinkedList<Point>) pathPoints;
	}

	public synchronized List<PathfindingTile> findPath(int iC, int iR, int fC, int fR) {
		closedList = new LinkedList<>();
		openList = new LinkedList<>();
		openList.add(tiles[iR][iC]);//add first

		PathfindingTile currentPathfindingTile;
		while (openList.size()>0) {
			currentPathfindingTile = getLowestCombinedInOpen();//get node with lowest combined Costs from openList
			openList.remove(currentPathfindingTile);
			closedList.add(currentPathfindingTile);
			if ((currentPathfindingTile.getC()==fC)&&(currentPathfindingTile.getR()==fR)) {//found goal
				return calcPath(tiles[iR][iC], currentPathfindingTile);
			}
			List<PathfindingTile> adjacentTiles = getAdjacents(currentPathfindingTile);
			for (int i = 0;i<adjacentTiles.size();i++) {
				PathfindingTile currentAdjacent = adjacentTiles.get(i);
				if (!openList.contains(currentAdjacent)) {
					currentAdjacent.setPrevious(currentPathfindingTile);//set current node as previous for this node
					currentAdjacent.setDistanceCost(tiles[fR][fC]);//set distance
					currentAdjacent.setTotalCost(currentPathfindingTile);//set total
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

	private synchronized List<PathfindingTile> calcPath(PathfindingTile start, PathfindingTile goal) {//starts at goal, doesnt include start
		LinkedList<PathfindingTile> path = new LinkedList<>();
		PathfindingTile curr = goal;
		while (true) {
			path.addFirst(curr);
			if (curr==start) break;
			curr = curr.getPrevious();
		}
		return path;
	}

	private synchronized PathfindingTile getLowestCombinedInOpen() {
		PathfindingTile min = openList.get(0);
		for (int i = 0;i<openList.size();i++) {
			if (openList.get(i).getCombinedCosts()<min.getCombinedCosts()) {
				min = openList.get(i);
			}
		}
		return min;
	}

	private synchronized List<PathfindingTile> getAdjacents(PathfindingTile tile) {
		int c = tile.getC(), r = tile.getR();
		List<PathfindingTile> adjacent = new LinkedList<>();
		PathfindingTile temp;
		if (r>0) {
			temp = tiles[r-1][c];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (r<useableTiles.length-1) {
			temp = tiles[r+1][c];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c>0) {
			temp = tiles[r][c-1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c<useableTiles[0].length) {
			temp = tiles[r][c+1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c<useableTiles[0].length&&r<useableTiles.length) {
			temp = tiles[r+1][c+1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c>0&&r>0) {
			temp = tiles[r-1][c-1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c<useableTiles[0].length&&r>0) {
			temp = tiles[r-1][c+1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c>0&&r<useableTiles.length) {
			temp = tiles[r+1][c-1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		return adjacent;
	}

	public LinkedList<Point> getCurrentPath() {
		return (LinkedList<Point>) currentPath;
	}

	public void clearCurrentPath() {
		currentPath = new LinkedList<>();
	}

	class FinderThread implements Runnable{
		private int x1, y1, x2, y2;

		public FinderThread(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public void run() {
			currentPath = RefinePath.refinePath(AStarPathFinder.this.getPath(x1, y1, x2, y2, useableTiles), 3);
		}
	}
}

