package de.wallbanger.scenes.singleplayer.levels;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.wallbanger.buttons.GameButton;
import de.wallbanger.buttons.LevelIcon;
import de.wallbanger.engine.Scene;
import de.wallbanger.scenes.start.Starter;
import de.wallbanger.util.ImageUtils;

public class LevelsScene extends Scene {

	private BufferedImage resetImage = ImageUtils.fromFile("images/smallReset.png");

	public LevelsScene() {
		this.addGameObject(
				new GameButton(resetImage, Starter.startScene, Starter.WINDOW_WIDTH - 60, 10));

		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 - 800, 100, 0));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 - 400, 100, 1));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2, 100, 2));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 + 400, 100, 3));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 - 800, 350, 4));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 - 400, 350, 5));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2, 350, 6));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 + 400, 350, 7));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 - 800, 600, 8));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 - 400, 600, 9));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2, 600, 10));
		this.addGameObject(new LevelIcon(Starter.WINDOW_WIDTH / 2 + 400, 600, 11));
	}
}
