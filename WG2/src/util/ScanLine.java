package util;

import data.Data;
import data.TileData;
import data.WeaponData;

public class ScanLine implements TileData, Data, WeaponData {
	protected float iX, iY, fX, fY;
	private static float INITIAL_INCREMENT = 0.25f;
	private static float FINAL_INCREMENT = 0.001f;

	public ScanLine(float iX, float iY, float angle, boolean[][] hitable) {
		this.iX = iX;//set initial position
		this.iY = iY;
		final float dX = Util.getXComp(angle, INITIAL_INCREMENT), dY = -Util.getYComp(angle, INITIAL_INCREMENT);
		float x = iX, y = iY, incrementMultiplier = INITIAL_INCREMENT;
		boolean firstHit = true, firstCheck = true;
		while (true) {
			if (!Util.inArrayBounds(x, y, hitable)||hitable[(int)y][(int)x]) {//if it hit something
				if (firstCheck) {
					break;
				}
				if (firstHit) {
					firstHit = false;
					x-=dX*incrementMultiplier;//change the x and y back
					y-=dY*incrementMultiplier;
					incrementMultiplier = FINAL_INCREMENT;
				}
				else {
					break;
				}
			}
			if (firstCheck) {
				firstCheck = false;
			}
			x+=dX*incrementMultiplier;//change the x and y
			y+=dY*incrementMultiplier;
		}
		float[] distances = new float[4];
		distances[RIGHT] = (int)x+1-x;
		distances[DOWN] = (int)y+1-y;
		distances[LEFT] = x-(int)x;
		distances[UP] = y-(int)y;
		int side = Util.minInArray(distances);
		if (side==RIGHT||side==LEFT) {
			x = Math.round(x);
		}
		if (side==DOWN||side==UP) {
			y = Math.round(y);
		}

		this.fX = x;//set final position
		this.fY = y;
	}

	public float getiX() {
		return iX;
	}

	public float getiY() {
		return iY;
	}

	public float getfX() {
		return fX;
	}

	public float getfY() {
		return fY;
	}
}
