package de.wallbanger.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Wallbanger;

public class Ball extends GameObject {

	private float x;
	private float y;
	private float speedY;
	private float speedX;
	private float radius;
	public Ball instance;
	
	Random rand = new Random();

	GameBoard board;

	public Ball() {
		instance = this;
	}
	
	@Override
	public void init() {
		x = rand.nextInt(Wallbanger.WINDOW_WIDTH);
		y = rand.nextInt(Wallbanger.WINDOW_HEIGHT);
		speedX = 10f / 60 * next();
		speedY = 10f / 60 * next();
		radius = 50f;
		radius = (float) Math.sqrt(((Wallbanger.WINDOW_HEIGHT * Wallbanger.WINDOW_WIDTH)* 0.002)/Math.PI);
	}

	private int next() {
		if (rand.nextBoolean()) {
			return -1;
		} else {
			return 1;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(Math.round(x), Math.round(y), Math.round(radius), Math.round(radius));
	}

	public void update(long delta) {
		moveBall((int) delta);
	}

	private float wallHitRight(int delta) {

		float vX = speedX * delta;

		boolean isOutOfBoundsRight = x + radius + vX > board.getWidth();
		boolean isOutOfBoundsLeft = x + vX < board.getX();

		if (isOutOfBoundsRight) {

			float dTWall = board.getWidth() - x - radius;
			float dFBTWall = (x + radius + vX) - board.getWidth();

			vX = (dTWall - dFBTWall) * -1;

			speedX = speedX * -1;

		} else if (isOutOfBoundsLeft) {

			float dTWall = (x - radius) - board.getX();
			float dFBTWall = (x - radius + vX) - board.getX();

			vX = dTWall - dFBTWall;

			speedX = speedX * -1;

		}

		return vX;

	}

	private float wallHitLeft(int delta) {

		float vY = speedY * delta;

		boolean isOutOfBoundsBottom = y + radius + vY > board.getHeight();
		boolean isOutOfBoundsTop = y + vY < board.getY();

		if (isOutOfBoundsBottom) {

			float dTWall = board.getHeight() - y - radius;
			float dFBTWall = (y + radius + vY) - board.getHeight();

			vY = dTWall - dFBTWall;

			speedY = speedY * -1;

		} else if (isOutOfBoundsTop) {

			float dTWall = (y - radius) - board.getY();
			float dFBTWall = (y - radius + vY) - board.getY();

			vY = dTWall - dFBTWall;

			speedY = speedY * -1;

		}

		return vY;

	}

	private void moveBall(int delta) {

		x = x + wallHitRight(delta);
		y = y + wallHitLeft(delta);

	}

	public float getSpeedX() {
		return speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public float getX() {
		return x + radius;
	}

	public float getY() {
		return y + radius;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}
}
