package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.List;

import client.edit.Edit;
import client.game.Game;
import client.graphics.Camera;
import client.graphics.Renderer;
import client.graphics.Window;
import client.input.Cursor;
import client.level.Level;
import client.level.SpawnPointVisibiltiyLine;
import client.level.pathfinding.PathFindingTester;
import client.main.ClientUpdateLoop;
import data.ColorData;
import data.ControlData;
import data.Data;
import data.GraphicsData;
import data.MapData;
import util.Util;

public class Debug implements Data, ColorData, MapData, ControlData {
	private static boolean debugText = true, losLine = true, drawWeapons = true, drawDebugPathfinding = true, drawSightLines = false, spawnPointVisibilityLines = true;
	private final static int textX = 25, textY = 30, textSize = 15;

//	public static int[] kills = new int[4];
//	public static void addKill(int type) {
//		kills[type]++;
//	}

	public static void drawDebug() {
//		System.out.println("BASICGUN: "+kills[0]+"\tSHOTGUN: "+kills[1]+"\tMACHINEGUN: "+kills[2]+"\tRAILGUN: "+kills[3]);
		if (DEBUG) {
			if (debugText){
				StringBuilder text = new StringBuilder();

				float ups = UPS/(ClientUpdateLoop.getCurrentUpdateTime()/(1000f/UPS));
				if (ups>UPS) ups = UPS;
				text.append("UPS = "+ups+"$");
//				text.append("I forgot = "+ClientUpdateLoop.getCurrentUpdateTime()+"$");
				text.append("Window = "+Window.width()+"x"+Window.height()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Camera.getScale()+"$");
				text.append("Zoomed = "+Camera.isZoomed()+"$");
				text.append("Render Distance = "+GraphicsData.getRenderDistanceX()+", "+GraphicsData.getRenderDistanceY()+"$");
				text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
				text.append("X, Y Exact = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")"+"$");
				text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*Data.UPS+"$");
				text.append("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY()+"$");
				text.append("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY()+"$");
				text.append("Facing = "+((float)Math.toDegrees(Game.getPlayer().getFacing())+((Game.getPlayer().getFacing()<0)?360:0))+" ("+Game.getPlayer().getFacing()+")"+"$");
				text.append("Cursor = "+Cursor.getScreenX()+","+Cursor.getScreenY()+" ("+Cursor.getPlayerX()+","+Cursor.getPlayerY()+")"+"$");
				text.append("Active Weapon = "+Game.getPlayer().getActiveWeapon()+" Cooldown = "+Game.getPlayer().getActiveWeapon().getCooldown()+"$");
				text.append("Debug Text = true, LOS Line = "+losLine+"$");
				if (Edit.editMode) text.append("Type = "+(char)Edit.getType());

				String[] textLines = text.toString().split("\\$");
				Renderer.getG().setColor(COLOR_DEBUG_GREEN);
				Renderer.getG().setFont(new Font("Helvetica", Font.BOLD, textSize));
				for (int i = 0;i<textLines.length;i++) {
					Renderer.getG().drawString(textLines[i], textX, textY+textSize*i);
				}
			}
			if (losLine&&!Edit.editMode) {
				Renderer.getG().setColor(COLOR_DEBUG_GREEN);
				Renderer.getG().setStroke(new BasicStroke(1));
				final int w = Window.centerX(), h = Window.centerY(), lineLength = Math.max(Window.width(), Window.height())*2;
				final int cursorPlayerX = (int) (Cursor.getPlayerX()*Camera.getScale()), cursorPlayerY = (int) (Cursor.getPlayerY()*Camera.getScale());
				if (Camera.isZoomed()) {
					Renderer.getG().drawLine(w-cursorPlayerX, h-cursorPlayerY, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w+cursorPlayerX), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h+cursorPlayerY));//los
				}
				else {
					Renderer.getG().drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));//los
				}
			}
			if (spawnPointVisibilityLines&&!Edit.editMode) {
				Renderer.getG().setStroke(new BasicStroke(1));
				for (int i = 0;i<Level.getSpawnPoints().size();i++)for (int j = 0;j<Level.getSpawnPoints().get(i).getSpawnPointVisibilityLines().size();j++) {
					SpawnPointVisibiltiyLine line = Level.getSpawnPoints().get(i).getSpawnPointVisibilityLines().get(j);
					if (!line.isBroken()) Renderer.getG().setColor(Util.colorOpacity(Color.BLUE, 0.75f));
					else Renderer.getG().setColor(Util.colorOpacity(Color.RED, 0.05f));
					Renderer.getG().drawLine(Renderer.gridX(line.getX1()), Renderer.gridY(line.getY1()), Renderer.gridX(line.getX2()), Renderer.gridY(line.getY2()));
				}
			}
//			drawPath(PathFindingTester.linesAStar, Color.BLUE, 2);
//			drawPath(PathFindingTester.linesMaze, Color.CYAN, 2);
		}
	}

	public static void drawPath(List<Point> lines, Color color, int size) {
		if (drawDebugPathfinding&&!Edit.editMode) {
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

	public static boolean isDrawSightLines() {
		return drawSightLines;
	}

	public static void toggleSightLines() {
		drawSightLines = !drawSightLines;
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

	public static boolean isDrawDebugPathfinding() {
		return drawDebugPathfinding;
	}

	public static boolean isSpawnPointVisibilityLines() {
		return spawnPointVisibilityLines;
	}

	public static void toggleSpawnPointVisibilityLines() {
		spawnPointVisibilityLines = !spawnPointVisibilityLines;
	}

}
