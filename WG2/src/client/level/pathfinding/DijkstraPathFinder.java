package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import util.Util;

public class DijkstraPathFinder extends PathFinder {
	public static final int BASIC_MOVEMENT_COST = 10;//10
	public static final int DIAGONAL_MOVEMENT_COST = 14;//14
	public static final int WALL_MOVEMENT_COST = 12;//12 additional cost if near a wall (avoid collisions)
	public static final int WALL_DISTANCE = 2;//2 max distance to walls it checks

	private List<PathfindingTile> unVisited;
	private PathfindingTile[][] tiles;
	boolean[][] useableTiles;

	@Override
	public LinkedList<Point> getPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles) {
		List<Point> pathPoints = new LinkedList<>();
		if (!Util.inArrayBounds(x1, y1, useableTiles)||!Util.inArrayBounds(x2, y2, useableTiles)||!useableTiles[y1][x1]||!useableTiles[y2][x2]) {
			return (LinkedList<Point>) pathPoints;
		}
		this.useableTiles = useableTiles;
		List<PathfindingTile> path = findPath(x1, y1, x2, y2);
		for (PathfindingTile tileNode:path) {
			pathPoints.add(new Point(tileNode.getC(), tileNode.getR()));
		}
		return (LinkedList<Point>) pathPoints;
	}

	public List<PathfindingTile> findPath(int iC, int iR, int fC, int fR) {
		tiles = new PathfindingTile[useableTiles.length][useableTiles[0].length];
		unVisited = new LinkedList<>();
		for (int r = 0;r<useableTiles.length;r++) {
			for (int c = 0;c<useableTiles[0].length;c++) {
				tiles[r][c] = new PathfindingTile(c, r, useableTiles);
				tiles[r][c].setDijkstraCost(Integer.MAX_VALUE/*fC, fR*/);
				if (useableTiles[r][c]) unVisited.add(tiles[r][c]);
			}
		}
		tiles[iR][iC].setDijkstraCost(0);
		PathfindingTile currentPathfindingTile;
		while (unVisited.size()>0) {
			currentPathfindingTile = getLowest();
			unVisited.remove(currentPathfindingTile);
			if ((currentPathfindingTile.getC()==fC)&&(currentPathfindingTile.getR()==fR)) return calcPath(tiles[iR][iC], tiles[fR][fC]);
			List<PathfindingTile> adjacentTiles = getAdjacents(currentPathfindingTile);
			for (int i = 0;i<adjacentTiles.size();i++) {
				PathfindingTile currentAdjacent = adjacentTiles.get(i);
				currentAdjacent.setPrevious(currentPathfindingTile);
				if (currentAdjacent.getDijkstraCost()>currentAdjacent.calculateDijkstraCost(currentPathfindingTile.getC(), currentPathfindingTile.getR())) {
					currentAdjacent.setDijkstraCost(currentPathfindingTile.getC(), currentPathfindingTile.getR());
				}
			}
		}
		return new LinkedList<>();
	}

	private List<PathfindingTile> calcPath(PathfindingTile start, PathfindingTile goal) {//starts at goal, doesnt include start
		LinkedList<PathfindingTile> path = new LinkedList<>();
		PathfindingTile curr = goal;
		while (curr!=start) {
			path.add(0, curr);
			curr = curr.getPrevious();
		}
		path.add(0, start);
		return path;
	}

	private PathfindingTile getLowest() {
		PathfindingTile min = unVisited.get(0);
		for (int i = 0;i<unVisited.size();i++) {
			if (unVisited.get(i).getDijkstraCost()<min.getDijkstraCost()) {
				min = unVisited.get(i);
			}
		}
		return min;
	}

	private List<PathfindingTile> getAdjacents(PathfindingTile tile) {
		int c = tile.getC(), r = tile.getR();
		List<PathfindingTile> adjacent = new LinkedList<>();
		PathfindingTile temp = null;
		if (r>0) {
			temp = tiles[r-1][c];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (r<tiles.length-1) {
			temp = tiles[r+1][c];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c>0) {
			temp = tiles[r][c-1];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c<tiles[0].length-1) {
			temp = tiles[r][c+1];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (c<tiles[0].length-1&&r<tiles.length-1) {
			temp = tiles[r+1][c+1];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c>0&&r>0) {
			temp = tiles[r-1][c-1];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c<tiles[0].length-1&&r>0) {
			temp = tiles[r-1][c+1];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (c>0&&r<tiles.length-1) {
			temp = tiles[r+1][c-1];
			if (temp.isUseable()&&unVisited.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		return adjacent;
	}
}