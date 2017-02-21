package main;

import java.awt.BasicStroke;
import java.awt.Font;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Renderer;
import client.graphics.Window;
import client.input.Cursor;
import client.level.Level;
import data.ColorData;
import data.Data;
import data.GraphicsData;
import util.Util;

public class Debug implements Data, ColorData {
	private static boolean debugText = true, losLine = true, drawWeapons = true;
	private final static int textX = 25, textY = 30, textSize = 15;

	public static void drawDebug() {
		if (DEBUG) {
			if (debugText){

				StringBuilder text = new StringBuilder();

				text.append("Window = "+Window.width()+"x"+Window.height()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Camera.getScale()+"$");
				text.append("Zoomed = "+Camera.inZoom()+"$");
				text.append("Render Distance = "+GraphicsData.getRenderDistanceX()+", "+GraphicsData.getRenderDistanceY()+"$");
				text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
				text.append("X, Y Exact = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")"+"$");
				text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS+"$");
				text.append("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY()+"$");
				text.append("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY()+"$");
				double a = Math.toDegrees(Game.getPlayer().getFacing());if (a<0) a+=360;//get the angle of the player in degrees
				text.append("Facing = "+(float)a+" ("+Game.getPlayer().getFacing()+")"+"$");
				text.append("Cursor = "+Cursor.getScreenX()+","+Cursor.getScreenY()+" ("+Cursor.getPlayerX()+","+Cursor.getPlayerY()+")"+"$");
				//text.append("Active Gun = "+Game.getPlayer().getActiveGun()+" Cooldown = "+(float)Game.getPlayer().getActiveGun().getCooldown()+"$");
				text.append("Debug Text = true, LOS Line = "+losLine+"$");

				String[] textLines = text.toString().split("\\$");
				Renderer.getG().setColor(COLOR_DEBUG_GREEN);
				Renderer.getG().setFont(new Font("Helvetica", Font.BOLD, textSize));
				for (int i = 0;i<textLines.length;i++) {
					Renderer.getG().drawString(textLines[i], textX, textY+textSize*i);
				}
			}

			if (losLine) {
				Renderer.getG().setColor(COLOR_DEBUG_GREEN);
				Renderer.getG().setStroke(new BasicStroke(1));
				final int w = Window.centerX(), h = Window.centerY(), lineLength = Math.max(Window.width(), Window.height())*2;
				final int cursorPlayerX = (int) (Cursor.getPlayerX()*Camera.getScale()), cursorPlayerY = (int) (Cursor.getPlayerY()*Camera.getScale());
				if (Camera.inZoom()) {
					Renderer.getG().drawLine(w-cursorPlayerX, h-cursorPlayerY, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w+cursorPlayerX), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h+cursorPlayerY));//los
				}
				else {
					Renderer.getG().drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));//los
				}
				/*g.setColor(COLOR_DEBUG_GREEN);
				g.drawLine(0, 0, Window.centerX(), Window.centerY());
				g.drawLine(0, 0, (int)(Window.centerX()+Cursor.getPlayerX()*Camera.getScale()), (int)(Window.centerY()+Cursor.getPlayerY()*Camera.getScale()));
				g.drawLine(0, 0, Cursor.getScreenX(), Cursor.getScreenY());
				g.drawLine(0, 0, (int)getXOrigin(), (int)getYOrigin());*/
			}
		}
	}

	public static void toggleText() {
		debugText = !debugText;
	}

	public static void toggleLOS() {
		losLine = !losLine;
	}

	public static void toggleDrawWeapons() {
		drawWeapons = !drawWeapons;
	}

	public static boolean isDebugText() {
		return debugText;
	}

	public static boolean isLosLine() {
		return losLine;
	}

	public static boolean isDrawWeapons() {
		return drawWeapons;
	}


}
