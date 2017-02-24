package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.List;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Renderer;
import client.graphics.Window;
import client.input.Cursor;
import client.level.Level;
import client.level.pathfinding.PathFindingTester;
import client.main.ClientUpdateLoop;
import data.ColorData;
import data.Data;
import data.GraphicsData;
import util.Util;

public class Debug implements Data, ColorData {
	private static boolean debugText = true, losLine = true, drawWeapons = true, drawDebugPathfinding = true;
	private final static int textX = 25, textY = 30, textSize = 15;

	public static void drawDebug() {
		if (DEBUG) {
			if (debugText){
				StringBuilder text = new StringBuilder();

				text.append("I forgot = "+ClientUpdateLoop.getCurrentUpdateTime()+"$");
				text.append("Window = "+Window.width()+"x"+Window.height()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Camera.getScale()+"$");
				text.append("Zoomed = "+Camera.inZoom()+"$");
				text.append("Render Distance = "+GraphicsData.getRenderDistanceX()+", "+GraphicsData.getRenderDistanceY()+"$");
				text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
				text.append("X, Y Exact = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")"+"$");
				text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS+"$");
				text.append("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY()+"$");
				text.append("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY()+"$");
				text.append("Facing = "+((float)Math.toDegrees(Game.getPlayer().getFacing())+((Game.getPlayer().getFacing()<0)?360:0))+" ("+Game.getPlayer().getFacing()+")"+"$");
				text.append("Cursor = "+Cursor.getScreenX()+","+Cursor.getScreenY()+" ("+Cursor.getPlayerX()+","+Cursor.getPlayerY()+")"+"$");
				text.append("Active Gun = "+Game.getPlayer().getActiveGun()+" Cooldown = "+Game.getPlayer().getActiveGun().getCooldown()+"$");
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
			}
			drawPath(PathFindingTester.linesAStar, Color.BLUE, 2);
//			drawPath(PathFindingTester.linesMaze, Color.CYAN, 2);
		}
	}

	public static void drawPath(List<Point> lines, Color color, int size) {
		if (drawDebugPathfinding) {
			Renderer.getG().setColor(color);
			Renderer.getG().setStroke(new BasicStroke(size));
			for (int i = 1;i<lines.size();i++) {
				Renderer.getG().drawLine((int)(Renderer.gridX(lines.get(i-1).x)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(lines.get(i-1).y)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridX(lines.get(i).x)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(lines.get(i).y)+Renderer.getHalfPlayerSize()));
			}
			Renderer.getG().setColor(Color.green);
			Renderer.getG().setStroke(new BasicStroke(7));
			Renderer.getG().drawLine((int)(Renderer.gridX(PathFindingTester.x1)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y1)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridX(PathFindingTester.x1)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y1)+Renderer.getHalfPlayerSize()));
			Renderer.getG().drawLine((int)(Renderer.gridX(PathFindingTester.x2)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y2)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridX(PathFindingTester.x2)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y2)+Renderer.getHalfPlayerSize()));
		}
	}

	public static void toggleText() {
		debugText = !debugText;
	}

	public static boolean isDebugText() {
		return debugText;
	}

	public static boolean isLosLine() {
		return losLine;
	}

	public static void toggleLOS() {
		losLine = !losLine;
	}

	public static boolean isDrawWeapons() {
		return drawWeapons;
	}

	public static void toggleDrawWeapons() {
		drawWeapons = !drawWeapons;
	}

	public static void toggleDrawDebugPathfinding() {
		drawDebugPathfinding = !drawDebugPathfinding;
	}


}
