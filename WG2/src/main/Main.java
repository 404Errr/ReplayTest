package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	private static boolean CLIENT;

	public static void main(String[] args) {
		Object[] options = {"CLIENT", "SERVER", "CANCEL"};
		int run = JOptionPane.showOptionDialog(new JFrame(), "Don't press SERVER", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		switch (run) {
		case 0:
			CLIENT = true;
			break;
		case 1:
			CLIENT = false;
			break;
		case 2:
			System.exit(0);
		}
		if (CLIENT) {
			client.main.Client.run();
		}
		else {
			server.main.Server.run();
		}
	}
}