package client.weapon.weapon;

import java.util.ArrayList;
import java.util.List;

import client.game.Game;
import client.input.Cursor;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.Triangle;
import data.WeaponData;
import util.Util;

public class RCTriangle extends Weapon implements WeaponData {
	private List<Triangle> triangles;
	private float ringAngle;

	public RCTriangle(Player owner) {
		super(owner, RCTRIANGLE_TPM);
		constantUse = true;
		triangles = new ArrayList<>();
	}

	@Override
	public void tick() {
		ringAngle+=Math.toRadians(RCTRIANGLE_RING_SPEED/UPS);
		if (owner.getActiveWeapon()==this) {
			float x, y;
			int sign = 1;
			boolean pathFind = true;
			if (owner.getMouseControl(USE_1)) {
				x = Cursor.getGridX()+HALF_PLAYER_SIZE;
				y = Cursor.getGridY()+HALF_PLAYER_SIZE;
			}
			else if (owner.getMouseControl(USE_2)) {
				sign = -1;
				pathFind = false;
				x = Cursor.getGridX()+HALF_PLAYER_SIZE;
				y = Cursor.getGridY()+HALF_PLAYER_SIZE;
			}
			else {
				x = owner.getXCenter();
				y = owner.getYCenter();
			}
			for (int i = 0;i<triangles.size();i++) {
				triangles.get(i).settSign(sign);
				float a = (float)(Math.PI*2/triangles.size())*i+ringAngle;
				triangles.get(i).setT(x, y, Util.getXComp(a, 1), Util.getYComp(a, 1), pathFind);
			}
		}
		else {
			for (int i = triangles.size()-1;i>=0;i--) {
				triangles.get(i).destroy();//explode or something

			}
		}
		for (int i = 0;i<triangles.size();i++) if (triangles.get(i).isDestroy()) triangles.remove(i);//TODO remove from list if destroyed
		super.tick();
	}

	@Override
	protected void use() {
		if (triangles.size()<RCTRIANGLE_MAX_COUNT) {
			Triangle newTriangle = new Triangle(getOwner(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), 0.1f);
			triangles.add(newTriangle);
			Game.addEntity(newTriangle);
			System.out.println("rctriangle");
		}
	}

	@Override
	public String toString() {
		return "RC Triangle";
	}

	public List<Triangle> getTriangles() {
		return triangles;
	}
}
