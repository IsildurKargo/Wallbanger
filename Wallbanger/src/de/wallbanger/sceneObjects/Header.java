package de.wallbanger.sceneObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Starter;

public class Header extends GameObject {

	private Image headerImage;
	private String writtenString;
	private Font font;
	private Color color;
	private int height;

	private int x;
	private int y;

	public Header(String imagePath) {
		try {
			headerImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {

			e.printStackTrace();
		}

		x = (Starter.WINDOW_WIDTH / 2) - (headerImage.getWidth(null) / 2);
		y = (Starter.WINDOW_HEIGHT / 4) - (headerImage.getHeight(null) / 2);
	}

	public Header(String writtenString, int xPos, int yPos, Color color, int height) {
		this.writtenString = writtenString;
		this.x = xPos;
		this.y = yPos;
		this.color = color;
		this.height = height;
	}

	public Header(String writtenString, int xPos, int yPos, Font font, Color color, int height) {
		this.writtenString = writtenString;
		this.x = xPos;
		this.y = yPos;
		this.font = font;
		this.color = color;
		this.height = height;
	}

	@Override
	public void render(Graphics g) {
		if (headerImage != null) {
			g.drawImage(headerImage, Math.round(x), Math.round(y), null);
		}
		if (writtenString != null && font == null) {
			g.setFont(new Font("Minecraft", Font.BOLD, height));
			g.setColor(color);
			g.drawString(writtenString, x, y);
		} else if (writtenString != null && font != null) {
			g.setFont(font);
			g.setColor(color);
			g.drawString(writtenString, x, y);
		}
	}
}
