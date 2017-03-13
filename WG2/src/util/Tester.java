package util;

public class Tester {

	public static void main(String[] args) {
		for (int i = 4999;i>=0;i--) {
			String roman = Util.toRomanNumeral(i);
			int num = Util.fromRomanNumeral(roman);
			System.out.println(i+"\t"+num+"\t"+roman);
		}
	}
}