package client.main;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.level.Level;

public class Client {
	public static void run() {
		Level.init();
//		EditHistory.init();
		Camera.init();
		Game.init();
		Level.initSpawnPoints();
		Window.init();
		ClientUpdateLoop.run();
	}
}
