package main;

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

public class Debug implements Data, ColorData {
	public static void drawDebug() {
		if (DEBUG) {
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
			Renderer.getG().setColor(COLOR_DEBUG_GREEN);
			Renderer.getG().setFont(new Font("Helvetica", Font.BOLD, textSize));
			for (int i = 0;i<textLines.length;i++) {
				Renderer.getG().drawString(textLines[i], x, y+textSize*i);
			}
		//}
//		if (debugLOSLine) {
//			g.setColor(COLOR_DEBUG_GREEN);
//			g.setStroke(new BasicStroke(1));
//			final int w = Window.getWindowWidth()/2, h = Window.getWindowHeight()/2, lineLength = Math.max(Window.getWindowWidth(), Window.getWindowHeight())*2;
//			final int cursorPlayerX = (int) (Cursor.getXPlayer()*Window.getScale()), cursorPlayerY = (int) (Cursor.getYPlayer()*Window.getScale());
//			if (Camera.cursorZoom()) {
//				g.drawLine(w-cursorPlayerX, h-cursorPlayerY, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w+cursorPlayerX), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h+cursorPlayerY));//los
//			}
//			else {
//				g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));//los
//			}
			/*g.setColor(COLOR_DEBUG_GREEN);
			g.drawLine(0, 0, Window.centerX(), Window.centerY());
			g.drawLine(0, 0, (int)(Window.centerX()+Cursor.getPlayerX()*Camera.getScale()), (int)(Window.centerY()+Cursor.getPlayerY()*Camera.getScale()));
			g.drawLine(0, 0, Cursor.getScreenX(), Cursor.getScreenY());
			g.drawLine(0, 0, (int)getXOrigin(), (int)getYOrigin());*/
		}
	}

}
