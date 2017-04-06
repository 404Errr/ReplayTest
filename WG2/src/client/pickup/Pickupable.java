package client.pickup;

import client.player.Player;

public abstract class Pickupable {
	public abstract void pickup(Player reciever);
	public abstract void tick();
}
