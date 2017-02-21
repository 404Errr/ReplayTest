package client.level.pathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import client.level.Level;
import data.TileData;

public class PathFind implements TileData {
	private static NavMap navMap;//the map

	public static void init() {
		navMap = new NavMap(Level.getWidth(), Level.getHeight(), Level.getLayout());
	}

	public static ArrayList<Point> findPath(int x1, int y1, int x2, int y2) {
		List<TileNode> path = navMap.findPath(x1, y1, x2, y2);
		ArrayList<Point> pathPoints = new ArrayList<>();
		pathPoints.add(new Point(x1, y1));
		for (TileNode tileNode:path) {
			pathPoints.add(new Point(tileNode.getX(), tileNode.getY()));
		}
		return pathPoints;
	}

	//temp
	public static int x1, y1, x2, y2;
	public static ArrayList<Point> lines = new ArrayList<>();
	public static void go() {
		try {
			lines = findPath(x1, y1, x2, y2);
		}
		catch (Exception e) {
			System.err.println("didnt work");
			e.printStackTrace();
		}

	}
	public static void set1(int x, int y) {x1 = x;y1 = y;}
	public static void set2(int x, int y) {x2 = x;y2 = y;}
	//temp
}
