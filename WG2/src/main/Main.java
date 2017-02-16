package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import game.Game;
import graphics.Camera;
import graphics.Window;
import level.Level;

public class Main {
	private static int SCREEN_HEIGHT, SCREEN_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH;

	public static boolean RUNNING = true;

	private static UpdateLoop updateLoop;
	private static int SCALE;

	public static void main(String[] args) {
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = screen.getDisplayMode().getWidth(), height = screen.getDisplayMode().getHeight();
		int screenSize = (Level.getWidth()>Level.getHeight())?width:height;
		int layoutSize = Math.max(Level.getWidth(), Level.getHeight());
		SCALE = (screenSize*7/8)/layoutSize;

		SCREEN_WIDTH = SCALE*Level.getWidth();
		SCREEN_HEIGHT = SCALE*Level.getHeight();
		WINDOW_WIDTH = SCREEN_WIDTH+3+3;
		WINDOW_HEIGHT = SCREEN_HEIGHT+25+3;

		System.out.println("WxH: "+width+"x"+height+" WxH: "+WINDOW_WIDTH+"x"+WINDOW_HEIGHT+" SCALE: "+SCALE);

		Level.init();
		Game.init();
		Window.init();
		Camera.init();
		updateLoop = new UpdateLoop();
		Thread update = new Thread(updateLoop, "Loop");
		update.start();
	}

	public static int getWINDOW_HEIGHT() {
		return WINDOW_HEIGHT;
	}

	public static int getWINDOW_WIDTH() {
		return WINDOW_WIDTH;
	}

	public static int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}

	public static int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}

	public static int getScale() {
		return SCALE;
	}

	public static void setScale(int scale) {
		Main.SCALE = scale;
	}

}
