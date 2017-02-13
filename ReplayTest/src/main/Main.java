package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import game.Game;

public class Main {
	private static int SCREEN_HEIGHT, SCREEN_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH;

	public static final int UPS = 60;
	public static boolean RUNNING = true;

	private static UpdateLoop updateLoop;

	public static void main(String[] args) {
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = screen.getDisplayMode().getWidth(), height = screen.getDisplayMode().getHeight();
		SCREEN_WIDTH = width-100;
		SCREEN_HEIGHT = height-150;
		WINDOW_WIDTH = SCREEN_WIDTH+3+3;
		WINDOW_HEIGHT = SCREEN_HEIGHT+25+3;
		System.out.println("WxH: "+width+"x"+height);

		updateLoop = new UpdateLoop();
		Game.init();
		Window.init();
		Thread update = new Thread(updateLoop, "Loop");
		update.start();
	}

	public static int getWINDOW_HEIGHT() {
		return WINDOW_HEIGHT;
	}

	public static int getWINDOW_WIDTH() {
		return WINDOW_WIDTH;
	}

}
