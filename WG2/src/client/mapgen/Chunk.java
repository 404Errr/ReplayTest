package client.mapgen;

import data.Data;
import data.LayoutGenData;
import util.Util;

public class Chunk implements Data, LayoutGenData {
	private int[][] layout;
	private int[][] seams;
	
	public Chunk(int[][] layout) {
		this.layout = layout;
		setSeams(layout);
		Util.printIntAsCharArray(getSeams(layout));
	}
	
	private void setSeams(int[][] layout) {
		for (int i = 0;i<4;i++) {
			seams[i] = Util.getArraySlice(layout, i);
		}
	}

	public int[][] getSeam(int side) {
		return seams[side];
	}
	
	public int[][] getLayout() {
		return layout;
	}
}
