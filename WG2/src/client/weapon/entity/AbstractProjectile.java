package client.weapon.entity;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

import client.entity.Entity;
import client.game.Game;
import client.level.Level;
import client.player.Player;
import data.GraphicsData;
import data.TileData;
import data.WeaponData;
import util.Util;

public abstract class AbstractProjectile extends WeaponEntity implements TileData, WeaponData, GraphicsData {
	protected float damage, size;
	protected boolean destroy;
	protected int destroyIn = -1;

	public AbstractProjectile(float damage, float size, Color color, float x, float y, float dX, float dY) {
		super(color, x-size/2, y-size/2, damage);
		this.damage = damage;
		this.size = size;
		this.dX = dX;
		this.dY = dY;
	}

	@Override
	public boolean tick() {
		if (destroyIn<0) {
			move();
			if (x<0||y<0||x>Level.getWidth()||y>Level.getHeight()) destroyIn = 0;//if off of the map
			if (checkCollision()) {
				lastMove();
				destroyIn = PROJECTILE_LIFE;
			}
		}
		else if (destroyIn>0) destroyIn--;
		return destroyIn==0;
	}

	protected void move() {
		dX+=ddX;
		dY+=ddY;
		x+=dX;
		y+=dY;
	}

	protected void lastMove() {
		x-=dX;
		y-=dY;
		Hitscan finder = new Hitscan(0, 0.2f, Color.MAGENTA, x+size/2, y+size/2, Util.getAngle(0, 0, dX, dY), false);
		if (DRAW_PROJECTILE_HIT) Game.addEntity(finder);
		x = finder.getfX()-size/2;
		y = finder.getfY()-size/2;
	}

	protected boolean checkCollision() {
		Line2D hitline = new Line2D.Float(x+size/2, y+size/2, x-dX+size/2, y-dY+size/2);
		checkPlayerCollision(hitline);
		return checkWallCollision(hitline);
	}

	private boolean checkWallCollision(Line2D hitline) {
		final int radius = 2;
		for (int r = (int)y-radius;r<=y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<=x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(c, r).isSolid(SOLID_PROJECTILES)) {//bounds check and if tile is solid
					if (hitline.intersects(Level.getTile(c, r).getBounds())) {//check for collision
						return true;//hit
					}
				}
			}
		}
		return false;
	}

	private void checkPlayerCollision(Line2D hitline) {
		List<Entity> entities = Game.getEntities();
		for (int i = 0;i<entities.size();i++) {
			if (entities.get(i) instanceof Player&&((Player)entities.get(i)).getColor()!=color&&((Player)entities.get(i)).getBounds().intersectsLine(hitline)) {
				damage((Player)entities.get(i));
				destroyIn = 0;//destory now
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
