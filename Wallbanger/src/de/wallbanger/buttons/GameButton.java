package de.wallbanger.buttons;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import de.wallbanger.engine.GameObject;
import de.wallbanger.engine.Scene;
import de.wallbanger.scenes.start.Wallbanger;

public class GameButton extends GameObject {

	private float x;
	private float y;
	private BufferedImage image;
	private BufferedImage imageOnHover;
	private BufferedImage usedImage;
	private Scene scene;
	private boolean useWidthAndHeight;
	public static final int height = (int) Math
			.round(Math.sqrt(0.00897 * (Wallbanger.WINDOW_WIDTH * Wallbanger.WINDOW_HEIGHT)));
	public static final int width = (int) Math.round(height * 2.73);

	private boolean notInside;

	public GameButton(BufferedImage image, BufferedImage imageOnHover, Scene scene, int x, int y) {
		this.x = (float) x;
		this.y = (float) y;
		this.image = image;
		this.imageOnHover = imageOnHover;
		this.scene = scene;

		usedImage = image;
	}

	public GameButton(BufferedImage image, Scene scene, int x, int y) {
		this.x = (float) x;
		this.y = (float) y;
		this.image = image;
		this.scene = scene;

		usedImage = image;
	}

	public GameButton(BufferedImage image, BufferedImage imageOnHover, Scene scene, int x, int y, boolean useWidthAndHeight) {
		this.x = (float) x;
		this.y = (float) y;
		this.image = image;
		this.imageOnHover = imageOnHover;
		this.scene = scene;
		this.useWidthAndHeight = useWidthAndHeight;

		usedImage = image;
	}

	@Override
	public void init() {
		notInside = true;
	}

	@Override
	public void onClick(MouseEvent e) {

		if (e.getX() < x)
			return;
		if (e.getX() > x + image.getWidth(null))
			return;
		if (e.getY() < y)
			return;
		if (e.getY() > y + image.getHeight(null))
			return;

		Wallbanger.engine.instance.setScene(scene);
		scene.instance.init();
	}

	@Override
	public void onMoving(MouseEvent e) {
		if (imageOnHover != null) {
			if (notInside) {
				usedImage = image;
			}

			if (e.getX() < x) {
				notInside = true;
				return;
			}
			if (e.getX() > x + image.getWidth()) {
				notInside = true;
				return;
			}
			if (e.getY() < y) {
				notInside = true;
				return;
			}
			if (e.getY() > y + image.getHeight()) {
				notInside = true;
				return;
			}

			notInside = false;
			usedImage = imageOnHover;
		}
	}

	@Override
	public void render(Graphics g) {
		if (useWidthAndHeight == true) {
			g.drawImage(usedImage, Math.round(x), Math.round(y), width, height, null);
		}else {
			g.drawImage(usedImage, Math.round(x), Math.round(y), null);
		}
		
	}
}
