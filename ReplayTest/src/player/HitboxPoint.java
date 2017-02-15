package player;

public class HitboxPoint {
	private double x, y;
	private boolean touching;

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public boolean isTouching() {
		return touching;
	}

	public void setTouching(boolean touching) {
		this.touching = touching;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}


}