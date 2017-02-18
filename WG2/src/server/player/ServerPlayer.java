package server.player;

public class ServerPlayer {
	private double x, y, dX, dY, facing;
	private String name;//name of the player

	public void update(double x, double y, double dX, double dY, double facing) {
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		this.facing = facing;
	}

	public ServerPlayer(String name, double x, double y, double dX, double dY, double facing) {
		this.name = name;
		update(x, y, dX, dY, facing);
	}

	public ServerPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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


}
