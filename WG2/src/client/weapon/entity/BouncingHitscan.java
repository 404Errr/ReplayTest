package client.weapon.entity;

import java.awt.Color;

import client.game.Game;
import data.GameData;
import data.TileData;
import util.ScanLine;
import util.Util;

public class BouncingHitscan extends Hitscan implements GameData {
	public BouncingHitscan(float initialWidth, Color color, float iX, float iY, float angle, int bounces) {
		super(0, initialWidth, color, iX, iY, angle);
		if (bounces>0) {
			ScanLine contactPointFinder = new ScanLine(x, y, Util.getAngle(dX, dY), TileData.getHitable(SOLID_WALLS));
			float x = contactPointFinder.getfX(), y = contactPointFinder.getfY();
			float newAngle = Util.getBounceAngle(angle, Math.min((int)x+1-x, x-(int)x)<Math.min((int)y+1-y, y-(int)y));
			move(x-Math.signum(dX)*0.0001f, x-Math.signum(dY)*0.0001f);
//			if (Math.min((int)x+1-x, x-(int)x)<Math.min((int)y+1-y, y-(int)y)) {
//				dX = -dX;
//			}
//			else {
//				dY = -dY;
//			}
			System.out.println(Math.toDegrees(angle)+"\t"+Math.toDegrees(newAngle)+"\t"+(Math.min((int)x+1-x, x-(int)x)<Math.min((int)y+1-y, y-(int)y)));
			Game.addEntity(new BouncingHitscan(initialWidth, color, x, y, newAngle, bounces-1));
		}
	}
}

//			float newAngle = Util.getBounceAngle(angle, side==LEFT||side==RIGHT);