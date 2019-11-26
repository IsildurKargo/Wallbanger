package de.wallbanger.engine;

public class Engine implements Runnable {

	Window window;
	Scene scene;
	public Engine instance;

	public Engine() {
		instance = this;
		window = new Window(this);
	}

	public void start() {
		scene.init();
		new Thread(this).start();
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void run() {
		long lastTimestamp = System.currentTimeMillis();

		while (true) {

			long delta = System.currentTimeMillis() - lastTimestamp;
			lastTimestamp = System.currentTimeMillis();
			scene.update(delta);
			if (window != null) {
				window.repaint();
			}

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {

			}
		}
	}
}