package client.main;

import client.entity.Entity;
import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import data.Data;

public class UpdateLoop implements Data {

	public static void run() {
		System.out.println("UPS: "+UPS);
		final long updateSpeed = 1000000000/UPS;
		long startTime = 0, wait = 0;
		while (true) {
			startTime = System.nanoTime();

			update();//update
			Window.getRendererer().repaint();//refresh the screen

			wait = (updateSpeed-(System.nanoTime()-startTime))/1000000;
			try {
				if (wait>0) Thread.sleep(wait);
			}
			catch (Exception e) {}
		}
	}

	private static void update() {
		try {
			for (Entity entity:Game.getEntities()) {
				entity.tick();
			}
			Camera.tick();
		}
		catch (Exception e) {
			System.err.println("-UPDATE ERROR");
			e.printStackTrace();
		}
	}
}
