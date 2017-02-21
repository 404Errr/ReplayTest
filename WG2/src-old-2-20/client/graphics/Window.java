package client.graphics;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;

import client.input.Input;
import shared.data.GraphicsData;

@SuppressWarnings("serial")
public class Window extends JFrame implements GraphicsData {
	private static JFrame frame;
	private static Input input;
	private static Renderer rendererer;
	private static int scale;

	public static void init() {
		rendererer = new Renderer();
		input = new Input();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setTitle("WG2");
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		frame.setPreferredSize(new Dimension((int)(screen.getDisplayMode().getWidth()*DEFAULT_WINDOW_SCREEN_RATIO), (int)(screen.getDisplayMode().getHeight()*DEFAULT_WINDOW_SCREEN_RATIO)));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.addWindowListener(input);
		frame.addComponentListener(input);
		frame.add(rendererer);
		updateScale();
		System.out.println("Window: "+getWindowWidth()+","+getWindowHeight()+" SCALE: "+getScale());
		frame.setVisible(true);
	}

	public static void updateScale() {//can go below 0 (potential bug)
		int screenSize = Math.min(frame.getWidth(), frame.getHeight());
		scale = (int)(screenSize*Camera.getScaleRatio());
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