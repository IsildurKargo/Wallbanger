package de.wallbanger.scenes.singleplayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import de.wallbanger.buttons.GameButton;
import de.wallbanger.engine.Scene;
import de.wallbanger.sceneObjects.Background;
import de.wallbanger.scenes.singleplayer.horde.Horde;
import de.wallbanger.scenes.singleplayer.levels.LevelsScene;
import de.wallbanger.scenes.start.Wallbanger;
import de.wallbanger.util.ImageUtils;

public class SPScene extends Scene {

	private BufferedImage levelsImage = ImageUtils.fromFile("images/levelsButton.png");
	private BufferedImage levelsOnHoverImage = ImageUtils.fromFile("images/levelsButtonOnHover.png");
	private BufferedImage hordeImage = ImageUtils.fromFile("images/hordeButton.png");
	private BufferedImage hordeOnHoverImage = ImageUtils.fromFile("images/hordeButtonOnHover.png");
	private BufferedImage resetImage = ImageUtils.fromFile("images/smallReset.png");
	private BufferedImage resetOnHoverImage = ImageUtils.fromFile("images/smallResetOnHover.png");
	private double levelX = Wallbanger.WINDOW_WIDTH * 0.052;
	private double hordeX = Wallbanger.WINDOW_WIDTH * 0.755;
	private double y = Wallbanger.WINDOW_HEIGHT * 0.4125;
	public LevelsScene levelsScene = new LevelsScene();
	private static int nextLevel = 0;
	public static Horde horde = new Horde(1, 50);

	public SPScene() {
		horde.score.setWalls(50);

		this.addGameObject(new Background("images/black.png"));
		this.addGameObject(new GameButton(levelsImage, levelsOnHoverImage, levelsScene, (int) levelX, (int) y, true));
		this.addGameObject(new GameButton(hordeImage, hordeOnHoverImage, horde, (int) hordeX, (int) y, true));
		this.addGameObject(new GameButton(resetImage, resetOnHoverImage, Wallbanger.startScene, Wallbanger.WINDOW_WIDTH - 60, 10));
	}

	public int getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(int nextLevel) {
		this.nextLevel = nextLevel;
	}
}
