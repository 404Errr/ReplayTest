package client.graphics;

import java.awt.GridLayout;

import javax.swing.JFrame;

import client.main.Client;
import client.player.UserInput;

@SuppressWarnings("serial")
public class Window extends JFrame {
	private static JFrame frame;
	private static UserInput input;
	private static Renderer rendererer;

	public static void init() {
		rendererer = new Renderer();
		input = new UserInput();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setSize(Client.getWINDOW_WIDTH(), Client.getWINDOW_HEIGHT());
		frame.setTitle(Client.getWINDOW_WIDTH()+"x"+Client.getWINDOW_HEIGHT());
//		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.add(rendererer);
		frame.setVisible(true);
	}

	public static Renderer getRendererer() {
		return rendererer;
	}
}
