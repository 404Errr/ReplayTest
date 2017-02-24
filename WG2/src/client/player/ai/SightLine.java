package client.player.ai;

import java.awt.geom.Line2D;

import client.level.Level;
import client.player.Player;
import data.PlayerData;
import data.TileData;

public class SightLine implements TileData, PlayerData {

	private Player owner, target;
	private Line2D line;
	private boolean canSee;

	public SightLine(Player owner, Player target) {
		this.owner = owner;
		this.target = target;
		line = new Line2D.Float();
	}

	public void updateLine() {
		line.setLine(owner.getX()+HALF_PLAYER_SIZE, owner.getY()+HALF_PLAYER_SIZE, target.getX()+HALF_PLAYER_SIZE, target.getY()+HALF_PLAYER_SIZE);
	}

	public void tick() {
		updateLine();
		canSee = canSee();
	}

	public boolean canSee() {
		int x1 = (int)line.getX1(), y1 = (int)line.getY1(), x2 = (int)line.getX2(), y2 = (int)line.getY2();
		int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
		int sx = x1 < x2 ? 1 : -1,  sy = y1 < y2 ? 1 : -1;
		int err = dx-dy;
		int e2;

		while (true) {
			if (Level.getTile(x1, y1).isSolid(SOLID_WALLS)) return false;
			if (x1==x2&&y1==y2) return true;
			e2 = 2*err;
			if (e2>-dy) {
				err = err-dy;
				x1 = x1+sx;
			}
			if (e2 < dx) {
				err = err+dx;
				y1 = y1+sy;
			}
		}

	}

	public Player getOwner() {
		return owner;
	}

	public Player getTarget() {
		return target;
	}

	public Line2D getLine() {
		return line;
	}

	public boolean getCanSee() {
		return canSee;
	}



}
