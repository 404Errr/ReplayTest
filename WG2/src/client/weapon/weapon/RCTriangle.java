package client.weapon.weapon;

import java.util.ArrayList;
import java.util.List;

import client.game.Game;
import client.input.Cursor;
import client.player.Player;
import client.weapon.Weapon;
import data.WeaponData;
import util.Util;

public class RCTriangle extends Weapon implements WeaponData {
	private List<Triangle> triangles;
	private float ringAngle;

	public RCTriangle(Player owner) {
		super(owner, RCTRIANGLE_TPM, 0, 0);
		constantUse = true;
		triangles = new ArrayList<>();
	}

	@Override
	public void tick() {
		ringAngle+=Math.toRadians(RCTRIANGLE_RING_SPEED/UPS);
		if (owner.getActiveWeapon()==this) {
			float x, y, dist = 0;
			int sign = 1;
			if (owner.getMouseControl(USE_1)) {
				dist = 0.5f;
				x = Cursor.getGridX()+0.5f;
				y = Cursor.getGridY()+0.5f;
			}
			else if (owner.getMouseControl(USE_2)) {
				sign = -1;
				x = Cursor.getGridX()+0.5f;
				y = Cursor.getGridY()+0.5f;
			}
			else {
				dist = 1;
				x = owner.getXCenter();
				y = owner.getYCenter();
			}
			for (int i = 0;i<triangles.size();i++) {
				triangles.get(i).settSign(sign);
				float a = (float)(Math.PI*2/triangles.size())*i+ringAngle;
				triangles.get(i).settX(x+Util.getXComp(a, dist));
				triangles.get(i).settY(y-Util.getYComp(a, dist));
			}
		}
		else {
			for (int i = triangles.size()-1;i>=0;i--) {
				triangles.get(i).destroy();//explode or something
				triangles.remove(i);
			}
		}
		super.tick();
	}

	@Override
	protected void use() {
		if (triangles.size()<RCTRIANGLE_MAX_COUNT) {
			Triangle newTriangle = new Triangle(getOwner(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), 0);
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
