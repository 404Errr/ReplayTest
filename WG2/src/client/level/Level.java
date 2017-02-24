package client.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import data.ColorData;
import data.MapData;
import data.TileData;

public class Level implements MapData, TileData {
	private static int[][] layout;
	private static Tile[][] tiles;//the level
	private static List<SpawnPoint> spawnPoints;
//	private ArrayList<Point> ;//TODO

	public static void init() {
		layout = MapParser.parseMap(MAP);
//		layout = NewOldLevelGen.generateMap();
		if (ADD_EDGE) {
			if (!(AUTO_DISABLE_ADD_EDGE&&(!shouldAddEdge(layout)||layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD))) {
				System.out.println("Adding edge of type: "+EDGE_TYPE);
				layout = addEdge(layout);
			}
			else {
				System.out.print("Not adding edge");
				if (layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD) System.out.println(", because map area is: "+layout.length*layout[0].length+"/"+AUTO_DISABLE_ADD_EDGE_THREASHOLD);
				else System.out.println(".");
			}
		}
		spawnPoints = findSpawnPoints(layout);
		tiles = new Tile[layout.length][layout[0].length];
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(c, r, layout[r][c], TileData.getSolid(layout[r][c])[SOLID_WALLS]);
			}
		}
	}

	public static List<SpawnPoint> findSpawnPoints(int[][] layout) {
		List<SpawnPoint> output = new ArrayList<>();
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				if (layout[r][c]==SPAWN_POINT_TYPE) {
					output.add(new SpawnPoint(new Point(c, r)));
					layout[r][c] = SPAWN_POINT_REPLACEMENT_TYPE;
				}
			}
		}
		if (output.isEmpty()) {
			output.add(new SpawnPoint(getFirstUsableTile()));
		}
		return output;
	}

	public static int[][] addEdge(int[][] map) {//takes given map and adds EDGE_TYPE around it
		System.out.println("Map size: "+map.length+","+map[0].length);
		int[][] output = new int[map.length+2][map[0].length+2];//new array size of map +1 on every side
		for (int r = 0;r<output.length;r++) {
			for (int c = 0;c<output[0].length;c++) {
				if (r==0||c==0||r==output.length-1||c==output[0].length-1) {
					output[r][c] = EDGE_TYPE;
				}
				else {
					output[r][c] = map[r-1][c-1];
				}
			}
		}
		return output;
	}

	public static boolean shouldAddEdge(int[][] layout) {//false if layout contains empty tiles (null color)
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				if (ColorData.getTileColor(layout[r][c])==null) {
					return false;
				}
			}
		}
		return true;
	}

	public static Point getFirstUsableTile() {//for spawning
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				if (Level.getTile(c, r).isUsable()) {
					return new Point(c, r);
				}
			}
		}
		return new Point(0, 0);
	}

	public static int[][] getLayout() {
		return layout;
	}

	public static Tile[][] getTiles() {//get all tiles
		return tiles;
	}

	public static Tile getTile(int c, int r) {//get a specific tile
		return tiles[r][c];
	}

	public static Tile getTile(float c, float r) {
		return getTile((int)c, (int)r);
	}

	public static int getWidth() {
		return tiles[0].length;
	}

	public static int getHeight() {
		return tiles.length;
	}

	public static int getLayoutType(int c, int r) {
		return layout[r][c];
	}

	public static List<SpawnPoint> getSpawnPoints() {
		return spawnPoints;
	}

	public static SpawnPoint getSafestSpawnPoint() {
		SpawnPoint safest = spawnPoints.get(0);
		for (int i = 0;i<spawnPoints.size();i++) {
			if (spawnPoints.get(i).distanceToClosestPlayer()>safest.distanceToClosestPlayer()) {
				safest = spawnPoints.get(0);
			}

		}
		return safest;
	}


}

