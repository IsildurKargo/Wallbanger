package de.wallbanger.score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.wallbanger.engine.GameObject;
import de.wallbanger.gameObjects.GameBoard;
import de.wallbanger.scenes.start.Starter;

public class Scoreboard extends GameObject {

	private int walls;
	private static float percentage;
	private Color c;
	private Integer level;

	EndText over;
	GameBoard board;
	public static Scoreboard instance;

	public Scoreboard() {
		instance = this;
	}

	@Override
	public void init() {
		walls = 10;
		percentage = 0;
		c = Color.BLACK;
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Minecraft", Font.BOLD, 25));
		g.setColor(c);
		g.drawString("Score: " + Math.round(percentage) + " / 100 % " + "Walls: " + walls, 10, 30);
	}

	@Override
	public void update(long delta) {
		if (walls < 0) {
			walls = 0;
		}
		changeScore();
	}

	private void changeScore() {
		percentage = 100 - (((board.getHeight() - board.getY()) * (board.getWidth() - board.getX()))
				/ (Starter.WINDOW_HEIGHT * Starter.WINDOW_WIDTH)) * 100;
	}

	public void removeWall() {
		walls = walls - 1;
	}

	public void setWalls(int walls) {
		this.walls = walls;
	}

	public void setOver(EndText over) {
		this.over = over;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public float getPercentage() {
		return percentage;
	}
	
	public int getWalls() {
		return walls;
	}
}
