package client.player;

import java.awt.Color;

import client.input.Cursor;
import client.level.SpawnPoint;
import data.Data;
import data.PlayerData;
import data.WeaponData;
import util.Util;

public class ControlledPlayer extends Player implements PlayerData, Data, WeaponData {

	public ControlledPlayer(Color color, float x, float y) {//creates at given x and y with given color
		super(color, x, y);
	}

	public ControlledPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, spawnPoint.getX(), spawnPoint.getY());
	}

	@Override
	protected void turn() {
		setFacing(Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY()));//rotate the player toward cursor
	}


}
