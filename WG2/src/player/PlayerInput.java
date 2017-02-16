package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import data.Controls;
import data.Data;
import game.Game;

public class PlayerInput implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener, Controls, Data {
	@Override
	public void keyPressed(KeyEvent e) {
		playerMovement(e, true);
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
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {Cursor.updateMouse(e);}
	@Override
	public void mousePressed(MouseEvent e) {Cursor.updateMouse(e);Cursor.click(e, true);}
	@Override
	public void mouseReleased(MouseEvent e) {Cursor.updateMouse(e);Cursor.click(e, false);}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {}
}
