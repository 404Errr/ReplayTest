package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import data.ColorData;
import level.Level;

@SuppressWarnings("serial")
public class Renderer extends JPanel {
	private Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		drawTiles();
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getTiles().length;r++) {
			for (int c = 0;c<Level.getTiles()[0].length;c++) {
				g.setColor(ColorData.getTileColor(Level.getTiles()[r][c].getType()));
				g.fill(Level.getTiles()[r][c].getBounds());
			}
		}
	}

}