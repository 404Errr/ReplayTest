package client.graphics;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import client.game.Game;
import client.input.Cursor;
import client.level.Level;
import client.projectile.Projectile;
import shared.data.ColorData;
import shared.data.Data;
import shared.data.PlayerData;
import shared.data.WindowData;
import shared.util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, WindowData {
	private Graphics2D g;
	private static boolean debug = true;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		drawTiles();
		if (debug) drawDebug();
		drawProjectiles();
		drawPlayer();





	}

	private void drawProjectiles() {
		for (int i = 0;i<Game.getProjectiles().size();i++) {
			drawProjectile(Game.getProjectiles().get(i));
		}
	}

	private void drawProjectile(Projectile projectile) {
		g.setColor(projectile.getColor());
		double x = projectile.getX(), y = projectile.getY(), size = projectile.getSize();
//		g.fill(new Ellipse2D.Double((x-size/2)*Window.getScale(), (y-size/2)*Window.getScale(), size*Window.getScale(), size*Window.getScale()));
		g.fill(new Ellipse2D.Double((x-Camera.getX()-size/2-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2, (y-Camera.getY()-size/2-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2, size*Window.getScale(), size*Window.getScale()));
	}

	private void drawDebug() {
		g.setColor(COLOR_DEBUG_GREEN);
		g.setFont(new Font("Helvetica", Font.BOLD, 15));
		g.drawString("Window = "+Window.getWindowWidth()+"x"+Window.getWindowHeight()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Window.getScale()+" Zoomed = "+Camera.cursorZoom(), 20, 30);
		g.drawString("X, Y = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile(), 20, 45);
		g.drawString("X, Y = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")", 20, 60);
		g.drawString("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS, 20, 75);
		g.drawString("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY(), 20, 90);
		g.drawString("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY(), 20, 105);
		double a = Math.toDegrees(Game.getPlayer().getFacing());
		if (a<0) a+=360;
		g.drawString("Facing = "+Game.getPlayer().getFacing()+" ("+a+")", 20, 120);
		g.drawString("Cursor = "+Cursor.getX()+","+Cursor.getY()+" ("+Cursor.getXPlayer()+","+Cursor.getYPlayer()+")", 20, 135);

		g.setColor(COLOR_DEBUG_GREEN);
		g.setStroke(new BasicStroke(1));
		final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2, lineLength = 150;
		g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));

		g.drawLine(w, h, (int)(Cursor.getXPlayer()*Window.getScale()+w), (int)(Cursor.getYPlayer()*Window.getScale()+h));
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		int offset = 0;
		if (Window.getScale()%2!=0) offset = -1;
		if (Camera.cursorZoom()) {
			g.fillRect(Window.getWindowWidth()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset-(int)(Cursor.getXPlayer()*Window.getScale()), Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset-(int)(Cursor.getYPlayer()*Window.getScale()), (int)(PLAYER_SIZE*Window.getScale()), (int)(PLAYER_SIZE*Window.getScale()));
		}
		else {
			g.fillRect(Window.getWindowWidth()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset, Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset, (int)(PLAYER_SIZE*Window.getScale()), (int)(PLAYER_SIZE*Window.getScale()));
		}
	}

	private void drawTiles() {
		if (Game.getPlayer()==null) return;
		int renderDistanceX = RENDER_DISTANCE_X, renderDistanceY = RENDER_DISTANCE_Y;
		if ((renderDistanceX<0||renderDistanceY<0)&&Window.getScale()!=0) {
			renderDistanceX = Window.getWindowWidth()/Window.getScale()/2+2;
			renderDistanceY = Window.getWindowHeight()/Window.getScale()/2+2;
		}
		for (int r = Camera.getYTile()-renderDistanceY;r<Camera.getYTile()+renderDistanceY;r++) {
			for (int c = Camera.getXTile()-renderDistanceX;c<Camera.getXTile()+renderDistanceX;c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
					g.fillRect((int)((c-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((r-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2), Window.getScale(), Window.getScale());
				}
			}
		}
	}

	public static void toggleDebug() {
		debug^=true;
	}
}
