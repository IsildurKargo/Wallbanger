package de.wallbanger.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import de.wallbanger.engine.GameObject;
import de.wallbanger.scenes.start.Starter;

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

		width = Starter.WINDOW_WIDTH;
		height = Starter.WINDOW_HEIGHT;

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
		borderShrinkRight();
		borderShrinkLeft();
	}

	public void borderShrinkRight() {
		if (wall.getBorderHHRight()) {
			for (Ball ball : balls) {
				if (ball.getX() < wall.getX()) {
					// right
					width = wall.getX() - x;

				} else if (ball.getX() > wall.getX() + wall.getWidth()) {
					// left
					if (hitR) {
						width = width - (wall.getX() + wall.getWidth()) + x;
						hitR = false;
					}
					x = wall.getX() + wall.getWidth();
				}
			}
		} else {
			hitR = true;
		}
	}

	public void borderShrinkLeft() {
		if (wall.getBorderHHLeft()) {
			for (Ball ball : balls) {
				if (ball.getY() < wall.getY()) {
					// bottom
					height = wall.getY() - y;
				} else if (ball.getY() > wall.getY() + wall.getHeight()) {
					// top
					if (hitL) {
						height = height - (wall.getY() + wall.getHeight()) + y;
						hitL = false;
					}
					y = wall.getY() + wall.getHeight();
				}
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

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
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