package main;

import data.Data;
import game.Game;
import player.Player;

public class UpdateLoop implements Runnable, Data {

	@Override
	public void run() {
		System.out.println("UPS: "+UPS);
		final long updateSpeed = 1000000000/UPS;
		long startTime = 0, wait = 0;
		while (Main.RUNNING) {
			startTime = System.nanoTime();

			update();//update
			Window.rendererer.repaint();//refresh the screen

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
			for (Player player:Game.getPlayers()) {
				player.tick();
			}
			System.out.println(Game.getPlayer().getX()+","+Game.getPlayer().getY());

			System.out.println(""
					+ Game.getPlayer().getHitboxPoint(0, 0).isTouching()+"|"//tl
					+ Game.getPlayer().getHitboxPoint(1, 0).isTouching()+"|"//tr
					+ Game.getPlayer().getHitboxPoint(0, 1).isTouching()+"|"//bl
					+ Game.getPlayer().getHitboxPoint(1, 1).isTouching()+"|"//br
					+ ""
			);

		}
		catch (Exception e) {
			System.out.println("-UPDATE ERROR");
			e.printStackTrace();
		}
	}
}
