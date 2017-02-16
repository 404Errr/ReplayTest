package graphics;

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

		/*g.setStroke(new BasicStroke(3));
		g.setColor(Color.MAGENTA);
		for (int i = 0;i<4;i++) {
			Rectangle2D s = Game.getPlayer().getHitbox().getSide(i);
			g.fill(new Rectangle.Double(s.getX()*Main.getScale(), s.getY()*Main.getScale(), s.getWidth()*Main.getScale(), s.getHeight()*Main.getScale()));
		}*/
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		if (!Camera.lockToPlayer()) g.fill(Game.getPlayer().getBounds(0, 0));
		else g.fillRect((int)(Main.getSCREEN_WIDTH()/2-1), (int)(Main.getSCREEN_HEIGHT()/2-1), (int)(PLAYER_SIZE*Main.getScale()-1/Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()));
	//	g.drawLine(Main.getWINDOW_WIDTH()/2, Main.getWINDOW_HEIGHT()/2, (int)(Game.getPlayer().getX()*Main.getScale()+PLAYER_SIZE*Main.getScale()/2), (int)(Game.getPlayer().getY()*Main.getScale()+PLAYER_SIZE*Main.getScale()/2));
	}

	private void drawTiles() {
		for (int r = 0;r<Level.getHeight();r++) {
			for (int c = 0;c<Level.getWidth();c++) {
				g.setColor(ColorData.getTileColor(Level.getTile(r, c).getType()));
				Rectangle2D b = Level.getTile(r, c).getBounds();//for scrolling (maybe)
				g.fill(new Rectangle((int)((b.getX()-Camera.getX())*Main.getScale()+Main.getSCREEN_WIDTH()/2), (int)((b.getY()-Camera.getY())*Main.getScale()+Main.getSCREEN_HEIGHT()/2), (int)(b.getWidth()*Main.getScale()), (int)(b.getHeight()*Main.getScale())));

			}
		}
	}

}
