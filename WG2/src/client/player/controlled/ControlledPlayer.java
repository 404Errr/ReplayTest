package client.player.controlled;

import java.awt.Color;

import client.input.Cursor;
import client.level.SpawnPoint;
import client.player.Player;
import data.Data;
import data.PlayerData;
import data.WeaponData;
import util.Util;

public class ControlledPlayer extends Player implements PlayerData, Data, WeaponData {

	public ControlledPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, (float)spawnPoint.getX(), (float)spawnPoint.getY());
	}

	@Override
	protected void turn() {
		/*Player closest = this;
		for (int i = 0;i<Game.getEntities().size();i++) {
			if (Game.getEntity(i) instanceof Player) {
				if (closest==this||Util.distance(x, y, Game.getPlayer(i).getX(), Game.getPlayer(i).getY())<Util.distance(x, y, closest.getX(), closest.getY())) {
					closest = Game.getPlayer(i);
				}
			}
		}
		if (closest!=this) {
			setFacing(Util.getAngle(x, y, closest.getX(), closest.getY()));//aimbot
		}*/
		/*else */
		setFacing(Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY()));//rotate the player toward cursor
	}
}
