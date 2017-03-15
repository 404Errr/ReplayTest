package client.level.pathfinding;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import data.MapData;
import data.TileData;
import util.Util;

public class PreGeneratedPathGenerator {
	private static boolean[][] useableTiles;
	private static String path, file, suffix = "_nav";

	public static void main(String[] args) {
//		generate(MapData.PATH, MapData.MAP);
//		generate(MapData.PATH, "map4");
//		new PreGeneratedPathGenerator().generate(MapData.PATH, "pretest");
		new PreGeneratedPathGenerator().generate(MapData.PATH, "map4");
	}

	public void generate(String path, String file) {
		PreGeneratedPathGenerator.path = path;
		PreGeneratedPathGenerator.file = file;
		PreGeneratedPathGenerator.useableTiles = TileData.getUseable(Util.parseIntArrayFromFile(path+file));
		for (int x1 = 0;x1<useableTiles[0].length;x1++) {
			for (int y1 = 0;y1<useableTiles.length;y1++) {
//				System.out.println(x1+", "+y1);
				Thread thread = new Thread(new Finder(x1, y1), x1+","+y1);
				thread.start();
			}
		}
	}

	class Finder implements Runnable {

		private final int x1, y1;

		public Finder(int x1, int y1) {
			this.x1 = x1;
			this.y1 = y1;
		}

		@Override
		public void run() {
			PathFinder finder = new AStarPathFinder();
			LinkedList<Point> generated;
			String generatedStr;
			long startTime = System.currentTimeMillis();
			StringBuilder fileContents = new StringBuilder();
			for (int x2 = 0;x2<useableTiles[0].length;x2++) {
				for (int y2 = 0;y2<useableTiles.length;y2++) {
//					System.out.println(x2+", "+y2);
					generated = null;
					generatedStr = "";
					if (useableTiles[y1][x1]&&useableTiles[y2][x2]) {
						generated = /*RefinePath.refinePath(*/finder.getPath(x1, y1, x2, y2, useableTiles)/*)*/;
						generatedStr = pointListToString(generated);
					}
					fileContents.append(generatedStr+"\n");
//					log.append(y1+", "+x1+"\t"+y2+", "+x2+"\t"+(System.currentTimeMillis()-startTime)/1000f+"\n");
				}
			}
			write(path+file+suffix+"/", fileContents.toString(), x1, y1);
			System.out.println(y1+", "+x1+"\t"+(System.currentTimeMillis()-startTime)/1000f);
		}
	}

//	public static void generate(String path, String file) {
//		final String suffix = "_nav";
//		final boolean[][] useableTiles = TileData.getUseable(Util.parseIntArrayFromFile(path+file));
//		PathFinder finder = new AStarPathFinder();
//		LinkedList<Point> generated;
//		String generatedStr;
//		long startTime = System.currentTimeMillis();
//		for (int x1 = 0;x1<useableTiles[0].length;x1++) {
//			for (int y1 = 0;y1<useableTiles.length;y1++) {
//				StringBuilder fileContents = new StringBuilder();
//				for (int x2 = 0;x2<useableTiles[0].length;x2++) {
////					StringBuilder log = new StringBuilder();
//					for (int y2 = 0;y2<useableTiles.length;y2++) {
//						generated = null;
//						generatedStr = "";
//						if (useableTiles[y1][x1]&&useableTiles[y2][x2]) {
//							generated = RefinePath.refinePath(finder.getPath(x1, y1, x2, y2, useableTiles));
//							generatedStr = pointListToString(generated);
//						}
//						fileContents.append(generatedStr+"\n");
////						log.append(y1+", "+x1+"\t"+y2+", "+x2+"\t"+(System.currentTimeMillis()-startTime)/1000f+"\n");
//					}
//				}
//				write(path+file+suffix+"/", fileContents.toString(), x1, y1);
////				long timeRemaining = ''
//				System.out.print(y1+", "+x1+"\t"+"-"+", "+"-"+"\t"+(System.currentTimeMillis()-startTime)/1000f+"\n");
//			}
//		}
//	}

	public static void write(String path, String str, int x1, int y1) {
		try {
			File directory = new File(path+x1);
			if (!directory.exists()){
				directory.mkdir();
			}
			PrintWriter pw = new PrintWriter(new FileWriter(path+x1+"/"+y1, true));
			pw.write(str);
			pw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String pointListToString(List<Point> points) {
		StringBuilder string = new StringBuilder();
		for (int i = 0;i<points.size();i++) {
			Point p = points.get(i);
			string.append(p.x+","+p.y);
			if (i<points.size()-1) {
				string.append(":");
			}
		}
		return string.toString();
	}
}
