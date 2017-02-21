package client.entity;

import java.awt.Color;

abstract public class Entity {
	protected float x, y, dX, dY, ddX, ddY;
	protected Color color;

	public Entity(Color color, float x, float y) {
		move(x, y);
		this.color = color;
	}

	public Entity(Color color, float x, float y, float dX, float dY) {
		move(x, y, dX, dY);
		this.color = color;
	}

	public Entity(Color color, float x, float y, float dX, float dY, float ddX, float ddY) {
		move(x, y, dX, dY, ddX, ddY);
		this.color = color;
	}

	public void move(float x, float y, float dX, float dY, float ddX, float ddY) {
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		this.ddX = ddX;
		this.ddY = ddY;
	}

	public void move(float x, float y, float dX, float dY) {
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
	}

	public void move(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public abstract boolean tick();

}
