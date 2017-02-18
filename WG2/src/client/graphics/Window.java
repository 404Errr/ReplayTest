package client.graphics;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;

import client.input.Input;
import data.WindowData;

@SuppressWarnings("serial")
public class Window extends JFrame implements WindowData {
	private static JFrame frame;
	private static Input input;
	private static Renderer rendererer;
	private static GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private static int windowHeight, windowWidth, scale;

	public static void init() {
		rendererer = new Renderer();
		input = new Input();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		int width = screen.getDisplayMode().getWidth(), height = screen.getDisplayMode().getHeight();
		windowWidth = (int)(width*DEFAULT_WINDOW_SCREEN_RATIO);
		windowHeight = (int)(height*DEFAULT_WINDOW_SCREEN_RATIO);
		frame.setSize(new Dimension(windowWidth, windowHeight));
		frame.setTitle("WG2");
		updateScreenSize();
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.addWindowListener(input);
		frame.addComponentListener(input);
		frame.add(rendererer);
		frame.setVisible(true);
		System.out.println("Window: "+windowWidth+","+windowHeight+" SCALE: "+getScale());
	}


	public static void updateScreenSize() {
		int width = frame.getWidth(), height = frame.getHeight();
		int screenSize = Math.min(width, height);
		scale = (int)(screenSize*SCALE_RATIO);
		windowWidth = (int)(width*DEFAULT_WINDOW_SCREEN_RATIO);
		windowHeight = (int)(height*DEFAULT_WINDOW_SCREEN_RATIO);
	}

	public static Renderer getRendererer() {
		return rendererer;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}

	public static int getWindowWidth() {
		return windowWidth;
	}

	public static int getScale() {
		return scale;
	}
}
