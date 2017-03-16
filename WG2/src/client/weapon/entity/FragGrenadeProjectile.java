package client.weapon.entity;

import java.awt.Color;
import java.awt.geom.Line2D;

import client.game.Game;
import client.level.Level;
import client.weapon.WeaponEntity;
import data.Data;
import data.GraphicsData;
import data.TileData;
import data.WeaponData;
import util.ScanLine;
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
		this.timer = FRAGGRENADE_GRENADE_TIMER;
	}

	@Override
	public boolean tick() {
		if (!checkCollision()) {
			move();
		}
		if (!Util.inArrayBounds(x, y, Level.getLayout())) destroy = true;//if off of the map
		timer-=1000/UPS;
		if (timer<0) {
			explode();
			destroy = true;
		}
		return destroy;
	}

	private void explode() {
		if (FRAGGRENADE_SHARD_COUNT>0) for (float a = 0;a<360;a+=360f/FRAGGRENADE_SHARD_COUNT) {
			float speed = Util.getSpread(FRAGGRENADE_SHARD_SPEED, FRAGGRENADE_SHARD_SPREAD);
			float range = Util.getSpread(FRAGGRENADE_SHARD_RANGE, FRAGGRENADE_SHARD_SPREAD);
			float dX = (float) Util.getXComp(Math.toRadians(a), speed), dY = (float) Util.getYComp(Math.toRadians(a), speed);
			Game.addEntity(new LimitedProjectile(FRAGGRENADE_SHARD_DAMAGE, FRAGGRENADE_SHARD_RECOIL, FRAGGRENADE_SHARD_SIZE, range, /*color*/Util.colorOpacity(color, 1f), x, y, dX, dY, true));
		}
	}

	private void bounce() {
//		Hitscan contactPointFinder = new Hitscan(0f, 0.2f, Color.magenta, x, y, Util.getAngle(0, 0, dX, dY));
//		Game.addEntity(contactPointFinder);
		ScanLine contactPointFinder = new ScanLine(x, y, Util.getAngle(dX, dY), TileData.getHitable(SOLID_WALLS));
		move(contactPointFinder.getfX()-Math.signum(dX)*0.0001f, contactPointFinder.getfY()-Math.signum(dY)*0.0001f);
		float x = contactPointFinder.getfX(), y = contactPointFinder.getfY();
		if (Math.min((int)x+1-x, x-(int)x)<Math.min((int)y+1-y, y-(int)y)) {
			dX = -dX;
		}
		else {
			dY = -dY;
		}
	}

	protected void move() {
		dX*=1-FRAGGRENADE_GRENADE_DECCELERATION;
		dY*=1-FRAGGRENADE_GRENADE_DECCELERATION;
		x+=dX;
		y+=dY;
	}

	protected boolean checkCollision() {
		Line2D hitline = new Line2D.Float(x, y, x+dX, y+dY);
		final int zone = 2;
		for (int r = (int)y-zone;r<=y+zone;r++) {
			for (int c = (int)x-zone;c<=x+zone;c++) {
				if (Util.inArrayBounds(c, r, Level.getLayout())&&(Level.getTile(c, r).isSolid(SOLID_WALLS)/*||Level.getTile(c, r).isSolid(SOLID_PROJECTILES)*/)) {
					if (hitline.intersects(Level.getTile(c, r).getBounds())) {
						bounce();
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
