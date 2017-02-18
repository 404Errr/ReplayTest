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
import data.Controls;
import data.Data;

public class Input implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener, ComponentListener, WindowListener, Controls, Data {
	@Override
	public void keyPressed(KeyEvent e) {
		playerMovement(e, true);

		switch (e.getKeyCode()) {
		case DEBUG_KEY:
			Renderer.toggleDebug();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		playerMovement(e, false);
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

	@Override
	public void mousePressed(MouseEvent e) {
		Cursor.updateMouse(e);
		Cursor.click(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Cursor.updateMouse(e);
		Cursor.click(e, false);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Window.updateScale();
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
	public void mouseExited(MouseEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
}

