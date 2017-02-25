package client.weapon;

import java.awt.Color;

import client.game.Game;
import data.Data;
import util.Util;

public class BouncingHitscan extends Hitscan implements Data {
	public BouncingHitscan(float damage, float recoil, float initialWidth, Color color, float iX, float iY, float angle, int bounces) {
		super(damage, recoil, initialWidth, color, iX, iY, angle);
		if (bounces>0) {
			float newAngle = Util.bounceAngle(angle, getSide(x, y)==LEFT||getSide(x, y)==RIGHT);
			int side = getSide(x, y);
			switch (side) {
			case UP:
				y = Math.round(y);
				y-=0.001f;
				break;
			case DOWN:
				y = Math.round(y);
				y+=0.001f;
				break;
			case LEFT:
				x = Math.round(x);
				x-=0.001f;
				break;
			case RIGHT:
				x = Math.round(x);
				x+=0.001f;
				break;
			}
			Game.addEntity(new BouncingHitscan(damage, recoil, initialWidth, color, x, y, newAngle, bounces-1));
		}
	}

	private int getSide(float x, float y) {
		if (Math.abs(x-(int)x)<0.001f) return LEFT;
		if (Math.abs((int)x+1-x)<0.001f) return RIGHT;
		if (Math.abs(y-(int)y)<0.001f) return UP;
		if (Math.abs((int)y+1-y)<0.001f) return DOWN;
		return 0;//right
	}
}
