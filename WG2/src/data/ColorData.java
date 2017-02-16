package data;

import java.awt.Color;

public interface ColorData {
	public static final Color COLOR_PLAYER = Color.BLUE;

	public static final Color COLOR_TILE_0 = Color.LIGHT_GRAY;
	public static final Color COLOR_TILE_1 = Color.BLACK;

	public static Color getTileColor(int type) {
		Color color = Color.BLACK;
		switch (type) {
		case 0:
			color = COLOR_TILE_0;
			break;
		case 1:
			color = COLOR_TILE_1;
			break;
		}
		return color;
	}
}
