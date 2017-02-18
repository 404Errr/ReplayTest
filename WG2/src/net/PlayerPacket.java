package net;

import java.io.Serializable;

import client.player.Player;
import data.NetData;
import server.player.ServerPlayer;

public class PlayerPacket implements Serializable, NetData {
	private static final long serialVersionUID = 1L;

	private String userName;
	private double x, y, dX, dY, facing;

//	public PlayerPacket(String userName, double x, double y, double facing) {
//		this.userName = userName;
//		this.x = x;
//		this.y = y;
//		this.facing = facing;
//	}

	public PlayerPacket(Player player) {//for client
		this.userName = USERNAME;
		this.x = player.getX();
		this.y = player.getY();
		this.dX = player.getdX();
		this.dY = player.getdY();
		this.facing = player.getFacing();
	}

	public PlayerPacket(ServerPlayer player) {//for server
		this.userName = USERNAME;
		this.x = player.getX();
		this.y = player.getY();
		this.dX = player.getdX();
		this.dY = player.getdY();
		this.facing = player.getFacing();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getdX() {
		return dX;
	}

	public double getdY() {
		return dY;
	}

	public double getFacing() {
		return facing;
	}

	@Override
	public String toString() {
		return "PlayerPacket "+userName+" [x=" + x + ", y=" + y + ", facing=" + facing + "]";
	}

	public String getUserName() {
		return userName;
	}


}
