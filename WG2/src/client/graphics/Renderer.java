package client.graphics;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.swing.JPanel;

import client.entity.Entity;
import client.game.Game;
import client.input.Cursor;
import client.level.Level;
import client.weapon.GunType;
import client.weapon.Hitscan;
import client.weapon.Projectile;
import data.ColorData;
import data.GraphicsData;
import data.PlayerData;
import main.Debug;
import util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, GraphicsData {
	private static Graphics2D g;

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		setBackground(COLOR_BACKROUND);
		super.paintComponent(g);
		if (Camera.getScale()>0) try {
			drawTiles();
			Debug.drawDebug();
			drawEntities();
			if (Debug.isDrawWeapons()) drawActiveGun();
			drawPlayer();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawEntities() {
		List<Entity> entities = Game.getEntities();
		for (int i = 0;i<entities.size();i++) {
			Entity entity = entities.get(i);
			if (entity instanceof Projectile) {
				drawProjectile((Projectile)entity);
			}
			if (entity instanceof Hitscan) {
				drawHitscan((Hitscan)entity);
			}
		}
	}

	private void drawHitscan(Hitscan hitscan) {
		g.setColor(Util.colorOpacity(hitscan.getColor(), hitscan.getOpacity()));
		g.setStroke(new BasicStroke((int)(hitscan.getWidth()*Camera.getScale())));
		g.drawLine(gridX(hitscan.getiX()), gridY(hitscan.getiY()), gridX(hitscan.getfX()), gridY(hitscan.getfY()));
	}

	private void drawProjectile(Projectile projectile) {
		g.setColor(projectile.getColor());
		g.fill(new Ellipse2D.Double(gridX(projectile.getX())-projectile.getSize()/2, gridY(projectile.getY())-projectile.getSize()/2, Camera.getScale()*projectile.getSize(), Camera.getScale()*projectile.getSize()));
	}

	private void drawActiveGun() {
		GunType gun = Game.getPlayer().getActiveGun().getType();
		g.setColor(Game.getPlayer().getColor());
		g.setStroke(new BasicStroke(gun.getWangWidth()*Camera.getScale()));
		g.drawLine((int)getPlayerX(), (int)getPlayerY(), (int)(getPlayerX()+Util.getXComp(Game.getPlayer().getFacing(), (gun.getWangLength()+HALF_PLAYER_SIZE))*Camera.getScale()), (int)(getPlayerY()-Util.getYComp(Game.getPlayer().getFacing(), (gun.getWangLength()+HALF_PLAYER_SIZE))*Camera.getScale()));
	}

	private void drawPlayer() {
		g.setColor(COLOR_PLAYER);
		g.fillRect((int)(getPlayerX()-getHalfPlayerSize()), (int)(getPlayerY()-getHalfPlayerSize()), (int)getPlayerSize(), (int)getPlayerSize());
	}

	private void drawTiles() {
		for (int r = Camera.getYTile()-GraphicsData.getRenderDistanceY();r<=Camera.getYTile()+GraphicsData.getRenderDistanceY();r++) {
			for (int c = Camera.getXTile()-GraphicsData.getRenderDistanceX();c<=Camera.getXTile()+GraphicsData.getRenderDistanceX();c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(c, r).getColor()!=null) {
					g.setColor(Level.getTile(c, r).getColor());
					g.fillRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
				}
			}
		}
	}

	public static int gridX(float x) {
		return (int)(x*Camera.getScale()+getXOrigin());
	}

	public static int gridY(float y) {
		return (int)(y*Camera.getScale()+getYOrigin());
	}

	private static float getXOrigin() {
		return (-Camera.getX()-HALF_PLAYER_SIZE)*Camera.getScale()+Window.centerX();
	}

	private static float getYOrigin() {
		return (-Camera.getY()-HALF_PLAYER_SIZE)*Camera.getScale()+Window.centerY();
	}

	private static float getPlayerX() {
		return Window.centerX()-Cursor.getPlayerX()*Camera.getScale();
	}

	private static float getPlayerY() {
		return Window.centerY()-Cursor.getPlayerY()*Camera.getScale();
	}

	public static float getPlayerSize() {
		return PLAYER_SIZE*Camera.getScale();
	}

	public static float getHalfPlayerSize() {
		return HALF_PLAYER_SIZE*Camera.getScale();
	}

	public static Graphics2D getG() {
		return g;
	}
}
