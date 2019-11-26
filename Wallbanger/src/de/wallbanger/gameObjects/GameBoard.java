package de.wallbanger.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Wallbanger;

public class GameBoard extends GameObject {

	private float x;
	private float y;
	private float width;
	private float height;

	private boolean hitR;
	private boolean hitL;
	public GameBoard instance;
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	Wall wall;

	public GameBoard() {
		instance = this;
	}

	@Override
	public void init() {
		x = 0;
		y = 0;

		width = Wallbanger.WINDOW_WIDTH;
		height = Wallbanger.WINDOW_HEIGHT;

		hitR = true;
		hitL = true;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
	}

	@Override
	public void update(long delta) {
		borderShrinkHorizontal();
		borderShrinkVertical();
	}

	//if the horizontal wall is complete shrink board accordingly to the side of the ball
	public void borderShrinkVertical() {
		if (wall.isCompletedWallVertical()) {
			if (balls.get(0).getX() < wall.getX()) {
				// right
				width = wall.getX() - x;

			} else if (balls.get(0).getX() > wall.getX() + wall.getWidth()) {
				// left
				if (hitR) {
					width = width - (wall.getX() + wall.getWidth()) + x;
					hitR = false;
				}
				x = wall.getX() + wall.getWidth();
			}
		} else {
			hitR = true;
		}
	}

	//if the vertical wall is complete shrink board accordingly to the side of the ball
	public void borderShrinkHorizontal() {
		if (wall.isCompletedWallHorizontal()) {
			if (balls.get(0).getY() < wall.getY()) {
				// bottom
				height = wall.getY() - y;
			} else if (balls.get(0).getY() > wall.getY() + wall.getHeight()) {
				// top
				if (hitL) {
					height = height - (wall.getY() + wall.getHeight()) + y;
					hitL = false;
				}
				y = wall.getY() + wall.getHeight();
			}

		} else {
			hitL = true;
		}

	}

	public float getHeight() {
		return height + y;
	}

	public float getWidth() {
		return width + x;
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public ArrayList<Ball> getBalls() {
		return balls;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public void addBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}

	public void addGameObject(GameObject gameObject) {
		if (gameObject instanceof Wall) {
			this.wall = (Wall) gameObject;
		} else if (gameObject instanceof Ball) {
			balls.add((Ball) gameObject);
		} else {
			System.err.println("Cannot add gameObject!");
		}
	}

	public void addBall(Ball ball) {
		balls.add(ball);
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}