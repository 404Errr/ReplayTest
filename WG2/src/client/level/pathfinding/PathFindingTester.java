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
	static {
		lines.add(new LinkedList<>());
		lines.add(new LinkedList<>());
		lines.add(new LinkedList<>());
		lines.add(new LinkedList<>());
		lines.add(new LinkedList<>());
	}
	public static final Color[] COLORS = {Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.RED, Color.ORANGE, Color.YELLOW};
	public static final float OPACITY = 0.75f;

//	static class AStarFinderThread implements Runnable{
//		@Override
//		public void run() {
//			long startTime = System.currentTimeMillis();
//			lines.set(0, new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()));
//			System.out.println("astar\t"+(System.currentTimeMillis()-startTime)/1000f);
//		}
//	}
//
//	static class RefinedAStarFinderThread implements Runnable{
//		@Override
//		public void run() {
//			long startTime = System.currentTimeMillis();
//			lines.set(1, RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3));
//			System.out.println("astar r\t"+(System.currentTimeMillis()-startTime)/1000f);
//		}
//	}

	private static AStarPathFinder finder = new AStarPathFinder();

	public static void find() {
		try {
//			long startTime;

//			startTime = System.currentTimeMillis();
//			Thread astar = new Thread(new AStarFinderThread(), "AStar");
//			astar.start();
//			Thread astarr = new Thread(new RefinedAStarFinderThread(), "Refined AStar");
//			astarr.start();
//			System.out.println("astar r\t"+(System.currentTimeMillis()-startTime)/1000f);
			finder.setPath(x1, y1, x2, y2, TileData.getUseable());
			lines.set(0, (LinkedList<Point>) finder.getCurrentPath());

//			startTime = System.currentTimeMillis();
//			lines.add(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()));
//			System.out.println("astar\t"+(System.currentTimeMillis()-startTime)/1000f);
//
//			startTime = System.currentTimeMillis();
//			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3));
//			System.out.println("astar r\t"+(System.currentTimeMillis()-startTime)/1000f);

//			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 2));
//			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable())));
//			lines.add(RefinePath.removeLines(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable())));

//			startTime = System.currentTimeMillis();
//			lines.add(preGeneratedPaths.getPath(x1, y1, x2, y2));
//			System.out.println("pre\t"+(System.currentTimeMillis()-startTime)/1000f);

//			try {
//				startTime = System.currentTimeMillis();
//				lines.add(RefinePath.refinePath(PreGeneratedPathFinder.getPath(x1, y1, x2, y2), 3));
//				System.out.println("pre\t"+(System.currentTimeMillis()-startTime)/1000f);
//			}
//			catch (Exception e) {
//			startTime = System.currentTimeMillis();
//			lines.add(RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3));
//			System.out.println("astar r\t"+(System.currentTimeMillis()-startTime)/1000f);
//			}

//			startTime = System.currentTimeMillis();
//			lines.add(PreGeneratedPathFinder.getPath(x1, y1, x2, y2));
//			System.out.println("pre\t"+(System.currentTimeMillis()-startTime)/1000f);

//			startTime = System.currentTimeMillis();
//			lines.add(new MazePathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()));
//			System.out.println("maze\t"+(System.currentTimeMillis()-startTime)/1000f);

//			lines.add(RefinePath.refinePath(new MazePathFinder().getPath(x1, y1, x2, y2)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
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
