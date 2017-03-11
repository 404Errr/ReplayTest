package client.mapgen;

import java.util.ArrayList;
import java.util.List;

import data.Data;
import data.LayoutGenData;
import data.MapData;
import util.Util;
import util.Util.HasWeight;

public class LayoutGenerator implements LayoutGenData, MapData, Data {
	private static List<HasWeight> chunks;

	public static void main(String[] args) {
		int[][] generated = generate(6, 4);
		System.out.println();
		Util.printIntAsCharArray(generated);
	}

	public static int[][] generate(int xSize, int ySize) {
		initChunks();
		long startTime = System.currentTimeMillis();

		Chunk[][] toPlace;

		final Chunk emptyChunk = new Chunk(Util.getNewfilledArray(CHUNK_SIZE, CHUNK_SIZE, EMPTY_TYPE), 1.0f);

		int attemptCount = 0;

		do {
			toPlace = new Chunk[ySize][xSize];
			for (int r = 0;r<toPlace.length;r++) {
				for (int c = 0;c<toPlace[0].length;c++) {
					Util.weighedShuffle(chunks, 0.4f);
//					Util.weighedShuffle(chunks, 0.0f);
					Chunk chunk = emptyChunk, tempChunk;
					for (int i = 0;i<chunks.size();i++) {
						tempChunk = (Chunk)chunks.get(i);

						if (r<=0&&!canSew(tempChunk, UP, emptyChunk, DOWN)) continue;//up bound
						if (r>=ySize-1&&!canSew(tempChunk, DOWN, emptyChunk, UP)) continue;//down bound
						if (c<=0&&!canSew(tempChunk, LEFT, emptyChunk, RIGHT)) continue;//left bound
						if (c>=xSize-1&&!canSew(tempChunk, RIGHT, emptyChunk, LEFT)) continue;//right bound

						if (r>0&&toPlace[r-1][c]!=null&&!canSew(tempChunk, UP, toPlace[r-1][c], DOWN)) continue;//invalid up
						if (r<ySize-1&&toPlace[r+1][c]!=null&&!canSew(tempChunk, DOWN, toPlace[r+1][c], UP)) continue;//invalid down
						if (c>0&&toPlace[r][c-1]!=null&&!canSew(tempChunk, LEFT, toPlace[r][c-1], RIGHT)) continue;//invalid left
						if (c<xSize-1&&toPlace[r][c+1]!=null&&!canSew(tempChunk, RIGHT, toPlace[r][c+1], LEFT)) continue;//invalid right

						chunk = tempChunk;
						break;//valid
					}
					toPlace[r][c] = chunk;
				}
			}
			attemptCount++;
		} while (invalidMapLayout(toPlace));

		System.out.println("\nAttemps: "+attemptCount);
		System.out.println("Time taken: "+(System.currentTimeMillis()-startTime)/1000f);

		int[][] layout = Util.getNewfilledArray(xSize*CHUNK_SIZE, ySize*CHUNK_SIZE, UNUSED_TYPE);
		for (int r = 0;r<ySize;r++) {
			for (int c = 0;c<xSize;c++) {
				Util.appendArrayToArray(c*CHUNK_SIZE, r*CHUNK_SIZE, toPlace[r][c].getLayout(), layout);
			}
		}
		Util.replaceAllInArray(layout, UNUSED_TYPE, EMPTY_TYPE);
		return layout;
	}

	private static void initChunks() {
		String[] chunkList = Util.fileToString(CHUNK_PATH+"chunkList").split(";");
		chunks = new ArrayList<>();
		for (String chunkPath:chunkList) {
			if (chunkPath.startsWith("//")) {
				continue;
			}
			String[] full = chunkPath.split(",");
			int[][] layout = Util.parseIntArrayFromFile(CHUNK_PATH+full[0]);
			List<int[][]> orientations = Util.getAllArrayOrientations(layout);
			float rarity = Float.parseFloat(full[1])/orientations.size();
			System.out.println("number of orientations: "+orientations.size()+"\tRarity: "+rarity);
			for (int[][] chunkLayout:orientations) {
				chunks.add(new Chunk(chunkLayout, rarity));
			}
		}
		System.out.println("\nTotal number of chunks: "+chunks.size());
	}

	private static boolean invalidMapLayout(Chunk[][] chunkLayout) {
		int[][] layout = Util.getNewfilledArray(chunkLayout[0].length*CHUNK_SIZE, chunkLayout.length*CHUNK_SIZE, UNUSED_TYPE);
		for (int r = 0;r<chunkLayout.length;r++) {
			for (int c = 0;c<chunkLayout[0].length;c++) {
				Util.appendArrayToArray(c*CHUNK_SIZE, r*CHUNK_SIZE, chunkLayout[r][c].getLayout(), layout);
			}
		}
		if (!Util.continuousCheck(layout, FLOOR_TYPE)) return true;
		return false;
	}

	private static boolean canSew(Chunk chunk1, int side1, Chunk chunk2, int side2) {
		return Util.equalArrays(chunk1.getSeam(side1), chunk2.getSeam(side2));
	}
}





