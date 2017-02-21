package client.weapon;


import java.awt.Color;
import java.awt.geom.Line2D;

import client.entity.Entity;
import client.level.Level;
import data.TileData;

public class Projectile extends Entity implements TileData {
	protected float size, damage;
	protected boolean destroy;

	public Projectile(float damage, float size, Color color, float x, float y, float dX, float dY) {
		super(color, x, y);
		this.damage = damage;
		this.size = size;
		this.dX = dX;
		this.dY = dY;
	}

	@Override
	public boolean tick() {
		dX+=ddX;
		dY+=ddY;
		x+=dX;
		y+=dY;
		if (x<0||y<0||x>Level.getWidth()||y>Level.getHeight()) destroy = true;//if off of the map
		checkCollision();
		return destroy;
	}

	protected void checkCollision() {
		Line2D hitline = new Line2D.Float(x, y, x-dX, y-dY);
		checkWallCollision(hitline);
//		checkPlayerCollision();//TODO
	}

	private void checkWallCollision(Line2D hitline) {
		final int radius = 2;
		for (int r = (int)y-radius;r<=y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<=x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid(SOLID_PROJECTILES)) {//bounds check and if tile is solid
					if (hitline.intersects(Level.getTile(r, c).getBounds())) {//check for collision
						destroy = true;//destroy it
					}
				}
			}
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getdX() {
		return dX;
	}

	public float getdY() {
		return dY;
	}

	public float getDdX() {
		return ddX;
	}

	public float getDdY() {
		return ddY;
	}

	public float getSize() {
		return size;
	}

	public float getDamage() {
		return damage;
	}

	public Color getColor() {
		return color;
	}


}
