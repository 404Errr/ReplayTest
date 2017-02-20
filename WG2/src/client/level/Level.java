package client.level;

import shared.data.MapData;

public class Level implements MapData {
	private static Tile[][] tiles;
	private static int[][] layout;

	public static void init() {//initialize layout
		layout = MapParser.parseMap(MAP);
		if (ADD_EDGE) {
			layout = addEdge(layout);
		}
		tiles = new Tile[layout.length][layout[0].length];
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(r, c, layout[r][c]);
			}
		}
	}

	public static int[][] addEdge(int[][] map) {//takes given map and adds 1's around it
		System.out.println("Map size: "+map.length+","+map[0].length);
		int[][] output = new int[map.length+2][map[0].length+2];//new array size of map +1 on every side
		for (int r = 0;r<output.length;r++) {
			for (int c = 0;c<output[0].length;c++) {
				output[r][c] = '1';//sets all to 1
			}
		}
		for (int r = 0;r<map.length;r++) {
			for (int c = 0;c<map[0].length;c++) {
				output[r+1][c+1] = map[r][c];
			}
		}
		return output;
	}

	public static Tile[][] getTiles() {//get all tiles
		return tiles;
	}

	public static Tile getTile(int r, int c) {//get a specific tile
		return tiles[r][c];
	}

	public static Tile getTile(double r, double c) {
		return tiles[(int)r][(int)c];
	}

	public static int getWidth() {//based on layout, not tiles
		return layout[0].length;
	}

	public static int getHeight() {//based on layout, not tiles
		return layout.length;
	}

}
