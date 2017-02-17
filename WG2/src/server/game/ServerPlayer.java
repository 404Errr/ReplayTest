package server.game;

public class ServerPlayer {
	private double x, y, facing;
	private String name;

	public void update(double x, double y, double facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	public ServerPlayer(String name, double x, double y, double facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
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

	public double getFacing() {
		return facing;
	}

}
