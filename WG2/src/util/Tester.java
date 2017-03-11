package util;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//import util.Util.HasRarity;
//
//public class Tester {
//
//	public static void main(String[] args) {
//		List<HasRarity> list = new ArrayList<>();
//		for (int i = 0;i<30;i++) list.add(new Obj(new Random().nextInt(50)+1));
//		Collections.sort(list);
//
//		for (int i = 0;i<list.size();i++) {
//			System.out.print(list.get(i)+" ");
//			for (int j = 0;j<list.get(i).getRarity();j++) System.out.print("-");
//			System.out.println();
//		}
//
//		Util.weighedShuffle(list);
//		System.out.println("\n\n\n");
//
//		for (int i = 0;i<list.size();i++) {
//			System.out.print(list.get(i)+" ");
//			for (int j = 0;j<list.get(i).getRarity();j++) System.out.print("-");
//			System.out.println();
//		}
//	}
//}
//
//class Obj implements HasRarity {
//	int rarity;
//
//	public Obj(int rarity) {
//		this.rarity = rarity;
//	}
//
//	@Override
//	public int getRarity() {
//		return rarity;
//	}
//
//	@Override
//	public String toString() {
//		return rarity+"";
//	}
//
//
//
//}