package client.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import client.ai.pathfinding.Map;
import client.ai.pathfinding.MapNode;
import client.level.Level;
import shared.data.TileData;

public class PathFind implements TileData {
	private static Map<MapNode> map;//the map

	public static void init() {
		map = new Map<>(Level.getWidth(), Level.getHeight());
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				map.setWalkable(c, r, !Level.getTile(r, c).isSolid(SOLID_WALLS));
			}
		}
	}

	public static ArrayList<Point> findPath(int x1, int y1, int x2, int y2) {
		List<MapNode> path = map.findPath(x1, y1, x2, y2);

		ArrayList<Point> pathPoints = new ArrayList<>();
		pathPoints.add(new Point(x1, y1));
		for (int i = 0; i < path.size(); i++) {
			pathPoints.add(new Point(path.get(i).getXPosition(), path.get(i).getYPosition()));
		}
		return pathPoints;
	}

	public static int x1, y1, x2, y2;
	public static ArrayList<Point> lines = new ArrayList<>();
	public static void go() {
		try {
			long before = System.currentTimeMillis();
			lines = findPath(x1, y1, x2, y2);
			System.out.println(System.currentTimeMillis()-before);
		}
		catch (Exception e) {
			System.err.println("didnt work");
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
