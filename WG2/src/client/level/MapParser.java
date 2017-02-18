package client.level;

import util.Util;

public class MapParser {
	public static final String PATH = "src/maps/";

	public static int[][] parseMap(String mapName) {
		String in = Util.fileToString(PATH+mapName);
		System.out.println("Map at: "+PATH+mapName+" loaded: "+in);
		String[] rawRows = in.split(";");
		String[][] raw = new String[rawRows.length][rawRows[0].length()/2+1];
		for (int r = 0;r<rawRows.length;r++) {
			raw[r] = rawRows[r].split(",");
		}
		int[][] layout = new int[raw.length][raw[0].length];
		for (int r = 0;r<raw.length;r++) {
			for (int c = 0;c<raw[0].length;c++) {
				try {
					layout[r][c] = Integer.parseInt(raw[r][c]);
				}
				catch (Exception e) {
					System.err.println("Map is not rectanglular; error at: "+r+","+c);
					layout[r][c] = -1;
				}
			}
		}
		return layout;
	}


}
