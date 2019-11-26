package de.wallbanger.sceneObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import de.wallbanger.engine.GameObject;

public class DropDownMenu extends GameObject {

	private ArrayList<BoxOption> options = new ArrayList<BoxOption>();

	private float MenuX;
	private float MenuY;
	private float MenuWidth;
	private float MenuHeight;

	private float x;
	private float y;
	private float width;
	private float height;

	private int count;

	private boolean dropDown;

	public DropDownMenu(int MenuX, int MenuY, int width, int height) {
		this.MenuX = (float) MenuX;
		this.MenuY = (float) MenuY;
		this.MenuWidth = (float) width;
		this.MenuHeight = (float) height;

		this.x = (float) MenuX;
		this.y = (float) MenuY;
		this.width = (float) width;
		this.height = (float) height;
	}

	@Override
	public void init() {
		if (options.size() == 0) {
			options.add(new BoxOption("Null", Math.round(x), calculateY(), Math.round(width), Math.round(height)));
		}
		for (BoxOption option : options) {
			option.init();
		}
		dropDown = false;
	}

	@Override
	public void update(long delta) {
		if (dropDown) {
			MenuHeight = this.height * options.size();
		} else {
			MenuY = this.y;
			MenuHeight = this.height;
		}
	}

	@Override
	public void onMoving(MouseEvent e) {
		for (BoxOption boxOption : options) {
			boxOption.onMoving(e);
		}

		if (e.getX() < this.MenuX || e.getX() > this.MenuX + this.MenuWidth || e.getY() < this.MenuY
				|| e.getY() > this.MenuY + this.MenuHeight) {
			dropDown = false;
			return;
		}

		dropDown = true;

	}

	@Override
	public void render(Graphics g) {
		if (dropDown) {
			for (BoxOption option : options) {
				option.render(g);
			}
		} else {
			options.get(0).render(g);
		}
	}

	private int calculateY() {
		float newY;
		newY = this.y + (50 * count);
		count++;
		return Math.round(newY);
	}

	public void addString(String addOption) {
		options.add(new BoxOption(addOption, Math.round(x), calculateY(), Math.round(width), Math.round(height)));
	}
}
