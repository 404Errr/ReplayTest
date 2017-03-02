package client.debug;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import data.GraphicsData;

@SuppressWarnings("serial")
public class DebugWindow extends JFrame implements GraphicsData {
	private static JFrame frame;
	private static DebugRenderer rendererer;

	public static void init() {
		rendererer = new DebugRenderer();
		rendererer.setDoubleBuffered(true);
//		input = new Input();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setTitle("WG2 Debug");
		frame.setPreferredSize(new Dimension(600, 900));
		frame.pack();
		frame.setLocationRelativeTo(null);
//		frame.addKeyListener(input);
//		frame.addMouseMotionListener(input);
//		frame.addMouseListener(input);
//		frame.addMouseWheelListener(input);
//		frame.addWindowListener(input);
//		frame.addComponentListener(input);
		frame.add(rendererer);
		System.out.println("Window size: "+width()+","+height());
		frame.setVisible(true);
	}

	public static DebugRenderer getRendererer() {
		return rendererer;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static int height() {
		return frame.getHeight();
	}

	public static int width() {
		return frame.getWidth();
	}

	public static int centerX() {
		return frame.getWidth()/2;
	}

	public static int centerY() {
		return frame.getHeight()/2;
	}
}