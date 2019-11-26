package de.wallbanger.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Wallbanger;
import de.wallbanger.score.Scoreboard;

public class Wall extends GameObject {

	private int direction; // 1 - horizontal || 3 - vertical || 0 - no direction

	private float x; // ←x→ cord pixel (←)
	private float y; // ↑y↓ cord pixel (↓)
	private float width; // wall width (→)
	private float height; // wall height (↑)

	private float speedX;
	private float speedY;

	private boolean completedWallHorizontal; // boolean if the horizontal wall is hitting the border on both sides
	private boolean completedWallVertical; // boolean if the vertical wall is hitting the border on both sides

	private Scoreboard scoreboard;
	private GameBoard gameBoard;
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	@Override
	public void init() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;

		speedX = 10f / 30;
		speedY = 10f / 30;

		completedWallHorizontal = false;
		completedWallVertical = false;

		direction = 0;
	}

	@Override
	public void onClick(MouseEvent e) {
		this.init();
		scoreboard.removeWall();

		direction = e.getButton();
		if (direction == 3) {
			x = e.getX() - ((int) Math.round(Wallbanger.WINDOW_WIDTH / 51.2) / 2);
			y = e.getY();
			width = (int) Math.round(Wallbanger.WINDOW_WIDTH / 51.2);
		}
		if (direction == 1) {
			x = e.getX();
			y = e.getY() - ((int) Math.round(Wallbanger.WINDOW_HEIGHT / 28.8) / 2);
			height = (int) Math.round(Wallbanger.WINDOW_HEIGHT / 28.8);
		}
	}

	@Override
	public void update(long delta) {
		direction(delta);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}

	private void direction(long delta) {
		hitdetection();
		if (direction == 1) { // left-click -> horizontal
			extendWallHorizontal(delta);
		} else if (direction == 3) { // right-click -> vertical
			extendWallVertical(delta);
		}
	}

	// HITDETECTION BALL -- WALL
	private void hitdetection() {
		for (Ball ball : balls) {
			// NOT (ball y smaller than y - underneath wall) AND NOT (ball y higher than y
			// - top of wall)
			if (!(ball.getY() < this.y) && !(ball.getY() > this.y + this.height)) {
				// NOT (ball x smaller than x - left of wall) AND NOT (ball x higher than x
				// - right of wall)
				if (!(ball.getX() < this.x) && !(ball.getX() > this.x + this.width)) {
					this.init();
				}
			}
		}
	}

	// BALLSIDE DETECTION - prevent different sides
	private boolean ballSideDetectionHorizontal() {
		int ballSideCount = 0;
		// for each ball in balls add 1 if he is above, remove one if he is underneath
		for (Ball ball : balls) {
			if (ball.getY() >= this.y + height) {
				ballSideCount++;
			} else if (ball.getY() < this.y) {
				ballSideCount--;
			}
		}
		// if ballSideCount is unequal |amount of balls| remove wall
		if (ballSideCount == balls.size() || ballSideCount == -balls.size()) {
			return true;
		} else {
			return false;
		}
	}

	// BALLSIDE DETECTION - prevent different sides
	private boolean ballSideDetectionVertical() {
		int ballSideCount = 0;
		// for each ball in balls add 1 if he is right, remove one if he is left
		for (Ball ball : balls) {
			if (ball.getX() >= this.x + width) {
				ballSideCount++;
			} else if (ball.getX() < this.x) {
				ballSideCount--;
			}
		}
		// if ballSideCount is unequal |amount of balls| remove wall
		if (ballSideCount == balls.size() || ballSideCount == -balls.size()) {
			return true;
		} else {
			return false;
		}
	}

	// EXTENDS WALL FOR CURRENT FRAME ON VERTICAL AXIS
	private void extendWallHorizontal(long delta) {
		float velocityX = speedX * delta;
		float velocityWidth = (speedX * delta) * 2;

		// CALCULATING X AND WIDTH FOR CURRENT FRAME

		// if x and width both are at maximum they supposed to stay there
		if (x + velocityX <= 0 && width + velocityWidth >= gameBoard.getWidth()) {
			velocityX = 0;
			x = 0;
			velocityWidth = 0;
			width = gameBoard.getWidth();

			// if all balls are on one side shrink gameBoard
			if (ballSideDetectionHorizontal()) {
				completedWallHorizontal = true;
			} else {
				this.init();
			}
			// if only x is at maximum stay there and slow down width because width equals
			// two times speedX
		} else if (x + velocityX <= 0) {
			velocityX = 0;
			x = 0;
			velocityWidth = speedX * delta;

			// if only width is at maximum stay there by slowing down to half speed
		} else if (width + velocityWidth >= gameBoard.getWidth()) {
			velocityWidth = speedX * delta;
			width = gameBoard.getWidth();
		}

		// set x and width for the current frame if border isn't completed yet
		if (completedWallHorizontal == false) {
			x -= velocityX;
			width += velocityWidth;
		}
	}

	// EXTENDS WALL FOR CURRENT FRAME ON HORIZONTAL AXIS
	private void extendWallVertical(long delta) {
		float velocityY = speedY * delta;
		float velocityHeight = (speedY * delta) * 2;

		// CALCULATING Y AND HEIGHT FOR CURRENT FRAME

		// if y and height both are at maximum they supposed to stay there
		if (y + velocityY <= 0 && height + velocityHeight >= gameBoard.getHeight()) {
			velocityY = 0;
			y = 0;
			velocityHeight = 0;
			height = gameBoard.getHeight();

			// if all balls are on one side shrink gameBoard
			if (ballSideDetectionVertical()) {
				completedWallVertical = true;
			} else {
				this.init();
			}
			// if only y is at maximum stay there and slow down height because height equals
			// two times speedY
		} else if (y + velocityY <= 0) {
			velocityY = 0;
			y = 0;
			velocityHeight = speedY * delta;
			// if only height is at maximum stay there by slowing down to half speed
		} else if (height + velocityHeight >= gameBoard.getHeight()) {
			velocityHeight = speedY * delta;
			height = gameBoard.getHeight();
		}

		// set y and height for the current frame if border isn't completed yet
		if (completedWallVertical == false) {
			y -= velocityY;
			height += velocityHeight;
		}
	}

	public void setBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void addBall(Ball ball) {
		balls.add(ball);
	}

	public void addBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}

	public boolean isCompletedWallHorizontal() {
		return completedWallHorizontal;
	}

	public boolean isCompletedWallVertical() {
		return completedWallVertical;
	}

}
