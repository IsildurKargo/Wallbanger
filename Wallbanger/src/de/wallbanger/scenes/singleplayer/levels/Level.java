package de.wallbanger.scenes.singleplayer.levels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.wallbanger.buttons.GameButton;
import de.wallbanger.engine.Scene;
import de.wallbanger.gameObjects.Ball;
import de.wallbanger.gameObjects.GameBoard;
import de.wallbanger.gameObjects.LevelUpdater;
import de.wallbanger.gameObjects.Wall;
import de.wallbanger.scenes.start.Starter;
import de.wallbanger.score.EndText;
import de.wallbanger.score.Scoreboard;

public class Level extends Scene{
	public static Level instance;
	public int thisNum;
	public ArrayList<Ball> balls = new ArrayList<Ball>();
	GameBoard board = new GameBoard();
	Wall wall = new Wall();
	public Scoreboard score = new Scoreboard();
	public EndText over = new EndText();
	
	private BufferedImage resetImage;
	private BufferedImage resetOnHoverImage;

	public void setLevelNum(int thisNum) {
		this.thisNum = thisNum;
	}
	
	public int getLevelNum() {
		return thisNum;
	}
	
	public Level(int thisNum) {
		instance = this;

		try {
			resetImage = ImageIO.read(new File("images/smallReset.png"));
			resetOnHoverImage = ImageIO.read(new File("images/smallResetOnHover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.thisNum = thisNum;

		for (int i = 0; i < thisNum + 1; i++) {
			balls.add(new Ball());
		}

		

		for (Ball ball : balls) {
			ball.setBoard(board);
		}

		board.setWall(wall);
		board.addBalls(balls);

		wall.setBoard(board);

		wall.setScoreboard(score);

		wall.setBalls(balls);

		score.setOver(over);
		score.setBoard(board);

		this.addGameObject(board);
		this.addGameObject(
				new GameButton(resetImage, resetOnHoverImage, Starter.startScene, Starter.WINDOW_WIDTH - 60, 10));
		this.addGameObject(wall);
		for (Ball ball : balls) {
			this.addGameObject(ball);
		}
		this.addGameObject(score);
		this.addGameObject(over);
		this.addGameObject(new LevelUpdater());
		
	}	
}
