package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class PlayerInput implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

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
