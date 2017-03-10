package client.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

import javax.swing.JPanel;

import client.edit.Edit;
import client.entity.Entity;
import client.game.Game;
import client.level.Level;
import client.player.Player;
import client.player.ai.AIPlayer;
import client.weapon.Weapon;
import client.weapon.entity.AbstractProjectile;
import client.weapon.entity.FragGrenadeProjectile;
import client.weapon.entity.Hitscan;
import data.ColorData;
import data.GraphicsData;
import data.MapData;
import data.PlayerData;
import main.Debug;
import util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, PlayerData, GraphicsData, MapData {
	private static Graphics2D g;

	@Override
	public void paintComponent(Graphics g0) {
		g = (Graphics2D) g0;
		if (!Edit.editMode) setBackground(COLOR_BACKGROUND);
		else setBackground(COLOR_BACKGROUND.darker());
		super.paintComponent(g);
		try {
			drawTiles();
			Debug.drawDebug();
			if (!Edit.editMode) drawEntities();
			if (Edit.editMode) Edit.drawSelected();

			if (!Edit.editMode&&Debug.isSpawnPointVisibilityLines()) for (int i = 0;i<Level.getSpawnPoints().size();i++) {
				if (Level.getSpawnPoints().get(i).isVisible()) g.setColor(Util.colorOpacity(Color.GREEN, 0.25f));
				else g.setColor(Util.colorOpacity(Color.RED, 0.1f));
				g.fillOval(gridX(Level.getSpawnPoints().get(i).x+0.4f), gridY(Level.getSpawnPoints().get(i).y+0.4f), (int) (Camera.getScale()*0.2f), (int) (Camera.getScale()*0.2f));
				g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/4));
				g.setColor(COLOR_DEBUG_GREEN);
				g.drawString(Level.getSpawnPoints().get(i).getSafetyRating(null)+"", gridX(Level.getSpawnPoints().get(i).x+0.35f), gridY(Level.getSpawnPoints().get(i).y+0.8f));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private void drawUI() {
//		g.setColor(Util.getRedGreenColorShift(Game.getPlayer().getHealth()));
//		g.fillRect(Window.width()-HEALTH_BAR_WIDTH, (int)((1f-Game.getPlayer().getHealth())*Window.height()), HEALTH_BAR_WIDTH, Window.height());
//	}

	private void drawEntities() {
		List<Entity> entities = Game.getEntities();
		for (int i = entities.size()-1;i>=0;i--) {//reverse order so players will be drawn last (the player last too)
			try {
				Entity entity = entities.get(i);
				if (entity instanceof AbstractProjectile) {//FIXME TODO test
					drawEntity((AbstractProjectile) entity);
				}
				if (entity instanceof Hitscan) {
					drawEntity((Hitscan) entity);
				}
				if (entity instanceof FragGrenadeProjectile) {
					drawEntity((FragGrenadeProjectile) entity);
				}
				if (entity instanceof Player) {
					drawPlayer((Player) entity);
					if (entity instanceof AIPlayer) {
						Debug.drawPath(((AIPlayer) entity).getCurrentPath(), ((Player) entity).getColor(), 2);
						if (Debug.isDrawSightLines()) {
							g.setStroke(new BasicStroke(2));
							for (int j = 0;j<((AIPlayer) entity).getSightLines().size();j++) {
								Line2D line = ((AIPlayer) entity).getSightLines().get(j).getLine();
								if (!((AIPlayer) entity).getSightLines().get(j).isBroken()) g.setColor(Util.colorOpacity(Color.BLUE, 0.75f));
								else g.setColor(Util.colorOpacity(Color.RED, 0.25f));
								g.drawLine(gridX((float)line.getX1()), gridY((float)line.getY1()), gridX((float)line.getX2()), gridY((float)line.getY2()));
							}
						}
					}
					if (!Edit.editMode) {
						g.setColor(Color.BLACK);
						g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/3));
						g.drawString((int)(((Player)entity).getHealth()*100)+"", gridX(((Player)entity).getX())+Camera.getScale()/8, gridY(((Player)entity).getY())+Camera.getScale()*5/8);
					}
				}
			}
			catch (Exception e) {
				continue;
			}
		}
	}

	private void drawEntity(FragGrenadeProjectile grenade) {
		g.setColor(grenade.getColor());
		g.fill(new Ellipse2D.Double(gridX(grenade.getX())-grenade.getGrenadeSize()/2, gridY(grenade.getY())-grenade.getGrenadeSize()/2, Camera.getScale()*grenade.getGrenadeSize(), Camera.getScale()*grenade.getGrenadeSize()));
	}

	private void drawEntity(Hitscan hitscan) {
		g.setColor(Util.colorOpacity(hitscan.getColor(), hitscan.getOpacity()));
		g.setStroke(new BasicStroke((int)(hitscan.getWidth()*Camera.getScale())));
		g.drawLine(gridX(hitscan.getiX()), gridY(hitscan.getiY()), gridX(hitscan.getfX()), gridY(hitscan.getfY()));
	}

	private void drawEntity(AbstractProjectile projectile) {
		g.setColor(projectile.getColor());
		g.fill(new Ellipse2D.Double(gridX(projectile.getX())-projectile.getSize()/2, gridY(projectile.getY())-projectile.getSize()/2, Camera.getScale()*projectile.getSize(), Camera.getScale()*projectile.getSize()));
	}

	private void drawWeapon(Player player) {
		Weapon gun = player.getActiveWeapon().getType();
		g.setColor(player.getColor());
		g.setStroke(new BasicStroke(gun.getWidth()*Camera.getScale()));
		float angle = player.getFacing(), wangLength = gun.getLength()+HALF_PLAYER_SIZE;
		g.drawLine(gridX(player.getXCenter()), gridY(player.getYCenter()), gridX(player.getXCenter())+(int)(Util.getXComp(angle, wangLength)*Camera.getScale()), gridY(player.getYCenter())+(int)(-Util.getYComp(angle, wangLength)*Camera.getScale()));
	}

	private void drawPlayer(Player player) {
		g.setColor(player.getColor());
		if (Debug.isDrawWeapons()&&!Edit.editMode) drawWeapon(player);
		g.fillRect(gridX(player.getX()), gridY(player.getY()), (int)getPlayerSize(), (int)getPlayerSize());
	}

	private void drawTiles() {
		for (int r = Camera.getYTile()-GraphicsData.getRenderDistanceY();r<=Camera.getYTile()+GraphicsData.getRenderDistanceY();r++) {
			for (int c = Camera.getXTile()-GraphicsData.getRenderDistanceX();c<=Camera.getXTile()+GraphicsData.getRenderDistanceX();c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					if (Level.getTile(c, r).getColor()!=null) {
						g.setColor(Level.getTile(c, r).getColor());
						g.fillRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
					}
					else if (Edit.editMode) {
						g.setColor(COLOR_BACKGROUND);
						g.fillRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
					}
					if (DRAW_TILE_COORDS) {
//						g.drawRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
						g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/4));
						g.setColor(COLOR_DEBUG_GREEN);
						g.drawString(c+","+r, gridX(c+0.35f), gridY(r+0.7f));
					}
					if (Edit.editMode&&Level.getLayoutType(c, r)==SPAWN_POINT_TYPE) {
//						g.drawRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
						g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/2));
						g.setColor(COLOR_DEBUG_GREEN);
						g.drawString("S", gridX(c+0.35f), gridY(r+0.7f));
					}
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
