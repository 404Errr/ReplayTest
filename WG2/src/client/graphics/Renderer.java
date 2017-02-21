package client.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import client.game.Game;
import client.input.Cursor;
import client.level.Level;
import data.ColorData;
import data.Data;
import data.GraphicsData;
import data.PlayerData;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, GraphicsData {
	private Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		try {//instead of null checks
			drawTiles();
			drawDebug();
//			drawProjectiles();
//			drawHitscans();
//			if (drawWeapons) drawActiveGun();
			drawPlayer();
//
//			drawPath(PathFind.lines);

			/*g.setColor(COLOR_DEBUG_GREEN);
			g.drawLine(0, 0, Window.centerX(), Window.centerY());
			g.drawLine(0, 0, (int)(Window.centerX()+Cursor.getPlayerX()*Camera.getScale()), (int)(Window.centerY()+Cursor.getPlayerY()*Camera.getScale()));
			g.drawLine(0, 0, Cursor.getScreenX(), Cursor.getScreenY());
			g.drawLine(0, 0, (int)getXOrigin(), (int)getYOrigin());*/
		}
		catch (Exception e) {}
	}

	private void drawDebug() {
		//if (debugText) {
			final int x = 25, y = 30, textSize = 15;
			StringBuilder text = new StringBuilder();

			text.append("Window = "+Window.width()+"x"+Window.height()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Camera.getScale()+" Zoomed = "+Camera.inZoom()+"$");
			text.append("Render Distance = "+GraphicsData.getRenderDistanceX()+", "+GraphicsData.getRenderDistanceY()+"$");
			text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
			text.append("X, Y Exact = ("+(float)Game.getPlayer().getX()+", "+(float)Game.getPlayer().getY()+")"+"$");
			text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS+"$");
			text.append("dx, dy = "+(float)Game.getPlayer().getdX()+", "+(float)Game.getPlayer().getdY()+"$");
			text.append("ddx, ddy = "+(float)Game.getPlayer().getddX()+", "+(float)Game.getPlayer().getddY()+"$");
			double a = Math.toDegrees(Game.getPlayer().getFacing());if (a<0) a+=360;//get the angle of the player in degrees
			text.append("Facing = "+(float)a+" ("+(float)Game.getPlayer().getFacing()+")"+"$");
			text.append("Cursor = "+Cursor.getScreenX()+","+Cursor.getScreenY()+" ("+(float)Cursor.getPlayerX()+","+(float)Cursor.getPlayerY()+")"+"$");
			//text.append("Active Gun = "+Game.getPlayer().getActiveGun()+" Cooldown = "+(float)Game.getPlayer().getActiveGun().getCooldown()+"$");
			//text.append("Debug Text = true, LOS Line = "+debugLOSLine+"$");

			String[] textLines = text.toString().split("\\$");
			g.setColor(COLOR_DEBUG_GREEN);
			g.setFont(new Font("Helvetica", Font.BOLD, textSize));
			for (int i = 0;i<textLines.length;i++) {
				g.drawString(textLines[i], x, y+textSize*i);
			}
		//}
		/*if (debugLOSLine) {
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
		}*/
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		g.fillRect((int)(getPlayerX()-getHalfPlayerSize()), (int)(getPlayerY()-getHalfPlayerSize()), (int)getPlayerSize(), (int)getPlayerSize());
	}

	private void drawTiles() {
		for (int r = Camera.getYTile()-GraphicsData.getRenderDistanceY();r<=Camera.getYTile()+GraphicsData.getRenderDistanceY();r++) {
			for (int c = Camera.getXTile()-GraphicsData.getRenderDistanceX();c<=Camera.getXTile()+GraphicsData.getRenderDistanceX();c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					g.setColor(Level.getTile(r, c).getColor());
					g.fillRect(getGridX(c), getGridY(r), Camera.getScale(), Camera.getScale());
				}
			}
		}
	}

//	private void drawTiles() {
//		for (int r = 0;r<Level.getHeight();r++) {
//			for (int c = 0;c<Level.getWidth();c++) {
//				g.setColor(Level.getTile(r, c).getColor());
//				g.fillRect(getGridX(c), getGridY(r), Camera.getScale(), Camera.getScale());
//			}
//		}
//	}

	private static int getGridX(double x) {
		return (int)(x*Camera.getScale()+getXOrigin());
	}

	private static int getGridY(double y) {
		return (int)(y*Camera.getScale()+getYOrigin());
	}

	private static double getXOrigin() {
		return (-Camera.getX()-HALF_PLAYER_SIZE)*Camera.getScale()+Window.centerX();
	}

	private static double getYOrigin() {
		return (-Camera.getY()-HALF_PLAYER_SIZE)*Camera.getScale()+Window.centerY();
	}
	//return Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset-(int)(Cursor.getYPlayer()*Window.getScale());
	private static double getPlayerX() {
		return Window.centerX()-Cursor.getPlayerX()*Camera.getScale();
	}

	private static double getPlayerY() {
		return Window.centerY()-Cursor.getPlayerY()*Camera.getScale();
	}

	private static double getPlayerSize() {
		return PLAYER_SIZE*Camera.getScale();
	}

	private static double getHalfPlayerSize() {
		return HALF_PLAYER_SIZE*Camera.getScale();
	}
}
