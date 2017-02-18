package net;

import java.io.Serializable;
import java.util.ArrayList;

import server.player.ServerPlayer;

public class PlayerPacketsPacket implements Serializable{
	private static final long serialVersionUID = 2L;

	private ArrayList<PlayerPacket> playerPackets = new ArrayList<>();

	public PlayerPacketsPacket(ArrayList<ServerPlayer> players) {
		for (ServerPlayer player:players) {
			playerPackets.add(new PlayerPacket(player));
		}
	}

	@Override
	public String toString() {
		return playerPackets.toString();
	}
}
