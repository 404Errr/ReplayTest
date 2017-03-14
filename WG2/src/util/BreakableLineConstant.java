package util;

import data.AIData;
import data.Data;
import data.PlayerData;
import data.TileData;

public abstract class BreakableLineConstant extends BreakableLine implements Data, AIData, TileData, PlayerData {

	public BreakableLineConstant(boolean[][] breaks) {
		super(breaks);
	}

	public abstract void setLocation();

	public void tick() {
		setLocation();
		broken = setBroken();
	}

}
