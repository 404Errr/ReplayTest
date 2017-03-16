package client.mapgen;

import java.util.ArrayList;
import java.util.List;

import data.GameData;
import data.LayoutGenData;
import data.MapData;
import util.Util;
import util.Util.HasWeight;

public class LayoutGenerator implements LayoutGenData, MapData, GameData {
	private static List<HasWeight> chunks;//chunk list
	private static int attemptCount = 0, leakCount = 0, notContinuousCount = 0;
	private static int chunkSize;

	private static float shuffledness = SHUFFLEDNESS;
	private static final int UNUSED_TYPE = '&';//don't change to something in use

	public static void main(String[] args) {
		int[][] generated = generate(50, 50);
		System.out.println();
		Util.printAsCharArray(generated);
	}

	public static int[][] generate(int xSize, int ySize) {
		if (chunks==null) initChunks();//initialize chunk list

		long startTime = System.currentTimeMillis(), lastTime = startTime;

		System.out.println("Generating "+xSize+"x"+ySize+"...\n");

		final Chunk emptyChunk = new Chunk(Util.getNewfilledArray(chunkSize, chunkSize, EMPTY_TYPE), -1);

		Chunk[][] chunkLayout;

		do {
			chunkLayout = new Chunk[ySize][xSize];//reset layout
			for (int r = 0;r<chunkLayout.length;r++) {
				for (int c = 0;c<chunkLayout[0].length;c++) {

					Util.weighedShuffle(chunks, shuffledness);//(re)shuffle the list of chunks

					Chunk chunk, chunkToPlace = emptyChunk;
					for (int i = 0;i<chunks.size();i++) {//for every chunk in the chunk list
						chunk = (Chunk)chunks.get(i);

						if (r<=0&&!canPlaceAdjacent(chunk, UP, emptyChunk, DOWN)) continue;//up out of bounds
						if (r>0&&chunkLayout[r-1][c]!=null&&!canPlaceAdjacent(chunk, UP, chunkLayout[r-1][c], DOWN)) continue;//invalid up

						if (r>=ySize-1&&!canPlaceAdjacent(chunk, DOWN, emptyChunk, UP)) continue;//down out of bounds
						if (r<ySize-1&&chunkLayout[r+1][c]!=null&&!canPlaceAdjacent(chunk, DOWN, chunkLayout[r+1][c], UP)) continue;//invalid down

						if (c<=0&&!canPlaceAdjacent(chunk, LEFT, emptyChunk, RIGHT)) continue;//left out of bounds
						if (c>0&&chunkLayout[r][c-1]!=null&&!canPlaceAdjacent(chunk, LEFT, chunkLayout[r][c-1], RIGHT)) continue;//invalid left

						if (c>=xSize-1&&!canPlaceAdjacent(chunk, RIGHT, emptyChunk, LEFT)) continue;//right out of bounds
						if (c<xSize-1&&chunkLayout[r][c+1]!=null&&!canPlaceAdjacent(chunk, RIGHT, chunkLayout[r][c+1], LEFT)) continue;//invalid right

						chunkToPlace = chunk;
						break;//valid, stops looking
					}
					chunkLayout[r][c] = chunkToPlace;//add the chunk to the layout
				}
			}
			if (System.currentTimeMillis()-lastTime>2000) {//debug info every ~2 seconds
				System.out.println("Time taken: "+(System.currentTimeMillis()-startTime)/1000f+"\tAttemps: "+attemptCount+"\tLeaks: "+leakCount+"\tNot-continuous: "+notContinuousCount);
				lastTime = System.currentTimeMillis();
			}
			attemptCount++;
		} while (!validMapLayout(chunkLayout));//checks if it created a valid layout

		int[][] layout = Util.getNewfilledArray(xSize*chunkSize, ySize*chunkSize, UNUSED_TYPE);//fill new array with temporary type
		for (int r = 0;r<ySize;r++) {
			for (int c = 0;c<xSize;c++) {
				Util.appendArrayToArray(c*chunkSize, r*chunkSize, chunkLayout[r][c].getLayout(), layout);//place all the chunks
			}
		}
		Util.replaceAllInArray(layout, UNUSED_TYPE, EMPTY_TYPE);//replace the temporary type with the empty type

		System.out.print("\nTotal attemps: "+attemptCount+"\tTotal leaks: "+leakCount+"\tTotal not-continuous: "+notContinuousCount+"\t\tTime taken: "+(System.currentTimeMillis()-startTime)/1000f+"\nTypes:\t");

		List<int[]> counts = Util.countValuesInArray(layout, 0, 256);
		for (int[] typeAndCount:counts) System.out.print((char)typeAndCount[0]+":"+typeAndCount[1]+"\t");
		System.out.println();

		return layout;
	}

	private static void initChunks() {
		long startTime = System.currentTimeMillis();
		String[] chunkList = Util.fileToString(CHUNK_LIST_PATH+"/"+CHUNK_LIST_NAME+"/").split(";");
		chunks = new ArrayList<>();
		chunkSize = -1;
		for (String chunkPath:chunkList) {
			if (chunkPath.startsWith("//")) {//comment check
				continue;
			}
			String[] full = chunkPath.split(",");
			int[][] layout = Util.parseIntArrayFromFile(CHUNK_LIST_PATH+full[0]);
			if (chunkSize==-1) {
				chunkSize = layout.length;
			}
			List<int[][]> orientations = Util.getAllArrayOrientations(layout);
			float weight = 1f;
			if (full.length>1) weight = Float.parseFloat(full[1])/orientations.size();
			System.out.println("Orientations: "+orientations.size()+"\t\tWeight (each): "+weight);
			for (int[][] chunkLayout:orientations) {
				chunks.add(new Chunk(chunkLayout, weight));
			}
		}
		System.out.println("\nTotal number of chunks: "+chunks.size()+"\tTime taken: "+(System.currentTimeMillis()-startTime)/1000f+"\n");
	}

	private static boolean validMapLayout(Chunk[][] chunkLayout) {
		int[][] layout = Util.getNewfilledArray(chunkLayout[0].length*chunkSize, chunkLayout.length*chunkSize, UNUSED_TYPE);
		for (int r = 0;r<chunkLayout.length;r++) {
			for (int c = 0;c<chunkLayout[0].length;c++) {
				Util.appendArrayToArray(c*chunkSize, r*chunkSize, chunkLayout[r][c].getLayout(), layout);
			}
		}
		/*if (!Util.continuousCheck(layout, FLOOR_TYPE)) {
			notContinuousCount++;
			return false;
		}*/
		/*if (Util.arrayAdjacentValuesCheck(layout, FLOOR_TYPE, EMPTY_TYPE)) {
			leakCount++;
			return false;
		}*/
		return true;
	}


	private static boolean canPlaceAdjacent(Chunk chunk1, int side1, Chunk chunk2, int side2) {
		return Util.equalArrays(chunk1.getSeam(side1), chunk2.getSeam(side2));
	}
}





