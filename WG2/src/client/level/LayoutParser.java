package client.level;

import util.Util;

public class LayoutParser {
	public static int[][] parseLayout(String path) {
		String in = Util.fileToString(path);
		System.out.println(path+" loaded");
		String[] rawRows = in.split(";");
		String[][] raw = new String[rawRows.length][rawRows[0].length()/2+1];
		for (int r = 0;r<rawRows.length;r++) {
			raw[r] = rawRows[r].split(",");
		}
		int[][] layout = new int[raw.length][raw[0].length];
		for (int r = 0;r<raw.length;r++) {
			for (int c = 0;c<raw[0].length;c++) {
				try {
					layout[r][c] = raw[r][c].charAt(0);
				}
				catch (Exception e) {
					System.err.println("error at: "+r+","+c);
					layout[r][c] = -1;
				}
			}
		}
		return layout;
	}
}
