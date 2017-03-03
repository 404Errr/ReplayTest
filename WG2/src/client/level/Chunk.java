package client.level;

public class Chunk {
	Tile[][] tiles;
	int[][] layout;
	private int x, y;

	public Chunk(int x, int y, int[][] layout) {
		this.x = x;
		this.y = y;
		this.layout = layout;
		createTiles();
	}

	public void createTiles() {
		tiles = new Tile[layout.length][layout[0].length];
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				tiles[r][c] = new Tile(x, y, c, r, layout[r][c]);
			}
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int[][] getLayout() {
		return layout;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Tile getTile(int x, int y) {
		int c = 0, r = 0;
		if (x<0) c = Level.size;
		if (y<0) r = Level.size;
		return tiles[y+r][x+c];
	}


}
