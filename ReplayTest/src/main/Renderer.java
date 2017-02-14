package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import data.ColorData;
import data.PlayerData;
import game.Game;
import level.Level;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData {
	private Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		drawTiles();
//		drawPlayers();
		drawPlayer();
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		g.fill(new Rectangle(Game.getPlayer().getX(), Game.getPlayer().getY(), PLAYER_SIZE, PLAYER_SIZE));

		g.drawLine(Main.getWINDOW_WIDTH()/2, Main.getWINDOW_HEIGHT()/2, Game.getPlayer().getX()+PLAYER_SIZE/2, Game.getPlayer().getY()+PLAYER_SIZE/2);
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				Rectangle2D b = Level.getTile(r, c).getBounds();
				g.fill(new Rectangle((int)b.getX(), (int)b.getY(), (int)b.getWidth(), (int)b.getHeight()));

			}
		}
	}

}