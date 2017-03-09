package client.mapgen;

import java.util.ArrayList;
import java.util.List;

import data.Data;
import data.LayoutGenData;
import data.MapData;
import util.Util;

public class LayoutGenerator implements LayoutGenData, MapData, Data {
	private static List<Chunk> chunks;

	public static void main(String[] args) {
		int[][] generated = generate(4, 3);
		Util.printIntAsCharArray(generated);
	}

	public static int[][] generate(int xSize, int ySize) {
		initChunks();
		int[][] layout = Util.getNewfilledArray(ySize*CHUNK_SIZE, xSize*CHUNK_SIZE, UNUSED_TYPE);
		/*for (int r = 0;r<ySize;r++) {
			for (int c = 0;c<xSize;c++) {
				Util.appendArrayToArray(c*CHUNK_SIZE, r*CHUNK_SIZE, chunks.get(0).getLayout(), layout);
			}
		}*/
		Chunk[][] toPlace = new Chunk[ySize][xSize];
		final Chunk blankChunk = new Chunk(Util.getNewFilledArray(xSize, ySize, EMPTY_TYPE));
		do {
			for (int r = 0;r<ySize;r++) {
				for (int c = 0;c<xSize;c++) {
					Collections.shuffle(chunks);
					Chunk chunk = blankChunk;
					for (int i = 0;i<chunks.size();i++) {
						chunk = chunks.get(i);make it so it will think its valid if its enitrely blank
						if (r<0||!canSew(chunk, UP, placed[r-1][c], DOWN) continue;//invalid up
						if (r>=ySize||!canSew(chunk, DOWN, placed[r+1][c], UP) continue;//invalid down
						if (c<0||!canSew(chunk, LEFT, placed[r][c-1], RIGHT) continue;//invalid left
						if (c>=xSize||!canSew(chunk, RIGHT, placed[r][c+1], LEFT) continue;//invalid right
						break;//valid
					}
					toPlace[][] = chunk;
				}
			}
		} while (invalidMapLayout(toPlace));
		
		for (int r = 0;r<ySize;r++) {
			for (int c = 0;c<xSize;c++) {
				Util.appendArrayToArray(c*CHUNK_SIZE, r*CHUNK_SIZE, toPlace[r][c], layout);
			}
		}
					    
		Util.replaceAllInArray(layout, UNUSED_TYPE, EMPTY_TYPE);
		return layout;
	}

	private static void initChunks() {
		String[] chunkList = Util.fileToString(CHUNK_PATH+"chunkList").split(";");
		chunks = new ArrayList<>();
		for (String path:chunkList) {
			int[][] layout = Util.parseIntArrayFromFile(CHUNK_PATH+path);
//			chunks.add(new Chunk(layout));//TODO FIXME
			chunks.addAll(getAllChunkPermutations(layout));
		}
	}
	
	private static List<Chunk> getAllChunkPermutations(int[][] layout) {//TODO
		List<Chunk> chunks = new ArrayList<>();
		for (int r = 0;r<4;r++) {
			for (int i = 0;i<2;i++) {
				for (int j = 0;j<2;j++) {
					int tempLayout = Util.copyArray(layout);
					Util.flipArray(tempLayout, i==1, j==1);
					Util.rotateArray(tempLayout, r);
					chunks.add(new Chunk(tempLayout));
				}
			}
		}	
		return chunks;
	}
	
	private static boolean invalidMapLayout(Chunk[][] layout) {
		return false;
	}

	private static boolean canSew(Chunk chunk1, int side1, Chunk chunk2, int side2) {//TODO
		return chunk1.getSeam(side1)==chunk2.getSeam(side2);
	}
}





