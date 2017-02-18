package client.graphics;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import client.game.Game;
import client.level.Level;
import data.ColorData;
import data.Data;
import data.PlayerData;
import util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData {
	private Graphics2D g;
	private static boolean debug = true;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		Graphics2D g = (Graphics2D) g0;
		super.paintComponent(g);
		drawTiles();
//		drawPlayers();

		if (debug) drawDebug();

		drawPlayer();

//		System.out.println("");//TODO fix player offset

	}

	private void drawDebug() {
		g.setColor(COLOR_DEBUG_GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 15));
		g.drawString("Window size = "+Window.getWindowWidth()+"x"+Window.getWindowHeight()+" Scale = "+Window.getScale(), 20, 30);
		g.drawString("X, Y = "+Math.round(Game.getPlayer().getX())+", "+Math.round(Game.getPlayer().getY()), 20, 45);
		g.drawString("X, Y = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")", 20, 60);
		g.drawString("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS, 20, 75);
		g.drawString("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY(), 20, 90);
		g.drawString("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY(), 20, 105);
		g.drawString("Facing = "+Game.getPlayer().getFacing()+" ("+Math.toDegrees(Game.getPlayer().getFacing())+")", 20, 120);

		g.setStroke(new BasicStroke(1));
		final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2;
		g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), 100)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), 100)+h));
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		int offset = 0;
		if (Window.getScale()%2!=0) offset = -1;
		g.fillRect(Window.getWindowWidth()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset, Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset, (int)(PLAYER_SIZE*Window.getScale()), (int)(PLAYER_SIZE*Window.getScale()));
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				g.fillRect((int)((c-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((r-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2), Window.getScale(), Window.getScale());
			}
		}
	}

	public static void toggleDebug() {
		debug^=true;
	}
}
