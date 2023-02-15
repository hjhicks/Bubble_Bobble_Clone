import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javafx.scene.shape.Ellipse;

/**
 * Dictates the behavior of bubbles within the game.
 * 
 * @author Gub and Will
 *
 */

public class Bubble extends Projectile {

	private static final double SPEED = 1;
	private double dir;
	private boolean caughtMonster;
	private double distance = 0;
	private static final double DIAMETER = 20;
	private static final double MAXDISTANCE = 400;

	public Bubble(double x, double y, double dir) {
		super(x, y);
		this.dir = dir;
	}

	@Override
	public void drawOn(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		Ellipse2D n = new Ellipse2D.Double(this.getX(), this.getY(), DIAMETER, DIAMETER);
		g.draw(n);
		g.fill(n);

	}

	@Override
	public void move(ArrayList<Character> characters) {

		ArrayList<Monster> d = new ArrayList<Monster>();
		for (Character p : characters) {
			if (p.getType().equals("monster")) {

				if (this.getBounds().intersects(((Monster) p).getBounds())) {

					caughtMonster = true;
					d.add((Monster) p);
					((Monster) p).setIsInBubble(true);
				}
			}
		}

		if (caughtMonster) {
			int count = 0;
			for (Monster m : d) {

				if (m.getIsMarked()) {
					setMarkedToRemove(true);
					m.setIsInBubble(false);
				} else {

					if (getY() <= DIAMETER * 2) {
						updateYDir(0);

					} else if (distance >= MAXDISTANCE) {
						caughtMonster = false;
						setMarkedToRemove(true);
						for (Monster s : d) {
							s.setIsInBubble(false);
						}
					} else {

						updateXDir(0);
						updateYDir(-1);

						updateY(1);
						distance += 1;
					}

				}

			}

		} else {
			this.updateXDir(dir);
			double count = 0;
			while (count < 10) {
				this.updateX(SPEED);
				count += SPEED;
			}

		}

	}
/*
	@Override
	public void collidesWith(Character c) {
		// TODO Auto-generated method stub
		if (c.getType().equals("monster")) {

		}

	}

	public boolean doesCollideWith(Character c) {
		if (c.getBounds().intersects(this.getBounds())) {
			return true;
		} else {
			return false;

		}

	}
	*/

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub

		return new Rectangle((int) getX(), (int) getY(), (int) DIAMETER, (int) DIAMETER);

	}

}
