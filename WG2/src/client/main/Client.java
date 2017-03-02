package client.main;

import client.edit.EditHistory;
import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.level.Level;

public class Client {
	public static void run() {
		Level.preInit();
		EditHistory.init();
		Camera.init();
		Game.init();
		Window.init();
		Level.initSpawnPoints();
		ClientUpdateLoop.run();
	}
}
