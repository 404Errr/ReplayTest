package main;

import data.Data;
import game.Game;
import graphics.Camera;
import graphics.Window;
import player.Cursor;
import player.Player;
import projectile.Projectile;

public class UpdateLoop implements Runnable, Data {

	@Override
	public void run() {
		System.out.println("UPS: "+UPS);
		final long updateSpeed = 1000000000/UPS;
		long startTime = 0, wait = 0;
		while (Main.RUNNING) {
			startTime = System.nanoTime();

			update();//update
			Window.getRendererer().repaint();//refresh the screen

			wait = (updateSpeed-(System.nanoTime()-startTime))/1000000;
			try {
				if (wait>0) Thread.sleep(wait);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
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
//			System.out.println(Cursor.getGridX()+","+Cursor.getGridY());
//			System.out.println(Game.getPlayer().getX()+","+Game.getPlayer().getY());
		}
		catch (Exception e) {
			System.out.println("-UPDATE ERROR");
			e.printStackTrace();
		}
	}
}
