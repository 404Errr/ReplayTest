package shared.data;

import java.awt.Color;

public interface ColorData {
	public static final Color COLOR_PLAYER = Color.BLUE;

	public static final Color COLOR_DEBUG_GREEN = new Color(0,180,0,255);
	public static final Color COLOR_ERROR = new Color(0x940e94);

	public static final Color COLOR_TILE_0 = new Color(0x808080);//grey (floor)
	public static final Color COLOR_TILE_1 = new Color(0x000000);//black (wall)
	public static final Color COLOR_TILE_2 = new Color(0xa0a0a0);//light grey (shield)
	public static final Color COLOR_TILE_3 = new Color(0xd0d0d0);//lighter grey (window)
	public static final Color COLOR_TILE_4 = new Color(0x666666);//dark grey (other solid (barrier))

	//not in use
	public static final Color COLOR_TILE_5 = COLOR_ERROR;
	public static final Color COLOR_TILE_6 = COLOR_ERROR;
	public static final Color COLOR_TILE_7 = COLOR_ERROR;
	public static final Color COLOR_TILE_8 = COLOR_ERROR;
	public static final Color COLOR_TILE_9 = COLOR_ERROR;

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
