package data;

public interface TileData {
	public static final int SOLID_WALLS = 0, SOLID_PROJECTILES = 1;//don't change

	public static boolean[] getSolid(int type) {
		boolean[] solid = new boolean[2];
		switch (type) {
		case 48://0 floor, don't change
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 49://1 wall, don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = true;
			break;
		case 50://2 no draw, don't change
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 51://3 window, don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 52://4 shield, don't change
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = true;
			break;
		case 53://5 barrier, don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = true;
			break;
		case 54://6
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 55://7
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 56://8
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 57://9
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 65://A
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 66://B
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 67://C
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 68://D
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 69://E
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 70://F
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 71://G
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 72://H
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 73://I
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 74://J
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 75://K
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 76://L
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 77://M
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 78://N
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 79://O
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 80://P
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 81://Q
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 82://R
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 83://S
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 84://T
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 85://U
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 86://V
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 87://W
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 88://X
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 89://Y
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 90://Z
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 97://a
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 98://b
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 99://c
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 100://d
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 101://e
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 102://f
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 103://g
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 104://h
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 105://i
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 106://j
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 107://k
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 108://l
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 109://m
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 110://n
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 111://o
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 112://p
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 113://q
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 114://r
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 115://s
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 116://t
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 117://u
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 118://v
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 119://w
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 120://x
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 121://y
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 122://z
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		}
		return solid;
	}
}
