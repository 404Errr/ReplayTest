package client.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import client.edit.Edit;
import client.mapgen.LayoutGenerator;
import client.player.Player;
import data.ColorData;
import data.MapData;
import data.TileData;
import util.Util;

public class Level implements MapData, TileData {
	private static int[][] layout;
	private static Tile[][] tiles;//the level
	private static List<SpawnPoint> spawnPoints;

	public static void preInit() {
		if (MAP!=null) {
//			if (MAP.charAt(0)==EMPTY_TAGS[0]) {
//				try {
//					String[] dimensions = MAP.substring(1).split("x");
//					layout = getEmpty(Integer.parseInt(dimensions[0]),Integer.parseInt(dimensions[1]));
//				}
//				catch (Exception e) {
//					System.err.println("Error with empty creation");
//				}
//			}
			/*else */layout = Util.parseIntArrayFromFile(PATH+MAP);
			if (ADD_EDGE&&!Edit.editMode) {
				if (!(AUTO_DISABLE_ADD_EDGE&&(!shouldAddEdge(layout)||layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD))) {
					System.out.println("Adding edge of type: "+EDGE_TYPE);
					layout = addEdge(layout);
				}
				else {
					System.out.print("Not adding edge");
					if (layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD) System.out.println(", because map area is too large: "+layout.length*layout[0].length+"/"+AUTO_DISABLE_ADD_EDGE_THREASHOLD);
					else System.out.println(".");
				}
			}
		}
		else layout = LayoutGenerator.generate(6, 6);
		spawnPoints = findSpawnPoints(layout);
		createTiles();
	}

	public static void initSpawnPoints() {
		for (int i = 0;i<spawnPoints.size();i++) spawnPoints.get(i).init();
	}

	public static void createTiles() {
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
				}
			}
		}
		if (output.isEmpty()) {
			output.add(new SpawnPoint(getFirstUsableTile()));
		}
		return output;
	}

	public static int[][] getEmpty(int width, int height) {
		return Util.fillArray(new int[height][width], EMPTY_TYPE);
	}

	public static Point getFirstUsableTile() {//for spawning and stuff
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				if (!TileData.getSolid(layout[r][c])[SOLID_WALLS]&&ColorData.getTileColor(layout[r][c])!=null) {
					return new Point(c, r);
				}
			}
		}
		return new Point(0, 0);
	}

	public static SpawnPoint getRandomSpawnPoint() {
		return spawnPoints.get((new Random()).nextInt(spawnPoints.size()));
	}

	public static SpawnPoint getSafestSpawnPoint(Player exclude) {
		SpawnPoint safest = spawnPoints.get(0);
		for (int i = 0;i<spawnPoints.size();i++) {
			if (spawnPoints.get(i).getSafetyRating(exclude)>safest.getSafetyRating(exclude)) {
				safest = spawnPoints.get(i);
			}
		}
		return safest;
	}

	public static void tick() {
		for (int i = 0;i<spawnPoints.size();i++) spawnPoints.get(i).tick();
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

	public static int getLayoutType(float c, float r) {
		return getLayoutType((int)c, (int)r);
	}

	public static List<SpawnPoint> getSpawnPoints() {
		return spawnPoints;
	}

	public static void setLayoutType(int x, int y, int type) {
		layout[y][x] = type;
	}

	public static void setLayout(int[][] layout) {
		Level.layout = layout;
	}


}

