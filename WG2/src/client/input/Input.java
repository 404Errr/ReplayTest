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
import client.graphics.Renderer;
import client.graphics.Window;
import shared.data.Controls;
import shared.data.Data;
import shared.data.PlayerData;

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
			Renderer.toggleDebugText();
			break;
		case DEBUG_LOS_LINE_KEY:
			Renderer.toggleDebugLOSLine();
			break;
		case DEBUG_DRAW_WEAPONS_KEY:
			Renderer.toggleDrawWeapons();
			break;
		case KeyEvent.VK_T:
			Game.getPlayer().move(Cursor.getXGrid(), Cursor.getYGrid());
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
			Camera.changeScaleRatio(-ZOOM_INCREMENT);
		}
		else {//zoom in
			Camera.changeScaleRatio(ZOOM_INCREMENT);
		}
		Window.updateScale();
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
			Camera.setCursorZoom(down);
			break;
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		Cursor.updateMouse(e);
		click(e, true);
//		if (e.getButton()==MouseEvent.BUTTON1) {
//			Game.getProjectiles().add(new Projectile(1, 0.15d, Game.getPlayer().getColor(), Game.getPlayer().getX()+PLAYER_SIZE/2, Game.getPlayer().getY()+PLAYER_SIZE/2, Game.getPlayer().getdX()+Util.getXComp(Game.getPlayer().getFacing(), 1), Game.getPlayer().getdY()-Util.getYComp(Game.getPlayer().getFacing(), 1)));
//		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Cursor.updateMouse(e);
		click(e, false);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Window.updateScale();
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

