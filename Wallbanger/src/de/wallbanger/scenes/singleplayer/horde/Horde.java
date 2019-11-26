package de.wallbanger.scenes.singleplayer.horde;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.wallbanger.buttons.GameButton;
import de.wallbanger.engine.Scene;
import de.wallbanger.gameObjects.Ball;
import de.wallbanger.gameObjects.GameBoard;
import de.wallbanger.gameObjects.LevelChanger;
import de.wallbanger.gameObjects.Wall;
import de.wallbanger.scenes.start.Wallbanger;
import de.wallbanger.score.Scoreboard;
import de.wallbanger.util.ImageUtils;

public class Horde extends Scene {

	public static Horde instance;
	public ArrayList<Ball> balls = new ArrayList<Ball>();

	GameBoard board = new GameBoard();
	Wall wall = new Wall();
	public Scoreboard score = new Scoreboard();
	private int ballAmount;
	private int walls;

	private BufferedImage resetImage = ImageUtils.fromFile("images/smallReset.png");
	private BufferedImage resetOnHoverImage = ImageUtils.fromFile("images/smallResetOnHover.png");

	public Horde(int ballAmount, int walls) {
		this.ballAmount = ballAmount;
		this.walls = walls;
		instance = this;

		score.setWalls(walls);

		for (int i = 0; i < ballAmount; i++) {
			balls.add(new Ball());
		}
		setGO();
		addGO();
	}

	public void setGO() {
		for (Ball ball : balls) {
			ball.setBoard(board);
		}

		board.addGameObject(wall);
		board.addBalls(balls);

		wall.setBoard(board);

		wall.setScoreboard(score);

		wall.setBalls(balls);

		score.setBoard(board);
	}

	public void addGO() {
		this.addGameObject(board);
		this.addGameObject(
				new GameButton(resetImage, resetOnHoverImage, Wallbanger.startScene, Wallbanger.WINDOW_WIDTH - 60, 10));
		this.addGameObject(wall);
		for (Ball ball : balls) {
			this.addGameObject(ball);
		}
		this.addGameObject(score);
		this.addGameObject(new LevelChanger());
	}

	public int getBallAmount() {
		return ballAmount;
	}

	public int getWalls() {
		return walls;
	}
}
