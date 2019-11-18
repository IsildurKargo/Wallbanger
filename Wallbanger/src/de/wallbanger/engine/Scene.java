package de.wallbanger.engine;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Scene {

	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public Scene instance;
	
	public Scene() {
		instance = this;
	}
	
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	public void update(long delta) {
		for (GameObject gameObject : gameObjects) {
			gameObject.update(delta);
		}
	}

	public void init() {
		for (GameObject gameObject : gameObjects) {
			gameObject.init();
		}
	}

	public void render(Graphics g) {
		for (GameObject gameObject : gameObjects) {
			gameObject.render(g);
		}
	}

	public void onClick(MouseEvent e) {
		for (GameObject gameObject : gameObjects) {
			gameObject.onClick(e);
		}
	}

	public void onMoving(MouseEvent e) {
		for (GameObject gameObject : gameObjects) {
			gameObject.onMoving(e);
		}
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
}
