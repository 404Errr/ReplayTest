package client.projectile;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import client.level.Level;

public class Projectile {//TODO
	protected double x, y, dX, dY, ddX, ddY, size, damage;
	protected Rectangle2D hitbox;
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
		hitbox = new Rectangle2D.Double(x-size/2, y-size/2, size, size);
	}

	public void tick() {
		dX+=ddX;
		dY+=ddY;
		x+=dX;
		y+=dY;
		checkCollision();
	}

	protected void checkCollision() {
		checkWallCollision();
	}

	private void checkWallCollision() {
		final int radius = 2;
		for (int r = (int)y-radius;r<y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid()) {//bounds check and if tile is solid
					if (hitbox.intersects(Level.getTile(r, c).getBounds())) {//check for collision
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
}
