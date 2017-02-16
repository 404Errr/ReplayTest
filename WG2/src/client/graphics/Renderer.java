package client.graphics;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import client.game.Game;
import client.level.Level;
import data.ColorData;
import data.PlayerData;
import main.Main;
import util.Util;

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

		drawDebug();

		drawPlayer();

	}

	private void drawDebug() {
		g.setColor(COLOR_DEBUG_GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 15));
		g.drawString("x, y: "+Game.getPlayer().getX()+","+Game.getPlayer().getY(), 20, 30);
		g.drawString("dx, dy: "+Game.getPlayer().getdX()+","+Game.getPlayer().getdY(), 20, 45);
		g.drawString("ddx, ddy: "+Game.getPlayer().getddX()+","+Game.getPlayer().getddY(), 20, 60);
		g.drawString("Facing: "+Game.getPlayer().getFacing()+" ("+Math.toDegrees(Game.getPlayer().getFacing())+")", 20, 75);

		//direction player is facing
		g.setStroke(new BasicStroke(1));
		final int w = Main.getSCREEN_WIDTH()/2, h = Main.getSCREEN_HEIGHT()/2;
		g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), 100)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), 100)+h));
	}

	private void drawPlayer() {
		int p = (int)(PLAYER_SIZE*Main.getScale()), w = Main.getSCREEN_WIDTH()/2, h = Main.getSCREEN_HEIGHT()/2;
		g.setColor(COLOR_PLAYER);
		if (!Camera.lockToPlayer()) g.fill(Game.getPlayer().getBounds(0, 0));
		else g.fillRect(w-p/2, h-p/2, p, p);

	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				Rectangle2D b = Level.getTile(r, c).getBounds();//for scrolling (maybe)
				g.fill(new Rectangle((int)((b.getX()-Camera.getX())*Main.getScale()-PLAYER_SIZE/2*Main.getScale()+((Camera.lockToPlayer())?Main.getSCREEN_WIDTH()/2:0)), (int)((b.getY()-Camera.getY())*Main.getScale()-PLAYER_SIZE/2*Main.getScale()+((Camera.lockToPlayer())?Main.getSCREEN_HEIGHT()/2:0)), (int)(b.getWidth()*Main.getScale()), (int)(b.getHeight()*Main.getScale())));

			}
		}
	}

}
