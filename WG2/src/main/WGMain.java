package main;

import client.main.Client;
import util.Util;

public class WGMain {
	public static void main(String[] args) {
		
		int[][] foo = Util.getRandomArray(10, 5, 10);
		Util.printArray(foo);
		System.out.println("\n\n");
		foo = Util.rotateArray(foo, 1);
		Util.printArray(foo);
		System.out.println("\n\n");
		foo = Util.rotateArray(foo, 1);
		Util.printArray(foo);
		
		//Client.run();
		
	}
}
