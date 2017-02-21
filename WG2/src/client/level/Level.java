package client.level;

import shared.data.MapData;

public class Level implements MapData {
	private static Tile[][] tiles;
	private static int[][] layout;

	@SuppressWarnings("unused")
	public static void init() {//initialize layout
		layout = MapParser.parseMap(MAP);
		if (ADD_EDGE) {
			if (!(AUTO_DISABLE_ADD_EDGE&&layout.length*layout[0].length>AUTO_DISABLE_ADD_EDGE_THREASHOLD)) {
				System.out.println("Adding edge...");
				layout = addEdge(layout);
			}
			else {
				System.out.println("Not Adding edge. Map area is: "+layout.length*layout[0].length+"/"+AUTO_DISABLE_ADD_EDGE_THREASHOLD);
			}
		}
		tiles = new Tile[layout.length][layout[0].length];
		for (int c = 0;c<layout.length;c++) {
			for (int r = 0;r<layout[0].length;r++) {
				tiles[c][r] = new Tile(c, r, layout[c][r]);
			}
		}
	}

	public static int[][] addEdge(int[][] map) {//takes given map and adds 1's around it
		System.out.println("Map size: "+map.length+","+map[0].length);
		int[][] output = new int[map.length+2][map[0].length+2];//new array size of map +1 on every side
		for (int c = 0;c<output.length;c++) {
			for (int r = 0;r<output[0].length;r++) {
				output[c][r] = '1';//sets all to 1
			}
		}
		for (int c = 0;c<map.length;c++) {
			for (int r = 0;r<map[0].length;r++) {
				output[c+1][r+1] = map[c][r];
			}
		}
		return output;
	}

	public static Tile[][] getTiles() {//get all tiles
		return tiles;
	}

	public static Tile getTile(int c, int r) {//get a specific tile
		return tiles[c][r];
	}

	public static Tile getTile(double c, double r) {
		return tiles[(int)c][(int)r];
	}

	public static int getWidth() {
		return tiles[0].length;
	}

	public static int getHeight() {
		return tiles.length;
	}

}
