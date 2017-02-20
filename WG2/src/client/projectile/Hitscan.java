package client.projectile;

import java.awt.Color;

import client.level.Level;
import shared.data.TileData;
import shared.data.WeaponData;
import shared.util.Util;

public class Hitscan implements TileData, WeaponData {
	private double damage, width, x, y, iX, iY, fX, fY;
	private Color color;

	public Hitscan(double damage, double initialWidth, Color color, double x, double y, double angle) {
		this.damage = damage;//how much damage it does
		this.width = initialWidth;//starting (largest) graphical width of the line
		this.color = color;//the color of the creator
		this.x = x;//starting posistion
		this.y = y;
		this.iX = x;//set initial position
		this.iY = y;
		scan(angle);//move util it hits something
		this.fX = this.x;//set final position
		this.fY = this.y;
	}

	public boolean tick() {//returns true if the object should stop existing
		width-=RAILGUN_LINE_INITIAL_WIDTH/RAILGUN_LINE_DURATION;//graphical width of the line
		if (width<=0) {//if its no longer visible
			return true;//the sweet release of death
		}
		return false;//continue living in this cruel world
	}

	private void scan(double angle) {
		final double magnitude = HITSCAN_INITIAL_INCREMENT;
		final double dX = Util.getXComp(angle, magnitude), dY = -Util.getYComp(angle, magnitude);
		double traveled = 0d, incrementMultiplier = 1.0d;
		boolean firstHit = true, firstCheck = true;
		while (true) {
			if (x>=0&&y>=0&&x<Level.getWidth()&&y<Level.getHeight()&&Level.getTile(y, x).isSolid(SOLID_PROJECTILES)) {//if it hit something
				if (firstCheck) {
					return;
				}
				if (firstHit) {
					firstHit = false;
					x-=dX*incrementMultiplier;//change the x and y back
					y-=dY*incrementMultiplier;
					traveled-=Math.hypot(dX*incrementMultiplier, dY*incrementMultiplier);//for checking if it went too far
					incrementMultiplier = HITSCAN_FINAL_INCREMENT;
				}
				else {
					return;
				}
			}
			if (firstCheck) {
				firstCheck = false;
			}
			if (traveled>HITSCAN_MAX_DISTANCE) {//for checking if it went too far
				return;
			}
			x+=dX*incrementMultiplier;//change the x and y
			y+=dY*incrementMultiplier;
			traveled+=Math.hypot(dX*incrementMultiplier, dY*incrementMultiplier);//for checking if it went too far
		}
	}

	/*private void scan(double dX, double dY) {
		double traveled = 0;
		boolean hit = false;
		while (!hit) {//if it hit something
			x+=dX;//change the x and y
			y+=dY;
			traveled+=Math.hypot(dX, dY);
			if (traveled>HITSCAN_MAX_DISTANCE||checkWallCollision()) {//check for collision
				hit = true;//stop checking
			}
		}
	}*/

	public double getDamage() {
		return damage;
	}

	public double getWidth() {
		return width;
	}

	public double getiX() {
		return iX;
	}

	public double getiY() {
		return iY;
	}

	public double getfX() {
		return fX;
	}

	public double getfY() {
		return fY;
	}

	public Color getColor() {
		return color;
	}
}
