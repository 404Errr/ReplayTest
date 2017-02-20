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
import client.projectile.Hitscan;
import client.projectile.Projectile;
import client.weapon.GunType;
import shared.data.ColorData;
import shared.data.Data;
import shared.data.PlayerData;
import shared.data.WeaponData;
import shared.data.WindowData;
import shared.util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, WindowData {
	private Graphics2D g;
	private static boolean debugText = true, debugCursorLine = false, debugLOSLine = true;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		try {//instead of null checks
			drawTiles();
			drawDebug();
			drawProjectiles();
			drawHitscans();
			drawActiveGun();
			drawPlayer();
		}
		catch (Exception e) {}
	}

	private void drawHitscans() {
		for (int i = 0;i<Game.getHitscans().size();i++) {
			drawHitscan(Game.getHitscans().get(i));
		}
	}

	private void drawHitscan(Hitscan hitscan) {
		g.setColor(Util.colorOpacity(hitscan.getColor(),(float)((hitscan.getWidth()/WeaponData.RAILGUN_INITIAL_WIDTH*0.6f)+0.4f)));
		g.setStroke(new BasicStroke((int)(hitscan.getWidth()*Window.getScale())));
		g.drawLine((int)((hitscan.getiX()-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((hitscan.getiY()-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2),	(int)((hitscan.getfX()-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((hitscan.getfY()-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2));
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
		final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2, lineLength = (int)((gun.getWangLength()+PLAYER_SIZE/2)*Window.getScale());
		if (Camera.cursorZoom()) {
			g.drawLine(w-(int)(Cursor.getXPlayer()*Window.getScale()), h-(int)(Cursor.getYPlayer()*Window.getScale()), (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w)-(int)(Cursor.getXPlayer()*Window.getScale()), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h)-(int)(Cursor.getYPlayer()*Window.getScale()));
		}
		else {
			g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));
		}
	}

	private void drawDebug() {
		if (debugText) {
			final int x = 20, y = 30, textSize = 15;
			StringBuilder text = new StringBuilder();

			text.append("Window = "+Window.getWindowWidth()+"x"+Window.getWindowHeight()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Window.getScale()+" Zoomed = "+Camera.cursorZoom()+"$");
			text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
			text.append("X, Y Exact = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")"+"$");
			text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS+"$");
			text.append("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY()+"$");
			text.append("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY()+"$");
			double a = Math.toDegrees(Game.getPlayer().getFacing());if (a<0) a+=360;
			text.append("Facing = "+a+" ("+Game.getPlayer().getFacing()+")"+"$");
			text.append("Cursor = "+Cursor.getX()+","+Cursor.getY()+" ("+Cursor.getXPlayer()+","+Cursor.getYPlayer()+")"+"$");
			text.append("Active Gun = "+Game.getPlayer().getActiveGun()+" Cooldown = "+Game.getPlayer().getActiveGun().getCooldown()+"$");
			text.append("Debug Text = true, LOS Line = "+debugLOSLine+", Cursor Line = "+debugCursorLine+"$");

			String[] textLines = text.toString().split("\\$");
			g.setColor(COLOR_DEBUG_GREEN);
			g.setFont(new Font("Helvetica", Font.BOLD, textSize));
			for (int i = 0;i<textLines.length;i++) {
				g.drawString(textLines[i], x, y+textSize*i);
			}
		}
		if (debugCursorLine||debugLOSLine) {
			g.setColor(COLOR_DEBUG_GREEN);
			g.setStroke(new BasicStroke(1));
			final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2, lineLength = Math.max(Window.getWindowWidth(), Window.getWindowHeight())*2;
			final int cursorPlayerX = (int) (Cursor.getXPlayer()*Window.getScale()), cursorPlayerY = (int) (Cursor.getYPlayer()*Window.getScale());
			if (Camera.cursorZoom()) {
				if (debugLOSLine) g.drawLine(w-cursorPlayerX, h-cursorPlayerY, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w+cursorPlayerX), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h+cursorPlayerY));//los
				if (debugCursorLine) g.drawLine(w-cursorPlayerX, h-cursorPlayerY, w+cursorPlayerX, h+cursorPlayerY);//player to cursor
			}
			else {
				if (debugLOSLine) g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));//los
				if (debugCursorLine) g.drawLine(w, h, w+cursorPlayerX, h+cursorPlayerY);//player to cursor
			}
		}
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
					g.setColor(Level.getTile(r, c).getColor());
					g.fillRect((int)((c-Camera.getX()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowWidth()/2), (int)((r-Camera.getY()-PLAYER_SIZE/2)*Window.getScale()+Window.getWindowHeight()/2), Window.getScale(), Window.getScale());
				}
			}
		}
	}

	public static void toggleDebugText() {
		debugText^=true;
	}

	public static void toggleDebugLOSLine() {
		debugLOSLine^=true;
	}

	public static void toggleDebugCursorLine() {
		debugCursorLine^=true;
	}
}
