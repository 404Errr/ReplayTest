package shared.data;

public interface TileData {
	public static boolean getSolid(int type) {
		boolean solid;
		switch (type) {
		case 0://floor don't change
			solid = false;
			break;
		case 1://wall don't change
			solid = true;
			break;
		case 2://shield don't change
			solid = false;
			break;
		case 3://window don't change
			solid = true;
			break;
		case 4://barrier don't change
			solid = true;
			break;
		case 5:
			solid = false;
			break;
		case 6:
			solid = false;
			break;
		case 7:
			solid = false;
			break;
		case 8:
			solid = false;
			break;
		case 9:
			solid = false;
			break;
		default:
			solid = false;
		}
		return solid;
	}
}
