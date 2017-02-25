package client.edit;

import java.util.Stack;

import client.level.Level;
import data.MapData;

public class EditHistory implements MapData {
	private static Stack<int[][]> history;

	public static void init() {
		history = new Stack<>();
	}

	public static void undo() {//undo
		if (!history.isEmpty()) Level.setLayout(history.pop());
	}

	public static void saveState(int[][] layout) {//do
		int[][] oldLayout = new int[layout.length][layout[0].length];
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) {
				oldLayout[y][x] = layout[y][x];
			}
		}
		if (history.size()>EDIT_HISTORY_LIMIT) {
			history.remove(0);
		}
		history.push(oldLayout);
	}
}
