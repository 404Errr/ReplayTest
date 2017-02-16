package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		Object[] options = {"CLIENT", "SERVER", "CANCEL"};
		int run = JOptionPane.showOptionDialog(new JFrame(), "Don't press SERVER", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		switch (run) {
		case 0:
			client.main.Client.run();
			break;
		case 1:
			server.main.Server.run();
			break;
		case 2:
			System.exit(0);
		}
	}
}