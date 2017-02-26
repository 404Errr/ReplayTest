package client.input;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.event.MouseInputListener;

import client.edit.Edit;
import client.edit.EditHistory;
import client.game.Game;
import client.graphics.Camera;
import client.level.Level;
import client.level.pathfinding.PathFindingTester;
import client.player.ai.AIPlayer;
import data.ControlData;
import data.Data;
import data.PlayerData;
import main.Debug;


public class Input implements KeyListener, MouseInputListener, MouseWheelListener, ComponentListener, WindowListener, ControlData, Data, PlayerData {
	@Override
	public void keyPressed(KeyEvent e) {
		playerMovement(e, true);
		try {
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
			case DEBUG_DRAW_PATHFINDING_KEY:
				Debug.toggleDrawDebugPathfinding();
				break;
			case DEBUG_DRAW_SIGHT_LINES_KEY:
				Debug.toggleSightLines();
				break;
			case KeyEvent.VK_F8:
				Edit.toggleEditMode();
				break;
			case KeyEvent.VK_MINUS://dash
				Camera.changeScaleRatio(-1);
				Camera.updateScale();
				break;
			case KeyEvent.VK_EQUALS://plus
				Camera.changeScaleRatio(1);
				Camera.updateScale();
				break;

			case KeyEvent.VK_Q://teleport 0
				Game.getPlayer().move(Cursor.getTileX(), Cursor.getTileY());
				break;
			case KeyEvent.VK_E://pathfind 0
				PathFindingTester.set1(Game.getPlayer().getXTile(), Game.getPlayer().getYTile());
				PathFindingTester.set2(Cursor.getTileX(), Cursor.getTileY());
				try{
					PathFindingTester.find();
				}
				catch (Exception e1) {}
				break;

			case KeyEvent.VK_BACK_SLASH://pathfind all
				for (int i = 0;i<Game.getEntities().size();i++) {
					if (Game.getEntities().get(i) instanceof AIPlayer) {
						((AIPlayer)Game.getPlayer(i)).setPathTo(Cursor.getTileX(), Cursor.getTileY());
					}
				}

			case KeyEvent.VK_Y://pathfind 1
				((AIPlayer)Game.getPlayer(1)).setPathTo(Cursor.getTileX(), Cursor.getTileY());
				break;
			case KeyEvent.VK_V://toggle movement 1
				((AIPlayer)Game.getPlayer(1)).toggleControl();
				break;
			case KeyEvent.VK_R://teleport 1
				((AIPlayer)Game.getPlayer(1)).move(Cursor.getTileX(), Cursor.getTileY());
				break;

			case KeyEvent.VK_O://pathfind 2
				((AIPlayer)Game.getPlayer(2)).setPathTo(Cursor.getTileX(), Cursor.getTileY());
				break;
			case KeyEvent.VK_M://toggle movement 2
				((AIPlayer)Game.getPlayer(2)).toggleControl();
				break;
			case KeyEvent.VK_U://teleport 2
				((AIPlayer)Game.getPlayer(2)).move(Cursor.getTileX(), Cursor.getTileY());
				break;

			case KeyEvent.VK_CLOSE_BRACKET://pathfind 3
				((AIPlayer)Game.getPlayer(3)).setPathTo(Cursor.getTileX(), Cursor.getTileY());
				break;
			case KeyEvent.VK_SLASH://toggle movement 3
				((AIPlayer)Game.getPlayer(3)).toggleControl();
				break;
			case KeyEvent.VK_P://teleport 3
				((AIPlayer)Game.getPlayer(3)).move(Cursor.getTileX(), Cursor.getTileY());
				break;

			case KeyEvent.VK_COMMA:
				PathFindingTester.set1(Cursor.getTileX(), Cursor.getTileY());
				break;
			case KeyEvent.VK_PERIOD:
				PathFindingTester.set2(Cursor.getTileX(), Cursor.getTileY());
				break;
			case KeyEvent.VK_SHIFT:
				if (e.getKeyLocation()==KeyEvent.KEY_LOCATION_RIGHT) try{
					PathFindingTester.find();
				}
				catch (Exception e1) {}
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

			case KeyEvent.VK_Z:
				if (Edit.editMode&&!e.isControlDown()) Edit.changeType(-1);
				break;
			case KeyEvent.VK_X:
				if (Edit.editMode&&!e.isControlDown()) Edit.changeType(1);
				break;
			case KeyEvent.VK_N:
				if (Edit.editMode) Edit.floodFill();
				break;
			}
		}
		catch (Exception e1) {}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		playerMovement(e, false);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			if (Edit.editMode) Edit.printLayout();
			break;
		case KeyEvent.VK_Z:
			if (e.isControlDown()) EditHistory.undo();
			break;
		case KeyEvent.VK_0:
			EditHistory.saveState(Level.getLayout());
			break;
		}
	}

	private void playerMovement(KeyEvent e, boolean pressed) {
		try {
			switch (e.getKeyCode()) {
			case UP_KEY_0:
			case UP_KEY_1:
				Game.getPlayer().setMovementControl(UP, pressed);
				break;
			case DOWN_KEY_0:
			case DOWN_KEY_1:
				Game.getPlayer().setMovementControl(DOWN, pressed);
				break;
			case LEFT_KEY_0:
			case LEFT_KEY_1:
				Game.getPlayer().setMovementControl(LEFT, pressed);
				break;
			case RIGHT_KEY_0:
			case RIGHT_KEY_1:
				Game.getPlayer().setMovementControl(RIGHT, pressed);
				break;

			case KeyEvent.VK_T:
				Game.getPlayer(1).setMovementControl(UP, pressed);
				break;
			case KeyEvent.VK_G:
				Game.getPlayer(1).setMovementControl(DOWN, pressed);
				break;
			case KeyEvent.VK_F:
				Game.getPlayer(1).setMovementControl(LEFT, pressed);
				break;
			case KeyEvent.VK_H:
				Game.getPlayer(1).setMovementControl(RIGHT, pressed);
				break;

			case KeyEvent.VK_I:
				Game.getPlayer(2).setMovementControl(UP, pressed);
				break;
			case KeyEvent.VK_K:
				Game.getPlayer(2).setMovementControl(DOWN, pressed);
				break;
			case KeyEvent.VK_J:
				Game.getPlayer(2).setMovementControl(LEFT, pressed);
				break;
			case KeyEvent.VK_L:
				Game.getPlayer(2).setMovementControl(RIGHT, pressed);
				break;

			case KeyEvent.VK_OPEN_BRACKET:
				Game.getPlayer(3).setMovementControl(UP, pressed);
				break;
			case KeyEvent.VK_QUOTE:
				Game.getPlayer(3).setMovementControl(DOWN, pressed);
				break;
			case KeyEvent.VK_SEMICOLON:
				Game.getPlayer(3).setMovementControl(LEFT, pressed);
				break;
			case KeyEvent.VK_ENTER:
				Game.getPlayer(3).setMovementControl(RIGHT, pressed);
				break;
			}
		}
		catch (Exception e1) {}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (!e.isControlDown()) {
			if (e.getWheelRotation()>0) Camera.changeScaleRatio(-1);//zoom out
			else Camera.changeScaleRatio(1);//zoom in
			Camera.updateScale();
		}
		else if (!Edit.editMode) {
			//TODO change weapon
			if (e.getWheelRotation()>0) ;
			else ;
		}
		else {
			if (e.getWheelRotation()>0) Edit.changeType(-1);//zoom out
			else Edit.changeType(1);//zoom in
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Cursor.updateMouse(e);
		if (Edit.editMode) {
			Edit.setEnd();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Cursor.updateMouse(e);
	}

	public static void click(MouseEvent e, boolean down) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1://left
			if (!Edit.editMode) Game.getPlayer().setMouseControl(MOUSE1, down);
			break;
		case MouseEvent.BUTTON2://middle
			Game.getPlayer().setMouseControl(MOUSE2, down);
			break;
		case MouseEvent.BUTTON3://right
			Game.getPlayer().setMouseControl(MOUSE3, down);
			Camera.setZoom(down);
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Cursor.updateMouse(e);
		if (e.getButton()==MouseEvent.BUTTON1&&Edit.editMode) {
			Edit.setStart();
			Edit.setEnd();
			Edit.setDraw(true);
		}
		if (e.getButton()==MouseEvent.BUTTON2&&Edit.editMode) {
			Edit.pickType();
		}
		click(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Cursor.updateMouse(e);
		if (e.getButton()==MouseEvent.BUTTON1&&Edit.editMode) {
			Edit.setDraw(false);
			Edit.setEnd();
			Edit.changeTiles();
			Edit.setStart();
		}
		click(e, false);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Camera.updateScale();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		Game.getPlayer().setAllMovementControl(false);
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
}

