package client.weapon.entity;

import java.awt.Color;
import java.awt.geom.Line2D;

import client.game.Game;
import client.level.Level;
import data.Data;
import data.GraphicsData;
import data.TileData;
import data.WeaponData;
import util.Util;

public class FragGrenadeProjectile extends WeaponEntity implements Data, TileData, WeaponData, GraphicsData {
	private boolean destroy;
	private float dX, dY, grenadeSize;
	private int timer;

	public FragGrenadeProjectile(Color color, float x, float y, float dX, float dY) {
		super(color, x, y, 0);
		this.dX = dX;
		this.dY = dY;
		this.grenadeSize = FRAGGRENADE_GRENADE_SIZE;
		this.timer = FRAGGRENADE_TIMER;
	}

	@Override
	public boolean tick() {
		move();
		checkCollision();
		timer-=1000/UPS;
		if (timer<0) {
			explode();
			destroy = true;
		}
		if (x<0||y<0||x>Level.getWidth()||y>Level.getHeight()) destroy = true;//if off of the map
		return destroy;
	}

	private void explode() {
		System.out.println();//FIXME
		if (FRAGGRENADE_SHARD_COUNT>0) for (float a = 0;a<Math.PI*2;a+=Math.PI/FRAGGRENADE_SHARD_COUNT) {
			float speed = Util.getSpread(FRAGGRENADE_SPEED, FRAGGRENADE_SPREAD);
			float range = Util.getSpread(FRAGGRENADE_RANGE, FRAGGRENADE_SPREAD);
			float dX = Util.getXComp(a, speed), dY = Util.getYComp(a, speed);
			Game.addEntity(new LimitedProjectile(FRAGGRENADE_DAMAGE, FRAGGRENADE_RECOIL, FRAGGRENADE_SIZE, range, /*color*/Util.colorOpacity(color, 1f), x+grenadeSize/2, y+grenadeSize/2, dX, dY, true));
		}
	}

	private void bounce() {//FIXME
		/*Hitscan contactPointFinder = new Hitscan(0, 0.2f, Color.MAGENTA, x+grenadeSize/2, y+grenadeSize/2, Util.getAngle(0, 0, dX, dY), true);
		/*if (DRAW_BOUNCE_HIT) Game.addEntity(contactPointFinder);//display it
		int side = WGUtil.getSide(contactPointFinder.getX(), contactPointFinder.getY(), dX, dY, Level.getLayout());
//		int side = WGUtil.getSide(contactPointFinder.getX(), contactPointFinder.getY(), dX, dY);
//		int side = WGUtil.getSide(contactPointFinder.getX(), contactPointFinder.getY(), Level.getLayout());
		float angle = Util.getBounceAngle(Util.getAngle(0, 0, dX, dY), side==LEFT||side==RIGHT);
		System.out.println(side);//FIXME
		float magnitude = Util.getDistance(0, 0, dX, dY);
		dX = Util.getXComp(angle, magnitude);
		dY = -Util.getYComp(angle, magnitude);*/
		/*if (side==RIGHT) {
//			dX = Math.abs(dX);
			x+=grenadeSize/3;
		}
		if (side==DOWN) {
//			dY = Math.abs(dY);
			y+=grenadeSize/3;
		}
		if (side==LEFT) {
//			dX = -Math.abs(dX);
			x-=grenadeSize/3;
		}
		if (side==UP) {
//			dY = -Math.abs(dY);
			y-=grenadeSize/3;
		}*/
	}

	protected void move() {
		dX*=1-FRAGGRENADE_GRENADE_DECCELERATION;
		dY*=1-FRAGGRENADE_GRENADE_DECCELERATION;
		x+=dX;
		y+=dY;
	}

	protected void checkCollision() {
		if (checkWallCollision()) bounce();
	}

	private boolean checkWallCollision() {
		Line2D hitline = new Line2D.Float(x+grenadeSize/2, y+grenadeSize/2, x+dX+grenadeSize/2, y+dY+grenadeSize/2);
		final int radius = 2;
		for (int r = (int)y-radius;r<=y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<=x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&(Level.getTile(c, r).isSolid(SOLID_WALLS)||Level.getTile(c, r).isSolid(SOLID_PROJECTILES))) {//bounds check and if tile is solid
					if (hitline.intersects(Level.getTile(c, r).getBounds())) {//check for collision
						return true;
					}
				}
			}
		}
		return false;
	}

	public float getGrenadeSize() {
		return grenadeSize;
	}

}
