package client.level.pathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import client.level.Level;
import client.level.Tile;
import data.TileData;
import main.Debug;
import main.Debug.TileDebugState;

public class PathFinder implements TileData {
	private static List<Tile> openList, closedList;

	public static ArrayList<Point> getPath(int x1, int y1, int x2, int y2) {
		if (Debug.getTileDebugState()!=TileDebugState.NONE) for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				Level.getTile(c, r).reset();
			}
		}
		List<Tile> path = findPath(x1, y1, x2, y2);
		ArrayList<Point> pathPoints = new ArrayList<>();
		pathPoints.add(new Point(x1, y1));
		for (Tile tileNode:path) {
			pathPoints.add(new Point(tileNode.getC(), tileNode.getR()));
		}

		return pathPoints;
	}

	public static List<Tile> findPath(int iC, int iR, int nC, int nR) {//initial and new
		openList = new LinkedList<>();
		closedList = new LinkedList<>();
		openList.add(Level.getTile(iC, iR));

		boolean done = false;
		Tile currentTile;
		while (!done) {
			currentTile = getLowestCombinedInOpen();//get node with lowest fCosts from openList
			closedList.add(currentTile);//add current node to closed
			openList.remove(currentTile); //delete current node from open
			if ((currentTile.getC()==nC)&&(currentTile.getR()==nR)) {//found goal
				return calcPath(Level.getTile(iC, iR), currentTile);
			}
			List<Tile> adjacentTiles = getAdjacents(currentTile);
			for (int i = 0;i<adjacentTiles.size();i++) {
				Tile currentAdj = adjacentTiles.get(i);
				if (!openList.contains(currentAdj)) {//node is not in openList
					currentAdj.setPrevious(currentTile);//set current node as previous for this node
					currentAdj.setDistanceCost(Level.getTile(nC, nR));//set h costs of this node (estimated costs to goal)
					currentAdj.setTotalCost(currentTile);//set g costs of this node (costs from start to this node)
					openList.add(currentAdj);//add node to openList
				}
				else {//node is in openList
					if (currentAdj.getTotalCost()>currentAdj.calculateTotalCost(currentTile)) {//costs from current node are cheaper than previous costs
						currentAdj.setPrevious(currentTile);//set current node as previous for this node
						currentAdj.setTotalCost(currentTile);//set g costs of this node (costs from start to this node)
					}
				}
			}
			if (openList.isEmpty()) {//no path exists
				return new LinkedList<>();//return nothing
			}
		}
		return null;
	}

	private static List<Tile> calcPath(Tile start, Tile goal) {//starts at goal, doesnt include start
		LinkedList<Tile> path = new LinkedList<>();
		Tile curr = goal;
		boolean done = false;
		while (!done) {
			path.addFirst(curr);
			curr = curr.getPrevious();
			if (curr==start) done = true;
		}
		return path;
	}

	private static Tile getLowestCombinedInOpen() {
		Tile min = openList.get(0);
		for (int i = 0;i<openList.size();i++) {
			if (openList.get(i).getCombinedCosts()<min.getCombinedCosts()) {
				min = openList.get(i);
			}
		}
		return min;
	}

	public static Tile getHighestCombined() {
		Tile max = Level.getTile(0, 0);
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				if (Level.getTile(c, r).getCombinedCosts()>max.getCombinedCosts()) {
					max = Level.getTile(c, r);
				}
			}
		}
		return max;
	}

	public static Tile getHighestTotal() {
		Tile max = Level.getTile(0, 0);
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				if (Level.getTile(c, r).getTotalCost()>max.getTotalCost()) {
					max = Level.getTile(c, r);
				}
			}
		}
		return max;
	}

	public static Tile getHighestDistance() {
		Tile max = Level.getTile(0, 0);
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				if (Level.getTile(c, r).getDistanceCost()>max.getDistanceCost()) {
					max = Level.getTile(c, r);
				}
			}
		}
		return max;
	}

	private static List<Tile> getAdjacents(Tile tile) {
		int x = tile.getC();
		int y = tile.getR();
		List<Tile> adjacent = new LinkedList<>();
		Tile temp;
		if (x>0) {
			temp = Level.getTile(x-1, y);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (x<Level.getWidth()-1) {
			temp = Level.getTile(x+1, y);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (y>0) {
			temp = Level.getTile(x, y-1);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (y<Level.getHeight()) {
			temp = Level.getTile(x, y+1);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(false);
				adjacent.add(temp);
			}
		}
		if (x<Level.getWidth()&&y<Level.getHeight()) {
			temp = Level.getTile(x+1, y+1);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (x>0&&y>0) {
			temp = Level.getTile(x-1, y-1);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (x>0&&y<Level.getHeight()) {
			temp = Level.getTile(x-1, y+1);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		if (x<Level.getWidth()&&y>0) {
			temp = Level.getTile(x+1, y-1);
			if (temp.isUsable()&&!closedList.contains(temp)) {
				temp.setIsDiagonal(true);
				adjacent.add(temp);
			}
		}
		return adjacent;
	}
}
