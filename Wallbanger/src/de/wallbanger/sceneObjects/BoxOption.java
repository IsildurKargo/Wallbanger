package de.wallbanger.sceneObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class BoxOption {

	private int x;
	private int y;
	private int width;
	private int height;

	private Color color;
	private String option;

	Font font;
	FontMetrics fontM;

	public BoxOption(String option, int x, int y, int width, int height) {
		this.option = option;
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public void init() {
		color = Color.DARK_GRAY;
		font = new Font("Minecraft", Font.BOLD, height);
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(option, x + 20, y + 45);
		fontM = g.getFontMetrics();
	}

	public void onMoving(MouseEvent e) {
		if (e.getX() < this.x || e.getX() > this.x + this.width || e.getY() < this.y
				|| e.getY() > this.y + this.height) {
			color = Color.DARK_GRAY;
			return;
		} else {
			color = Color.ORANGE;
		}
	}

	public FontMetrics getFontM() {
		return fontM;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
