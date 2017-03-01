package data;

import java.awt.Color;

public interface ColorData {
	Color COLOR_PLAYER = new Color(0x0000ff);//blue
	Color COLOR_PLAYER_1 = new Color(0xff0000);//red
	Color COLOR_PLAYER_2 = new Color(0x00ff00);//green
	Color COLOR_PLAYER_3 = new Color(0x660088);//purple
//	Color COLOR_PLAYER = new Color(0x23389d);//blue
//	Color COLOR_PLAYER_1 = new Color(0xd4311b);//red
//	Color COLOR_PLAYER_2 = new Color(0x89be17);//green
//	Color COLOR_PLAYER_3 = new Color(0xd357f8);//purple

	Color COLOR_DEBUG_RED = new Color(0x8D0B0D);//red
	Color COLOR_DEBUG_GREEN = new Color(0x00b400);//darker than lime green
	Color COLOR_DEBUG_BLUE = new Color(0x0000C1);//blue
	Color COLOR_ERROR = new Color(0x940e94);//magenta
	Color COLOR_BACKGROUND = new Color(0xf0f0f0);//background color

	Color COLOR_HEALTH_BAR = new Color(0xff0000);//red

	Color COLOR_TILE_0 = new Color(0x909090);//grey (floor)
	Color COLOR_TILE_1 = new Color(0x000000);//black (wall)
	Color COLOR_TILE_2 = null;//no draw (background color)
	Color COLOR_TILE_3 = new Color(0xe0e0e0);//lighter grey (window)
	Color COLOR_TILE_4 = new Color(0xa5a5a5);//light grey (shield)
	Color COLOR_TILE_5 = new Color(0x353535);//dark grey (other solid (barrier))
	Color COLOR_TILE_s = COLOR_TILE_0;//spawn don't change

	//not in use
	Color COLOR_TILE_6 = COLOR_ERROR;
	Color COLOR_TILE_7 = COLOR_ERROR;
	Color COLOR_TILE_8 = COLOR_ERROR;
	Color COLOR_TILE_9 = COLOR_ERROR;

	Color COLOR_TILE_A = COLOR_ERROR;
	Color COLOR_TILE_B = COLOR_ERROR;
	Color COLOR_TILE_C = COLOR_ERROR;
	Color COLOR_TILE_D = COLOR_ERROR;
	Color COLOR_TILE_E = COLOR_ERROR;
	Color COLOR_TILE_F = COLOR_ERROR;
	Color COLOR_TILE_G = COLOR_ERROR;
	Color COLOR_TILE_H = COLOR_ERROR;
	Color COLOR_TILE_I = COLOR_ERROR;
	Color COLOR_TILE_J = COLOR_ERROR;
	Color COLOR_TILE_K = COLOR_ERROR;
	Color COLOR_TILE_L = COLOR_ERROR;
	Color COLOR_TILE_M = COLOR_ERROR;
	Color COLOR_TILE_N = COLOR_ERROR;
	Color COLOR_TILE_O = COLOR_ERROR;
	Color COLOR_TILE_P = COLOR_ERROR;
	Color COLOR_TILE_Q = COLOR_ERROR;
	Color COLOR_TILE_R = COLOR_ERROR;
	Color COLOR_TILE_S = COLOR_ERROR;
	Color COLOR_TILE_T = COLOR_ERROR;
	Color COLOR_TILE_U = COLOR_ERROR;
	Color COLOR_TILE_V = COLOR_ERROR;
	Color COLOR_TILE_W = COLOR_ERROR;
	Color COLOR_TILE_X = COLOR_ERROR;
	Color COLOR_TILE_Y = COLOR_ERROR;
	Color COLOR_TILE_Z = COLOR_ERROR;
	
	Color COLOR_TILE_a = COLOR_ERROR;
	Color COLOR_TILE_b = COLOR_ERROR;
	Color COLOR_TILE_c = COLOR_ERROR;
	Color COLOR_TILE_d = COLOR_ERROR;
	Color COLOR_TILE_e = COLOR_ERROR;
	Color COLOR_TILE_f = COLOR_ERROR;
	Color COLOR_TILE_g = COLOR_ERROR;
	Color COLOR_TILE_h = COLOR_ERROR;
	Color COLOR_TILE_i = COLOR_ERROR;
	Color COLOR_TILE_j = COLOR_ERROR;
	Color COLOR_TILE_k = COLOR_ERROR;
	Color COLOR_TILE_l = COLOR_ERROR;
	Color COLOR_TILE_m = COLOR_ERROR;
	Color COLOR_TILE_n = COLOR_ERROR;
	Color COLOR_TILE_o = COLOR_ERROR;
	Color COLOR_TILE_p = COLOR_ERROR;
	Color COLOR_TILE_q = COLOR_ERROR;
	Color COLOR_TILE_r = COLOR_ERROR;
	Color COLOR_TILE_t = COLOR_ERROR;
	Color COLOR_TILE_u = COLOR_ERROR;
	Color COLOR_TILE_v = COLOR_ERROR;
	Color COLOR_TILE_w = COLOR_ERROR;
	Color COLOR_TILE_x = COLOR_ERROR;
	Color COLOR_TILE_y = COLOR_ERROR;
	Color COLOR_TILE_z = COLOR_ERROR;

	static Color[] COLOR_TILES = {
			null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
			COLOR_TILE_0, COLOR_TILE_1, COLOR_TILE_2, COLOR_TILE_3, COLOR_TILE_4, COLOR_TILE_5, COLOR_TILE_6, COLOR_TILE_7, COLOR_TILE_8, COLOR_TILE_9,  
			null,null,null,null,null,null,null,//TODO test this
			COLOR_TILE_A, COLOR_TILE_B, COLOR_TILE_C, COLOR_TILE_D, COLOR_TILE_E, COLOR_TILE_F, COLOR_TILE_G, COLOR_TILE_H, COLOR_TILE_I, COLOR_TILE_J, COLOR_TILE_K, COLOR_TILE_L, COLOR_TILE_M, COLOR_TILE_N, COLOR_TILE_O, COLOR_TILE_P, COLOR_TILE_Q, COLOR_TILE_R, COLOR_TILE_S, COLOR_TILE_T, COLOR_TILE_U, COLOR_TILE_V, COLOR_TILE_W, COLOR_TILE_X, COLOR_TILE_Y, COLOR_TILE_Z,
			null,null,null,null,null,null,//TODO test this
			COLOR_TILE_a, COLOR_TILE_b, COLOR_TILE_c, COLOR_TILE_d, COLOR_TILE_e, COLOR_TILE_f, COLOR_TILE_g, COLOR_TILE_h, COLOR_TILE_i, COLOR_TILE_j, COLOR_TILE_k, COLOR_TILE_l, COLOR_TILE_m, COLOR_TILE_n, COLOR_TILE_o, COLOR_TILE_p, COLOR_TILE_q, COLOR_TILE_r, COLOR_TILE_s, COLOR_TILE_t, COLOR_TILE_u, COLOR_TILE_v, COLOR_TILE_w, COLOR_TILE_x, COLOR_TILE_y, COLOR_TILE_z,
	};
	
	//'0' 48-'9' 57
	//'A' 65-'Z' 90
	//'a' 97-'z' 122
	
	static Color getTileColor(int type) {//don't touch
		return COLOR_TILES[type];
	}
}
