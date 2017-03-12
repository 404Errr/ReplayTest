package util;

import data.TileData;
import data.WeaponData;

public class ScanLine implements TileData, WeaponData {
	protected float iX, iY, fX, fY;
	private static float INITIAL_INCREMENT = 0.25f;
	private static float FINAL_INCREMENT = 0.001f;

	public ScanLine(float iX, float iY, float angle, boolean[][] hitable) {
		this.iX = iX;//set initial position
		this.iY = iY;
		final float dX = Util.getXComp(angle, INITIAL_INCREMENT), dY = -Util.getYComp(angle, INITIAL_INCREMENT);
		float x = iX, y = iY, incrementMultiplier = 1.0f;
		boolean firstHit = true, firstCheck = true;
		while (true) {
			if (y>=hitable.length||x>=hitable[0].length||y<0||x<0||hitable[(int)y][(int)x]) {//if it hit something
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
