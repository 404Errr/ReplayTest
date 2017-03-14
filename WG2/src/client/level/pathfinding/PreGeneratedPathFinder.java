package client.level.pathfinding;

import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import client.level.Level;
import data.MapData;
import util.Util;

public class PreGeneratedPathFinder {
	public static LinkedList<Point> getPath(int x1, int y1, int x2, int y2) throws IOException {
		LinkedList<Point> pathPoints = new LinkedList<>();
		if (!Util.inArrayBounds(x1, y1, Level.getLayout())||!Util.inArrayBounds(x2, y2, Level.getLayout())) {
			return pathPoints;
		}
		String navMapPath = MapData.PATH+"/"+MapData.MAP+"_nav/"+x1+"/"+y1;
		int lineNum = x2*Level.getLayout().length+y2;
		String rawLine = Util.readLineInFileThrows(navMapPath, lineNum);
		if (rawLine.equals("")) {
			return new LinkedList<>();//no path exists
		}
		String[] rawList = rawLine.split(":");
		for (String point:rawList) {
			String[] pointNums = point.split(",");
			pathPoints.add(new Point(Integer.parseInt(pointNums[0]), Integer.parseInt(pointNums[1])));
		}
		return pathPoints;
	}
}
