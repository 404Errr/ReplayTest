package util;

import java.util.Map;
import java.util.TreeMap;

public class Roman {
	public static void main(String[] args) {
		for (int i = 4999;i>0;i--) {
			String roman = intToRoman(i);
			System.out.println(i+"\t"+roman+"\t"+romanToInt(roman));
		}
	}

	private static final TreeMap<Integer, String> numerals = new TreeMap<>();
	private static final char[] NUMERAL_CHARS = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

	static {
		boolean p = true;
		for (int i = 1, pI = 0;i<NUMERAL_CHARS.length*2;i++, pI+=(i%5==0)?2:0) {
			numerals.put((int) (Math.pow(10,(((i-1)/4)+1)-1)*new int[] {1, 4, 5, 9}[(i-1)%4]), ((p=!p)?NUMERAL_CHARS[pI]:"")+""+NUMERAL_CHARS[i/2]);
		}
	}

	private static int MIN_VALUE = 1, MAX_VALUE = 4999;
	public static String intToRoman(int num) {
		if (num<MIN_VALUE||num>MAX_VALUE) throw new IllegalArgumentException("Number must be between "+MIN_VALUE+" and "+MAX_VALUE);
		return toRoman(num);
	}

	private static String toRoman(int number) {
		int lower = numerals.floorKey(number);
		if (number==lower) return numerals.get(number);
		return numerals.get(lower)+toRoman(number-lower);
	}

	public static int romanToInt(String roman) {
		roman = roman.toUpperCase();
		int number = fromRoman(roman);
		if (number==-1) throw new IllegalArgumentException("\""+roman+"\" is invalid");
		return number;
	}

	private static int fromRoman(String roman) {
		if (roman.isEmpty()) return 0;
		for (Map.Entry<Integer, String> set:numerals.descendingMap().entrySet()) {
			if (roman.startsWith(set.getValue())) {
				return set.getKey()+fromRoman(roman.substring(set.getValue().length()));
			}
		}
		return -1;
	}
}