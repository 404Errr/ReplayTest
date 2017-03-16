package client.player.controlled;

import java.awt.Color;

import client.game.Game;
import client.input.Cursor;
import client.level.SpawnPoint;
import client.player.Player;
import data.GameData;
import data.PlayerData;
import data.WeaponData;
import util.Util;

public class ControlledPlayer extends Player implements PlayerData, GameData, WeaponData {

	public ControlledPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, (float)spawnPoint.getX(), (float)spawnPoint.getY());
	}

	@Override
	protected void turn() {

		setFacing(Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY()));//rotate the player toward cursor
		if (AIM_ASSIST) {
			final float cursorAngle = Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY());
			for (int i = 0;i<Game.getEntities().size();i++) {
				if (Game.getEntity(i) instanceof Player) {
					float angle = Util.getAngle(x, y, Game.getPlayer(i).getX(), Game.getPlayer(i).getY());
					if (Util.withinAngle(cursorAngle, angle, AIM_ASSIST_RANGE)) {
						setFacing(angle);
						return;
					}
				} else
					break;//no more players
			}
		}
	}



}
