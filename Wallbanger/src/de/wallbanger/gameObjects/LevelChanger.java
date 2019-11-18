package de.wallbanger.gameObjects;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.singleplayer.SPScene;
import de.wallbanger.scenes.singleplayer.horde.Horde;
import de.wallbanger.scenes.start.Starter;

public class LevelChanger extends GameObject {
	
	@Override
	public void init() {
		SPScene.horde.score.setWalls(SPScene.horde.getWalls());
	}
	
	@Override
	public void update(long delta) {
		if(SPScene.horde.score.getPercentage() >= 60) {
			SPScene.horde = new Horde(SPScene.horde.getBallAmount() + 1, SPScene.horde.score.getWalls());
			SPScene.horde.init();
			Starter.engine.setScene(SPScene.horde);
		}
	}
}
