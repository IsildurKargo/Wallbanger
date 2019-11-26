package de.wallbanger.scenes.multiplayer;

import java.util.ArrayList;

import de.wallbanger.engine.Scene;
import de.wallbanger.gameObjects.Ball;
import de.wallbanger.gameObjects.GameBoard;
import de.wallbanger.gameObjects.Wall;
import de.wallbanger.scenes.start.Wallbanger;
import de.wallbanger.score.Scoreboard;

public class MPScene extends Scene {

	public ArrayList<Ball> balls = new ArrayList<Ball>();

	public MPScene() {

		GameBoard gameBoard = new GameBoard();
		Wall wall = new Wall();
		Scoreboard score = new Scoreboard();

		balls.add(new Ball());

		gameBoard.setHeight(Wallbanger.WINDOW_HEIGHT - 400);
		gameBoard.setWidth(Wallbanger.WINDOW_WIDTH - 1400);
		gameBoard.setX(100);
		gameBoard.setY(200);
		gameBoard.setWall(wall);
		gameBoard.addBalls(balls);

		wall.setBoard(gameBoard);
		wall.setBalls(balls);
		
		score.setBoard(gameBoard);

		this.addGameObject(gameBoard);
	}
}
