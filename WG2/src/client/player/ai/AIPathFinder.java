package client.player.ai;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import client.level.pathfinding.AStarPathFinder;
import client.level.pathfinding.RefinePath;
import data.TileData;

public class AIPathFinder {
	private List<Point> currentPath;
	private AStarPathFinder finder;

	public AIPathFinder() {
		this.currentPath = new LinkedList<>();
		this.finder = new AStarPathFinder();
	}

	public void setPath(int x1, int y1, int x2, int y2) {
		finder.setPath(x1, y1, x2, y2, TileData.getUseable());
	}

	public void refresh() {
		currentPath = RefinePath.refinePath(finder.getCurrentPath(), 3/*, 3*/);
	}

	public LinkedList<Point> getCurrentPath() {
		return (LinkedList<Point>) currentPath;
	}

	public void clearCurrentPath() {
		currentPath = new LinkedList<>();
	}

}
