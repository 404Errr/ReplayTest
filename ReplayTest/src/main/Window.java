package main;

import java.awt.GridLayout;

import javax.swing.JFrame;

import player.PlayerInput;

@SuppressWarnings("serial")
public class Window extends JFrame {
	private static JFrame frame;
	private static PlayerInput input;
	static Renderer renderer;

	static void init() {
		renderer = new Renderer();
		input = new PlayerInput();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setSize(Main.getWINDOW_WIDTH(), Main.getWINDOW_HEIGHT());
		frame.setTitle(Main.getWINDOW_WIDTH()+"x"+Main.getWINDOW_HEIGHT());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.add(renderer);
		frame.setVisible(true);
	}
}
