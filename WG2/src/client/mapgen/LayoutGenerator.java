package client.mapgen;

import java.util.ArrayList;
import java.util.List;

import client.level.LayoutParser;
import data.Data;
import data.LayoutGenData;
import data.MapData;
import util.Util;

public class LayoutGenerator implements LayoutGenData, MapData, Data {
	private static List<Chunk> chunks;

	public static void main(String[] args) {
		int[][] generated = generate(3, 3);
		Util.printIntAsCharArray(generated);
	}

	public static int[][] generate(int xSize, int ySize) {
		initChunks();
		int[][] layout = Util.getNewfilledArray(ySize*CHUNK_SIZE, xSize*CHUNK_SIZE, UNUSED_TYPE);
		for (int r = 0;r<ySize;r++) {
			for (int c = 0;c<xSize;c++) {
				Util.appendArrayToArray(c*CHUNK_SIZE, r*CHUNK_SIZE, chunks.get(0).getLayout(), layout);
			}
		}
		Util.replaceAllInArray(layout, UNUSED_TYPE, EMPTY_TYPE);
		return layout;
	}

	private static void initChunks() {
		String[] chunkList = Util.fileToString(CHUNK_PATH+"chunkList").split(";");
		chunks = new ArrayList<>();
		for (String path:chunkList) {
			int[][] layout = LayoutParser.parseLayout(CHUNK_PATH+path);
//			chunks.add(new Chunk(layout, getChunkDoorSize(layout)));//TODO FIXME
			chunks.addAll(getAllChunkPermutations(layout));
		}
	}
	
	private static List<Chunk> getAllChunkPermutations(int[][] layout) {t//TODO
		return new ArrayList<>();
	}

	private static int[] getChunkDoorSize(int[][] layout) {
		int[] sides = new int[4];
		for (int i = 0;i<4;i++) {
			sides[i] = getDoorSize(layout, i);
		}
		return sides;
	}
	
	public static int getDoorSize(int[][] layout, int side) {
		int[] layoutSide = Util.getArraySlice(layout, side);
		int i = layoutSide.length/2;
		while (i<layoutSide.length&&layoutSide[i]=='0') {
			i++;
		}
		return i-layoutSide.length/2+1;
	}
}





