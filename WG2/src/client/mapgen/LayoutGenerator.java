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
		generate(3, 3);
	}
	
	static {
		initChunks();
	}

	public static int[][] generate(int xSize, int ySize) {
		int[][] layout = new int[ySize*CHUNK_SIZE][xSize*CHUNK_SIZE];
		layout = Util.fillArray(layout, EMPTY_TYPE);

		for (int r = 0;r<ySize;r++) {
			for (int c = 0;c<xSize;c++) {
				appendChunk(c*CHUNK_SIZE, r*CHUNK_SIZE, chunks.get(0).getLayout(), layout);
			}
		}
//		appendChunk(0, 0, chunks.get(0).getLayout(), layout);

		return layout;
	}

	private static void initChunks() {
		String[] chunkList = Util.fileToString(CHUNK_PATH+"chunkList").split(";");
		chunks = new ArrayList<>();
		for (String path:chunkList) {
			System.out.println(path);
			int[][] layout = LayoutParser.parseLayout(CHUNK_PATH+path);
			chunks.add(new Chunk(layout, getChunkSideSize(layout)));
		}
	}

	public static void appendChunk(int x, int y, int[][] chunk, int[][] layout) {
		for (int r = 0;r<chunk.length&&r<layout.length-y;r++) {
			for (int c = 0;c<chunk[r].length&&c<layout[r].length-x;c++) {
				if (r+y>=0&&c+x>=0) layout[r+y][c+x] = chunk[r][c];
			}
		}
	}

	public static int[] getChunkSideSize(int[][] layout) {
		int[] sides = new int[4];
		for (int i = 0;i<4;i++) {
			sides[i] = getSideSize(getSide(layout, i));
			System.out.println(getSideSize(getSide(layout, i)));
		}
		return sides;
	}
	
	public static int[] getSide(int[][] layout, int side) {
		int col = 0;
		switch (side) {
		case UP:
			return layout[0];
		case DOWN:
			return layout[layout.length-1];
		case LEFT:
			col = 0;
			break;
		case RIGHT:
			col = layout[0].length-1;
			break;
		}
		int[] layoutSide = new int[layout.length];
		for (int i = 0;i<layout.length;i++) layoutSide[i] = layout[i][col];
		return layoutSide;
	}
	
	public static int getSideSize(int[] layoutSide) {
		int i = layoutSide.length/2;
		if (layoutSide[i]!='0') return 0;
		while (i<layoutSide.length) {
			System.out.println(i+", "+(char)layoutSide[i]);
			if (layoutSide[i]!='0') {
				break;
			}
			i++;
		}
		return i-layoutSide.length/2+1;
	}
}





