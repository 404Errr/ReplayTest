package client.mapgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import data.Data;
import data.LayoutGenData;
import data.MapData;
import util.Util;

public class LayoutGenerator implements LayoutGenData, MapData, Data {
	private static List<Chunk> chunks;

	public static void main(String[] args) {
		int[][] generated = generate(5, 3);
		System.out.println("\n");
		Util.printIntAsCharArray(generated);
	}

	public static int[][] generate(int xSize, int ySize) {
		initChunks();

		int[][] layout = Util.getNewfilledArray(xSize*CHUNK_SIZE, ySize*CHUNK_SIZE, UNUSED_TYPE);

		Chunk[][] toPlace;

		final Chunk emptyChunk = new Chunk(Util.getNewfilledArray(CHUNK_SIZE, CHUNK_SIZE, EMPTY_TYPE));

		do {
			toPlace = new Chunk[ySize][xSize];
			for (int r = 0;r<toPlace.length;r++) {
				for (int c = 0;c<toPlace[0].length;c++) {
					Collections.shuffle(chunks);
					Chunk chunk = emptyChunk, tempChunk;
					for (int i = 0;i<chunks.size();i++) {
						tempChunk = chunks.get(i);

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
		} while (invalidMapLayout(toPlace));//TODO

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
		for (String path:chunkList) {
			int[][] layout = Util.parseIntArrayFromFile(CHUNK_PATH+path);
			List<int[][]> allArrayPermutations = getAllArrayPermutations(layout);
			System.out.println("number of layouts: "+allArrayPermutations.size());
			for (int[][] chunkLayout:allArrayPermutations) chunks.add(new Chunk(chunkLayout));
		}
		System.out.println("\nNumber of chunks: "+chunks.size());
	}

	private static List<int[][]> getAllArrayPermutations(int[][] layout) {
		List<int[][]> arrays = new ArrayList<>();
		boolean h = false, v = false;
		for (int p = 0;p<16;p++) {
			int[][] tempArray = Util.copyArray(layout);
			tempArray = Util.flipArray(tempArray, (v)?h=!h:h, v=!v);
			tempArray = Util.rotateArray(tempArray, p/4);
			arrays.add(tempArray);
		}
		for (int i = 0;i<arrays.size()-1;i++) {
			for (int j = i+1;j<arrays.size();) {
				if (Util.equalArrays(arrays.get(i), arrays.get(j))) {
					arrays.remove(j);
				}
				else j++;
			}
		}
		return arrays;
	}

	private static boolean invalidMapLayout(Chunk[][] chunkLayout) {
		int[][] layout = Util.getNewfilledArray(chunkLayout[0].length*CHUNK_SIZE, chunkLayout.length*CHUNK_SIZE, UNUSED_TYPE);
		for (int r = 0;r<chunkLayout.length;r++) {
			for (int c = 0;c<chunkLayout[0].length;c++) {
				Util.appendArrayToArray(c*CHUNK_SIZE, r*CHUNK_SIZE, chunkLayout[r][c].getLayout(), layout);
			}
		}
		final int fillTypeTo = '!';
		int iX = -1, iY = -1;
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) {
				if (layout[y][x]==FLOOR_TYPE) {
					iX = x;
					iY = y;
				}
			}
		}
		if (iX==-1||iY==-1) return true;
		Util.floodFill(iX, iY, FLOOR_TYPE, fillTypeTo, layout);
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) {
				if (layout[y][x]==FLOOR_TYPE) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean canSew(Chunk chunk1, int side1, Chunk chunk2, int side2) {
//		return Util.equalArrays(chunk1.getSeam(side1), chunk2.getSeam(side2));
		int[] seam1 = chunk1.getSeam(side1), seam2 = chunk2.getSeam(side2);
		if (seam1.length!=seam2.length) return false;
		for (int i = 0;i<seam1.length;i++) {
			if ((seam1[i]==FLOOR_TYPE||seam2[i]==FLOOR_TYPE)&&seam1[i]!=seam2[i]) return false;
		}
		return true;
	}
}





