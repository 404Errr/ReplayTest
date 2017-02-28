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
	Color COLOR_DEBUG_GREEN = new Color(0,180,0,255);//darker than lime green
	Color COLOR_DEBUG_BLUE = new Color(0x0000C1);//blue
	Color COLOR_ERROR = new Color(0x940e94);//magenta
	Color COLOR_BACKGROUND = new Color(0xf0f0f0);//backround color

	Color COLOR_HEALTH_BAR = new Color(0xff0000);//red

	Color COLOR_TILE_0 = new Color(0x909090);//grey (floor)
	Color COLOR_TILE_1 = new Color(0x000000);//black (wall)
	Color COLOR_TILE_2 = null;//no draw (backround color)
	Color COLOR_TILE_3 = new Color(0xe0e0e0);//lighter grey (window)
	Color COLOR_TILE_4 = new Color(0xa5a5a5);//light grey (shield)
	Color COLOR_TILE_5 = new Color(0x353535);//dark grey (other solid (barrier))

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
//	Color COLOR_TILE_s = COLOR_ERROR;
	Color COLOR_TILE_t = COLOR_ERROR;
	Color COLOR_TILE_u = COLOR_ERROR;
	Color COLOR_TILE_v = COLOR_ERROR;
	Color COLOR_TILE_w = COLOR_ERROR;
	Color COLOR_TILE_x = COLOR_ERROR;
	Color COLOR_TILE_y = COLOR_ERROR;
	Color COLOR_TILE_z = COLOR_ERROR;

	static Color getTileColor(int type) {//dont touch
		switch (type) {//preferably in order of frequency
		case '0':
		case 's'://spawn
			return COLOR_TILE_0;
		case '2':
			return COLOR_TILE_2;
		case '1':
			return COLOR_TILE_1;
		case '5':
			return COLOR_TILE_5;
		case '4':
			return COLOR_TILE_4;
		case '3':
			return COLOR_TILE_3;
		case '6':
			return COLOR_TILE_6;
		case '7':
			return COLOR_TILE_7;
		case '8':
			return COLOR_TILE_8;
		case '9':
			return COLOR_TILE_9;
		case 65:
			return COLOR_TILE_A;
		case 66:
			return COLOR_TILE_B;
		case 67:
			return COLOR_TILE_C;
		case 68:
			return COLOR_TILE_D;
		case 69:
			return COLOR_TILE_E;
		case 70:
			return COLOR_TILE_F;
		case 71:
			return COLOR_TILE_G;
		case 72:
			return COLOR_TILE_H;
		case 73:
			return COLOR_TILE_I;
		case 74:
			return COLOR_TILE_J;
		case 75:
			return COLOR_TILE_K;
		case 76:
			return COLOR_TILE_L;
		case 77:
			return COLOR_TILE_M;
		case 78:
			return COLOR_TILE_N;
		case 79:
			return COLOR_TILE_O;
		case 80:
			return COLOR_TILE_P;
		case 81:
			return COLOR_TILE_Q;
		case 82:
			return COLOR_TILE_R;
		case 83:
			return COLOR_TILE_S;
		case 84:
			return COLOR_TILE_T;
		case 85:
			return COLOR_TILE_U;
		case 86:
			return COLOR_TILE_V;
		case 87:
			return COLOR_TILE_W;
		case 88:
			return COLOR_TILE_X;
		case 89:
			return COLOR_TILE_Y;
		case 90:
			return COLOR_TILE_Z;
		case 91:
			return COLOR_TILE_a;
		case 98:
			return COLOR_TILE_b;
		case 99:
			return COLOR_TILE_c;
		case 100:
			return COLOR_TILE_d;
		case 101:
			return COLOR_TILE_e;
		case 102:
			return COLOR_TILE_f;
		case 103:
			return COLOR_TILE_g;
		case 104:
			return COLOR_TILE_h;
		case 105:
			return COLOR_TILE_i;
		case 106:
			return COLOR_TILE_j;
		case 107:
			return COLOR_TILE_k;
		case 108:
			return COLOR_TILE_l;
		case 109:
			return COLOR_TILE_m;
		case 110:
			return COLOR_TILE_n;
		case 111:
			return COLOR_TILE_o;
		case 112:
			return COLOR_TILE_p;
		case 113:
			return COLOR_TILE_q;
		case 114:
			return COLOR_TILE_r;
//		case 115:
//			return COLOR_TILE_s;
		case 116:
			return COLOR_TILE_t;
		case 117:
			return COLOR_TILE_u;
		case 118:
			return COLOR_TILE_v;
		case 119:
			return COLOR_TILE_w;
		case 120:
			return COLOR_TILE_x;
		case 121:
			return COLOR_TILE_y;
		case 122:
			return COLOR_TILE_z;
		}
		return COLOR_ERROR;
	}
}
