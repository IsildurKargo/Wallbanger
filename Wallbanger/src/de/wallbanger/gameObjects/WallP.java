package de.wallbanger.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Starter;
import de.wallbanger.score.Scoreboard;

public class Wall extends GameObject {

	private float x;
	private float y;
	private float width;
	private float height;
	private int button;
	private float speedY;
	private float speedX;
	int hitH = 0;
	int hitV = 0;
	private boolean borderHHRight;
	private boolean borderHHLeft;

	private boolean borderHHRightFinal;
	private boolean borderHHLeftFinal;

	private boolean count = false;
	private float sendX;
	private float sendY;

	private Color color;

	private ArrayList<Ball> balls = new ArrayList<Ball>();

	GameBoard board;
	Scoreboard score;

	public void init() {
		speedX = 10f / 30;
		speedY = 10f / 30;
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		button = 0;
		count = false;
		borderHHRight = false;
		borderHHLeft = false;
		borderHHRightFinal = false;
		borderHHLeftFinal = false;
		color = Color.RED;
	}

	@Override
	public void onClick(MouseEvent e) {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		borderHHLeftFinal = false;
		borderHHRightFinal = false;
		button = e.getButton();
		buttonSetRight(e);
		buttonSetLeft(e);
		count = true;
	}

	public void update(long delta) {
		createWallRightClick((int) delta);
		createWallLeftCLick((int) delta);
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}

	private void buttonSetRight(MouseEvent e) {
		if (button == 3) {
			score.removeWall();
			borderHHRight = false;
			x = e.getX() - ((int) Math.round(Starter.WINDOW_WIDTH / 51.2) / 2);
			y = e.getY();

			sendX = e.getX();
		}
	}

	// creates Wall after a click

	private void createWallRightClick(int delta) {
		rightOneSideBallsDet();

		if (button == 3) {
			if (count) {
				width = (int) Math.round(Starter.WINDOW_WIDTH / 51.2);
				height = 0;
				count = false;
			}

			moveWallRightClick(delta);
		}
		if (borderHHRight == false) {
			hitDetectionRightClick();
		}
	}

	// detects ball hit and removes wall
	private void hitDetectionRightClick() {
		for (Ball ball : balls) {
			if (!(ball.getX() < x) && !(ball.getX() > x + width)) {
				if (!(ball.getY() < y) && !(ball.getY() > y + height)) {
					x = 0;
					y = 0;
					width = 0;
					height = 0;
					button = 0;
				}
			}
		}
	}

	private void moveWallRightClick(int delta) {

		float vDown = speedY * delta; // velocity Down | Height velocity
		float vUp = (speedY * delta) / 2; // velocity Up | Y cord Velocity

		if (y - vUp < board.getY()) { // board.getY is boards height
			vUp = y - board.getY();
		}

		if (y + height + vDown > board.getHeight()) {
			vDown = board.getHeight() - y - height + vUp;
		} else if (y <= board.getY() && y + height + vDown < board.getHeight()) {
			vDown = vDown / 2;
		}

		height = height + vDown;
		y = y - vUp;

		if (y + height + vDown >= board.getHeight() && y <= board.getY()) {
			borderHHRight = true;
		} else {
			borderHHRight = false;
		}
	}

	private void buttonSetLeft(MouseEvent e) {
		if (button == 1) {
			score.removeWall();
			borderHHLeft = false;
			x = e.getX();
			y = e.getY() - ((int) Math.round(Starter.WINDOW_HEIGHT / 28.8) / 2);

			sendY = e.getY();
		}
	}

	// creates Wall after a click

	private void createWallLeftCLick(int delta) {
		leftOneSideBallsDet();

		if (button == 1) {
			if (count) {
				height = (int) Math.round(Starter.WINDOW_HEIGHT / 28.8);
				width = 0;
				count = false;
			}

			moveWallLeft(delta);
		}
		if (borderHHLeft == false) {
			hitDetectionLeft();
		}
	}

	// detects ball hit and removes wall
	private void hitDetectionLeft() {
		for (Ball ball : balls) {
			if (!(ball.getY() < this.y) && !(ball.getY() > this.y + this.height)) {
				if (!(ball.getX() < this.x) && !(ball.getX() > this.x + this.width)) {
					x = 0;
					y = 0;
					width = 0;
					height = 0;
					button = 0;
				}
			}
		}
	}

	private void moveWallLeft(int delta) {
		float vH = speedX * delta; // in right direction
		float vX = (speedX * delta) / 2; // in left direction

		if (x - vX < board.getX()) {
			vX = x - board.getX();
		}

		if (x + width + vH > board.getWidth()) {
			vH = board.getWidth() - x - width + vX; // only vX
		} else if (x <= board.getX() && x + width + vH < board.getWidth()) {
			vH = vH / 2;
		}

		width = width + vH;
		x = x - vX;

		if (x + width + vH >= board.getWidth() && x <= board.getX()) {
			borderHHLeft = true;
		} else {
			borderHHLeft = false;
		}
	}

	private void rightOneSideBallsDet() {
		if (borderHHRight == true) {
			int countBalls = 0;
			for (Ball ball : balls) {
				if (ball.getX() <= this.x) {
					countBalls++;
				} else {
					countBalls--;
				}
			}
			if (countBalls == balls.size() || countBalls == -balls.size()) {
				borderHHRightFinal = true;
				countBalls = 0;
			} else {
				x = 0;
				y = 0;
				width = 0;
				height = 0;
				button = 0;
				borderHHRightFinal = false;
			}
		}
	}

	private void leftOneSideBallsDet() {
		if (borderHHLeft == true) {
			int countBalls = 0;

			for (Ball ball : balls) {
				if (ball.getY() <= this.y) {
					countBalls++;
				} else {
					countBalls--;
				}
			}
			if (countBalls == balls.size() || countBalls == -balls.size()) {
				borderHHLeftFinal = true;
				countBalls = 0;
			} else {
				x = 0;
				y = 0;
				width = 0;
				height = 0;
				button = 0;
				borderHHLeftFinal = false;
			}
		}
	}

	public boolean getBorderHHRight() {
		return borderHHRightFinal;
	}

	public boolean getBorderHHLeft() {
		return borderHHRightFinal;
	}

	public float getX() {
		return sendX;
	}

	public float getHeight() {
		return height;
	}

	public float getY() {
		return sendY;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public float getWidth() {
		return width;
	}

	public void setScore(Scoreboard score) {
		this.score = score;
	}

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}

	public void addBall(Ball ball) {
		balls.add(ball);
	}
}