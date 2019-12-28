package de.wallbanger.scenes.start;

import de.wallbanger.engine.Engine;
import de.wallbanger.settings.Settings;

public class Wallbanger {

	public static int WINDOW_WIDTH = 2560;
	public static int WINDOW_HEIGHT = 1440;
	public static final String version = "alpha 1.0.4";
	public static Engine engine = new Engine();
	public static Wallbanger instance;

	public static StartScene startScene = new StartScene();

	public static void main(String[] args) {
		
//		NeuralNetwork nn = new NeuralNetwork(2, 2, 2);
//
//		MatrixFNN inputs = new MatrixFNN(1, 2);
//		inputs.randomize();
//		
//		MatrixFNN targets = new MatrixFNN(1, 2);
//		
//
//		double[] output = nn.feedforward(inputs);
//
//		for (int i = 0; i < output.length; i++) {
//			System.out.println(output[i]);
//		}

		instance = new Wallbanger();
		new Settings("settings");
		
		engine.setScene(startScene);
		engine.start();
	}
}
