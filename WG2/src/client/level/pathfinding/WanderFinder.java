//package client.level.pathfinding;
//
//import java.awt.Point;
//import java.util.Random;
//
//import client.level.Level;
//import client.level.Tile;
//import data.AIData;
//import util.Util;
//
//public class WanderFinder implements AIData {
//	private static Random rand = new Random();
//	private static final int SAMPLE_SIZE = 30;
//
//	public static Point getWanderLocation(int x, int y) {
//		Tile best = null, temp;
//		for (int i = 0;i<SAMPLE_SIZE;i++) {
//			temp = Level.getTile(rand.nextInt(Level.getWidth()), rand.nextInt(Level.getHeight()));
//			if (best==null||getCost(x, y, temp)<getCost(x, y, best)) {
//				best = temp;
//			}
//		}
//		return new Point(best.getC(), best.getR());
//	}
//
//	public static int getCost(int x, int y, Tile tile) {
//		if (!tile.isUsable()) return Integer.MAX_VALUE;
//		return (int)(tile.getNextToWallCost()+Util.distance(x, y, tile.getC(), tile.getR())/8);
//	}
//
////		Point point;
////		do {
////			point = new Point(rand.nextInt(Level.getWidth()), rand.nextInt(Level.getHeight()));
////		} while (!Level.getTile(point.x, point.y).isUsable());
////		return point;
//
//}
