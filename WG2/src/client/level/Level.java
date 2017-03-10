package client.level;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import data.MapData;
import data.TileData;
import util.Util;

public class Level implements MapData, TileData {
	public static final int size = 15;

	public static int widthN, widthP, heightN, heightP;

//	private static int[][] layout;
	private static HashMap<Point, Chunk> chunks;//the level
//	private static Chunk[][] chunks;//the level
//	private static List<SpawnPoint> spawnPoints;

	public static void preInit() {

		chunks = new HashMap<>();


		for (int i = -5;i<5;i++) {
			for (int j = -5;j<5;j++) {
				chunks.put(new Point(i, j), new Chunk(i, j, Util.fillArray(new int[size][size], '0')));
			}
		}

		updateDims();

//		chunks.put(new Point(0, 0), new Chunk(0, 0, Util.fillArray(new int[size][size], '1')));
//		chunks.put(new Point(1, 0), new Chunk(1, 0, Util.fillArray(new int[size][size], '4')));
//		chunks.put(new Point(0, 1), new Chunk(0, 1, Util.fillArray(new int[size][size], '3')));
//		chunks.put(new Point(0, -1), new Chunk(0, -1, Util.fillArray(new int[size][size], '0')));
//		chunks.put(new Point(-1, 0), new Chunk(-1, 0, Util.fillArray(new int[size][size], '5')));

//		if (MAP!=null) {
//			if (MAP.charAt(0)==EMPTY_TAGS[0]) {
//				try {
//					String[] dimensions = MAP.substring(1).split("x");
//					layout = getEmpty(Integer.parseInt(dimensions[0]),Integer.parseInt(dimensions[1]));
//				}
//				catch (Exception e) {
//					System.err.println("Error with empty creation");
//				}
//			}
//			else layout = LayoutParser.parseLayout(PATH+MAP);
//			if (ADD_EDGE&&!Edit.editMode) {
//				if (!(AUTO_DISABLE_ADD_EDGE&&(!shouldAddEdge(layout)||layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD))) {
//					System.out.println("Adding edge of type: "+EDGE_TYPE);
//					layout = addEdge(layout);
//				}
//				else {
//					System.out.print("Not adding edge");
//					if (layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD) System.out.println(", because map area is too large: "+layout.length*layout[0].length+"/"+AUTO_DISABLE_ADD_EDGE_THREASHOLD);
//					else System.out.println(".");
//				}
//			}
//		}
//		else layout = LayoutGenerator.generate(6, 6);
//		spawnPoints = findSpawnPoints(layout);
//		createTiles();
	}

//	public static void initSpawnPoints() {
//		for (int i = 0;i<spawnPoints.size();i++) spawnPoints.get(i).init();
//	}

