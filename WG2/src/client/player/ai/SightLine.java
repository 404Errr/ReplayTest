package client.player.ai;

import java.awt.geom.Line2D;

import client.player.Player;
import util.BreakableLineConstant;

public class SightLine extends BreakableLineConstant {

	private Player owner, target;
	private Line2D line;

	public SightLine(Player owner, Player target, boolean[][] breaks) {
		super(breaks);
		this.owner = owner;
		this.target = target;
		line = new Line2D.Float();
	}

	@Override
	public void setLocation() {
		x1 = owner.getX()+HALF_PLAYER_SIZE;
		y1 = owner.getY()+HALF_PLAYER_SIZE;
		x2 = target.getX()+HALF_PLAYER_SIZE;
		y2 = target.getY()+HALF_PLAYER_SIZE;
	}

	@Override
	public void tick() {
		super.tick();
		line.setLine(x1, y1,x2, y2);
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
}
