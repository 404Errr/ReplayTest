package client.projectile;

import java.awt.Color;

import client.level.Level;
import shared.data.TileData;
import shared.data.WeaponData;
import shared.util.Util;

public class Hitscan implements TileData, WeaponData {
	private double damage, initialWidth, width, iX, iY, fX, fY;
	private Color color;

	public Hitscan(double damage, double initialWidth, Color color, double iX, double iY, double angle) {
		this.damage = damage;//how much damage it does
		this.width = initialWidth;//starting (largest) graphical width of the line
		this.initialWidth = initialWidth;
		this.color = color;//the color of the creator
		this.iX = iX;//set initial position
		this.iY = iY;
		this.x = (float)iX;
		this.y = (float)iY;
		scan(angle);//scan
		this.fX = x;//set final position
		this.fY = y;
	}

	public boolean tick() {//returns true if the object should stop existing
		width-=RAILGUN_LINE_INITIAL_WIDTH/RAILGUN_LINE_DURATION;//graphical width of the line
		if (width<=0) {//if its no longer visible
			return true;//the sweet release of death
		}
		return false;//continue living in this cruel world
	}

	private float x, y;
	private void scan(double angle) {
		final float dX = Util.getXComp(angle, HITSCAN_INITIAL_INCREMENT), dY = -Util.getYComp(angle, HITSCAN_INITIAL_INCREMENT);
		float incrementMultiplier = 1.0f;
		boolean firstHit = true, firstCheck = true;
		while (true) {
			if (x<0||y<0||x>Level.getWidth()||y>Level.getHeight()||(x>=0&&y>=0&&x<Level.getWidth()&&y<Level.getHeight()&&Level.getTile(y, x).isSolid(SOLID_PROJECTILES))) {//if it hit something
				if (firstCheck) {
					return;
				}
				if (firstHit) {
					firstHit = false;
					x-=dX*incrementMultiplier;//change the x and y back
					y-=dY*incrementMultiplier;
					incrementMultiplier = HITSCAN_FINAL_INCREMENT;
				}
				else {
					return;
				}
			}
			if (firstCheck) {
				firstCheck = false;
			}
			x+=dX*incrementMultiplier;//change the x and y
			y+=dY*incrementMultiplier;
		}
	}

	public double getDamage() {
		return damage;
	}

	public double getWidth() {
		return width;
	}

	public int getOpacity() {//out of 255
		return (int)(width/initialWidth*100)+155;
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
