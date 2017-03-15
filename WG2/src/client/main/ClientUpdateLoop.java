package client.main;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.level.Level;
import client.level.pathfinding.PathFindingTester;
import client.weapon.entity.Projectile;
import data.Data;

public class ClientUpdateLoop implements Data {
	static float currentUpdateTime;

	public static void run() {
		System.out.println("UPS: "+UPS);
		final float updateSpeed = 1000000000/UPS;
		float wait;
		long startTime = 0;
		currentUpdateTime = 0;
		while (true) {
			startTime = System.nanoTime();

			update();//update
			Window.getRendererer().repaint();//repaint the screen

			wait = (updateSpeed-(System.nanoTime()-startTime))/1000000;
			currentUpdateTime = UPS-(UPS*((updateSpeed-(System.nanoTime()-startTime))/updateSpeed));
			if (wait>=1) try {
				Thread.sleep((long)wait);
			}
			catch (Exception e) {}
		}
	}

	private static void update() {
		try {
			for (int i = 0;i<Game.getEntities().size();i++) {//oldest first
				try {
					if (Game.getEntities().get(i).tick()||(Game.getEntities().get(i) instanceof Projectile&&Game.getEntities().size()>MAX_ENTITIES)) {
						Game.getEntities().remove(i);
						i--;
					}
				}
				catch (Exception e) {}
			}
			PathFindingTester.tick();
			Level.tick();
			Camera.tick();
		}
		catch (Exception e) {
			System.err.println("-UPDATE ERROR");
			e.printStackTrace();
		}
	}

	public static float getCurrentUpdateTime() {
		return currentUpdateTime;
	}
}
