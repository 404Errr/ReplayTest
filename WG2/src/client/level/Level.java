package client.level;

import data.MapData;

public class Level implements MapData {
	private static Tile[][] tiles;
	private static int[][] layout = MapData.getMap();

	public static void init() {//initialize layout
		tiles = new Tile[layout.length][layout[0].length];
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(r, c, layout[r][c]);
			}
		}
	}

	public static Tile[][] getTiles() {//get all tiles
		return tiles;
	}

	public static Tile getTile(int r, int c) {//get a specific tile
		return tiles[r][c];
	}

	public static int getWidth() {//based on layout, not tiles
		return layout[0].length;
	}

	public static int getHeight() {//based on layout, not tiles
		return layout.length;
	}
}
