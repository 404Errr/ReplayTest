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
		tiles = new PathfindingTile[1000][1000];
		System.out.println(tiles.length+"\t"+tiles[0].length);
		for (int r = 0;r<Level.getWidthP();r++) {
			for (int c = 0;c<Level.getHeightP();c++) {
				tiles[r][c] = new PathfindingTile(c, r);
			}
		}
	}

	public List<Point> getPath(int iX, int iY, int fX, int fY) {
		List<Point> pathPoints = new LinkedList<>();
		if (!Level.tileExists(iX, iY)||!Level.tileExists(fX, fY)) return pathPoints;
		if (!Level.getTile(iX, iY).isUsable()||!Level.getTile(fX, fY).isUsable()) {
			return pathPoints;
		}
		List<PathfindingTile> path = findPath(iX, iY, fX, fY);
		for (PathfindingTile tileNode:path) {
			pathPoints.add(new Point(tileNode.getX(), tileNode.getY()));
		}
		return pathPoints;
	}

	public List<PathfindingTile> findPath(int iX, int iY, int fX, int fY) {
		for (int r = 0;r<Level.getHeightP();r++) {
			for (int c = 0;c<Level.getWidthP();c++) {
				if (tiles[r][c]!=null) tiles[r][c].setCurrentList(CurrentList.NONE);
			}
		}
		System.out.println(tiles.length+"\t"+tiles[0].length);
		openList = new LinkedList<>();
		openList.add(tiles[iY][iX]);//add first
		tiles[iY][iX].setCurrentList(CurrentList.OPEN);//add first

		PathfindingTile currentPathfindingTile;
		while (openList.size()>0) {
			currentPathfindingTile = getLowestCombinedInOpen();//get node with lowest combined Costs from openList
			openList.remove(currentPathfindingTile);
			currentPathfindingTile.setCurrentList(CurrentList.CLOSED);
			if ((currentPathfindingTile.getX()==fX)&&(currentPathfindingTile.getY()==fY)) {//found goal
				return calcPath(tiles[iY][iX], currentPathfindingTile);
			}
			List<PathfindingTile> adjacentTiles = getAdjacents(currentPathfindingTile);
			for (int i = 0;i<adjacentTiles.size();i++) {
				PathfindingTile currentAdjacent = adjacentTiles.get(i);
				if (currentAdjacent.getCurrentList()!=CurrentList.OPEN) {//node is not in openList
					currentAdjacent.setPrevious(currentPathfindingTile);//set current node as previous for this node
					currentAdjacent.setDistanceCost(tiles[fY][fX]);//set distance
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
