package shared.data;

public interface TileData {
	public static final int SOLID_WALLS = 0, SOLID_PROJECTILES = 1;

	public static boolean[] getSolid(int type) {
		boolean[] solid = new boolean[2];
		switch (type) {
		case '0'://floor don't change
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case '1'://wall don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = true;
			break;
		case '2'://shield don't change
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = true;
			break;
		case '3'://window don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = false;
			break;
		case '4'://barrier don't change
			solid[SOLID_WALLS] = true;
			solid[SOLID_PROJECTILES] = true;
			break;
		case '5':
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case '6':
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case '7':
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case '8':
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case '9':
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 65:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 66:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 67:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 68:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 69:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 70:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 71:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 72:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 73:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 74:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 75:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 76:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 77:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 78:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 79:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 80:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 81:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 82:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 83:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 84:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 85:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 86:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 87:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 88:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 89:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 90:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 97:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 98:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 99:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 100:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 101:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 102:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 103:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 104:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 105:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 106:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 107:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 108:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 109:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 110:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 111:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 112:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 113:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 114:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 115:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 116:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 117:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 118:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 119:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 120:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 121:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;
		case 122:
			solid[SOLID_WALLS] = false;
			solid[SOLID_PROJECTILES] = false;
			break;

		}
		return solid;
	}
}
