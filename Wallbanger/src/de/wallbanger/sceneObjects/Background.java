package de.wallbanger.sceneObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Starter;

public class Background extends GameObject {

	private BufferedImage image;

	public Background(String imagePath) {
		try {
				image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, Starter.WINDOW_WIDTH, Starter.WINDOW_HEIGHT, null);
		}
	}
}