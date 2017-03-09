package util;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class Util {
	public static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;

	public static boolean inArrayBounds(float x, float y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static int getSide(float x, float y) {//FIXME
		float[] distances = new float[4];
		distances[RIGHT] = (int)x+1-x;
		distances[DOWN] = (int)y+1-y;
		distances[LEFT] = x-(int)x;
		distances[UP] = y-(int)y;
		return minInArray(distances);
	}

//	public static void dumbThing(int[][] array, boolean doDiag) {
//		int[][] ref = copyArray(array);
//		for (int r = 0;r<array.length;r++) {
//			for (int c = 0;c<array[0].length;c++) {
//				int total = 0;
//				for (int i = -1;i<=1;i++) {//r
//					for (int j = -1;j<=1;j++) {//c
//						if (Util.inArrayBounds(c+j, r+i, array)&&!(i==0&&j==0)&&(doDiag==!(i==0||j==0))) {//FIXME
//							total+=ref[r+i][c+j];
//						}
//					}
//				}
//				array[r][c] = total;
//			}
//		}
//	}
	
	public static int[][] copyArray(int[][] array) {
		int[][] newArray = new int[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}
	
	public static void appendArrayToArray(int x, int y, int[][] toAppend, int[][] array) {
		for (int r = 0;r<toAppend.length&&r<array.length-y;r++) {
			for (int c = 0;c<toAppend[r].length&&c<array[r].length-x;c++) {
				if (r+y>=0&&c+x>=0) array[r+y][c+x] = toAppend[r][c];
			}
		}
	}

	public static void replaceAllInArray(int[][] array, int from, int to) {
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				if (array[r][c]==from) array[r][c] = to;
			}
		}
	}

	public static void rotateArray(int[][] array, int rotations) {//90 degrees clockwise
		for (int rotationCount = 0;rotationCount<rotations;rotationCount++) {
			int[][] newArray = new int[array[0].length][array.length];
			for (int r = 0;r<newArray.length;r++) {
				for (int c = 0;c<newArray[0].length;c++) {
					newArray[r][c] = array[array.length-1-c][r];
				}
			}
			array = newArray;
		}
	}

	/*public static void mirrorArrayDiag(int[][] array, boolean tLBR, boolean tRBL) {
		if (tLBR) for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[c][r] = array[r][c];
			}
		}
		if (tRBL) for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[c][r] = array[array.length-1-r][array[0].length-1-c];
			}
		}
	}*/

	public static void mirrorArray(int[][] array, boolean horz, boolean vert) {//copies top/left into bottom/right
		if (horz) for (int r = 0;r<array.length;r++) {
			for (int cR = array[0].length-1, cL = 0;cL<cR;cR--, cL++) {
				array[r][cR] = array[r][cL];
			}
		}
		if (vert) for (int rR = array.length-1, rL = 0;rL<rR;rR--, rL++) {
			for (int c = 0;c<array[0].length;c++) {
				array[rR][c] = array[rL][c];
			}
		}
	}
	
	public static void flipArray(int[][] array, boolean horz, boolean vert) {
		int[][] refArray = copyArray(array);
		if (horz) for (int r = 0;r<array.length;r++) {
			for (int cR = array[0].length-1, cL = 0;cL<cR;cR--, cL++) {
				array[r][cL] = refArray[r][cR];
				array[r][cR] = refArray[r][cL];
			}
		}
		if (vert) for (int rR = array.length-1, rL = 0;rL<rR;rR--, rL++) {
			for (int c = 0;c<array[0].length;c++) {
				array[rL][c] = refArray[rR][c];
				array[rR][c] = refArray[rL][c];
			}
		}
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

	public static <T> void swap(int i1, int i2, List<T> array) {
		array.set(i1, array.set(i2, array.get(i1)));
	}

	public static <T> void printArray(List<T> array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.size();i++) {
			str.append(array.get(i)+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printIntAsCharArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append((char)array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printIntAsCharArray(int[][] array) {
		for (int r = 0;r<array.length;r++) {
			printIntAsCharArray(array[r]);
		}
	}

	public static void printArray(int[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
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

	public static int[] StringTo1DArray(String string) {//, (and ;)
		if (string.charAt(string.length()-1)==';') string = string.split(";")[0];
		int[] array = new int[string.split(",").length];
		String[] arrayStr = string.split(",");
		for (int i = 0;i<arrayStr.length;i++) {
			try {
				array[i] = Integer.parseInt(arrayStr[i]);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return array;
	}

	public static int[][] StringTo2DArray(String string) {//, and ;
		String[] rows = string.split(";");
		int[][] array = new int[rows.length][rows[0].split(",").length];
		for (int r = 0;r<rows.length;r++) {
			String[] collumns = rows[r].split(",");
			for (int c = 0;c<collumns.length;c++) {
				try {
					array[r][c] = Integer.parseInt(collumns[c]);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
		return array;
	}

	public static int[][] parseIntArrayFromFile(String path) {
		String in = Util.fileToString(path);
		System.out.println(path+" loaded");
		String[] rawRows = in.split(";");
		String[][] raw = new String[rawRows.length][rawRows[0].length()/2+1];
		for (int r = 0;r<rawRows.length;r++) {
			raw[r] = rawRows[r].split(",");
		}
		int[][] layout = new int[raw.length][raw[0].length];
		for (int r = 0;r<raw.length;r++) {
			for (int c = 0;c<raw[0].length;c++) {
				try {
					layout[r][c] = raw[r][c].charAt(0);
				}
				catch (Exception e) {
					System.err.println("error at: "+r+","+c);
					layout[r][c] = -1;
				}
			}
		}
		return layout;
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

	public static int[] getArraySlice(int[][] array, int side) {//from top or left
		int col = 0;
		switch (side) {
		case RIGHT:
			col = array[0].length-1;
			break;
		case DOWN:
			return array[array.length-1];
		case LEFT:
			col = 0;
			break;
		case UP:
			return array[0];
		}
		int[] layoutSide = new int[array.length];
		for (int i = 0;i<array.length;i++) layoutSide[i] = array[i][col];
		return layoutSide;
	}

	public static int[][] getNewfilledArray(int sizeX, int sizeY, int type) {
		int[][] layout = new int[sizeY][sizeX]; 
		for (int r = 0;r<layout.length;r++) {
			for (int c = 0;c<layout[0].length;c++) {
				layout[r][c] = type;
			}
		}
		return layout;
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




