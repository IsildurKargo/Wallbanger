package de.wallbanger.settings;

import java.io.FileOutputStream;
import java.util.prefs.Preferences;

public class Settings {

	public Preferences pref = Preferences.userNodeForPackage(Settings.class);
	public static Settings instance;
	
	public Settings() {
		instance = this;
		
		try {
			pref.exportNode(new FileOutputStream("settings.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
