package client.edit;

import java.awt.BasicStroke;
import java.awt.Point;
import java.util.Stack;

import client.graphics.Camera;
import client.graphics.Renderer;
import client.input.Cursor;
import client.level.Level;
import data.ColorData;
import data.MapData;
import data.TileData;
import util.Util;

public class Edit implements MapData, TileData, ColorData {
	public static boolean editMode = EDIT_MODE, draw;

	private static int startX, startY, endX, endY;

	private static int currentType;
	private static final int[] TYPES = {//'s' appears twice
		'0', '1', '2', '3', '4', '5', 's', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
	};

	public static void toggleEditMode() {
		editMode = !editMode;
	}

	public static void drawSelected() {
		Renderer.getG().setStroke(new BasicStroke(2));
		Renderer.getG().setColor(Util.colorOpacity(COLOR_DEBUG_GREEN, 220));
		if (draw) {
			int sX = Math.max(0, Math.min(startX, endX)), sY = Math.max(0, Math.min(startY, endY)), eX = Math.min(Level.getWidth()-1, Math.max(startX, endX)), eY = Math.min(Level.getHeight()-1, Math.max(startY, endY));
			for (int y = sY;y<=eY;y++) {
				for (int x = sX;x<=eX;x++) {
					Renderer.getG().drawRect(Renderer.gridX(x), Renderer.gridY(y), Camera.getScale(), Camera.getScale());
				}
			}
			Renderer.getG().setColor(COLOR_DEBUG_RED);
			Renderer.getG().drawRect(Renderer.gridX(startX), Renderer.gridY(startY), Camera.getScale(), Camera.getScale());
			Renderer.getG().setColor(COLOR_DEBUG_BLUE);
			Renderer.getG().drawRect(Renderer.gridX(endX), Renderer.gridY(endY), Camera.getScale(), Camera.getScale());
		}
		else {
			Renderer.getG().setColor(COLOR_DEBUG_GREEN);
			Renderer.getG().drawRect(Renderer.gridX(Cursor.getTileX()), Renderer.gridY(Cursor.getTileY()), Camera.getScale(), Camera.getScale());
		}

	}

	public static void changeType(int d) {
		if ((Math.signum(d)<0&&currentType<=0)||(Math.signum(d)>0&&currentType>=TYPES.length-1)) return;
		currentType+=d;
	}

	public static void changeType(int x, int y) {
		Level.setLayoutType(x, y, TYPES[currentType]);
	}

	public static int getType() {
		return TYPES[currentType];
	}

	public static void changeTiles() {
		EditHistory.saveState(Level.getLayout().clone());
		int sX = Math.max(0, Math.min(startX, endX)), sY = Math.max(0, Math.min(startY, endY)), eX = Math.min(Level.getWidth()-1, Math.max(startX, endX)), eY = Math.min(Level.getHeight()-1, Math.max(startY, endY));
		if (startX==endX&&startY==endY) System.out.println((char)TYPES[currentType]+"\t"+sX+","+sY);
		else System.out.println((char)TYPES[currentType]+"\t"+sX+","+sY+" - "+eX+","+eY);
		for (int y = sY;y<=eY;y++) {
			for (int x = sX;x<=eX;x++) {
				changeType(x, y);
			}
		}
	}

	public static void setEnd() {
		endX = Cursor.getTileX();
		endY = Cursor.getTileY();
	}

	public static void setStart() {
		startX = Cursor.getTileX();
		startY = Cursor.getTileY();
	}

	public static void setDraw(boolean value) {
		draw = value;
	}

	public static void printLayout() {
		StringBuilder layout = new StringBuilder();
		for (int y = 0;y<Level.getHeight();y++) {
			for (int x = 0;x<Level.getWidth();x++) {
				layout.append((char)Level.getLayoutType(x, y)+",");
			}
			layout.setCharAt(layout.length()-1, ';');
			layout.append("\n");
		}
		System.out.print("\n\n\n"+layout+"\n\n\n");
	}

	public static void pickType() {
		int x = Cursor.getTileX(), y = Cursor.getTileY();
		if (x<0||y<0||y>=Level.getHeight()||x>=Level.getWidth()) return;
		int type = Level.getLayoutType(x, y);
		System.out.println("Picked: "+(char)type);
		for (int i = 0;i<TYPES.length;i++) {
			if (type==TYPES[i]) currentType = i;
		}
	}

	public static void fill(int sX, int sY, int from, int to, int[][] layout) {
		Stack<Point> points = new Stack<>();
		points.add(new Point(sX, sY));
		while (!points.isEmpty()) {
			Point currentPoint = points.pop();
			int x = currentPoint.x, y = currentPoint.y;
			if (x<0||y<0||y>=layout.length||x>=layout[0].length) continue;
			if (from==layout[y][x]) {
				layout[y][x] = to;
				points.push(new Point(x+1, y));
				points.push(new Point(x-1, y));
				points.push(new Point(x, y+1));
				points.push(new Point(x, y-1));
			}
		}
	}

	public static void floodFill() {
		EditHistory.saveState(Level.getLayout());
		int sX = Cursor.getTileX(), sY = Cursor.getTileY();
		int[][] layout = Level.getLayout().clone();
		if (layout[sY][sX]==getType()) return;
		System.out.println("Fill "+(char)layout[sY][sX]+" --> "+(char)getType());
		fill(sX, sY, layout[sY][sX], getType(), layout);
		for (int x = 0;x<Level.getWidth();x++) {
			for (int y = 0;y<Level.getHeight();y++) {
				if (Level.getLayoutType(x, y)!=layout[y][x]) changeType(x, y);
			}
		}
	}

	/*public static void saveLevel(int[][] layout, int sizeX, int sizeY) throws IOException {//old
		StringBuilder board = new StringBuilder();
		for (int y = 0;y<sizeY;y++) {
			for (int x = 0;x<sizeX;x++) {
				board.append(layout[y][x]+",");
			}
			board.setCharAt(board.length()-1, ';');
			board.append("\n");
		}

		System.out.println(board+"\n\n"+fileName);
		FileWriter fw = new FileWriter(new File(fileName));

		BufferedWriter w = new BufferedWriter(fw);
		w.write(board.toString());
		w.close();
	}*/

}
