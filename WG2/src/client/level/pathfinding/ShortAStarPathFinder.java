package client.level.pathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import data.TileData;
import util.Util;

public class ShortAStarPathFinder extends PathFinder {
	public static final int BASIC_MOVEMENT_COST = 10;//10
	public static final int DIAGONAL_MOVEMENT_COST = 14;//14
	public static final int WALL_MOVEMENT_COST = 12;//12 additional cost if near a wall
	public static final int WALL_DISTANCE = 2;//2 max distance to walls it checks

	private List<PathfindingTile> openList, closedList;
	private PathfindingTile[][] tiles;
	boolean[][] useableTiles;

	private List<Point> currentPath;

	public ShortAStarPathFinder() {
		this.currentPath = new LinkedList<>();
	}

	public void setPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles) {
		this.useableTiles = useableTiles;
		Thread finder = new Thread(new ShortFinderThread(x1, y1, x2, y2), "AStar");
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
		closedList = new ArrayList<>();
		openList = new ArrayList<>();
		openList.add(tiles[iR][iC]);
		PathfindingTile currentPathfindingTile;
		while (openList.size()>0) {
			currentPathfindingTile = getLowestCombinedInOpen();
			openList.remove(currentPathfindingTile);
			closedList.add(currentPathfindingTile);
			if ((currentPathfindingTile.getC()==fC)&&(currentPathfindingTile.getR()==fR)) {
				return calcPath(tiles[iR][iC], currentPathfindingTile);
			}
			List<PathfindingTile> adjacentTiles = getAdjacents(currentPathfindingTile);
			for (int i = 0;i<adjacentTiles.size();i++) {
				PathfindingTile currentAdjacent = adjacentTiles.get(i);
				currentAdjacent.setDistanceCost(tiles[fR][fC]);
				if (!openList.contains(currentAdjacent)) {
					currentAdjacent.setPrevious(currentPathfindingTile);
					currentAdjacent.setTotalCost(currentPathfindingTile);
					openList.add(currentAdjacent);
				}
				else {
					if (currentAdjacent.getTotalCost()>currentAdjacent.calculateTotalCost(currentPathfindingTile)) {
						currentAdjacent.setPrevious(currentPathfindingTile);
						currentAdjacent.setTotalCost(currentPathfindingTile);
					}
				}
				if (currentPathfindingTile.getPrevious()!=null
						&&!Util.lineIsBrokenByBooleanArray(currentPathfindingTile.getPrevious().getC(), currentPathfindingTile.getPrevious().getR(), currentAdjacent.getC(), currentAdjacent.getR(), Util.negateArray(TileData.getUseable()))) {
					currentAdjacent.setPrevious(currentPathfindingTile.getPrevious());
					currentAdjacent.setTotalCost(currentPathfindingTile.getPrevious());
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
	
	private synchronized void update(PathfindingTile current, PathfindingTile adjacent) {//FIXME
		
	}
	
//	function theta*(start, goal)
//    // This main loop is the same as A*
//    gScore(start) := 0
//    parent(start) := start
//    // Initializing open and closed sets. The open set is initialized 
//    // with the start node and an initial cost
//    open := {}
//    open.insert(start, gScore(start) + heuristic(start))
//    // gScore(node) is the current shortest distance from the start node to node
//    // heuristic(node) is the estimated distance of node from the goal node
//    // there are many options for the heuristic such as Euclidean or Manhattan 
//    closed := {}
//    while open is not empty
//        s := open.pop()
//        if s = goal
//            return reconstruct_path(s)
//        closed.push(s)
//        for each neighbor of s
//        // Loop through each immediate neighbor of s
//            if neighbor not in closed
//                if neighbor not in open
//                    // Initialize values for neighbor if it is 
//                    // not already in the open list
//                    gScore(neighbor) := infinity
//                    parent(neighbor) := Null
//                update_vertex(s, neighbor)
//    return Null
//	
//	function update_vertex(s, neighbor)
//    // This part of the algorithm is the main difference between A* and Theta*
//    if line_of_sight(parent(s), neighbor)
//        // If there is line-of-sight between parent(s) and neighbor
//        // then ignore s and use the path from parent(s) to neighbor 
//        if gScore(parent(s)) + c(parent(s), neighbor) < gScore(neighbor)
//            // c(s, neighbor) is the Euclidean distance from s to neighbor
//            gScore(neighbor) := gScore(parent(s)) + c(parent(s), neighbor)
//            parent(neighbor) := parent(s)
//            if neighbor in open
//                open.remove(neighbor)
//            open.insert(neighbor, gScore(neighbor) + heuristic(neighbor))
//    else
//        // If the length of the path from start to s and from s to 
//        // neighbor is shorter than the shortest currently known distance
//        // from start to neighbor, then update node with the new distance
//        if gScore(s) + c(s, neighbor) < gScore(neighbor)
//            gScore(neighbor) := gScore(s) + c(s, neighbor)
//            parent(neighbor) := s
//            if neighbor in open
//                open.remove(neighbor)
//            open.insert(neighbor, gScore(neighbor) + heuristic(neighbor))

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
		if (r<useableTiles.length-1-1) {
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
		if (c<useableTiles[0].length-1) {
			temp = tiles[r][c+1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c<useableTiles[0].length-1&&r<useableTiles.length-1) {
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
		if (c<useableTiles[0].length-1&&r>0) {
			temp = tiles[r-1][c+1];
			if (temp.isUseable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c>0&&r<useableTiles.length-1) {
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

	class ShortFinderThread implements Runnable {
		private int x1, y1, x2, y2;

		public ShortFinderThread(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public void run() {
			currentPath = ShortAStarPathFinder.this.getPath(x1, y1, x2, y2, useableTiles);
		}
	}
}

