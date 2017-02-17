package client.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.level.Level;
import data.GraphicsData;

public class Client implements GraphicsData {
	private static int SCREEN_HEIGHT, SCREEN_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH;

	public static boolean RUNNING = true;

	private static ClientUpdateLoop updateLoop;
	private static int SCALE;
	private static GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static void run(boolean online) {
		if (online) {//if should use server
			System.out.println("CLIENT STARTED");
		}
		else {
			System.out.println("OFFLINE CLIENT STARTED");
		}
		
		int width = screen.getDisplayMode().getWidth(), height = screen.getDisplayMode().getHeight();
		int screenSize = Math.min(width, height);
		SCALE = (int)(screenSize*SCALE_RATIO);
		updateScreenSize();
		System.out.println("WxH of screen: "+width+"x"+height+" WxH of window: "+getWINDOW_WIDTH()+"x"+getWINDOW_HEIGHT()+" SCALE: "+SCALE);
		Level.init();
		Game.init();
		Window.init();
		Camera.init();
		updateLoop = new ClientUpdateLoop();
		Thread update = new Thread(updateLoop, "Loop");
		update.start();
	}

	public static void updateScreenSize() {
		SCREEN_WIDTH = (int)(screen.getDisplayMode().getWidth()*WINDOW_SCREEN_RATIO);
		SCREEN_HEIGHT = (int)(screen.getDisplayMode().getHeight()*WINDOW_SCREEN_RATIO);
		WINDOW_WIDTH = SCREEN_WIDTH+3+3;
		WINDOW_HEIGHT = SCREEN_HEIGHT+25+3;
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
		Client.SCALE = scale;
	}

}
