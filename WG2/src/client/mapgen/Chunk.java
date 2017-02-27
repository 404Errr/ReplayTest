package client.mapgen;

public class Chunk {
	private int[][] layout;

	public Chunk(int[][] layout) {
		this.layout = layout;
	}

	public int[][] getLayout() {
		return layout;
	}
}
