package server.main;

public class ServerUpdateLoop/*implements Runnable, Data */{
/*
	@Override
	public void run() {
		System.out.println("UPS: "+UPS);
		final long updateSpeed = 1000000000/UPS;
		long startTime = 0, wait = 0;
		while (Server.RUNNING) {
			startTime = System.nanoTime();

			update();//update
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

	}*/

}
