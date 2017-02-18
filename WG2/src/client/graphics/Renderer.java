package client.graphics;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import client.game.Game;
import client.level.Level;
import data.ColorData;
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

		System.out.println("");//TODO fix player offset

	}

	private void drawDebug() {
		g.setColor(COLOR_DEBUG_GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 15));
		g.drawString("x, y: "+Game.getPlayer().getX()+", "+Game.getPlayer().getY(), 20, 30);
		g.drawString("dx, dy: "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY(), 20, 45);
		g.drawString("ddx, ddy: "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY(), 20, 60);
		g.drawString("Facing: "+Game.getPlayer().getFacing()+" ("+Math.toDegrees(Game.getPlayer().getFacing())+")", 20, 75);

		//direction player is facing
		g.setStroke(new BasicStroke(1));
		final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2;
		g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), 100)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), 100)+h));
	}

	private void drawPlayer() {
		int p = (int)(PLAYER_SIZE*Window.getScale()), w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2;
		g.setColor(COLOR_PLAYER);
		g.fillRect(w-(int)(PLAYER_SIZE*Window.getScale()/2), h-(int)(PLAYER_SIZE*Window.getScale()/2), (int)(PLAYER_SIZE*Window.getScale()), (int)(PLAYER_SIZE*Window.getScale()));
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				g.fillRect(
					(int)(c*Window.getScale()-Camera.getX()*Window.getScale()+Window.getWindowWidth()/2),
					(int)(r*Window.getScale()-Camera.getY()*Window.getScale()+Window.getWindowHeight()/2),
					Window.getScale(),
					Window.getScale()
				);
//				Rectangle2D b = Level.getTile(r, c).getBounds();//for scrolling (maybe)
//				g.fill(new Rectangle((int)((b.getX()-Camera.getX())*Window.getScale()-PLAYER_SIZE/2*Window.getScale()+Window.getWindowWidth()/2), (int)((b.getY()-Camera.getY())*Window.getScale()-PLAYER_SIZE/2*Window.getScale()+Window.getWindowHeight()/2), (int)(b.getWidth()*Window.getScale()), (int)(b.getHeight()*Window.getScale())));

			}
		}
	}

	public static void toggleDebug() {
		Renderer.debug ^= true;
	}



}
