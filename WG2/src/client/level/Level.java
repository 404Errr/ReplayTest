package client.level;

import client.level.pathfinding.NavMap;
import data.MapData;

public class Level implements MapData {
	private static int[][] layout;
	private static Tile[][] tiles;//the level
	private static NavMap navMap;//for pathfinding

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
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(r, c, layout[r][c]);
			}
		}
		navMap = new NavMap(Level.getWidth(), Level.getHeight(), layout);
	}

	public static int[][] addEdge(int[][] map) {//takes given map and adds 1's around it
		System.out.println("Map size: "+map.length+","+map[0].length);
		int[][] output = new int[map.length+2][map[0].length+2];//new array size of map +1 on every side
		for (int r = 0;r<output.length;r++) {
			for (int c = 0;c<output[0].length;c++) {
				if (r==0||c==0||r==output.length-1||c==output[0].length-1) {
					output[r][c] = '1';//sets border to '1' (wall)
				}
				else {
					output[r][c] = map[r-1][c-1];
				}
			}
		}
		return output;
	}

	public static NavMap getNavMap() {
		return navMap;
	}

	public static Tile[][] getTiles() {//get all tiles
		return tiles;
	}

	public static Tile getTile(int c, int r) {//get a specific tile
		return tiles[r][c];
	}

	public static Tile getTile(double c, double r) {
		return tiles[(int)r][(int)c];
	}

	public static int getWidth() {
		return tiles[0].length;
	}

	public static int getHeight() {
		return tiles.length;
	}

	public static int getNavMapHeight() {
		return navMap.height;
	}

	public static int getNavMapWidth() {
		return navMap.width;
	}

	public static int[][] getLayout() {
		return layout;
	}

}

