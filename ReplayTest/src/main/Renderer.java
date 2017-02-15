package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import data.ColorData;
import data.PlayerData;
import game.Game;
import level.Level;
import player.HitboxBar;
import player.HitboxPoint;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData {
	private Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		Graphics2D g = (Graphics2D) g0; 
		super.paintComponent(g);
		drawTiles();
//		drawPlayers();
		drawPlayer();
		
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.MAGENTA);
		for (HitboxBar bar:Game.getPlayer().getHitboxBars()) {
			g.fill(bar.getBar());
		}
		/*
		g.setStroke(new BasicStroke(5));
		for (HitboxPoint[] points:Game.getPlayer().getHitboxPoints()) {
			for (HitboxPoint point:points) {
				if (point.isTouching()) {
					g.setColor(Color.GREEN);
				}
				else {
					g.setColor(Color.MAGENTA);
				}
				g.drawLine((int)(point.getX()*Main.getScale()), (int)(point.getY()*Main.getScale()), (int)(point.getX()*Main.getScale()), (int)(point.getY()*Main.getScale()));
			}
		}*/
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		g.fill(Game.getPlayer().getBounds(0, 0));
		g.drawLine(Main.getWINDOW_WIDTH()/2, Main.getWINDOW_HEIGHT()/2, (int)(Game.getPlayer().getX()*Main.getScale()+PLAYER_SIZE*Main.getScale()/2), (int)(Game.getPlayer().getY()*Main.getScale()+PLAYER_SIZE*Main.getScale()/2));
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				Rectangle2D b = Level.getTile(r, c).getBounds();//for scrolling (maybe)
				g.fill(new Rectangle((int)(b.getX()*Main.getScale()), (int)(b.getY()*Main.getScale()), (int)(b.getWidth()*Main.getScale()), (int)(b.getHeight()*Main.getScale())));

			}
		}
	}

}
