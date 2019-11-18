package de.wallbanger.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.wallbanger.scenes.start.Starter;

public class Window extends JPanel implements Runnable {

	private static final long serialVersionUID = -5283263847296305119L;

	Engine engine;

	public Window(Engine engine) {

		SwingUtilities.invokeLater(this);
		this.engine = engine;

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				engine.scene.onClick(e);
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				engine.scene.onMoving(e);
			}
		});
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("WALLBANGER !!!");

		this.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);

		frame.pack();
		frame.setVisible(true);
	}

	public Dimension getPreferredSize() {
		return new Dimension(Starter.WINDOW_WIDTH, Starter.WINDOW_HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		engine.scene.render(g);
	}

}
