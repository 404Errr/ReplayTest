package client.level.pathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import data.TileData;

public class PathFindingTester implements TileData {
	public static int x1, y1, x2, y2;
	public static List<Point> linesAStar = new ArrayList<>();//, linesMaze = new ArrayList<>();

	public static void go() {
//		long start = System.currentTimeMillis();
		try {
			linesAStar = new AStarPathFinder().getPath(x1, y1, x2, y2);

		}
		catch (Exception e) {
			System.err.println("astar didnt work");
		}
//		System.out.println("AStar: "+(System.currentTimeMillis()-start)+"\nAStar Length: "+linesAStar.size());
//		start = System.currentTimeMillis();
//		try {
//			linesMaze = new MazePathFinder().getPath(x1, y1, x2, y2);
//		}
//		catch (Exception e) {
//			System.err.println("maze didnt work");
//		}
//		System.out.println("Maze: "+(System.currentTimeMillis()-start)+"\nMaze Length: "+linesMaze.size());
//		System.out.println();

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
