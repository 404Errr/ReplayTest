package client.mapgen;

import data.Data;
import data.LayoutGenData;
import util.Util;
import util.Util.HasWeight;

public class Chunk implements Data, LayoutGenData, HasWeight {
	private int[][] layout;
	private int[][] seams;
	private float weight;

	public Chunk(int[][] layout, float rarity) {
		this.layout = layout;
		this.weight = rarity;
		setSeams(layout);
	}

	private void setSeams(int[][] layout) {
		seams = new int[4][0];
		for (int i = 0;i<4;i++) {
			seams[i] = Util.getArraySlice(layout, i);
		}
	}

	@Override
	public float getWeight() {
		return weight;
	}

	public int[] getSeam(int side) {
		return seams[side];
	}

	public int[][] getLayout() {
		return layout;
	}
}
