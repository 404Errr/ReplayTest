package data;

import java.awt.Color;

public interface ColorData {
	public static final Color COLOR_PLAYER = Color.BLUE;

	public static final Color COLOR_DEBUG_GREEN = new Color(0,180,0,255);
	public static final Color COLOR_ERROR = Color.MAGENTA;

	public static final Color COLOR_TILE_0 = new Color(120,120,120,255);//grey
	public static final Color COLOR_TILE_1 = new Color(0,0,0,255);//black (wall)
	public static final Color COLOR_TILE_2 = new Color(200,200,200,255);//light grey
	public static final Color COLOR_TILE_3 = new Color(80,80,80,255);//dark grey

	//you're welcome
	public static final Color COLOR_TILE_4 = new Color(0,0,0,255);
	public static final Color COLOR_TILE_5 = new Color(0,0,0,255);
	public static final Color COLOR_TILE_6 = new Color(0,0,0,255);
	public static final Color COLOR_TILE_7 = new Color(0,0,0,255);
	public static final Color COLOR_TILE_8 = new Color(0,0,0,255);
	public static final Color COLOR_TILE_9 = new Color(0,0,0,255);

	public static Color getTileColor(int type) {
		Color color = COLOR_ERROR;//default
		switch (type) {
		case 0:
			color = COLOR_TILE_0;
			break;
		case 1:
			color = COLOR_TILE_1;
			break;
		case 2:
			color = COLOR_TILE_2;
			break;
		case 3:
			color = COLOR_TILE_3;
			break;
		case 4:
			color = COLOR_TILE_4;
			break;
		case 5:
			color = COLOR_TILE_5;
			break;
		case 6:
			color = COLOR_TILE_6;
			break;
		case 7:
			color = COLOR_TILE_7;
			break;
		case 8:
			color = COLOR_TILE_8;
			break;
		case 9:
			color = COLOR_TILE_9;
			break;
		}
		return color;
	}
}
