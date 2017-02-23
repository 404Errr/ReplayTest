package mapgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewOldLevelGen {
	static long SEED;

	static int[][] level;
	static int totalAttempts, sizeX, sizeY,

	count = 1,
	size = 7,

	genSizeX = size,
	genSizeY = size;

	static int failThreashold = 20;
	static NewOldSegment[][] board;
	static Random rand;
	static List<NewOldSegment> segments = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 0;i<count;i++) {
			SEED = (new Random()).nextLong();
			generateMap();
		}
	}

	public static int[][] generateMap() {
		if (segments.size()==0) {
			NewOldSegment.initSegments();
			initSegments();
		}
		if (SEED==0) SEED = (new Random()).nextLong();
		rand = new Random(SEED);
		sizeY = 15*genSizeY;
		sizeX = 15*genSizeX;
		int[][] layout = new int[sizeY][sizeX];
		System.out.print("Starting Map Gen...\nSeed: "+SEED+"\nGenerator Size: "+genSizeX+","+genSizeY+" ("+sizeX+"x"+sizeY+")\n");
		long initTime = System.currentTimeMillis();
		do {
			layout = genMap();
			if (totalAttempts%150==0) System.out.print("Time: "+((System.currentTimeMillis()-initTime)/1000f)+" s\t\tTried "+totalAttempts+" times\n");
		} while (layout==null);
		try {
			saveLevel(layout, sizeX, sizeY);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.print("Time: "+((System.currentTimeMillis()-initTime)/1000f)+"\nGenerated.\nTries: "+totalAttempts+"\n\n");//+"\nSpawns: "+spawns+"\nLoot: "+loot+"\n\n");
		for (int y = 0;y<sizeY;y++) for (int x = 0;x<sizeX;x++) layout[y][x] = (layout[y][x]+"").charAt(0);
		return layout;
	}

	static int[][] genMap() {
		level = new int[sizeY][sizeX];
		board = new NewOldSegment[genSizeY][genSizeX];
		for (int y = 0;y<genSizeY;y++) for (int x = 0;x<genSizeX;x++) {
			boolean valid = false;
			int attempts = 0;
			NewOldSegment segment = null;
			while (!valid) {
				segment = segments.get(rand.nextInt(segments.size()));
				attempts++;
				if (attempts>failThreashold*segments.size()) {
					totalAttempts++;
					return null;
				}
				if (checkValid(x, y, segment)) valid = true;
			}
			board[y][x] = segment;
		}
		for (int y = 0;y<genSizeY;y++) for (int x = 0;x<genSizeX;x++) addSegment(x, y, board[y][x]);
		return level;
	}

	static void initSegments() {
		for (int i = 0;i<NewOldSegment.segments.size();i++) {
			for (int r = 0;r<4;r++) {
				for (int m = 0;m<3;m++) {
					segments.add(new NewOldSegment(i, r, m));
				}
			}
		}
		System.out.println("Total segments: "+segments.size());
	}

	static boolean checkValid(int x, int y, NewOldSegment segment) {
		boolean valid = true;
		if ((x==0&&segment.left!=0)||(y==0&&segment.up!=0)||(x==genSizeX-1&&segment.right!=0)||(y==genSizeY-1&&segment.down!=0)) valid = false;
		if (y!=0&&((board[y-1][x].down!=segment.up)||(!board[y-1][x].continuous&&!segment.continuous))) valid = false;
		if (x!=0&&((board[y][x-1].right!=segment.left)||(!board[y][x-1].continuous&&!segment.continuous))) valid = false;
		return valid;
	}

	static void addSegment(int locationX, int locationY, NewOldSegment segment) {
		board[locationY][locationX] = segment;
		locationX*=15;locationY*=15;
		for (int y = 0;y<segment.sizeY;y++) for (int x = 0;x<segment.sizeX;x++) level[locationY+y][locationX+x] = segment.getSpot(y, x);
	}

	public static void saveLevel(int[][] layout, int sizeX, int sizeY) throws IOException {
		StringBuilder board = new StringBuilder();
		for (int y = 0;y<sizeY;y++) {
			for (int x = 0;x<sizeX;x++) {
				board.append(layout[y][x]+",");
			}
			board.setCharAt(board.length()-1, ';');
			board.append("\n");
		}
		String fileName = "src/Maps/generated/Map 2017 "+sizeX+"x"+sizeY+" ("+sizeX/15+"x"+sizeY/15+") "+System.currentTimeMillis()+".txt";
		System.out.println(board+"\n\n"+fileName);
		FileWriter fw = new FileWriter(new File(fileName));

		BufferedWriter w = new BufferedWriter(fw);
		w.write(board.toString());
		w.close();
	}

}