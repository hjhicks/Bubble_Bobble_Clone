import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * 
 * @author Will Jordan & Gub
 * 
 * creates the abstract class for a projectile object
 *
 */

public abstract class Projectile implements Item {

	private double x;
	private double y;
	private double xDir;
	private double yDir;
	private boolean markToRemove;

	public Projectile(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract void move(ArrayList<Character> characters);

	public void updateXDir(double dir) {
		xDir = dir;
	}

	public void updateYDir(double dir) {
		yDir = dir;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void updateX(double d) {
		x += d * xDir;
	}

	public void updateY(double d) {
		y += d * yDir;
	}

	public boolean getMarkedToRemove() {
		return markToRemove;
	}

	public void setMarkedToRemove(boolean m) {
		markToRemove = m;
	}

}
