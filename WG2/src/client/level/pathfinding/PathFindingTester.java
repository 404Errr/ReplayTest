package client.level.pathfinding;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import data.TileData;

public class PathFindingTester implements TileData {
	public static int x1, y1, x2, y2;
	public static List<LinkedList<Point>> lines = new ArrayList<>();
	public static final Color[] COLORS = {Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.RED, Color.ORANGE, Color.YELLOW};
	public static final float OPACITY = 0.75f;
	
	public static void find() {
		lines = new ArrayList<>();
		try {
			lines.add(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()));
//			lines.add(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()));
//			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 2));
			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3));
//			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable())));
//			lines.add(RefinePath.removeLines(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable())));
//			linesMaze = new MazePathFinder().getPath(x1, y1, x2, y2);
//			linesMaze2 = RefinedPathFinder.refinePath(new MazePathFinder().getPath(x1, y1, x2, y2));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void set1(int x, int y) {
		x1 = x;
		y1 = y;
	}

	public static void set2(int x, int y) {
		x2 = x;
		y2 = y;
	}
}
