package de.wallbanger.scenes;

import de.wallbanger.engine.Scene;
import de.wallbanger.sceneObjects.DropDownMenu;

public class SettingsScene extends Scene{
	public SettingsScene() {
		DropDownMenu resolutions = new DropDownMenu(300, 300, 350, 50);
		
		resolutions.addString("1920x1080");
		resolutions.addString("2560x1440");
		resolutions.addString("2800x2100");
		resolutions.addString("3840x1600");
		this.addGameObject(resolutions);
	}
}
