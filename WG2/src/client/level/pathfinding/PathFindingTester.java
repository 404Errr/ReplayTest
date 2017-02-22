package client.level.pathfinding;

import java.awt.Point;
import java.util.ArrayList;

import data.TileData;

public class PathFindingTester implements TileData {

	//temp
	public static int x1, y1, x2, y2;
	public static ArrayList<Point> lines = new ArrayList<>();
	public static void go() {
		try {
			lines = PathFinder.getPath(x1, y1, x2, y2);
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
