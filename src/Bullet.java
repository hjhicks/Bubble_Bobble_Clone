import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Dictates the behavior and effects of bullets within the game.
 * 
 * @author Gub and Will
 *
 */

public class Bullet extends Projectile {
	private double dir;
	private static final double SPEED = 1.5;
	private static final double DIAMETER = 15;

	public Bullet(double x, double y, double dir) {
		super(x, y);
		this.dir = dir;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawOn(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		Ellipse2D n = new Ellipse2D.Double(this.getX(), this.getY(), DIAMETER, DIAMETER);
		g.draw(n);
		g.fill(n);

	}

	@Override
	public void move(ArrayList<Character> c) {
		// TODO Auto- method stub
		Player d = null;
		for (Character p : c) {
			if (p.getType().equals("player")) {
				d = (Player) p;
			}
		}

		if (this.getBounds().intersects(d.getBounds())) {
			d.updateLives(-1);
			d.resetPos();
			setMarkedToRemove(true);
		}
		this.updateXDir(dir);
		double count = 0;
		while (count < 10) {
			this.updateX(SPEED);
			count += SPEED;
		}

	}
/*
	@Override
	public void collidesWith(Character c) {
		// TODO Auto-generated method stub
		if (c.getType().equals("player")) {
			c.resetPos();

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
