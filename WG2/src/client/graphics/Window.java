package client.graphics;

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
	private static int scale;

	public static void init() {
		rendererer = new Renderer();
		input = new Input();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setSize((int)(screen.getDisplayMode().getWidth()*DEFAULT_WINDOW_SCREEN_RATIO), (int)(screen.getDisplayMode().getHeight()*DEFAULT_WINDOW_SCREEN_RATIO));
		frame.setTitle("WG2");
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.addWindowListener(input);
		frame.addComponentListener(input);
		frame.add(rendererer);
		updateScreenSize();
		frame.setVisible(true);
		System.out.println("Window: "+getWindowWidth()+","+getWindowHeight()+" SCALE: "+getScale());
	}

	public static void updateScreenSize() {
		int screenSize = Math.min(frame.getWidth(), frame.getHeight());
		scale = (int)(screenSize*SCALE_RATIO);
	}

	public static Renderer getRendererer() {
		return rendererer;
	}

	public static int getWindowHeight() {
		return frame.getHeight();
	}

	public static int getWindowWidth() {
		return frame.getWidth();
	}

	public static int getScale() {
		return scale;
	}
}
