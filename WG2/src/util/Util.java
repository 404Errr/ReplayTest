package util;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Stack;

public final class Util {
	private static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;
	private static Random rand;

	static {
		rand = new Random();
	}

	public static String toRomanNumeral(int num) {
		return Roman.intToRoman(num);
	}

	public static int fromRomanNumeral(String roman) {
		return Roman.romanToInt(roman);
	}

	public static void clearConsole() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<100;i++) sb.append("\n");
		System.out.print(sb);
	}

	public static <T> List<T> getReverse(List<T> list) {
		List<T> reverse = getCopy(list);
		for (int i = reverse.size()-1, j = 0;i>=0;i--,j++) {
			reverse.set(i, list.get(j));
		}
		return reverse;
	}

	public static Object[] getReverse(Object[] array) {
		Object[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static String[] getReverse(String[] array) {
		String[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static byte[] getReverse(byte[] array) {
		byte[] reverse = new byte[array.length];
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static double[] getReverse(double[] array) {
		double[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static float[] getReverse(float[] array) {
		float[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static short[] getReverse(short[] array) {
		short[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static long[] getReverse(long[] array) {
		long[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static char[] getReverse(char[] array) {
		char[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static int[] getReverse(int[] array) {
		int[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		return reverse;
	}

	public static void reverse(Object[] array) {
		Object[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(String[] array) {
		String[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(byte[] array) {
		byte[] reverse = new byte[array.length];
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(double[] array) {
		double[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(float[] array) {
		float[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(short[] array) {
		short[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(long[] array) {
		long[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(char[] array) {
		char[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void reverse(int[] array) {
		int[] reverse = getCopy(array);
		for (int i = reverse.length-1, j = 0;i>=0;i--, j++) {
			reverse[i] = array[j];
		}
		array = reverse;
	}

	public static void addEnd(String str, String toAdd) {
		str = str+toAdd;
	}

	public static void addStart(String str, String toAdd) {
		str = toAdd+str;
	}

	public static boolean containsOnly(String string, char[] potentialcontent) {
		for (char character:string.toCharArray()) {
			boolean contains = false;
			for (char potentialMatch:potentialcontent) {
				if (character==potentialMatch) {
					contains = true;
				}
			}
			if (!contains) return false;
		}
		return true;
	}

	public static boolean isNumeric(String string) {
		for (char character:string.toCharArray()) {
			if (!Character.isDigit(character)) return false;
		}
		return true;
	}

	public static boolean withinAngle(float angle1Rad, float angle2Rad, float rangeDeg) {//FIXME
		double temp1 = Math.toDegrees(angle1Rad), temp2 = Math.toDegrees(angle2Rad);
		while (temp1<0) temp1+=360;
		while (temp2<0) temp2+=360;
		while (temp1>180) temp1-=180;
		while (temp2>180) temp2-=180;
		return Math.abs(temp1-temp2)<rangeDeg;
	}

	public static <T> List<T> removeBetween(List<T> list, int lower, int upper) {
		for (int i = upper-1;i>lower;i--) {
			list.remove(i);
		}
		return list;
	}

	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double getDistance(double x1, double y1, int x2, int y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double getDistance(int x1, int y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static float getDistance(float x1, float y1, float x2, float y2) {
		return (float)Math.hypot(x1-x2, y1-y2);
	}

	public static float getDistance(float x1, float y1, int x2, int y2) {
		return (float)Math.hypot(x1-x2, y1-y2);
	}

	public static float getDistance(int x1, int y1, float x2, float y2) {
		return (float)Math.hypot(x1-x2, y1-y2);
	}

	public static double getDistance(double x1, double y1, float x2, float y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double getDistance(float x1, float y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static float getBounceAngle(float angle, boolean yAxis) {
		if (yAxis) return (float)(Math.PI-angle);
		else return -angle;
	}

	public static double getAngleDegrees(double x1, double y1, double x2, double y2) {
		double result = Math.toDegrees(Math.atan2(-(x2-x1), -(y2-y1)))+90;
		if (result<0) {
			result+=360;
		}
		return result;
	}

	public static List<String> getStringPermutations(String string) {
		List<String> combos = new ArrayList<>();
		char[] str = string.toCharArray();
		int[] c = new int[string.length()];
		for (int i = 0;i<string.length();i++) {
			c[i] = 0;
		}
		combos.add(String.valueOf(str));
		int i = 0;
		while (i<string.length()) {
			if (c[i]<i) {
				if (i%2==0) str = swap(str, 0, i);
				else str = swap(str, c[i], i);
				if (!combos.contains(String.valueOf(str))) combos.add(String.valueOf(str));
				c[i]++;
				i = 0;
			}
			else {
				c[i] = 0;
				i++;
			}
		}
		return combos;
	}

	private static char[] swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
		return str;
	}

	public static <T> void removeRepeats(List<T> list) {
		for (int i = 0;i<list.size()-1;i++) {
			for (int j = i+1;j<list.size();j++) {
				if (list.get(i)==list.get(j)) {
					list.remove(j);
					j--;
				}
			}
		}
	}

	public static <T> void removeAdjacentRepeats(List<T> list) {
		for (int i = list.size()-1;i>0;i--) {
			if (list.get(i)==list.get(i-1)) list.remove(i);
		}
	}

	public static <T> void removeRepeatsFromEnds(List<T> list) {
		while (list.size()>1&&list.get(0)==list.get(1)) {
			list.remove(0);
		}
		while (list.size()>1&&list.get(list.size()-1)==list.get(list.size()-2)) {
			list.remove(list.size()-1);
		}
	}

	public static <T> String combineWithSpace(List<T> list) {
		return combineWith(" ", list);
	}

	public static String combineWithSpace(Object[] array) {
		return combineWith(" ", array);
	}

	public static String combineWithSpace(String... strs) {
		return combineWith(" ", strs);
	}

	public static <T> String combineWith(String seperator, List<T> list) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<list.size();i++) {
			str.append(list.get(i)+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, Object[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineStringsWith(String seperator, String[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, int[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, char[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, short[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, long[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, float[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, double[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, byte[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, boolean[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static String combineWith(String seperator, String... strs) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<strs.length;i++) {
			if (strs[i]!=null) str.append(strs[i]+seperator);
		}
		return str.toString().substring(0, str.length()-1);
	}

	public static boolean lineIsBrokenByBooleanArray(float x1, float y1, float x2, float y2, boolean[][] breaks) {//FIXME
		float dx = Math.abs(Math.round(x2)-Math.round(x1)), dy = Math.abs(Math.round(y2)-Math.round(y1)), sX = x1<x2?1:-1, sY = y1<y2?1:-1, err = dx-dy, e2;
		while (Math.round(x1)!=Math.round(x2)||Math.round(y1)!=Math.round(y2)) {
			if (Util.inArrayBounds(x1, y1, breaks)&&breaks[(int)y1][(int)x1]) return true;
			e2 = 2*err;
			if (e2>-dy) {
				err = err-dy;
				x1 = x1+sX;
			}
			if (e2<dx) {
				err = err+dx;
				y1 = y1+sY;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> T avg(List<T> nums) {
		if (nums.get(0) instanceof Float) {
			float total = 0;
			for (float num:(List<Float>) nums) total+=num;
			return (T)new Float(total/nums.size());
		}
		if (nums.get(0) instanceof Double) {
			double total = 0;
			for (double num:(List<Double>) nums) total+=num;
			return (T)new Double(total/nums.size());
		}
		if (nums.get(0) instanceof Integer) {
			int total = 0;
			for (int num:(List<Integer>) nums) total+=num;
			return (T)new Integer(total/nums.size());
		}
		if (nums.get(0) instanceof Long) {
			long total = 0;
			for (long num:(List<Long>) nums) total+=num;
			return (T)new Long(total/nums.size());
		}
		if (nums.get(0) instanceof Short) {
			short total = 0;
			for (short num:(List<Short>) nums) total+=num;
			return (T)new Short((short)(total/nums.size()));
		}
		throw new IllegalArgumentException("unknown number type");
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

	public static long avg(long... nums) {
		long total = 0;
		for (long num:nums) total+=num;
		return total/nums.length;
	}

	public static short avg(short... nums) {
		short total = 0;
		for (short num:nums) total+=num;
		return (short)(total/nums.length);
	}

	public static int randomInt(int upperBound) {
		return new Random().nextInt(upperBound);
	}

	public static int randomInt(int lowerBound, int upperBound) {
		return new Random().nextInt(upperBound-lowerBound)+lowerBound;
	}

	public static boolean[][] negateArray(boolean[][] array) {
		for (int r = 0;r<array.length;r++) {
			negateArray(array[r]);
		}
		return array;
	}

	public static boolean[] negateArray(boolean[] array) {
		for (int i = 0;i<array.length;i++) {
			array[i] = !array[i];
		}
		return array;
	}

	public static boolean[][] getBooleanArrayContains(int[][] layout, int[] values, boolean value) {
		boolean[][] array = new boolean[layout.length][layout[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				for (int val:values) {
					if (layout[r][c]==val) {
						array[r][c] = value;
						break;
					}
				}
			}
		}
		return array;
	}

	public static boolean arrayContains(int[][] array, int value) {
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				if (array[r][c]==value) return true;
			}
		}
		return false;
	}

	public static boolean arrayContains(int[] array, int value) {
		for (int i = 0;i<array.length;i++) {
			if (array[i]==value) return true;
		}
		return false;
	}

	public static void addEdgeToArray(int[][] array, int val) {
		int[][] newArray = new int[array.length+2][array[0].length+2];//new array size of map +1 on every side
		for (int r = 0;r<newArray.length;r++) {
			for (int c = 0;c<newArray[0].length;c++) {
				if (r==0||c==0||r==newArray.length-1||c==newArray[0].length-1) {
					newArray[r][c] = val;
				}
				else {
					newArray[r][c] = array[r-1][c-1];
				}
			}
		}
		array = newArray;
	}

	public static boolean equalArrays(int[][] array1, int[][] array2) {
		if (array1.length!=array2.length||array1[0].length!=array2[0].length) return false;
		for (int r = 0;r<array1.length;r++) {
			for (int c = 0;c<array1[0].length;c++) {
				if (array1[r][c]!=array2[r][c]) return false;
			}
		}
		return true;
	}

	public static boolean equalArrays(int[] array1, int[] array2) {
		if (array1.length!=array2.length) return false;
		for (int i = 0;i<array1.length;i++) {
			if (array1[i]!=array2[i]) return false;
		}
		return true;
	}

	public static boolean equalArraysType(int[] array1, int[] array2, int type) {
		if (array1.length!=array2.length) return false;
		for (int i = 0;i<array1.length;i++) {
			if (array1[i]==type||array2[i]==type&&array1[i]!=array2[i]) return false;
		}
		return true;
	}

	public static void floodFill(int iX, int iY, int from, int to, int[][] array) {
		Stack<Point> points = new Stack<>();
		points.add(new Point(iX, iY));
		while (!points.isEmpty()) {
			Point currentPoint = points.pop();
			int x = currentPoint.x, y = currentPoint.y;
			if (x<0||y<0||y>=array.length||x>=array[0].length) continue;
			if (from==array[y][x]) {
				array[y][x] = to;
				points.push(new Point(x+1, y));
				points.push(new Point(x-1, y));
				points.push(new Point(x, y+1));
				points.push(new Point(x, y-1));
			}
		}
	}

	public static List<int[]> countValuesInArray(int[][] array, int lower, int upper) {
		List<int[]> counted = new ArrayList<>();
		for (int currentValue = lower;currentValue<=upper;currentValue++) {
			int count = 0;
			for (int r = 0;r<array.length;r++) {
				for (int c = 0;c<array[0].length;c++) {
					if (array[r][c]==currentValue) {
						count++;
					}
				}
			}
			if (count>0) {
				counted.add(new int[] {currentValue, count});
			}
		}
		return counted;
	}

	public static boolean arrayAdjacentValues(int[][] array, int val1, int val2) {
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				if (array[r][c]==val1) {
					if (r>0&&array[r+1][c]==val2) return true;
					if (r<array.length-1&&array[r-1][c]==val2) return true;
					if (c>0&&array[r][c+1]==val2) return true;
					if (c<array[0].length&&array[r][c-1]==val2) return true;
				}
			}
		}
		return false;
	}

	public static int[][] getRandomArray(int sizeX, int sizeY, int lowerBound, int upperBound) {
		int[][] array = new int[sizeY][sizeX];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[r][c] = randomInt(lowerBound, upperBound);
			}
		}
		return array;
	}

	public static int[][] getRandomArray(int sizeX, int sizeY, int upperBound) {
		int[][] array = new int[sizeY][sizeX];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[r][c] = randomInt(upperBound);
			}
		}
		return array;
	}

	public static <T> void printArray(List<T> list) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<list.size();i++) {
			str.append(list.get(i)+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(Object[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(Object[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i].toString()+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(int[][] array) {//TODO
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(int[] array) {
//		System.out.println(combineWith(",", array));//TODO
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(long[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(long[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(short[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(short[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(float[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(float[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}


	public static void printArray(double[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(double[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(char[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(char[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printArray(String[][] array) {
		for (int r = 0;r<array.length;r++) {
			printArray(array[r]);
		}
	}

	public static void printArray(String[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append(array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static <T> void printAsCharArray(List<T> list) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<list.size();i++) {
			str.append((char)list.get(i)+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printAsCharArray(int[][] array) {
		for (int r = 0;r<array.length;r++) {
			printAsCharArray(array[r]);
		}
	}

	public static void printAsCharArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append((char)array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printAsCharArray(short[][] array) {
		for (int r = 0;r<array.length;r++) {
			printAsCharArray(array[r]);
		}
	}

	public static void printAsCharArray(short[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append((char)array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static void printAsCharArray(long[][] array) {
		for (int r = 0;r<array.length;r++) {
			printAsCharArray(array[r]);
		}
	}

	public static void printAsCharArray(long[] array) {
		StringBuilder str = new StringBuilder();
		for (int i = 0;i<array.length;i++) {
			str.append((char)array[i]+",");
		}
		str.replace(str.length()-1, str.length(), "\n");
		System.out.print(str);
	}

	public static int maxInArray(short[] values) {
		int max = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]>values[max]) max = i;
		}
		return max;
	}

	public static int minInArray(short[] values) {
		int min = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]<values[min]) min = i;
		}
		return min;
	}

	public static int maxInArray(long[] values) {
		int max = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]>values[max]) max = i;
		}
		return max;
	}

	public static int minInArray(long[] values) {
		int min = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]<values[min]) min = i;
		}
		return min;
	}

	public static int maxInArray(int[] values) {
		int max = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]>values[max]) max = i;
		}
		return max;
	}

	public static int minInArray(int[] values) {
		int min = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]<values[min]) min = i;
		}
		return min;
	}

	public static int maxInArray(double[] values) {
		int max = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]>values[max]) max = i;
		}
		return max;
	}

	public static int minInArray(double[] values) {
		int min = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]<values[min]) min = i;
		}
		return min;
	}

	public static int maxInArray(float[] values) {
		int max = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]>values[max]) max = i;
		}
		return max;
	}

	public static int minInArray(float[] values) {
		int min = 0;
		for (int i = 0;i<values.length;i++) {
			if (values[i]<values[min]) min = i;
		}
		return min;
	}

	public static double getAngle(double x, double y) {
		return Math.atan2(-x, -y)+1.57079632679d;
	}

	public static float getAngle(float x, float y) {
		return (float)(Math.atan2(-x, -y)+1.57079632679d);
	}

	public static double getAngle(double x1, double y1, double x2, double y2) {
		return Math.atan2(x1-x2, y1-y2)+1.57079632679d;
	}

	public static float getAngle(float x1, float y1, float x2, float y2) {
		return (float)(Math.atan2(x1-x2, y1-y2)+1.57079632679d);
	}

	public static double getSpread(double spread) {
		return (Math.random()-0.5)*spread;
	}

	public static float getSpread(float spread) {
		return (float)((Math.random()-0.5)*spread);
	}

	public static double getSpread(double value, double spread) {
		return value+(Math.random()-0.5)*spread;
	}

	public static float getSpread(float value, float spread) {
		return (float)(value+(Math.random()-0.5)*spread);
	}

	public static double getSpread(double value, float spread) {
		return value+(Math.random()-0.5)*spread;
	}

	public static float getSpread(float value, double spread) {
		return (float)(value+(Math.random()-0.5)*spread);
	}

	public static double getAngleSpread(double angleRad, double spreadDeg) {//returns radians
		return angleRad+(Math.random()-0.5)*Math.toRadians(spreadDeg);
	}

	public static float getAngleSpread(float angleRad, float spreadDeg) {//returns radians
		return (float) (angleRad+(Math.random()-0.5)*Math.toRadians(spreadDeg));
	}

	public static Ellipse2D getCircle(int x, int y, int size, boolean center) {
		if (center) return new Ellipse2D.Double(x-size/2, y-size/2, size, size);
		else return new Ellipse2D.Double(x, y, size, size);
	}

	public static Polygon getPoly(int x, int y, int sides, int size) {
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

	public static double getXCompDegrees(double angleDeg, double magnitude) {
		return Math.cos(Math.toRadians(angleDeg))*magnitude;
	}

	public static double getYCompDegrees(double angleDeg, double magnitude) {
		return Math.sin(Math.toRadians(angleDeg))*magnitude;
	}

	public static float getXComp(float angleRad) {
		return (float) Math.cos(angleRad);
	}

	public static float getYComp(float angleRad) {
		return (float) Math.sin(angleRad);
	}

	public static double getXComp(double angleRad) {
		return Math.cos(angleRad);
	}

	public static double getYComp(double angleRad) {
		return Math.sin(angleRad);
	}

	public static float getXComp(float angleRad, float magnitude) {
		return (float)(Math.cos(angleRad)*magnitude);
	}

	public static float getYComp(float angleRad, float magnitude) {
		return (float)(Math.sin(angleRad)*magnitude);
	}

	public static double getXComp(double angleRad, double magnitude) {
		return Math.cos(angleRad)*magnitude;
	}

	public static double getYComp(double angleRad, double magnitude) {
		return Math.sin(angleRad)*magnitude;
	}

	public static List<int[]> getPermutations(int length) {
		List<int[]> permutations = new ArrayList<>();
		int[] array = new int[length], c = new int[length];
		for (int i = 0;i<length;i++) {
			array[i] = i;
			c[i] = 0;
		}
		permutations.add(array.clone());
		int i = 0;
		while (i<length) {
			if (c[i]<i) {
				if (i%2==0) array = swap(array, 0, i);
				else array = swap(array, c[i], i);
				permutations.add(array.clone());
				c[i]+=1;
				i = 0;
			}
			else {
				c[i] = 0;
				i+=1;
			}
		}
		return permutations;
	}

	public static int[] swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}

	public static Color getRedGreenColorShift(float value) {//0 = red, 1 = green (probably)
		if (value<0) return new Color(1,0,0,1);
		if (value>1) return new Color(0,1,0,0);
		return new Color(1-value, value, 0, 1);
	}

	public static Color getColorShift(Color c1, Color c2, float v) {//TODO
		return null;
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

	public static String readLineInFileThrows(String path, int line) throws IOException {
		return Files.readAllLines(Paths.get(path)).get(line);
	}

	public static List<String> fileToStringList(String path) {
		try {
			return Files.readAllLines(Paths.get(path));
		}
		catch (IOException e) {
			System.err.println("can't read file at "+path);
		}
		return new ArrayList<>();
	}

	public static String readLineInFile(String path, int line) {
		try {
			return Files.readAllLines(Paths.get(path)).get(line);
		}
		catch (Exception e) {
			System.err.println("can't read line "+line+" of file at "+path);
			e.printStackTrace();
		}
		return "";
	}

	public static void appendToFile(String path, String toAppend) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path, true));
			pw.write(toAppend);
			pw.close();
		}
		catch (Exception e) {
			System.err.println("can't append to file at "+path);
			e.printStackTrace();
		}
	}

	public static int[][] parseIntArrayFromFile(String path) {
		String in = Util.fileToString(path);
		System.out.println(path+" loaded");
		String[] rawRows = in.split(";");
		String[][] raw = new String[rawRows.length][rawRows[0].length()/2+1];
		for (int r = 0;r<rawRows.length;r++) {
			raw[r] = rawRows[r].split(",");
		}
		int[][] array = new int[raw.length][raw[0].length];
		for (int r = 0;r<raw.length;r++) {
			for (int c = 0;c<raw[0].length;c++) {
				try {
					array[r][c] = raw[r][c].charAt(0);
				}
				catch (Exception e) {
					System.err.println("error at: "+r+","+c);
					array[r][c] = 0;
				}
			}
		}
		return array;
	}

	public static String fileToString(String path) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(path));
			StringBuilder str = new StringBuilder();
			for (String line:lines) {
				str.append(line);
			}
			return str.toString();
		}
		catch (FileNotFoundException e) {
			System.err.println("Can't find file at: "+path);
			System.exit(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
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
		int[][] array = new int[sizeY][sizeX];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[r][c] = type;
			}
		}
		return array;
	}

	public static void fillArray(int[][] array, int type) {
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				array[r][c] = type;
			}
		}
	}

	public static final float byteArray2Char(byte[] in) {
		return ByteBuffer.wrap(in).getChar();
	}

	public static final float byteArray2Float(byte[] in) {
		return ByteBuffer.wrap(in).getFloat();
	}

	public static final short byteArray2Short(byte[] in) {
		return ByteBuffer.wrap(in).getShort();
	}

	public static final int byteArray2Int(byte[] in) {
		return ByteBuffer.wrap(in).getInt();
	}

	public static final long byteArray2Long(byte[] in) {
		return ByteBuffer.wrap(in).getLong();
	}

	public static final double byteArray2Double(byte[] in) {
		return ByteBuffer.wrap(in).getDouble();
	}

	public static byte[] char2ByteArray (char value) {
		return ByteBuffer.allocate(8).putChar(value).array();
	}

	public static byte[] short2ByteArray (short value) {
		return ByteBuffer.allocate(8).putShort(value).array();
	}

	public static byte[] double2ByteArray (double value) {
		return ByteBuffer.allocate(8).putDouble(value).array();
	}

	public static byte[] int2ByteArray (int value) {
		return ByteBuffer.allocate(8).putInt(value).array();
	}

	public static byte[] long2ByteArray (long value) {
		return ByteBuffer.allocate(8).putLong(value).array();
	}

	public static byte[] float2ByteArray (float value) {
		return ByteBuffer.allocate(4).putFloat(value).array();
	}

	public static boolean inArrayBounds(float x, float y, Object[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, Object[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, Object[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, char[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, char[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, char[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, String[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, String[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, String[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, double[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, double[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, double[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, float[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, float[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, float[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, long[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, long[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, long[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, short[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, short[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, short[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, boolean[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, boolean[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, boolean[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(float x, float y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(double x, double y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static boolean inArrayBounds(int x, int y, int[][] array) {
		return y>=0&&x>=0&&y<array.length&&x<array[0].length;
	}

	public static <T> List<T> getCopy(List<T> list) {
		List<T> newList = new ArrayList<>();
		for (int i = 0;i<list.size();i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public static String[] getCopy(String[] array) {
		String[] newArray = new String[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static String[][] getCopy(String[][] array) {
		String[][] newArray = new String[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static Object[] getCopy(Object[] array) {
		Object[] newArray = new String[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static Object[][] getCopy(Object[][] array) {
		Object[][] newArray = new String[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static byte[] getCopy(byte[] array) {
		byte[] newArray = new byte[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static byte[][] getCopy(byte[][] array) {
		byte[][] newArray = new byte[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static short[] getCopy(short[] array) {
		short[] newArray = new short[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static short[][] getCopy(short[][] array) {
		short[][] newArray = new short[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static long[] getCopy(long[] array) {
		long[] newArray = new long[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static long[][] getCopy(long[][] array) {
		long[][] newArray = new long[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static char[] getCopy(char[] array) {
		char[] newArray = new char[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static char[][] getCopy(char[][] array) {
		char[][] newArray = new char[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static float[] getCopy(float[] array) {
		float[] newArray = new float[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static float[][] getCopy(float[][] array) {
		float[][] newArray = new float[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static double[] getCopy(double[] array) {
		double[] newArray = new double[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static double[][] getCopy(double[][] array) {
		double[][] newArray = new double[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static int[] getCopy(int[] array) {
		int[] newArray = new int[array.length];
		for (int i = 0;i<array.length;i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	public static int[][] getCopy(int[][] array) {
		int[][] newArray = new int[array.length][array[0].length];
		for (int r = 0;r<array.length;r++) {
			for (int c = 0;c<array[0].length;c++) {
				newArray[r][c] = array[r][c];
			}
		}
		return newArray;
	}

	public static void appendArrayToArray(int x, int y, int[][] toAppend, int[][] array) {//add toAppend to array starting at x, y
		for (int r = 0;r<toAppend.length&&r<array.length-y;r++) {
			for (int c = 0;c<toAppend[r].length&&c<array[r].length-x;c++) {
				if (r+y>=0&&c+x>=0) {
					array[r+y][c+x] = toAppend[r][c];
				}
			}
		}
	}

	public static void replaceAllInArray(int[][] array, int from, int to) {
		for (int r = 0;r<array.length;r++) {
			replaceAllInArray(array[r], from, to);
		}
	}

	public static void replaceAllInArray(int[] array, int from, int to) {
		for (int i = 0;i<array.length;i++) {
			if (array[i]==from) {
				array[i] = to;
			}
		}
	}

	public static int[][] rotateArray(int[][] array, int rotations) {//90 degrees clockwise
		for (int rotationCount = 0;rotationCount<rotations;rotationCount++) {
			int[][] tempArray = new int[array[0].length][array.length];
			for (int r = 0;r<tempArray.length;r++) {
				for (int c = 0;c<tempArray[0].length;c++) {
					tempArray[r][c] = array[array.length-1-c][r];
				}
			}
			array = tempArray;
		}
		return array;
	}

	public static void mirrorArrayDiag(int[][] array, boolean tLBR, boolean tRBL) {
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
	}

	public static void mirrorArray(int[][] array, boolean horz, boolean vert) {//copies top/left into bottom/right
		if (horz) for (int r = 0;r<array.length;r++) {
			for (int cR = array[0].length-1, cL = 0;cL<=cR;cR--, cL++) {
				array[r][cR] = array[r][cL];
			}
		}
		if (vert) for (int rR = array.length-1, rL = 0;rL<=rR;rR--, rL++) {
			for (int c = 0;c<array[0].length;c++) {
				array[rR][c] = array[rL][c];
			}
		}
	}

	public static int[][] flipArray(int[][] array, boolean horz, boolean vert) {
		int[][] refArray = getCopy(array);
		if (horz) for (int r = 0;r<array.length;r++) {
			for (int cH = array[0].length-1, cL = 0;cL<=cH;cH--, cL++) {
				array[r][cL] = refArray[r][cH];
				array[r][cH] = refArray[r][cL];
			}
		}
		if (vert) for (int rH = array.length-1, rL = 0;rL<=rH;rH--, rL++) {
			for (int c = 0;c<array[0].length;c++) {
				array[rL][c] = refArray[rH][c];
				array[rH][c] = refArray[rL][c];
			}
		}
		return array;
	}

	public static boolean continuousCheck(int[][] array, int type) {
		int[][] arr = getCopy(array);
		int iX = -1, iY = -1;
		for (int r = 0;r<arr.length;r++) {
			for (int c = 0;c<arr[0].length;c++) {
				if (arr[r][c]==type) {
					iX = c;
					iY = r;
				}
			}
		}
		if (iX==-1||iY==-1) return false;
		final int tempFillType = 1000;
		floodFill(iX, iY, type, tempFillType, arr);
		for (int r = 0;r<arr.length;r++) {
			for (int c = 0;c<arr[0].length;c++) {
				if (arr[r][c]==type) {
					return false;
				}
			}
		}
		return true;
	}

	public static List<int[][]> getAllArrayOrientations(int[][] array) {
		List<int[][]> orientations = new ArrayList<>();
		boolean h = false, v = false;
		for (int p = 0;p<16;p++) {
			int[][] tempArray = getCopy(array);
			tempArray = flipArray(tempArray, (v)?h=!h:h, v=!v);
			tempArray = rotateArray(tempArray, p/4);
			orientations.add(tempArray);
		}
		for (int i = 0;i<orientations.size()-1;i++) {
			for (int j = i+1;j<orientations.size();) {
				if (equalArrays(orientations.get(i), orientations.get(j))) {
					orientations.remove(j);
				}
				else j++;
			}
		}
		return orientations;
	}

	public interface HasWeight extends Comparable<HasWeight> {//for weighedShuffle
		public float getWeight();

		@Override
		default int compareTo(HasWeight other) {
			if (getWeight()==other.getWeight()) return 0;
			return (getWeight()>other.getWeight())?-1:1;//-1 if higher weight (closer to beginning)
		}
	}

	public static void weighedShuffle(List<HasWeight> list, float shuffledness) {//0 = completely sorted, 1 = complelety randomized
		sort(list);
		for (int i = list.size();i>1;i--) {
			if (rand.nextFloat()<=shuffledness) swap(list, i-1, rand.nextInt(i/2));
		}
	}

	public static <T> void shuffle(List<T> list) {
		for (int i = list.size();i>1;i--) {
			swap(list, i-1, rand.nextInt(i));
		}
	}

	public static <T> void swap(List<T> list, int i, int j) {
		list.set(i, list.set(j, list.get(i)));
	}

	public static void swap(Object[] array, int i, int j) {
		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(List<T> list) {
		Object[] a = list.toArray();
		sort(a);
		ListIterator<T> i = list.listIterator();
		for (int j=0; j<a.length; j++) {
			i.next();
			i.set((T)a[j]);
		}
	}

	public static void sort(Object[] a) {
		mergeSort(a.clone(), a, 0, a.length, 0);
	}

	public static void sort(Object[] a, int fromIndex, int toIndex) {
		rangeCheck(a.length, fromIndex, toIndex);
		mergeSort(copyOfRange(a, fromIndex, toIndex), a, fromIndex, toIndex, -fromIndex);
	}

	private static final int INSERTIONSORT_THRESHOLD = 7;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void mergeSort(Object[] src, Object[] dest, int low, int high, int off) {
		int length = high-low;
		if (length<INSERTIONSORT_THRESHOLD) {
			for (int i = low;i<high;i++) {
				for (int j = i;j>low&&((Comparable) dest[j-1]).compareTo(dest[j])>0;j--) {
					swap(dest, j, j-1);
				}
			}
			return;
		}
		int destLow  = low, destHigh = high;
		low+=off;
		high+=off;
		int mid = (low+high)>>>1;
		mergeSort(dest, src, low, mid, -off);
		mergeSort(dest, src, mid, high, -off);
		if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
			System.arraycopy(src, low, dest, destLow, length);
			return;
		}
		for (int i = destLow, p = low, q = mid;i<destHigh;i++) {
			if (q>=high||p<mid&&((Comparable)src[p]).compareTo(src[q])<=0) {
				dest[i] = src[p++];
			}
			else {
				dest[i] = src[q++];
			}
		}
	}

	private static void rangeCheck(int arrayLen, int fromIndex, int toIndex) {
		if (fromIndex>toIndex) throw new IllegalArgumentException("fromIndex("+fromIndex+") > toIndex("+toIndex+")");
		if (fromIndex<0) throw new ArrayIndexOutOfBoundsException(fromIndex);
		if (toIndex>arrayLen) throw new ArrayIndexOutOfBoundsException(toIndex);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] copyOfRange(T[] original, int from, int to) {
		return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
	}

	@SuppressWarnings("unchecked")
	public static <T,U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {
		int newLength = to-from;
		if (newLength<0) {
			throw new IllegalArgumentException(from+" > "+to);
		}
		T[] copy = ((Object)newType==(Object) Object[].class)?(T[]) new Object[newLength]:(T[]) Array.newInstance(newType.getComponentType(), newLength);
		System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
		return copy;
	}
}

