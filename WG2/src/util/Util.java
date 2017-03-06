package util;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Util {
	public static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;

	public static boolean inArrayBounds(float x, float y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}
	
	public static int getSide(float x, float y) {
		float[] distances = new float[4];
		distances[RIGHT] = (int)x+1-x;
		distances[DOWN] = (int)y+1-y;
		distances[LEFT] = x-(int)x;
		distances[UP] = y-(int)y;
		return minInArray(distances);
	}

	public static int[][] getRandomArray(int sizeX, int sizeY, int lowerBound, int upperBound) {
		int[][] array = new int[sizeY][sizeX];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[r][c] = rand(lowerBound, upperBound);
			}
		}
		return array;
 	}
	
	public static int[][] getRandomArray(int sizeX, int sizeY, int upperBound) {
		int[][] array = new int[sizeY][sizeX];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[r][c] = rand(upperBound);
			}
		}
		return array;
 	}
	
	public static int rand(int upperBound) {
		return new Random().nextInt(upperBound);
	}
	
	public static int rand(int lowerBound, int upperBound) {
		return new Random().nextInt(upperBound-lowerBound)+lowerBound;
	}
	
	public static void printArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		System.out.println(str);
	}
	
	public static void printArray(int[][] array) {
		StringBuilder str = new StringBuilder();
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				str.append(array[r][c]+",");
			}
			str.replace(str.length()-1, str.length(), "\n");
		}
		System.out.println(str);
	}
	
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

	public static float distance(float x1, float y1, float x2, float y2) {
		return (float)Math.hypot(x1-x2, y1-y2);
	}

	public static float distance(float x1, float y1, int x2, int y2) {
		return (float)Math.hypot(x1-x2, y1-y2);
	}

	public static float distance(int x1, int y1, float x2, float y2) {
		return (float)Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(double x1, double y1, float x2, float y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(float x1, float y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static float bounceAngle(float angle, boolean yAxis) {
		if (yAxis) return (float)(Math.PI-angle);
		else return -angle;
	}

	public static double getAngleDegrees(double x, double y, double xT, double yT) {
		double result = Math.toDegrees(Math.atan2(-(xT-x), -(yT-y)))+90;
		if (result<0) {
			result+=360;
		}
		return result;
	}

	public static float avg(float... nums) {
		float total = 0;
		for (float num:nums) total+=num;
		return total/nums.length;
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

	public static int minInArray(float[] values) {
		int min = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]<values[min]) min = i;
		}
		return min;
	}

	public static double getAngle(double x, double y, double xT, double yT) {
		return Math.atan2(x-xT, y-yT)+1.57079632679d;
	}

	public static float getAngle(float x, float y, float xT, float yT) {
		return (float)(Math.atan2(x-xT, y-yT)+1.57079632679d);
	}

	public static double getSpread(double value, double spread) {
		return value+(Math.random()-0.5)*spread;
	}

	public static float getSpread(float value, float spread) {
		return (float)(value+(Math.random()-0.5)*spread);
	}

	public static double getAngleSpread(double angle, double spread) {//returns radians (radian angle input, degree spread input) spread includes both directions
		return angle+(Math.random()-0.5)*Math.toRadians(spread);
	}

	public static float getAngleSpread(float angle, float spread) {//returns radians (radian angle input, degree spread input) spread includes both directions
		return (float) (angle+(Math.random()-0.5)*Math.toRadians(spread));
	}

	public static Ellipse2D getCircle(int x, int y, int size, boolean center) {
		if (center) return new Ellipse2D.Double(x-size/2, y-size/2, size, size);
		else return new Ellipse2D.Double(x, y, size, size);
	}

	public static Polygon getPoly(int x, int y, int sides, int size) {//for TD
		Polygon poly = new Polygon();
		double a, shift = 0;
		for (int i = 0;i<=sides;i++) {
			if (sides%2!=0) shift = Math.PI;
			else shift = Math.PI/sides;
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

	public static float getXComp(float angle, float magnitude) {
		return (float)(Math.cos(angle)*magnitude);
	}

	public static float getYComp(float angle, float magnitude) {
		return (float)(Math.sin(angle)*magnitude);
	}

	public static double getXComp(double angle, double magnitude) {
		return Math.cos(angle)*magnitude;
	}

	public static double getYComp(double angle, double magnitude) {
		return Math.sin(angle)*magnitude;
	}

	public static Color getRedGreenColorShift(float value) {//0f = red, 1f = green (probably)
		if (value<0) return new Color(1,0,0,1);
		if (value>1) return new Color(0,1,0,0);
		return new Color(1-value, value, 0, 1);
	}

	public static Color colorOpacity(Color color, int opacity) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}

	public static Color colorOpacity(Color color, float opacity) {
		float[] colorComps = color.getRGBComponents(null);
		return new Color(colorComps[0], colorComps[1], colorComps[2], opacity);
	}

	public static int[][] StringToArray(String string) {//, and ;
		String[] rows = string.split(";");
		int[][] array = new int[rows.length][rows[0].split(",").length];
		for (int r = 0;r<rows.length;r++) {
			String[] collumns = rows[r].split(",");
			for (int c = 0;c<collumns.length;c++) {
				try {
					array[r][c] = Integer.parseInt(collumns[c]);
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return array;
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

	public static int[][] fillArray(int[][] layout, int type) {
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				layout[r][c] = type;
			}
		}
		return layout;
	}
	
	public static final float byteArray2Float(byte[] in) {
		return ByteBuffer.wrap(in).getFloat();
	}

	public static byte [] long2ByteArray (long value) {
	    return ByteBuffer.allocate(8).putLong(value).array();
	}

	public static byte [] float2ByteArray (float value) {
	     return ByteBuffer.allocate(4).putFloat(value).array();
	}
}