package client.input;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import client.game.Game;
import client.graphics.Camera;
import client.level.Level;
import client.level.pathfinding.PathFind;
import data.Controls;
import data.Data;
import data.PlayerData;
import main.Debug;


public class Input implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener, ComponentListener, WindowListener, Controls, Data, PlayerData {
	private static boolean mouse1Down, mouse2Down, mouse3Down;
	@Override
	public void keyPressed(KeyEvent e) {
		playerMovement(e, true);

		switch (e.getKeyCode()) {
		/*case ZOOM_KEY:
			Camera.setCursorZoom(true);
			break;*/
		case DEBUG_TEXT_KEY:
			Debug.toggleText();
			break;
		case DEBUG_LOS_LINE_KEY:
			Debug.toggleLOS();
			break;
		case DEBUG_DRAW_WEAPONS_KEY:
			Debug.toggleDrawWeapons();
			break;
		case KeyEvent.VK_MINUS://dash
			Camera.changeScaleRatio(-1);
			Camera.updateScale();
			break;
		case KeyEvent.VK_EQUALS://plus
			Camera.changeScaleRatio(1);
			Camera.updateScale();
			break;
		case KeyEvent.VK_T:
			Game.getPlayer().move(Cursor.getGridX(), Cursor.getGridY());
			break;
		case KeyEvent.VK_I:
			PathFind.set1((int)Cursor.getGridX(), (int)Cursor.getGridY());
			break;
		case KeyEvent.VK_O:
			PathFind.set2((int)Cursor.getGridX(), (int)Cursor.getGridY());
			break;
		case KeyEvent.VK_P:
			if ((PathFind.x1!=PathFind.x2||PathFind.y1!=PathFind.y2)//markers are not in the same place
					&&!Level.getTile(PathFind.x1, PathFind.y1).isSolid(0)//check that neither marker is over a solid tile
					&&!Level.getTile(PathFind.x2, PathFind.y2).isSolid(0)
					&&Level.getTile(PathFind.x1, PathFind.y1).getColor()!=null//both inside the map
					&&Level.getTile(PathFind.x2, PathFind.y2).getColor()!=null) {
				PathFind.go();
			}
			break;
		case KeyEvent.VK_1:
			Game.getPlayer().selectGun(0);
			break;
		case KeyEvent.VK_2:
			Game.getPlayer().selectGun(1);
			break;
		case KeyEvent.VK_3:
			Game.getPlayer().selectGun(2);
			break;
		case KeyEvent.VK_4:
			Game.getPlayer().selectGun(3);
			break;
		case KeyEvent.VK_5:
			Game.getPlayer().selectGun(4);
			break;
		case KeyEvent.VK_6:
			Game.getPlayer().selectGun(5);
			break;
		case KeyEvent.VK_7:
			Game.getPlayer().selectGun(6);
			break;
		case KeyEvent.VK_8:
			Game.getPlayer().selectGun(7);
			break;
		case KeyEvent.VK_9:
			Game.getPlayer().selectGun(8);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		playerMovement(e, false);

		switch (e.getKeyCode()) {
		/*case ZOOM_KEY:
			Camera.setCursorZoom(false);
			break;*/
		}
	}

	private void playerMovement(KeyEvent e, boolean pressed) {
		switch (e.getKeyCode()) {
		case UP_KEY_0:
		case UP_KEY_1:
			Game.getPlayer().setMovementKeyPressed(UP, pressed);
			break;
		case DOWN_KEY_0:
		case DOWN_KEY_1:
			Game.getPlayer().setMovementKeyPressed(DOWN, pressed);
			break;
		case LEFT_KEY_0:
		case LEFT_KEY_1:
			Game.getPlayer().setMovementKeyPressed(LEFT, pressed);
			break;
		case RIGHT_KEY_0:
		case RIGHT_KEY_1:
			Game.getPlayer().setMovementKeyPressed(RIGHT, pressed);
			break;
		}
	}

	private void stopPlayerMovement() {
		for (int i = 0;i<4;i++) {
			Game.getPlayer().setMovementKeyPressed(i, false);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation()>0) {//zoom out
			Camera.changeScaleRatio(-1);
		}
		else {//zoom in
			Camera.changeScaleRatio(1);
		}
		Camera.updateScale();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Cursor.updateMouse(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Cursor.updateMouse(e);
	}

	public static void click(MouseEvent e, boolean down) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1://left
			mouse1Down = down;
			break;
		case MouseEvent.BUTTON2://middle
			mouse2Down = down;
			break;
		case MouseEvent.BUTTON3://right
			mouse3Down = down;
			Camera.setZoom(down);
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Cursor.updateMouse(e);
		click(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Cursor.updateMouse(e);
		click(e, false);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Camera.updateScale();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		stopPlayerMovement();
	}

	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}

	public static boolean isMouse1Down() {
		return mouse1Down;
	}

	public static boolean isMouse2Down() {
		return mouse2Down;
	}

	public static boolean isMouse3Down() {
		return mouse3Down;
	}


}

