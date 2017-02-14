package player;

public class HitboxPoint {
	private int x, y;
	private boolean touching;
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean getTouching() {
		return touching;
	}
	
	public void setTouching(boolean touching) {
		this.touching = touching;
	}
	
	
}