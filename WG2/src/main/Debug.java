package main;

import data.ColorData;
import data.ControlData;
import data.GameData;
import data.MapData;

public class Debug implements GameData, ColorData, MapData, ControlData {
	private static boolean debugText = true, losLine = false, drawWeapons = true, drawDebugPathfinding = true, drawSightLines = false, spawnPointVisibilityLines = false;

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
