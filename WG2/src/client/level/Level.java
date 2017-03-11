package client.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import client.player.Player;
import data.ColorData;
import data.MapData;
import data.TileData;

public class Level implements MapData, TileData {
	public static final int size = 15;

	public static int widthN, widthP, heightN, heightP;

	private static HashMap<Point, Chunk> chunks;//the level
	private static List<SpawnPoint> spawnPoints;

	public static void init() {
		int[][] layout = LayoutParser.parseLayout(PATH+MAP);
		createChunks(layout);
		createSpawnPoints(layout);
		updateDims();
	}

	public static void createChunks(int[][] layout) {
		chunks = new HashMap<>();
		for (int y = 0;y<layout.length/size;y++) {
			for (int x = 0;x<layout[0].length/size;x++) {
				int[][] chunkLayout = new int[size][size];
				for (int cR = 0;cR<chunkLayout.length;cR++) {
					for (int cC = 0;cC<chunkLayout[0].length;cC++) {
						if (y+cR<layout.length&&x+cC<layout[0].length) {
							chunkLayout[cR][cC] = layout[size*y+cR][size*x+cC];
						}
					}
				}
				chunks.put(new Point(x, y), new Chunk(x, y, chunkLayout));
			}
		}
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
		widthN = widthMin*size;
		heightN = heightMin*size;
		widthP = widthMax*size;
		heightP = heightMax*size;
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

	public static Point getFirstUsableTile(int[][] layout) {//for spawning and stuff
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				if (!TileData.getSolid(layout[r][c])[SOLID_WALLS]&&ColorData.getTileColor(layout[r][c])!=null) {
					return new Point(c, r);
				}
			}
		}
		return new Point(0, 0);
	}

	public static void createSpawnPoints(int[][] layout) {
		spawnPoints = findSpawnPoints(layout);
	}

	public static List<SpawnPoint> findSpawnPoints(int[][] layout) {
		List<SpawnPoint> spawnPoints = new ArrayList<>();
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				if (layout[r][c]==SPAWN_POINT_TYPE) {
					spawnPoints.add(new SpawnPoint(new Point(c, r)));
				}
			}
		}
		if (spawnPoints.isEmpty()) {
			spawnPoints.add(new SpawnPoint(getFirstUsableTile(layout)));
		}
		return spawnPoints;
	}

	public static SpawnPoint getRandomSpawnPoint() {
		return spawnPoints.get((new Random()).nextInt(spawnPoints.size()));
	}

	public static SpawnPoint getSafestSpawnPoint(Player ignore) {
		SpawnPoint safest = spawnPoints.get(0);
		for (int i = 0;i<spawnPoints.size();i++) {
			if (spawnPoints.get(i).getSafetyRating(ignore)>safest.getSafetyRating(ignore)) {
				safest = spawnPoints.get(i);
			}
		}
		return safest;
	}

	public static List<SpawnPoint> getSpawnPoints() {
		return spawnPoints;
	}

	public static void initSpawnPoints() {
		for (int i = 0;i<spawnPoints.size();i++) spawnPoints.get(i).init();
	}

	public static void tick() {
		for (int i = 0;i<spawnPoints.size();i++) spawnPoints.get(i).tick();
	}
}

