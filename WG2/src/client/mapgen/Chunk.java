package client.mapgen;

import data.Data;

public class Chunk implements Data {
	private int[][] layout;
	private int[] sideSize = new int[4];
	
	public Chunk(int[][] layout, int[] sideSize) {
		this.layout = layout;
		this.sideSize = sideSize;
	}
	
	public int[] getSideSize() {
		return sideSize;
	}

	public int[][] getLayout() {
		return layout;
	}
}
