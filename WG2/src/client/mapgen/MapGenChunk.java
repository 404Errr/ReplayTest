package client.mapgen;

public class MapGenChunk {
	private int[][] layout;

	public MapGenChunk(int[][] layout) {
		this.layout = layout;
	}

	public int[][] getLayout() {
		return layout;
	}
}
