package client.player.controlled;

import java.awt.Color;

import client.game.Game;
import client.input.Cursor;
import client.level.SpawnPoint;
import client.player.Player;
import data.GameData;
import data.PlayerData;
import data.TileData;
import data.WeaponData;
import util.Util;

public class ControlledPlayer extends Player implements PlayerData, GameData, WeaponData {

	public ControlledPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, (float)spawnPoint.getX(), (float)spawnPoint.getY());
	}

	@Override
	protected void turn() {
		final float cursorAngle = Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY());
		setFacing(cursorAngle);//rotate the player toward cursor
		if (AIM_ASSIST) {//FIXME
			for (int i = 0;i<Game.getEntities().size();i++) {
				if (Game.getEntity(i) instanceof Player) {
					float angle = Util.getAngle(x, y, Game.getPlayer(i).getX(), Game.getPlayer(i).getY());
					if (withinAngle(cursorAngle, angle, AIM_ASSIST_RANGE)
							) {
//							&&!Util.lineIsBrokenByBooleanArray(this.x, this.y, Game.getPlayer(i).getX(), Game.getPlayer(i).getY(), Util.negateArray(TileData.getUseable()))) {
						setFacing(angle);
						return;
					}
				}
				else {
					break;
				}
			}
		}
	}

	public static boolean withinAngle(float angle1, float angle2, float rangeDeg) {//FIXME
		double range = Math.toRadians(rangeDeg);
		
		
		
		
		
		return Math.abs(angle1-angle2)<range;
	}
}
