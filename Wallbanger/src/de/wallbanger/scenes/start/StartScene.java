package de.wallbanger.scenes.start;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.wallbanger.buttons.GameButton;
import de.wallbanger.engine.Scene;
import de.wallbanger.sceneObjects.Background;
import de.wallbanger.sceneObjects.Header;
import de.wallbanger.scenes.SettingsScene;
import de.wallbanger.scenes.credits.CreditsScene;
import de.wallbanger.scenes.multiplayer.MPScene;
import de.wallbanger.scenes.singleplayer.SPScene;
import de.wallbanger.util.ImageUtils;

public class StartScene extends Scene{

	public static StartScene instance;
	private BufferedImage spImage = ImageUtils.fromFile("images/startButton.png");
	private BufferedImage spOnHoverImage = ImageUtils.fromFile("images/startButtonOnHover.png");
	private BufferedImage mpImage = ImageUtils.fromFile("images/mpButton.png");
	private BufferedImage mpOnHoverImage = ImageUtils.fromFile("images/mpButtonOnHover.png");
	private BufferedImage creditsImage = ImageUtils.fromFile("images/creditsButton.png");
	private BufferedImage creditsOnHoverImage = ImageUtils.fromFile("images/creditsButtonOnHover.png");
	private BufferedImage settingsImage = ImageUtils.fromFile("images/settings.png");
	public static SPScene spScene = new SPScene();
	public MPScene mpScene = new MPScene();
	public SettingsScene settingsScene = new SettingsScene();
	public static CreditsScene creditsScene = new CreditsScene();

	public StartScene() {
		instance = this;

		Background background = new Background("images/mainBack.jpg");
		Header version = new Header("version: " + Wallbanger.version, 10, 40, Color.WHITE, 30);

		this.addGameObject(background);
		this.addGameObject(new GameButton(spImage, spOnHoverImage, spScene,
				(Wallbanger.WINDOW_WIDTH / 2) - (GameButton.width / 2),
				(Wallbanger.WINDOW_HEIGHT / 2) - (GameButton.height / 2) - 100, true));
		this.addGameObject(new GameButton(mpImage, mpOnHoverImage, mpScene,
				(Wallbanger.WINDOW_WIDTH / 2) - (GameButton.width / 2),
				(Wallbanger.WINDOW_HEIGHT / 2) - (GameButton.height / 2) + 100, true));
		this.addGameObject(new GameButton(creditsImage, creditsOnHoverImage, creditsScene,
				(Wallbanger.WINDOW_WIDTH / 2) - (GameButton.width / 2),
				(Wallbanger.WINDOW_HEIGHT / 2) - (GameButton.height / 2) + 500, true));
		this.addGameObject(new GameButton(settingsImage, settingsScene, Wallbanger.WINDOW_WIDTH - 120, Wallbanger.WINDOW_HEIGHT - 120));
		this.addGameObject(new Header("images/wallbanger.png"));
		this.addGameObject(version);
	}
}
