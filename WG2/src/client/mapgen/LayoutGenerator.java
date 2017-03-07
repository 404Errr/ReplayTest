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
		
		
		return layout;
	}

	private static void initChunks() {
		String[] chunkList = Util.fileToString(CHUNK_PATH+"chunkList").split(";");
		chunks = new ArrayList<>();
		for (String path:chunkList) {
			int[][] layout = LayoutParser.parseLayout(CHUNK_PATH+path);
			chunks.add(new Chunk(layout, getChunkDoorSize(layout)));
		}
	}

	public static void appendChunk(int x, int y, int[][] chunk, int[][] layout) {
		for (int r = 0;r<chunk.length&&r<layout.length-y;r++) {
			for (int c = 0;c<chunk[r].length&&c<layout[r].length-x;c++) {
				if (r+y>=0&&c+x>=0) layout[r+y][c+x] = chunk[r][c];
			}
		}
	}

	public static int[] getChunkDoorSize(int[][] layout) {
		int[] sides = new int[4];
		for (int i = 0;i<4;i++) {
			sides[i] = getDoorSize(getSide(layout, i));
		}
		return sides;
	}
	
	public static int[] getSide(int[][] layout, int side) {
		int col = 0;
		switch (side) {
		case RIGHT:
			col = layout[0].length-1;
			break;
		case DOWN:
			return layout[layout.length-1];
		case LEFT:
			col = 0;
			break;
		case UP:
			return layout[0];
		}
		int[] layoutSide = new int[layout.length];//for vertical sides (left and right)
		for (int i = 0;i<layout.length;i++) layoutSide[i] = layout[i][col];
		return layoutSide;
	}
	
	public static int getDoorSize(int[] layoutSide) {
		int i = layoutSide.length/2;
		while (i<layoutSide.length&&layoutSide[i]=='0') {
			i++;
		}
		return i-layoutSide.length/2+1;
	}
}





