package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import data.ColorData;
import data.PlayerData;
import game.Game;
import level.Level;
import main.Main;
import util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData {
	private Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		Graphics2D g = (Graphics2D) g0;
		super.paintComponent(g);
		drawTiles();
//		drawPlayers();
		drawPlayer();

		g.setColor(new Color(0,180,0,255));
		g.setFont(new Font("Helvetica", Font.BOLD, 15));
		g.drawString("x, y: "+Game.getPlayer().getX()+","+Game.getPlayer().getY(), 20, 30);
		g.drawString("dx, dy: "+Game.getPlayer().getdX()+","+Game.getPlayer().getdY(), 20, 45);
		g.drawString("ddx, ddy: "+Game.getPlayer().getddX()+","+Game.getPlayer().getddY(), 20, 60);
		g.drawString("Facing: "+Game.getPlayer().getFacing(), 20, 75);
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		if (!Camera.lockToPlayer()) g.fill(Game.getPlayer().getBounds(0, 0));
		else g.fillRect((int)(Main.getSCREEN_WIDTH()/2-1), (int)(Main.getSCREEN_HEIGHT()/2-1), (int)(PLAYER_SIZE*Main.getScale()-1/Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()));
		g.drawLine(Main.getSCREEN_WIDTH()/2, Main.getSCREEN_HEIGHT()/2, (int)Util.getXComp(Game.getPlayer().getFacing(), 100)+Main.getSCREEN_WIDTH()/2, (int)Util.getYComp(Game.getPlayer().getFacing(), 100)+Main.getSCREEN_HEIGHT()/2);
	//	g.drawLine(Main.getWINDOW_WIDTH()/2, Main.getWINDOW_HEIGHT()/2, (int)(Game.getPlayer().getX()*Main.getScale()+PLAYER_SIZE*Main.getScale()/2), (int)(Game.getPlayer().getY()*Main.getScale()+PLAYER_SIZE*Main.getScale()/2));
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				Rectangle2D b = Level.getTile(r, c).getBounds();//for scrolling (maybe)
				g.fill(new Rectangle((int)((b.getX()-Camera.getX())*Main.getScale()+((Camera.lockToPlayer())?Main.getSCREEN_WIDTH()/2:0)), (int)((b.getY()-Camera.getY())*Main.getScale()+((Camera.lockToPlayer())?Main.getSCREEN_HEIGHT()/2:0)), (int)(b.getWidth()*Main.getScale()), (int)(b.getHeight()*Main.getScale())));

			}
		}
	}

}
