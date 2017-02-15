package player;

import java.awt.geom.Line2D;


public class HitboxBar {
	private boolean touching;
	private Line2D bar;
	
	public HitboxBar() {
		bar = new Line2D.Double();
	}
	
	public void move(double x1, double y1, double x2, double y2) {
		bar.setLine(x1, y1, x2, y2);
		
	}

	public Line2D getBar() {
		return bar;
	}

	public boolean isTouching() {
		return touching;
	}

	public void setTouching(boolean touching) {
		this.touching = touching;
	}
}
