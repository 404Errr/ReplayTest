package client.weapon.entity;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

import client.entity.Entity;
import client.game.Game;
import client.player.Player;
import client.weapon.WeaponEntity;
import data.TileData;
import data.WeaponData;
import util.ScanLine;

public class Hitscan extends WeaponEntity implements TileData, WeaponData {
	protected float damage, initialWidth, width, iX, iY, fX, fY;
	protected Color color;

	public Hitscan(float damage, float initialWidth, Color color, float iX, float iY, float angle) {
		super(color, iX, iY, damage);
		this.damage = damage;//how much damage it does
		this.width = initialWidth;//starting (largest) graphical width of the line
		this.initialWidth = initialWidth;
		this.color = color;//the color of the creator
		ScanLine scan = new ScanLine(iX, iY, angle, TileData.getHitable(1));//SOLID_WALLS));
		this.iX = scan.getiX();//set initial position
		this.iY = scan.getiY();
		this.x = scan.getiX();
		this.y = scan.getiY();
		this.fX = scan.getfX();//set final position
		this.fY = scan.getfY();
		checkPlayerCollision(new Line2D.Double(iX, iY, fX, fY));
	}

	private void checkPlayerCollision(Line2D hitline) {
		List<Entity> entities = Game.getEntities();
		for (int i = 0;i<entities.size();i++) {
			if (entities.get(i) instanceof Player&&((Player)entities.get(i)).getColor()!=color&&((Player)entities.get(i)).getBounds().intersectsLine(hitline)) {
				damage((Player)entities.get(i));
			}
		}
	}

	@Override
	public boolean tick() {//returns true if the object should stop existing
		width-=RAILGUN_LINE_INITIAL_WIDTH/RAILGUN_LINE_DURATION;//graphical width of the line
		if (width<=0) {//if its no longer visible
			return true;//the sweet release of death
		}
		return false;//continue living in this cruel world
	}

	public float getDamage() {
		return damage;
	}

	public float getWidth() {
		return width;
	}

	public int getOpacity() {//out of 255
		return (int)(width/initialWidth*200)+55;
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
