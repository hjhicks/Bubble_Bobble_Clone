import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 * Dictates the behaviors of fruit
 * 
 * @author Gub and Will
 *
 */

public class Fruit implements Item {

	private double x;
	private double y;
	private boolean mark;
	private static final double DIAMETER = 10;

	public Fruit(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void drawOn(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		Ellipse2D n = new Ellipse2D.Double(this.getX(), this.getY(), DIAMETER, DIAMETER);
		g.draw(n);
		g.fill(n);
	}

	private double getY() {
		return this.y;
	}

	private double getX() {
		return this.x;
	}

	/*
	 * @Override public void collidesWith(Character c) { // TODO Auto-generated
	 * method stub }
	 */

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) DIAMETER, (int) DIAMETER);
	}

	public void setMark(boolean b) {
		mark = b;
	}

	public boolean getMark() {
		return mark;
	}

}
