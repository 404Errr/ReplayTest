package client.mapgen;

import java.util.ArrayList;
import java.util.List;

import client.level.LayoutParser;
import data.LayoutGenData;
import data.MapData;
import util.Util;

public class LayoutGenerator implements LayoutGenData, MapData {
	private static List<MapGenChunk> chunks;

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
			chunks.add(new MapGenChunk(LayoutParser.parseLayout(CHUNK_PATH+path)));
		}
	}

	public static void appendChunk(int x, int y, int[][] chunk, int[][] layout) {
		for (int r = 0;r<chunk.length&&r<layout.length-y;r++) {
			for (int c = 0;c<chunk[r].length&&c<layout[r].length-x;c++) {
				if (r+y>=0&&c+x>=0) layout[r+y][c+x] = chunk[r][c];
			}
		}
	}


}
