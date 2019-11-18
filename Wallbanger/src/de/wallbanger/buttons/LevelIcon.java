package de.wallbanger.buttons;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.singleplayer.levels.Level;
import de.wallbanger.scenes.start.Starter;
import de.wallbanger.settings.Settings;

public class LevelIcon extends GameObject {

	private BufferedImage iconImage;
	private int x;
	private int y;
	public Integer levelNum;
	public static LevelIcon instance;

	public LevelIcon(int posX, int posY, int level) {
		instance = this;
		try {
			iconImage = ImageIO.read(new File("images/greyIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = posX;
		y = posY;

		this.levelNum = level;
	}

	@Override
	public void onClick(MouseEvent e) {
		if (levelNum + 1 <= Integer.parseInt(Settings.instance.pref.get("level", "1"))) {
			if (e.getX() < x)
				return;
			if (e.getX() > x + iconImage.getWidth(null))
				return;
			if (e.getY() < y)
				return;
			if (e.getY() > y + iconImage.getHeight(null))
				return;

			Level level = new Level(levelNum);
			
			Starter.engine.instance.setScene(level);
			level.init();
		}
	}

	@Override
	public void update(long delta) {
		if (levelNum + 1 <= Integer.parseInt(Settings.instance.pref.get("level", "1"))) {
			try {
				iconImage = ImageIO.read(new File("images/whiteIcon.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(iconImage, Math.round(x), Math.round(y), null);
		g.setFont(new Font("GoGoPosterPunch", Font.BOLD, 80));
		Integer levelString = levelNum + 1;
		g.drawString(levelString.toString(), Math.round(x) + iconImage.getWidth(null) / 2 - 20,
				Math.round(y) + iconImage.getHeight(null) / 2 + 30);
	}
}
