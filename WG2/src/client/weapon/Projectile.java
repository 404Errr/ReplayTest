package client.weapon;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

import client.entity.Entity;
import client.game.Game;
import client.level.Level;
import client.player.Player;
import data.TileData;
import data.WeaponData;
import util.Util;

public class Projectile extends Entity implements Damages, TileData, WeaponData {
	protected float damage, recoil, size;
	protected boolean destroy;

	public Projectile(float damage, float recoil, float size, Color color, float x, float y, float dX, float dY) {
		super(color, x, y);
		this.damage = damage;
		this.recoil = recoil;
		this.size = size;
		this.dX = dX;
		this.dY = dY;
	}

	@Override
	public boolean tick() {
		move();
		if (x<0||y<0||x>Level.getWidth()||y>Level.getHeight()) destroy = true;//if off of the map
		checkCollision();
		return destroy;
	}

	protected void move() {
		dX+=ddX;
		dY+=ddY;
		x+=dX;
		y+=dY;
	}

	protected void checkCollision() {
		Line2D hitline = new Line2D.Float(x, y, x-dX, y-dY);
		checkWallCollision(hitline);
		checkPlayerCollision(hitline);
	}

	private void checkWallCollision(Line2D hitline) {
		final int radius = 2;
		for (int r = (int)y-radius;r<=y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<=x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(c, r).isSolid(SOLID_PROJECTILES)) {//bounds check and if tile is solid
					if (hitline.intersects(Level.getTile(c, r).getBounds())) {//check for collision
						destroy = true;//destroy it
					}
				}
			}
		}
	}

	private void checkPlayerCollision(Line2D hitline) {
		List<Entity> entities = Game.getEntities();
		for (int i = 0;i<entities.size();i++) {
			if (entities.get(i) instanceof Player&&((Player)entities.get(i)).getColor()!=color&&((Player)entities.get(i)).getBounds().intersectsLine(hitline)) {
				damage((Player)entities.get(i), damage);
				if (RECOIL) {
					((Player)entities.get(i)).recoil(Util.getAngle(0, 0, dX, dY), recoil);
				}
				destroy = true;//destroy it
			}
		}
	}

	public float getSize() {
		return size;
	}

	public float getDamage() {
		return damage;
	}
}
