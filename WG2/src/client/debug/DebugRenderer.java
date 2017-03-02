package client.debug;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import data.ColorData;
import data.GraphicsData;

@SuppressWarnings("serial")
public class DebugRenderer extends JPanel implements ColorData, GraphicsData {
	private static Graphics2D g;

	@Override
	public void paintComponent(Graphics g0) {
		g = (Graphics2D) g0;
		setBackground(COLOR_BACKGROUND);
		super.paintComponent(g);
	}
}
