package net;

import java.io.Serializable;

import client.player.Player;

public class PlayerPacket implements Serializable {
	private static final long serialVersionUID = 1L;

	private double x, y, facing;

	public PlayerPacket(double x, double y, double facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	public PlayerPacket(Player player) {
		this.x = player.getX();
		this.y = player.getY();
		this.facing = player.getFacing();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getFacing() {
		return facing;
	}

	@Override
	public String toString() {
		return "PlayerPacket [x=" + x + ", y=" + y + ", facing=" + facing + "]";
	}

}
