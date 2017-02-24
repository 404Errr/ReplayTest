package client.player.ai;

import java.awt.geom.Line2D;

import client.player.Player;
import util.Util;

public class SightLine {

	private Player owner, target;
	private Line2D line;
	private boolean canSee;

	public SightLine(Player owner, Player target) {
		this.owner = owner;
		this.target = target;
		line = new Line2D.Float();
	}

	public void updateLine() {
		line.setLine(owner.getX(), owner.getY(), target.getX(), target.getY());
	}

	public void tick() {
		updateLine();
		canSee = canSee();
	}

	public boolean canSee() {
		return Util.distance(line.getX1(), line.getY1(), line.getX2(), line.getY2())<10;
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
