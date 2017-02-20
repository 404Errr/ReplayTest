package shared.data;

import java.awt.Color;

public interface ColorData {
	public static final Color COLOR_PLAYER = new Color(0x0000ff);//blue

	public static final Color COLOR_DEBUG_GREEN = new Color(0,180,0,255);//darker than lime green
	public static final Color COLOR_ERROR = new Color(0x940e94);//magenta

	public static final Color COLOR_TILE_0 = new Color(0x909090);//grey (floor)
	public static final Color COLOR_TILE_1 = new Color(0x000000);//black (wall)
	public static final Color COLOR_TILE_2 = new Color(0xa0a0a0);//light grey (shield)
	public static final Color COLOR_TILE_3 = new Color(0xe0e0e0);//lighter grey (window)
	public static final Color COLOR_TILE_4 = new Color(0x353535);//dark grey (other solid (barrier))

	//not in use
	public static final Color COLOR_TILE_5 = COLOR_ERROR;
	public static final Color COLOR_TILE_6 = COLOR_ERROR;
	public static final Color COLOR_TILE_7 = COLOR_ERROR;
	public static final Color COLOR_TILE_8 = COLOR_ERROR;
	public static final Color COLOR_TILE_9 = COLOR_ERROR;

	public static Color getTileColor(int type) {
		switch (type) {
		case 0:
			return COLOR_TILE_0;
		case 1:
			return COLOR_TILE_1;
		case 2:
			return COLOR_TILE_2;
		case 3:
			return COLOR_TILE_3;
		case 4:
			return COLOR_TILE_4;
		case 5:
			return COLOR_TILE_5;
		case 6:
			return COLOR_TILE_6;
		case 7:
			return COLOR_TILE_7;
		case 8:
			return COLOR_TILE_8;
		case 9:
			return COLOR_TILE_9;
		default:
			return COLOR_ERROR;
		}
	}
}
