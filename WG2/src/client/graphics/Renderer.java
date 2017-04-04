package client.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

import javax.swing.JPanel;

import client.edit.Edit;
import client.entity.Entity;
import client.game.Game;
import client.input.Cursor;
import client.level.Level;
import client.level.SpawnPointVisibiltiyLine;
import client.level.pathfinding.PathFindingTester;
import client.main.ClientUpdateLoop;
import client.player.Player;
import client.player.ai.AIPlayer;
import client.weapon.Weapon;
import client.weapon.entity.AbstractProjectile;
import client.weapon.entity.FragGrenadeProjectile;
import client.weapon.entity.Hitscan;
import client.weapon.entity.Triangle;
import client.weapon.weapon.BasicGun;
import client.weapon.weapon.FragGrenade;
import client.weapon.weapon.MachineGun;
import client.weapon.weapon.RCTriangle;
import client.weapon.weapon.RailGun;
import client.weapon.weapon.ShotGun;
import data.ColorData;
import data.GameData;
import data.GraphicsData;
import data.MapData;
import data.PlayerData;
import main.Debug;
import util.Util;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ColorData, GameData, PlayerData, GraphicsData, MapData {
	private static Graphics2D g;

	@Override
	public void paintComponent(Graphics g0) {
		g = (Graphics2D) g0;
		if (!Edit.editMode) setBackground(COLOR_BACKGROUND);
		else setBackground(COLOR_BACKGROUND.darker());
		super.paintComponent(g);
		try {
			drawTiles();
			drawDebug();
			if (!Edit.editMode) drawEntities();
			if (Edit.editMode) Edit.drawSelected();
			if (!Edit.editMode&&Debug.isSpawnPointVisibilityLines()) drawSpawnPointVisibilityLines();
//			drawGUI();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawGUI() {
		drawHealthBar();
		drawInventory();
	}
	
	private void drawInventory() {
		List<Weapon> inventory = Game.getPlayer().getWeapons();
		Weapon hL = Game.getPlayer().getActiveWeapon();
		final int x = (int) (Window.centerX()-(inventory.size()/2*(Window.height()*(INVENTORY_SIZE+INVENTORY_GAP)))), y = (int) (Window.height()*INVENTORY_Y);
		final int gap = (int) (Window.height()*INVENTORY_GAP);
		final int highlighted = inventory.indexOf(hL);
		int currentX = x;
		for (int i = 0;i<inventory.size();i++) {
			int size = (int) (Window.height()*INVENTORY_SIZE*((i==highlighted)?INVENTORY_HIGHLIGHT_SIZE:1));
			drawInventoryIcon(currentX, y-size, size, inventory.get(i));
			currentX+=size+gap;
		}
	}

	private void drawInventoryIcon(int x, int y, int size, Weapon weapon) {
		g.setColor(Color.GRAY);
//		g.fillRect(x, y, size, size);
		g.fillOval(x, y, size, size);
		g.setColor(Color.BLACK);
		if (weapon instanceof BasicGun) {
			drawBasicGunIcon(x, y, size);
		}
		if (weapon instanceof ShotGun) {
			drawShotGunIcon(x, y, size);
		}
		if (weapon instanceof MachineGun) {
			drawMachineGunIcon(x, y, size);
		}
		if (weapon instanceof RailGun) {
			drawRailGunIcon(x, y, size);
		}
		if (weapon instanceof FragGrenade) {
			drawFragGrenadeIcon(x, y, size);
		}
		if (weapon instanceof RCTriangle) {
			drawRCTriangleIcon(x, y, size);
		}
		g.setStroke(new BasicStroke(3));
		g.drawOval(x, y, size, size);
//		g.drawRect(x, y, size, size);
	}

	private void drawBasicGunIcon(int x, int y, int size) {
		g.fillOval(x+size/3, y+size/3, size/3, size/3);
	}
	
	private void drawFragGrenadeIcon(int x, int y, int size) {
		g.fillOval(x+size/3, y+size/3, size/3, size/2);
		g.fillRect(x+size/3, y+size/4, size/3, size/8);
	}
	
	private void drawShotGunIcon(int x, int y, int size) {
		for (int i = 0;i<3;i++) {
			for (int j = 0;j<3;j++) {
				g.fillOval(x+(size/7)+(i*size*2/7), y+(size/7)+(j*size*2/7), size/7, size/7);
			}
		}
	}
	
	private void drawRailGunIcon(int x, int y, int size) {
		g.setStroke(new BasicStroke(size/15));
		g.drawLine(x+size/2, y+size/8, x+size/2, y+size*7/8);
	}
	
	private void drawMachineGunIcon(int x, int y, int size) {
		Polygon poly = new Polygon();
		poly.addPoint((int) (x+size*0.5f), y+(int) (size*0.8f));
		poly.addPoint((int) (x+size*0.27f), y+(int) (size*0.2f));
		poly.addPoint((int) (x+size*0.73f), y+(int) (size*0.2f));		
		g.fill(poly);
	}

	private void drawRCTriangleIcon(int x, int y, int size) {
		Polygon poly = new Polygon();
		poly.addPoint((int) (x+size*0.3), y+(int) (size*0.65f));
		poly.addPoint((int) (x+size*0.5f), y+(int) (size*0.28f));
		poly.addPoint((int) (x+size*0.7f), y+(int) (size*0.65f));
		g.fill(poly);
	}

	private void drawHealthBar() {
		final int size = (int) (Window.height()*HEALTH_BAR_SIZE);
		float health = Game.getPlayer().getHealth();
		float aS = -(1-health*0.75f)*90, aE = -health*0.75f*2*90;
		g.setStroke(new BasicStroke(HEALTH_BAR_WIDTH*Window.height()));
		float opacity = (1-health)*0.5f+0.5f;
		g.setColor(Util.getColorShift(Color.RED, Color.GREEN, health, opacity));
		g.drawArc(Window.centerX()-size/2, Window.centerY()-size/2, size, size, Math.round(aS), Math.round(aE));
	}

	private void drawSpawnPointVisibilityLines() {
		for (int i = 0;i<Level.getSpawnPoints().size();i++) {
			if (!Level.getSpawnPoints().get(i).isVisible()) g.setColor(Util.colorOpacity(Color.GREEN, 0.25f));
			else g.setColor(Util.colorOpacity(Color.RED, 0.1f));
			g.fillOval(gridX(Level.getSpawnPoints().get(i).x+0.2f), gridY(Level.getSpawnPoints().get(i).y+0.2f), (int) (Camera.getScale()*0.6f), (int) (Camera.getScale()*0.6f));
			g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/4));
			g.setColor(COLOR_DEBUG_GREEN);
			g.drawString(Level.getSpawnPoints().get(i).getSafetyRating(null)+"", gridX(Level.getSpawnPoints().get(i).x+0.35f), gridY(Level.getSpawnPoints().get(i).y+0.8f));
		}
	}

	private final static int textX = 25, textY = 30, textSize = 15;
	private void drawDebug() {
		if (DEBUG) {
			if (Debug.isDebugText()) {
				StringBuilder text = new StringBuilder();

				float ups = UPS/(ClientUpdateLoop.getCurrentUpdateTime()/(1000f/UPS));
				if (ups>UPS) ups = UPS;
				text.append("UPS = "+ups+"$");
				text.append("Window = "+Window.width()+"x"+Window.height()+" Map = "+Level.getWidth()+"x"+Level.getHeight()+" Scale = "+Camera.getScale()+"$");
				text.append("Zoomed = "+Camera.isZoomed()+"$");
				text.append("Render Distance = "+GraphicsData.getRenderDistanceX()+", "+GraphicsData.getRenderDistanceY()+"$");
				text.append("X, Y Tile = "+Game.getPlayer().getXTile()+", "+Game.getPlayer().getYTile()+"$");
				text.append("X, Y Exact = ("+Game.getPlayer().getX()+", "+Game.getPlayer().getY()+")"+"$");
				text.append("velocity (m/s) = "+Math.hypot(Game.getPlayer().getdX(), Game.getPlayer().getdY())*GameData.UPS+"$");
				text.append("dx, dy = "+Game.getPlayer().getdX()+", "+Game.getPlayer().getdY()+"$");
				text.append("ddx, ddy = "+Game.getPlayer().getddX()+", "+Game.getPlayer().getddY()+"$");
				text.append("Facing = "+((float)Math.toDegrees(Game.getPlayer().getFacing())+((Game.getPlayer().getFacing()<0)?360:0))+" ("+Game.getPlayer().getFacing()+")"+"$");
				text.append("Cursor = "+Cursor.getScreenX()+","+Cursor.getScreenY()+" ("+Cursor.getPlayerX()+","+Cursor.getPlayerY()+")"+"$");
				text.append("Active Weapon = "+Game.getPlayer().getActiveWeapon()+"$");
				text.append("Debug Text = true, LOS Line = "+Debug.isLosLine()+"$");
				if (Edit.editMode) text.append("Type = "+(char)Edit.getType());

				String[] textLines = text.toString().split("\\$");
				g.setColor(COLOR_DEBUG_GREEN);
				g.setFont(new Font("Helvetica", Font.BOLD, textSize));
				for (int i = 0;i<textLines.length;i++) {
					g.drawString(textLines[i], textX, textY+textSize*i);
				}
			}
			if (Debug.isLosLine()&&!Edit.editMode) {
				g.setColor(COLOR_DEBUG_GREEN);
				g.setStroke(new BasicStroke(1));
				final int w = Window.centerX(), h = Window.centerY(), lineLength = Math.max(Window.width(), Window.height())*2;
				final int cursorPlayerX = (int) (Cursor.getPlayerX()*Camera.getScale()), cursorPlayerY = (int) (Cursor.getPlayerY()*Camera.getScale());
				if (Camera.isZoomed()) {
					g.drawLine(w-cursorPlayerX, h-cursorPlayerY, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w+cursorPlayerX), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h+cursorPlayerY));//los
				}
				else {
					g.drawLine(w, h, (int)(Util.getXComp(Game.getPlayer().getFacing(), lineLength)+w), (int)(-Util.getYComp(Game.getPlayer().getFacing(), lineLength)+h));//los
				}
			}
			if (Debug.isSpawnPointVisibilityLines()&&!Edit.editMode) {
				g.setStroke(new BasicStroke(1));
				for (int i = 0;i<Level.getSpawnPoints().size();i++)for (int j = 0;j<Level.getSpawnPoints().get(i).getSpawnPointVisibilityLines().size();j++) {
					SpawnPointVisibiltiyLine line = Level.getSpawnPoints().get(i).getSpawnPointVisibilityLines().get(j);
					if (!line.isBroken()) g.setColor(Util.colorOpacity(Color.BLUE, 0.75f));
					else g.setColor(Util.colorOpacity(Color.RED, 0.05f));
					g.drawLine(Renderer.gridX(line.getX1()), Renderer.gridY(line.getY1()), Renderer.gridX(line.getX2()), Renderer.gridY(line.getY2()));
				}
			}
			for (int i = PathFindingTester.lines.size()-1;i>=0;i--) if (PathFindingTester.lines!=null) drawPath(PathFindingTester.lines.get(i), PathFindingTester.COLORS[i], 2);
			drawPathDots();
		}
	}

	private void drawPath(List<Point> lines, Color color, int size) {
		if (lines!=null&&Debug.isDrawDebugPathfinding()&&!Edit.editMode) {
			g.setColor(Util.colorOpacity(color, PathFindingTester.OPACITY));
			g.setStroke(new BasicStroke(size));
			for (int i = 1;i<lines.size();i++) {
//				g.setColor(Util.colorOpacity(PathFindingTester.COLORS[i%2], PathFindingTester.OPACITY));
				g.drawLine((int)(Renderer.gridX(lines.get(i-1).x)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(lines.get(i-1).y)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridX(lines.get(i).x)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(lines.get(i).y)+Renderer.getHalfPlayerSize()));
			}
		}
	}

	private void drawPathDots() {
		g.setColor(Color.green);
		g.setStroke(new BasicStroke(7));
		g.drawLine((int)(Renderer.gridX(PathFindingTester.x1)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y1)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridX(PathFindingTester.x1)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y1)+Renderer.getHalfPlayerSize()));
		g.drawLine((int)(Renderer.gridX(PathFindingTester.x2)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y2)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridX(PathFindingTester.x2)+Renderer.getHalfPlayerSize()), (int)(Renderer.gridY(PathFindingTester.y2)+Renderer.getHalfPlayerSize()));
	}

	private void drawEntities() {
		List<Entity> entities = Game.getEntities();
		for (int i = entities.size()-1;i>=0;i--) {//reverse order so players will be drawn last (the player last too)
			try {
				Entity entity = entities.get(i);
				if (entity instanceof AbstractProjectile) {
					drawEntity((AbstractProjectile) entity);
				}
				if (entity instanceof Hitscan) {
					drawEntity((Hitscan) entity);
				}
				if (entity instanceof FragGrenadeProjectile) {
					drawEntity((FragGrenadeProjectile) entity);
				}
				if (entity instanceof Triangle) {
					drawEntity((Triangle) entity);
				}
				if (entity instanceof Player) {
					drawPlayer((Player) entity);
					if (entity instanceof AIPlayer) {
						drawPath(((AIPlayer) entity).getCurrentPath(), ((Player) entity).getColor(), 2);
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
						if (Debug.isDrawWeapons()) drawWeapon((Player) entity);
						drawHealth((Player) entity);
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
		g.fill(new Ellipse2D.Double(gridX(grenade.getX()-grenade.getGrenadeSize()/2), gridY(grenade.getY()-grenade.getGrenadeSize()/2), Camera.getScale()*grenade.getGrenadeSize(), Camera.getScale()*grenade.getGrenadeSize()));
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

	private void drawEntity(Triangle triangle) {
		g.setColor(triangle.getColor());
		g.fill(getTriange(gridX(triangle.getX()), gridY(triangle.getY()), triangle.getFacing(), triangle.getSize()*Camera.getScale()));
		drawPath(triangle.getPathFinder().getCurrentPath(), COLOR_DEBUG_GREEN, 2);
//		g.setColor(COLOR_DEBUG_GREEN);
//		g.setStroke(new BasicStroke(3));
//		g.drawLine(gridX(triangle.getX()), gridY(triangle.getY()), gridX(triangle.gettX()), gridY(triangle.gettY()));
	}

	private void drawHealth(Player player) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/3));
		g.drawString(getHealthString(player.getHealth()), gridX((player).getX())+Camera.getScale()/8, gridY((player).getY())+Camera.getScale()*5/8);
	}

	private void drawWeapon(Player player) {
		Weapon gun = player.getActiveWeapon();
		g.setColor(player.getColor());
		g.setStroke(new BasicStroke(gun.getGirth()*Camera.getScale()));
		float angle = player.getFacing(), length = gun.getDepth();
		g.drawLine(gridX(player.getXCenter()), gridY(player.getYCenter()), gridX(player.getXCenter())+(int)(Util.getXComp(angle, length)*Camera.getScale()), gridY(player.getYCenter())+(int)(-Util.getYComp(angle, length)*Camera.getScale()));
	}

	private void drawPlayer(Player player) {
		g.setColor(player.getColor());
		g.fillRect(gridX(player.getX()), gridY(player.getY()), (int)getPlayerSize(), (int)getPlayerSize());
	}

	private void drawTiles() {
		for (int r = Camera.getYTile()-GraphicsData.getRenderDistanceY();r<=Camera.getYTile()+GraphicsData.getRenderDistanceY();r++) {
			for (int c = Camera.getXTile()-GraphicsData.getRenderDistanceX();c<=Camera.getXTile()+GraphicsData.getRenderDistanceX();c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					if (Level.getTile(c, r).getColor()!=null) {
						g.setColor(Level.getTile(c, r).getColor());
//						if (!canSee((int) Game.getPlayer().getX(), (int) Game.getPlayer().getY(), new Point(c, r))) g.setColor(Color.BLACK);
						g.fillRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
					}
					else if (Edit.editMode) {
						g.setColor(COLOR_BACKGROUND);
						g.fillRect(gridX(c), gridY(r), Camera.getScale(), Camera.getScale());
					}
					if (DRAW_TILE_COORDS) {
						g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/4));
						g.setColor(COLOR_DEBUG_GREEN);
						g.drawString(c+","+r, gridX(c+0.35f), gridY(r+0.7f));
					}
					if (Edit.editMode&&Level.getLayoutType(c, r)==SPAWN_POINT_TYPE) {
						g.setFont(new Font("Helvetica", Font.BOLD, Camera.getScale()/2));
						g.setColor(COLOR_DEBUG_GREEN);
						g.drawString("S", gridX(c+0.35f), gridY(r+0.7f));
					}
				}

			}
		}
	}

//	public static boolean canSee(float x, float y, Point p) {//true if there are no obstacles between p1 and p2
//		if (Util.lineIsBrokenByBooleanArray(x, y, p.x, p.y, Util.negateArray(TileData.getUseable()))) return false;
//		return true;
//	}

	private static final int HEALTH_VISUAL_MAX = 100;

	private static final boolean ROMAN = false;
	private static String getHealthString(float health) {
		if (ROMAN) return Util.toRomanNumeral((int) (health*HEALTH_VISUAL_MAX));
		else return Math.round(health*HEALTH_VISUAL_MAX)+"";
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

	public Polygon getTriange(float x, float y, float angle, float size) {
		Polygon poly = new Polygon();
		double a;
		for (int i = 0;i<3;i++) {
			a = Math.PI*2/3*i+Math.PI/2+angle;
			poly.addPoint((int)(Math.round(x+Math.sin(a)*size)),(int)(Math.round(y+Math.cos(a)*size)));
		}
		return poly;
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
