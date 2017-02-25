package client.weapon;

import java.awt.Color;

import client.entity.Entity;

public class FragGrenade extends Entity {

	public FragGrenade(Color color, float x, float y) {
		super(color, x, y);
	}

	@Override
	public boolean tick() {
		return false;
	}

}
