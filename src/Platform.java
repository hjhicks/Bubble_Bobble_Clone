import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Will Jordan & Gub
 * 
 *         creates platforms and draws them
 *
 */
public class Platform {

	private static final double WIDTH = 50;
	private static final double HEIGHT = 50;
	private Color color = Color.RED;
	private double x;
	private double y;
	private boolean isBorder;

	public Platform(double x, double y, boolean isBorder) {
		this.x = x;
		this.y = y;
		this.isBorder = isBorder;
	}

	public void drawOn(Graphics2D g) {

		g.setColor(Color.BLACK);

		Rectangle2D p = new Rectangle2D.Double(this.x, this.y, WIDTH, HEIGHT);
		g.draw(p);

		g.setColor(Color.GRAY);

		g.fill(p);

	}

	public double getY() {
		return this.y;
	}

	public double getX() {
		return x;
	}

	public double getHeight() {
		return HEIGHT;
	}

	public double getWidth() {
		return HEIGHT;
	}

	public boolean getIsBorder() {
		return isBorder;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) WIDTH, (int) HEIGHT);

	}

}
