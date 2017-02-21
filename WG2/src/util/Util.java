package util;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util {

	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(double x1, double y1, int x2, int y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(int x1, int y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(int x1, int y1, int x2, int y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double getAngleDegrees(double x, double y, double xT, double yT) {
		double result = Math.toDegrees(Math.atan2(-(xT-x), -(yT-y)))+90;
		if (result<0) {
			result+=360;
		}
		return result;
	}

	public static double avg(double... nums) {
		double total = 0;
		for (double num:nums) total+=num;
		return total/nums.length;
	}

	public static int avg(int... nums) {
		int total = 0;
		for (int num:nums) total+=num;
		return total/nums.length;
	}

	public static double getAngle(double x, double y, double xT, double yT) {
		return Math.atan2(x-xT, y-yT)+1.57079632679d;
	}

	public static double getSpread(double value, double spread) {
		return value+(Math.random()-0.5)*spread;
	}

	public static double getAngleSpread(double angle, double spread) {//returns radians (radian angle input, degree spread input) spread includes both directions
		return angle+(Math.random()-0.5)*Math.toRadians(spread);
	}

	public static Ellipse2D getCircle(int x, int y, int size, boolean center) {
		if (center) return new Ellipse2D.Double(x-size/2, y-size/2, size, size);
		else return new Ellipse2D.Double(x, y, size, size);
	}

	public static Polygon getPoly(int x, int y, int sides, int size) {//for TD
		Polygon poly = new Polygon();
		double a, shift = 0;
		for (int i = 0;i<=sides;i++) {
			if (sides%2!=0) {
				shift = Math.PI;
			}
			else {
				shift = Math.PI/sides;
			}
			a = Math.PI/(sides/2d)*i+shift;
			poly.addPoint((int)(Math.round(x+Math.sin(a)*size)),(int)(Math.round(y+Math.cos(a)*size)));
		}
		return poly;
	}

	public static Rectangle2D getRect(int x, int y, int size) {
		return new Rectangle(x, y, size, size);
	}

	public static Rectangle2D getRect(int x, int y, int sizeX, int sizeY) {
		return new Rectangle(x, y, sizeX, sizeY);
	}

	public static double getXCompDegrees(double angle, double magnitude) {
		return Math.cos(Math.toRadians(angle))*magnitude;
	}

	public static double getYCompDegrees(double angle, double magnitude) {
		return Math.sin(Math.toRadians(angle))*magnitude;
	}

	public static float getXComp(double angle, float magnitude) {
		return (float)(Math.cos(angle)*magnitude);
	}

	public static float getYComp(double angle, float magnitude) {
		return (float)(Math.sin(angle)*magnitude);
	}

	public static double getXComp(float angle, float magnitude) {
		return Math.cos(angle)*magnitude;
	}

	public static double getYComp(float angle, float magnitude) {
		return Math.sin(angle)*magnitude;
	}

	public static double getXComp(double angle, double magnitude) {
		return Math.cos(angle)*magnitude;
	}

	public static double getYComp(double angle, double magnitude) {
		return Math.sin(angle)*magnitude;
	}

	public static Color colorOpacity(Color color, int opacity) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}

	public static Color colorOpacity(Color color, float opacity) {
		float[] colorComps = color.getRGBComponents(null);
		return new Color(colorComps[0], colorComps[1], colorComps[2], opacity);
	}

	public static String fileToString(String path) {
		try {
			File theFile = new File(path);
			Scanner scan = new Scanner(theFile);
			StringBuilder output = new StringBuilder();
			while (scan.hasNextLine()) {
				output.append(scan.nextLine());
			}
			try {
				return output.toString();
			}
			finally {
				scan.close();
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Can't find file at: "+path);
			System.exit(0);
		}
		return null;
	}


}
