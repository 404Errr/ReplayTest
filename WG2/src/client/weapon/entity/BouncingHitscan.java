package client.weapon.entity;

import java.awt.Color;

import data.GameData;

public class BouncingHitscan extends Hitscan implements GameData {
	public BouncingHitscan(float damage, float recoil, float initialWidth, Color color, float iX, float iY, float angle, int bounces) {
		super(damage, initialWidth, color, iX, iY, angle);
//		if (bounces>0) {
//			int side = WGUtil.getSide(x, y, Level.getLayout());
//			float newAngle = Util.getBounceAngle(angle, side==LEFT||side==RIGHT);
//			switch (side) {
//			case UP:
//				y = Math.round(y)-0.001f;
//				break;
//			case DOWN:
//				y = Math.round(y)+0.001f;
//				break;
//			case LEFT:
//				x = Math.round(x)-0.001f;
//				break;
//			case RIGHT:
//				x = Math.round(x)+0.001f;
//				break;
//			}
//			Game.addEntity(new BouncingHitscan(damage, recoil, initialWidth, color, x, y, newAngle, bounces-1));
//		}
	}
}
