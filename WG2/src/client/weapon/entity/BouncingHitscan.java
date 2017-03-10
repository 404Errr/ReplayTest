package client.weapon.entity;

import java.awt.Color;

import client.game.Game;
import data.Data;
import util.Util;

public class BouncingHitscan extends Hitscan implements Data {
	public BouncingHitscan(float damage, float recoil, float initialWidth, Color color, float iX, float iY, float angle, int bounces) {
		super(damage, initialWidth, color, iX, iY, angle, false);
		if (bounces>0) {
			int side = 0;//WGUtil.getSide(x, y, Level.getLayout());FIXME
			float newAngle = Util.bounceAngle(angle, side==LEFT||side==RIGHT);
			switch (side) {
			case UP:
				y = Math.round(y)-0.001f;
				break;
			case DOWN:
				y = Math.round(y)+0.001f;
				break;
			case LEFT:
				x = Math.round(x)-0.001f;
				break;
			case RIGHT:
				x = Math.round(x)+0.001f;
				break;
			}
			Game.addEntity(new BouncingHitscan(damage, recoil, initialWidth, color, x, y, newAngle, bounces-1));
		}
	}
}
