package client.projectile;

import java.awt.Color;

import client.level.Level;
import shared.data.TileData;
import shared.data.WeaponData;

public class Hitscan implements TileData, WeaponData {
	private double damage, width, x, y, iX, iY, fX, fY;
	private Color color;

	public Hitscan(double damage, double initialWidth, Color color, double x, double y, double dX, double dY) {
		this.damage = damage;//how much damage it does
		this.width = initialWidth;//starting (largest) graphical width of the line
		this.color = color;//the color of the creator
		this.x = x;//starting posistion
		this.y = y;
		this.iX = x;//set initial position
		this.iY = y;
		scan(dX*HITSCAN_INCREMENT, dY*HITSCAN_INCREMENT);//move util it hits something
		this.fX = this.x;//set final position
		this.fY = this.y;
	}

	public boolean tick() {//returns true if the object should stop existing
		width-=RAILGUN_INITIAL_WIDTH/RAILGUN_LINE_DURATION;//graphical width of the line
		if (width<=0) {//if its no longer visible
			return true;//stop existing
		}
		return false;//continue living in this cruel world
	}

	private void scan(double dX, double dY) {
		double traveled = 0;
		boolean hit = false;
		while (!hit) {//if it hit something
			x+=dX;//change the x and y
			y+=dY;
			traveled+=Math.hypot(dX, dY);
			if (traveled>HITSCAN_MAX_DISTANCE||checkWallCollision()/*||checkPlayerCollision()*/) {//check for collision
				hit = true;//stop checking
			}
		}
	}

	private boolean checkWallCollision() {
		final int radius = 2;
		for (int r = (int)y-radius;r<y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid(SOLID_PROJECTILES)) {//bounds check and if tile is solid
					if (Level.getTile(r, c).getBounds().contains(x, y)) {//check for collision with tile
						return true;//hit a wall
					}
				}
			}
		}
		return false;//didnt hit a wall
	}

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
