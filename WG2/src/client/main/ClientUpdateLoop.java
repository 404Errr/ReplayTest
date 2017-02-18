package client.main;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.input.Cursor;
import client.player.Player;
import client.projectile.Projectile;
import data.Data;

public class ClientUpdateLoop implements Runnable, Data {

	@Override
	public void run() {
		System.out.println("UPS: "+UPS);
		final long updateSpeed = 1000000000/UPS;
		long startTime = 0, wait = 0;
		while (Client.RUNNING) {
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

	private void update() {
		try {
			Cursor.tick();
			for (Player player:Game.getPlayers()) {
				player.tick();
			}
			for (Projectile projectile:Game.getProjectiles()) {
				projectile.tick();
			}
			Camera.tick();
		}
		catch (Exception e) {
			System.err.println("-UPDATE ERROR");
			e.printStackTrace();
		}
	}
}
