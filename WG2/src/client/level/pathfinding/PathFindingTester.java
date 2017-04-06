package client.level.pathfinding;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import data.TileData;

public class PathFindingTester implements TileData {
	public static int x1, y1, x2, y2;
	public static final Color[] COLORS = {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.ORANGE, Color.MAGENTA, Color.YELLOW};
	public static final float OPACITY = 0.75f;
	public static List<LinkedList<Point>> lines = new ArrayList<>();
	static {
		for (int i = 0;i<COLORS.length;i++) lines.add(new LinkedList<>());
	}

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

//	private static AStarPathFinder finder = new AStarPathFinder();
	private static ShortAStarPathFinder finder = new ShortAStarPathFinder();

	public static void find() {
		try {
			finder.setPath(x1, y1, x2, y2, TileData.getUseable());

//			lines.set(1, RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable())));
//			lines.set(2, RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3));
//			lines.set(3, RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3, 3));
//			lines.set(4, RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3, 4));
//			lines.set(5, RefinePath.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()), 3, 5));
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
//				long startTime = System.currentTimeMillis();
//				lines.set(1, RefinePath.refinePath(PreGeneratedPathFinder.getPath(x1, y1, x2, y2), 3));
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
	}

	public static void tick() {
//		lines.set(0, RefinePath.refinePath(finder.getCurrentPath(), 3));
		lines.set(0, finder.getCurrentPath());
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
