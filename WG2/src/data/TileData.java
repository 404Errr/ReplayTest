package data;

public interface TileData {
	static final int SOLID_WALLS = 0, SOLID_PROJECTILES = 1;//don't change

	static boolean[] getSolid(int type) {
		boolean[] solid = new boolean[2];
		switch (type) {
		case 48://0 floor, don't change
			break;
		case 49://1 wall, don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = true;
			break;
		case 50://2 no draw, don't change
			break;
		case 51://3 window, don't change
			solid[SOLID_WALLS] = true;
			break;
		case 52://4 shield, don't change
			solid[SOLID_PROJECTILES] = true;
			break;
		case 53://5 barrier, don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = true;
			break;
		case 54://6
			break;
		case 55://7
			break;
		case 56://8
			break;
		case 57://9
			break;
		case 65://A
			break;
		case 66://B
			break;
		case 67://C
			break;
		case 68://D
			break;
		case 69://E
			break;
		case 70://F
			break;
		case 71://G
			break;
		case 72://H
			break;
		case 73://I
			break;
		case 74://J
			break;
		case 75://K
			break;
		case 76://L
			break;
		case 77://M
			break;
		case 78://N
			break;
		case 79://O
			break;
		case 80://P
			break;
		case 81://Q
			break;
		case 82://R
			break;
		case 83://S
			break;
		case 84://T
			break;
		case 85://U
			break;
		case 86://V
			break;
		case 87://W
			break;
		case 88://X
			break;
		case 89://Y
			break;
		case 90://Z
			break;
		case 97://a
			break;
		case 98://b
			break;
		case 99://c
			break;
		case 100://d
			break;
		case 101://e
			break;
		case 102://f
			break;
		case 103://g
			break;
		case 104://h
			break;
		case 105://i
			break;
		case 106://j
			break;
		case 107://k
			break;
		case 108://l
			break;
		case 109://m
			break;
		case 110://n
			break;
		case 111://o
			break;
		case 112://p
			break;
		case 113://q
			break;
		case 114://r
			break;
		case 115://s
			break;
		case 116://t
			break;
		case 117://u
			break;
		case 118://v
			break;
		case 119://w
			break;
		case 120://x
			break;
		case 121://y
			break;
		case 122://z
			break;
		}
		return solid;
	}
}
