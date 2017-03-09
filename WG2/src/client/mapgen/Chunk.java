package client.mapgen;

import data.Data;
import data.LayoutGenData;
import util.Util;

public class Chunk implements Data, LayoutGenData {
	private int[][] layout;
	
	public Chunk(int[][] layout) {
		this.layout = layout;
		Util.printIntAsCharArray(getSeams(layout));
	}
	
	private static int[][] getSeams(int[][] layout) {
		int[][] seams = new int[4][CHUNK_SIZE];
		for (int i = 0;i<4;i++) {
			seams[i] = Util.getArraySlice(layout, i);
		}
		return seams;
	}

	public int[][] getLayout() {
		return layout;
	}
}
