package server.graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import shared.data.WindowData;

@SuppressWarnings("serial")
public class Window extends JFrame implements WindowData {
	private static JFrame frame;

	public static void init() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setTitle("WG2 Server");
		frame.setPreferredSize(new Dimension(400,300));
		frame.pack();
		frame.setLocationRelativeTo(null);
		System.out.println("Server window created.");
		frame.setVisible(true);
	}
}
