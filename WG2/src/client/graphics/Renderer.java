package client.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import client.input.Cursor;
import client.level.Level;
import data.ColorData;
import data.GraphicsData;
import data.PlayerData;
import main.Debug;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, GraphicsData {
	private static Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		try {//instead of null checks
			drawTiles();
			Debug.drawDebug();
//			drawProjectiles();
//			drawHitscans();
//			if (drawWeapons) drawActiveGun();
			drawPlayer();
//
//			drawPath(PathFind.lines);
		}
		catch (Exception e) {}
	}


	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		g.fillRect((int)(getPlayerX()-getHalfPlayerSize()), (int)(getPlayerY()-getHalfPlayerSize()), (int)getPlayerSize(), (int)getPlayerSize());
	}

	private void drawTiles() {
		for (int r = Camera.getYTile()-GraphicsData.getRenderDistanceY();r<=Camera.getYTile()+GraphicsData.getRenderDistanceY();r++) {
			for (int c = Camera.getXTile()-GraphicsData.getRenderDistanceX();c<=Camera.getXTile()+GraphicsData.getRenderDistanceX();c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					g.setColor(Level.getTile(r, c).getColor());
					g.fillRect(getGridX(c), getGridY(r), Camera.getScale(), Camera.getScale());
				}
			}
		}
	}

//	private void drawTiles() {//not oprimized
//		for (int r = 0;r<Level.getHeight();r++) {
//			for (int c = 0;c<Level.getWidth();c++) {
//				g.setColor(Level.getTile(r, c).getColor());
//				g.fillRect(getGridX(c), getGridY(r), Camera.getScale(), Camera.getScale());
//			}
//		}
//	}

	private static int getGridX(double x) {
		return (int)(x*Camera.getScale()+getXOrigin());
	}

	private static int getGridY(double y) {
		return (int)(y*Camera.getScale()+getYOrigin());
	}

	private static double getXOrigin() {
		return (-Camera.getX()-HALF_PLAYER_SIZE)*Camera.getScale()+Window.centerX();
	}

	private static double getYOrigin() {
		return (-Camera.getY()-HALF_PLAYER_SIZE)*Camera.getScale()+Window.centerY();
	}
	//return Window.getWindowHeight()/2-(int)(PLAYER_SIZE*Window.getScale()/2)+offset-(int)(Cursor.getYPlayer()*Window.getScale());
	private static double getPlayerX() {
		return Window.centerX()-Cursor.getPlayerX()*Camera.getScale();
	}

	private static double getPlayerY() {
		return Window.centerY()-Cursor.getPlayerY()*Camera.getScale();
	}

	private static double getPlayerSize() {
		return PLAYER_SIZE*Camera.getScale();
	}

	private static double getHalfPlayerSize() {
		return HALF_PLAYER_SIZE*Camera.getScale();
	}

	public static Graphics2D getG() {
		return g;
	}
}
