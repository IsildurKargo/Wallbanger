package de.wallbanger.scenes.credits;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.wallbanger.buttons.GameButton;
import de.wallbanger.engine.Scene;
import de.wallbanger.sceneObjects.Header;
import de.wallbanger.scenes.start.Wallbanger;

public class CreditsScene extends Scene {

	private String dev = "Linus C. Schulze";
	private String helper = "Richard Peteren";
	private String graphics = "Helen Hausdorf";
	private BufferedImage resetImage;

	public CreditsScene() {

		try {
			resetImage = ImageIO.read(new File("images/smallReset.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.addGameObject(new GameButton(resetImage, Wallbanger.startScene, Wallbanger.WINDOW_WIDTH - 60, 10));

																									Header header = new Header("Credits", Wallbanger.WINDOW_WIDTH / 3, Wallbanger.WINDOW_HEIGHT / 4,
																											new Font("04b", Font.BOLD, 160), Color.ORANGE, 100);
		Header developer = new Header("Developer: " + dev, Wallbanger.WINDOW_WIDTH / 5, Wallbanger.WINDOW_HEIGHT / 3,
				new Font("04b", Font.BOLD, 60), Color.GRAY, 60);
		Header help = new Header("Help + Inspiration: " + helper, Wallbanger.WINDOW_WIDTH / 5,
				Wallbanger.WINDOW_HEIGHT / 2 - 80, new Font("04b", Font.BOLD, 60), Color.GRAY, 60);
		Header graphicDesigner = new Header("Graphic Designer: " + graphics, Wallbanger.WINDOW_WIDTH / 5,
				Wallbanger.WINDOW_HEIGHT - 550, new Font("04b", Font.BOLD, 60), Color.GRAY, 60);

		this.addGameObject(header);
		this.addGameObject(developer);
		this.addGameObject(help);
		this.addGameObject(graphicDesigner);
	}
}
