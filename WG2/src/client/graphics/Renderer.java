package client.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import client.ai.PathFind;
import client.game.Game;
import client.input.Cursor;
import client.level.Level;
import client.projectile.Hitscan;
import client.projectile.Projectile;
import client.weapon.GunType;
import shared.data.ColorData;
import shared.data.Data;
import shared.data.GraphicsData;
import shared.data.PlayerData;
import shared.util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, GraphicsData {
	private Graphics2D g;
	private static boolean debugText = true, debugLOSLine = true, drawWeapons = true;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		try {//instead of null checks
			drawTiles();
			drawDebug();
			drawProjectiles();
			drawHitscans();
			if (drawWeapons) drawActiveGun();
			drawPlayer();

			drawPath(PathFind.lines);
		}
		catch (Exception e) {}
	}

	private void drawHitscans() {
		for (int i = 0;i<Game.getHitscans().size();i++) {
			drawHitscan(Game.getHitscans().get(i));
		}
	}

	private void drawHitscan(Hitscan hitscan) {
		g.setColor(Util.colorOpacity(hitscan.getColor(), hitscan.getOpacity()));
		g.setStroke(new BasicStroke((int)(hitscan.getWidth()*Window.getScale())));

		//g.fillRect((int)(c*Window.getScale()+getX0()), (int)(r*Window.getScale()+getY0()), Window.getScale(), Window.getScale());
		g.drawLine((int)(hitscan.getiX()*Window.getScale()+getXOrigin()), (int)((hitscan.getiY()-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2),	(int)((hitscan.getfX()-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((hitscan.getfY()-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2));
//		g.drawLine((int)((hitscan.getiX()-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((hitscan.getiY()-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2),	(int)((hitscan.getfX()-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((hitscan.getfY()-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2));
	}

	private void drawProjectiles() {
		for (int i = 0;i<Game.getProjectiles().size();i++) {
			drawProjectile(Game.getProjectiles().get(i));
		}
	}

	private void drawProjectile(Projectile projectile) {
		g.setColor(projectile.getColor());
		double x = projectile.getX(), y = projectile.getY(), size = projectile.getSize();
		g.fill(new Ellipse2D.Double((x-Camera.getX()-size/2-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2, (y-Camera.getY()-size/2-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2, size*Window.getScale(), size*Window.getScale()));
	}

	private void drawActiveGun() {
		GunType gun = Game.getPlayer().getActiveGun().getType();
		g.setColor(Game.getPlayer().getColor());
		g.setStroke(new BasicStroke((float)(gun.getWangWidth()*Window.getScale())));
		g.drawLine(getPlayerX()+getPlayerSize()/2, getPlayerY()+getPlayerSize()/2, (int)(Util.getXComp(Game.getPlayer().getFacing(), (gun.getWangLength()+PLAYER_SIZE/2)*Window.getScale())+getPlayerX()+getPlayerSize()/2), (int)(-Util.getYComp(Game.getPlayer().getFacing(), (gun.getWangLength()+PLAYER_SIZE/2)*Window.getScale())+getPlayerY()+getPlayerSize()/2));
	}

	private void drawDebug() {
		if (debugText) {
			final int x = 25, y = 30, textSize = 15;
			StringBuilder text = new StringBuilder();

			text.append("Window = "+Window.getWindowWidth()+"x"+Window.getWindowHeight()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Window.getScale()+" Zoomed = "+Camera.cursorZoom()+"$");
			text.append("Render Distance = "+GraphicsData.getRenderDistanceX()+", "+GraphicsData.getRenderDistanceY()+"$");
			text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
			text.append("X, Y Exact = ("+(float)Game.getPlayer().getX()+", "+(float)Game.getPlayer().getY()+")"+"$");
			text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS+"$");
			text.append("dx, dy = "+(float)Game.getPlayer().getdX()+", "+(float)Game.getPlayer().getdY()+"$");
			text.append("ddx, ddy = "+(float)Game.getPlayer().getddX()+", "+(float)Game.getPlayer().getddY()+"$");
			double a = Math.toDegrees(Game.getPlayer().getFacing());if (a<0) a+=360;//get the angle of the player in degrees
			text.append("Facing = "+(float)a+" ("+(float)Game.getPlayer().getFacing()+")"+"$");
			text.append("Cursor = "+Cursor.getX()+","+Cursor.getY()+" ("+(float)Cursor.getXPlayer()+","+(float)Cursor.getYPlayer()+")"+"$");
			text.append("Active Gun = "+Game.getPlayer().getActiveGun()+" Cooldown = "+(float)Game.getPlayer().getActiveGun().getCooldown()+"$");
			text.append("Debug Text = true, LOS Line = "+debugLOSLine+"$");

			String[] textLines = text.toString().split("\\$");
			g.setColor(COLOR_DEBUG_GREEN);
			g.setFont(new Font("Helvetica", Font.BOLD, textSize));
			for (int i = 0;i<textLines.length;i++) {
				g.drawString(textLines[i], x, y+textSize*i);
			}
		}
		if (debugLOSLine) {
			g.setColor(COLOR_DEBUG_GREEN);
			g.setStroke(new BasicStroke(1));
			final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2, lineLength = Math.max(Window.getWindowWidth(), Window.getWindowHeight())*2;
			final int cursorPlayerX = (int) (Cursor.getXPlayer()*Window.getScale()), cursorPlayerY = (int) (Cursor.getYPlayer()*Window.getScale());
			if (Camera.cursorZoom()) {
				g.drawLine(w-cursorPlayerX, h-cursorPlayerY, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w+cursorPlayerX), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h+cursorPlayerY));//los
			}
			else {
				g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));//los
			}
		}
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		g.fillRect(getPlayerX(), getPlayerY(), getPlayerSize(), getPlayerSize());
	}

	private void drawTiles() {
		for (int r = Camera.getYTile()-GraphicsData.getRenderDistanceY();r<=Camera.getYTile()+GraphicsData.getRenderDistanceY();r++) {
			for (int c = Camera.getXTile()-GraphicsData.getRenderDistanceX();c<=Camera.getXTile()+GraphicsData.getRenderDistanceX();c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					g.setColor(Level.getTile(r, c).getColor());
					g.fillRect(getGridX(c), getGridY(r), Window.getScale(), Window.getScale());
				}
			}
		}
	}

	public void drawPath(ArrayList<Point> points) {
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(2));
		for (int i = 1;i<points.size();i++) {
			g.drawLine(getGridX(points.get(i-1).x)+getPlayerSize()/2, getGridY(points.get(i-1).y)+getPlayerSize()/2, getGridX(points.get(i).x)+getPlayerSize()/2, getGridY(points.get(i).y)+getPlayerSize()/2);
		}
		g.setColor(Color.green);
		g.setStroke(new BasicStroke(7));
		g.drawLine(getGridX(PathFind.x1)+getPlayerSize()/2, getGridY(PathFind.y1)+getPlayerSize()/2, getGridX(PathFind.x1)+getPlayerSize()/2, getGridY(PathFind.y1)+getPlayerSize()/2);
		g.drawLine(getGridX(PathFind.x2)+getPlayerSize()/2, getGridY(PathFind.y2)+getPlayerSize()/2, getGridX(PathFind.x2)+getPlayerSize()/2, getGridY(PathFind.y2)+getPlayerSize()/2);
	}

	private static int getGridX(double x) {
		return (int)(x*Window.getScale()+getXOrigin());
	}

	private static int getGridY(double y) {
		return (int)(y*Window.getScale()+getYOrigin());
	}

	private static double getXOrigin() {
		return (-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2;
	}

	private static double getYOrigin() {
		return (-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2;
	}

	private static int getPlayerX() {
		int offset = 0;
		if (Window.getScale()%2!=0) offset = -1;
		if (Camera.cursorZoom()) {
			return Window.getWindowWidth()/2-(int)(PLAYER_SIZE/2*Window.getScale())+offset-(int)(Cursor.getXPlayer()*Window.getScale());
		}
		else {
			return Window.getWindowWidth()/2-(int)(PLAYER_SIZE/2*Window.getScale())+offset;
		}
	}

	private static int getPlayerY() {
		int offset = 0;
		if (Window.getScale()%2!=0) offset = -1;
		if (Camera.cursorZoom()) {
			return Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset-(int)(Cursor.getYPlayer()*Window.getScale());
		}
		else {
			return Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset;
		}
	}

	private static int getPlayerSize() {
		return (int)(PLAYER_SIZE*Window.getScale());
	}

	public static void toggleDebugText() {
		debugText^=true;
	}

	public static void toggleDebugLOSLine() {
		debugLOSLine^=true;
	}

	public static void toggleDrawWeapons() {
		drawWeapons^=true;
	}
}
