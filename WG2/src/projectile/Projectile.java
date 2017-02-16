package projectile;

public class Projectile {//TODO
	private double x, y, dX, dY, ddX, ddY;

	public Projectile() {}

	public Projectile(double x, double y, double dX, double dY, double ddX, double ddY) {
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		this.ddX = ddX;
		this.ddY = ddY;
	}

	public Projectile(double x, double y, double dX, double dY) {
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
	}

	public void tick() {
		dX+=ddX;
		dY+=ddY;
		x+=dX;
		y+=dY;
	}






	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
