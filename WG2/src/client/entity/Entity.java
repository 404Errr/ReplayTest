package client.entity;

import java.awt.Color;
import java.awt.Point;

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
		move(x, y, dX, dY);
		this.ddX = ddX;
		this.ddY = ddY;
	}

	public void move(float x, float y, float dX, float dY) {
		move(x, y);
		this.dX = dX;
		this.dY = dY;
	}

	public void move(float x, float y) {
		this.x = x;
		this.y = y;
		this.dX = 0;
		this.dY = 0;
		this.ddX = 0;
		this.ddY = 0;
	}

	public void move(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
		this.dX = 0;
		this.dY = 0;
		this.ddX = 0;
		this.ddY = 0;
	}

	public void move(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public abstract boolean tick();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getXTile() {
		return Math.round(x);
	}

	public int getYTile() {
		return Math.round(y);
	}

	public float getdX() {
		return dX;
	}

	public float getdY() {
		return dY;
	}

	public float getddX() {
		return ddX;
	}

	public float getddY() {
		return ddY;
	}

	public Color getColor() {
		return color;
	}



}
