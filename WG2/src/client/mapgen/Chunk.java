package client.mapgen;

import data.Data;
import data.LayoutGenData;
import util.Util;

public class Chunk implements Data, LayoutGenData {
	private int[][] layout;
	private int[][] seams;
	private float rarity;

	public Chunk(int[][] layout, float rarity) {
		this.layout = layout;
		this.rarity = rarity;
		setSeams(layout);
	}

	private void setSeams(int[][] layout) {
		seams = new int[4][0];
		for (int i = 0;i<4;i++) {
			seams[i] = Util.getArraySlice(layout, i);
		}
	}

	public float getRarity() {
		return rarity;
	}

	public int[] getSeam(int side) {
		return seams[side];
	}

	public int[][] getLayout() {
		return layout;
	}
}
