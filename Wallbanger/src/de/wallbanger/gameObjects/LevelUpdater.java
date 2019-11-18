package de.wallbanger.gameObjects;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.singleplayer.levels.Level;
import de.wallbanger.settings.Settings;

public class LevelUpdater extends GameObject{
	
	private static float percentage;
	private static byte utilBit;
	

	@Override
	public void init() {
		percentage = Level.instance.score.getPercentage();
		utilBit = 0;
	}
	
	@Override
	public void update(long delta) {
		unlockNextLevel(Level.instance.balls.size());
		percentage = Level.instance.score.getPercentage();
	}
	
	public static void unlockNextLevel(Integer level) {
		if (utilBit == 0 && Math.round(percentage) >= 60
				&& level.equals(Integer.parseInt(Settings.instance.pref.get("level", "1")))) {
			System.out.println(level);
			Integer nextLevel = Integer.parseInt(Settings.instance.pref.get("level", "1")) + 1;
			String nextLevelString = nextLevel.toString();
			Settings.instance.pref.put("level", nextLevelString);
			utilBit = 1;
		}
	}

}
