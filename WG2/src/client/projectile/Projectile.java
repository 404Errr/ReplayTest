package client.projectile;

import java.awt.Color;
import java.awt.geom.Line2D;

import client.level.Level;
import shared.data.TileData;

public class Projectile implements TileData {
	protected double x, y, dX, dY, ddX, ddY, size, damage;
	protected Line2D hitline;
	protected boolean destroy;
	protected Color color;

	public Projectile(double damage, double size, Color color, double x, double y, double dX, double dY) {
		this.damage = damage;
		this.size = size;
		this.color = color;
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		hitline = new Line2D.Double(x-size/2, y-size/2, size, size);
	}

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
		hitline.setLine(x, y, x-dX, y-dY);
		checkWallCollision();
	}

	private void checkWallCollision() {
		final int radius = 1;
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getdX() {
		return dX;
	}

	public double getdY() {
		return dY;
	}

	public double getDdX() {
		return ddX;
	}

	public double getDdY() {
		return ddY;
	}

	public double getSize() {
		return size;
	}

	public double getDamage() {
		return damage;
	}

	public Line2D getHitbox() {
		return hitline;
	}

	public Color getColor() {
		return color;
	}


}
