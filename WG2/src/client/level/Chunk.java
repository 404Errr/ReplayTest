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
}
