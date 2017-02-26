package client.weapon;

import java.awt.Color;
import java.awt.geom.Line2D;

import client.entity.Entity;
import client.game.Game;
import client.level.Level;
import data.Data;
import data.TileData;
import data.WeaponData;
import util.Util;

public class FragGrenade extends Entity implements Damages, Data, TileData, WeaponData {
	private boolean destroy;
	private float dX,  dY, grenadeSize;
	private int timer;

	public FragGrenade(Color color, float x, float y, float dX, float dY, float grenadeSize, int timer) {
		super(color, x, y);
		this.dX = dX;
		this.dY = dY;
		this.grenadeSize = grenadeSize;
		this.timer = timer;
	}

	@Override
	public boolean tick() {
		move();
		if (x<0||y<0||x>Level.getWidth()||y>Level.getHeight()) destroy = true;//if off of the map
		checkCollision();
		timer-=1000/UPS;
		if (timer<0) {
			explode();
			destroy = true;
		}
		return destroy;
	}

	private void explode() {
		for (float a = 0;a<Math.PI*2;a+=Math.PI/FRAGGRENADE_SHARD_COUNT) {
			float speed = Util.getSpread(FRAGGRENADE_SPEED, FRAGGRENADE_SPEED_SPREAD);
			float dX = Util.getXComp(a, speed), dY = Util.getYComp(a, speed);
			Game.addEntity(new Shard(FRAGGRENADE_DAMAGE, FRAGGRENADE_SIZE, FRAGGRENADE_RANGE, /*color*/Util.colorOpacity(color, 1f), x+grenadeSize/2, y+grenadeSize/2, dX, dY));
		}

	}

	private void bounce() {//FIXME
		Hitscan finder = new Hitscan(0, 0, 0.2f, null/*Color.MAGENTA*/, x+grenadeSize/2, y+grenadeSize/2, Util.getAngle(0, 0, dX, dY), true);
//		Game.addEntity(finder);
		int side = Util.getSide(finder.getX(), finder.getY(), Level.getLayout());
		if (side==RIGHT) {
			dX = Math.abs(dX);
			x+=grenadeSize/3;
		}
		if (side==DOWN) {
			dY = Math.abs(dY);
			y+=grenadeSize/3;
		}
		if (side==LEFT) {
			dX = -Math.abs(dX);
			x-=grenadeSize/3;
		}
		if (side==UP) {
			dY = -Math.abs(dY);
			y-=grenadeSize/3;
		}
	}

	protected void move() {
		dX*=FRAGGRENADE_GRENADE_DECCELERATION;
		dY*=FRAGGRENADE_GRENADE_DECCELERATION;
		x+=dX;
		y+=dY;
	}

	protected void checkCollision() {
		Line2D hitline = new Line2D.Float(x+grenadeSize/2, y+grenadeSize/2, x+dX+grenadeSize/2, y+dY+grenadeSize/2);
		checkWallCollision(hitline);
//		checkPlayerCollision(hitline);
	}

	private void checkWallCollision(Line2D hitline) {
		final int radius = 2;
		for (int r = (int)y-radius;r<=y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<=x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(c, r).isSolid(SOLID_WALLS)) {//bounds check and if tile is solid
					if (hitline.intersects(Level.getTile(c, r).getBounds())) {//check for collision
						bounce();
					}
				}
			}
		}
	}

	public float getGrenadeSize() {
		return grenadeSize;
	}

	/*private void checkPlayerCollision(Line2D hitline) {
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
	}*/

}
