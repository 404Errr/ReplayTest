package client.mapgen;

import data.Data;
import util.Util;

public class Chunk implements Data {
	private int[][] layout;
	private int[] doorSize = new int[4];
	
	public Chunk(int[][] layout, int[] doorSize) {
		this.layout = layout;
		this.doorSize = doorSize;
		Util.printArray(doorSize);
	}
	
	public int[] getSideSize() {
		return doorSize;
	}

	public int[][] getLayout() {
		return layout;
	}
}
