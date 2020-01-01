package de.wallbanger.scenes;

import java.awt.Color;
import java.awt.Font;

import de.wallbanger.engine.Scene;
import de.wallbanger.sceneObjects.DropDownMenu;
import de.wallbanger.sceneObjects.Header;
import de.wallbanger.scenes.start.Wallbanger;

public class SettingsScene extends Scene {
	public SettingsScene() {
		Header header = new Header("Settings", Wallbanger.WINDOW_WIDTH / 3, Wallbanger.WINDOW_HEIGHT / 5,
				new Font("04b", Font.BOLD, 160), Color.ORANGE, 100);

		DropDownMenu resolutions = new DropDownMenu(Wallbanger.WINDOW_WIDTH / 4, Wallbanger.WINDOW_HEIGHT / 3, 380, 60,
				"resolution");

		resolutions.addOption("1920x1080");
		resolutions.addOption("2560x1440");
		resolutions.addOption("2800x2100");
		resolutions.addOption("3840x1600");

		this.addGameObject(resolutions);
		this.addGameObject(header);
	}
}
