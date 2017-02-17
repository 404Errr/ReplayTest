//WG 2: The Hardening
package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	private static final boolean SHOW_DIALOG = false;
	
	public static void main(String[] args) {
		if (SHOW_DIALOG) {
			Object[] options = {"OFFLINE CLIENT", "CLIENT", "SERVER"};
			int run = JOptionPane.showOptionDialog(new JFrame(), "Press the first one.", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			switch (run) {
			case 0:
				client.main.Client.run(false);
				break;
			case 1:
				client.main.Client.run(true);
				break;
			case 2:
				server.main.Server.run();
			}
		}
		else {
			client.main.Client.run(false);
		}
	}
}