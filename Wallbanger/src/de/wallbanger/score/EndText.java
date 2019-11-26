package de.wallbanger.score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.singleplayer.levels.Level;
import de.wallbanger.scenes.start.Wallbanger;

public class EndText extends GameObject {

	private int size;
	private int x;
	private int y;
	private int t;
	private Color c;

	private String text;

	@Override
	public void init() {
		size = 0;
		text = "";
		x = Wallbanger.WINDOW_WIDTH / 3;
		y = Wallbanger.WINDOW_HEIGHT / 2;
		c = Color.RED;
		t = 0;
	}

	@Override
	public void update(long delta) {
		if (Math.round(Level.instance.score.getPercentage()) >= 60) {
			setWin();
		}
		if (Level.instance.score.getWalls() == 0) {
			setOver();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Minecraft", Font.BOLD, size));
		g.setColor(c);
		if (text != null) {
			g.drawString(text, x, y);
		}
	}

	public void setOver() {
		text = "GAMEOVER!";
		size = 100;
	}

	public void setWin() {
		text = "YOU WON!";
		size = 100;

		if (c == Color.RED && t == 3) {
			c = Color.BLACK;
		} else if (c == Color.BLACK && t == 6) {
			c = Color.RED;
			t = 0;
		}
		t++;
	}

}
