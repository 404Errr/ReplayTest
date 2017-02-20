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
		default:
			solid[SOLID_WALLS] = false;
		}
		return solid;
	}
}