	/*public static void createTiles() {
		tiles = new Tile[layout.length][layout[0].length];
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(c, r, layout[r][c], TileData.getSolid(layout[r][c])[SOLID_WALLS]);
			}
		}
	}*/

//	public static List<SpawnPoint> findSpawnPoints(int[][] layout) {
//		List<SpawnPoint> output = new ArrayList<>();
//		for (int r = 0;r<layout.length;r++) {
//			for (int c = 0;c<layout[0].length;c++) {
//				if (layout[r][c]==SPAWN_POINT_TYPE) {
//					output.add(new SpawnPoint(new Point(c, r)));
//				}
//			}
//		}
//		if (output.isEmpty()) {
//			output.add(new SpawnPoint(getFirstUsableTile()));
//		}
//		return output;
//	}
//
//	public static int[][] addEdge(int[][] map) {//takes given map and adds EDGE_TYPE around it
//		System.out.println("Map size: "+map.length+","+map[0].length);
//		int[][] output = new int[map.length+2][map[0].length+2];//new array size of map +1 on every side
//		for (int r = 0;r<output.length;r++) {
//			for (int c = 0;c<output[0].length;c++) {
//				if (r==0||c==0||r==output.length-1||c==output[0].length-1) {
//					output[r][c] = EDGE_TYPE;
//				}
//				else {
//					output[r][c] = map[r-1][c-1];
//				}
//			}
//		}
//		return output;
//	}
//
//	public static boolean shouldAddEdge(int[][] layout) {//false if layout contains empty tiles (null color)
//		for (int r = 0;r<layout.length;r++) {
//			for (int c = 0;c<layout[0].length;c++) {
//				if (ColorData.getTileColor(layout[r][c])==null) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	public static int[][] getEmpty(int width, int height) {
		return Util.fillArray(new int[height][width], '1');//EMPTY_TYPE);1
	}

	public static Tile getTile(int x, int y) {
		return chunks.get(new Point((int)Math.floor(x/(float)size), (int)Math.floor(y/(float)size))).getTile(x-size*(int)Math.floor(x/(float)size), y-size*(int)Math.floor(y/(float)size));
	}

	public static boolean tileExists(int x, int y) {
		return chunks.get(new Point((int)Math.floor(x/(float)size), (int)Math.floor(y/(float)size)))!=null;
	}

	public static void updateDims() {
		int widthMin = 0, widthMax = 0, heightMin = 0, heightMax = 0;
		Map<Point, Chunk> map = chunks;
		for (Map.Entry<Point, Chunk> chunk:map.entrySet()) {
			Point point = chunk.getKey();
			if (point.x<widthMin) widthMin = point.x;
			if (point.x>widthMax) widthMax = point.x;
			if (point.y<heightMin) heightMin = point.y;
			if (point.y>heightMax) heightMax = point.y;
		}
		widthN = widthMin;
		widthP = widthMax;
		heightN = heightMin;
		heightP = heightMax;
	}

	public static int getWidthN() {
		return widthN;
	}

	public static int getWidthP() {
		return widthP;
	}

	public static int getHeightN() {
		return heightN;
	}

	public static int getHeightP() {
		return heightP;
	}

	public static HashMap<Point, Chunk> getChunks() {
		return chunks;
	}

	public static Tile getTile(float x, float y) {
		return getTile((int)x, (int)y);
	}



//	public static Point getFirstUsableTile() {//for spawning and stuff
//		for (int r = 0;r<layout.length;r++) {
//			for (int c = 0;c<layout[0].length;c++) {
//				if (!TileData.getSolid(layout[r][c])[SOLID_WALLS]&&ColorData.getTileColor(layout[r][c])!=null) {
//					return new Point(c, r);
//				}
//			}
//		}
//		return new Point(0, 0);
//	}

//	public static SpawnPoint getRandomSpawnPoint() {
//		return spawnPoints.get((new Random()).nextInt(spawnPoints.size()));
//	}
//
//	public static SpawnPoint getSafestSpawnPoint(Player ignore) {
//		SpawnPoint safest = spawnPoints.get(0);
//		for (int i = 0;i<spawnPoints.size();i++) {
//			if (spawnPoints.get(i).getSafetyRating(ignore)>safest.getSafetyRating(ignore)) {
//				safest = spawnPoints.get(i);
//			}
//		}
//		return safest;
//	}
//
//	public static void tick() {
//		for (int i = 0;i<spawnPoints.size();i++) spawnPoints.get(i).tick();
//	}

//	public static int[][] getLayout() {
//		return layout;
//	}
//
//	public static Tile[][] getTiles() {//get all tiles
//		return tiles;
//	}
//
//	public static Tile getTile(int c, int r) {//get a specific tile
//		return tiles[r][c];
//	}
//
//	public static Tile getTile(float c, float r) {
//		return getTile((int)c, (int)r);
//	}
//
//	public static int getWidth() {
//		return tiles[0].length;
//	}
//
//	public static int getHeight() {
//		return tiles.length;
//	}

//	public static int getLayoutType(int c, int r) {
//		return layout[r][c];
//	}
//
//	public static int getLayoutType(float c, float r) {
//		return getLayoutType((int)c, (int)r);
//	}

//	public static List<SpawnPoint> getSpawnPoints() {
//		return spawnPoints;
//	}

//	public static void setLayoutType(int x, int y, int type) {
//		layout[y][x] = type;
//	}
//
//	public static void setLayout(int[][] layout) {
//		Level.layout = layout;
//	}


}

