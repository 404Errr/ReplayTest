package level;

import data.MapData;

public class Level implements MapData {
	private static Tile[][] tiles;
	private static int[][] layout = MapData.getMap();

	public static void init() {
		tiles = new Tile[layout.length][layout[0].length];
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(r, c, layout[r][c]);
			}
		}
	}

	public static Tile[][] getTiles() {
		return tiles;
	}

	public static Tile getTile(int r, int c) {
		return tiles[r][c];
	}

	public static int getWidth() {
		return layout[0].length;
	}

	public static int getHeight() {
		return layout.length;
	}
}
