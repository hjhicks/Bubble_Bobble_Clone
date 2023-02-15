import java.awt.Rectangle;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;

/**
 * 
 * @author Will Jordan & Gub
 * 
 *         this is where it checks the monsters and players for collision with
 *         platforms
 *
 */
public abstract class Touchable {

	protected ArrayList<Platform> platforms = new ArrayList<Platform>();
	protected ArrayList<Character> characters = new ArrayList<Character>();
	private Platform spec;

	public Touchable(GraphicalComponent g) {
		this.platforms = g.getPlatforms();
		this.characters = g.getCharacters();
	}

	public boolean doesIntersectWithX(Character c) {
		Rectangle bounds = c.getBounds();
		ArrayList<Boolean> pt = new ArrayList<Boolean>();
		for (Platform plat : platforms) {
			if (plat.getBounds().intersects(bounds)) {
				setSpec(plat);
				pt.add(true);
			} else {
				pt.add(false);
			}

		}
		return pt.contains(true);
	}

	public boolean doesIntersectWithY(Character c) {
		Rectangle bounds = c.getBoundsL();
		ArrayList<Boolean> pt = new ArrayList<Boolean>();
		for (Platform plat : platforms) {
			if (plat.getBounds().intersects(bounds)) {

				pt.add(true);
			} else {
				pt.add(false);
			}

		}
		return pt.contains(true);
	}

	public void setSpec(Platform p) {
		spec = p;
	}

	public Platform getSpec() {
		return spec;
	}

	public boolean isRight(Character c, Platform p) {
		return (c.getX() + c.getWidth() <= p.getX());
	}

	public boolean isLeft(Character c, Platform p) {
		return (c.getX() >= p.getX() + p.getWidth());
	}

	public boolean isOnTopOf(Character c) {
		// TODO Auto-generated method stub
		ArrayList<Boolean> pt = new ArrayList<Boolean>();
		for (Platform p : platforms) {
			if ((c.getY() == p.getY() - c.getHeight())
					&& ((c.getX() <= p.getX() + p.getHeight() && c.getX() + c.getWidth() >= p.getX())
							|| (c.getX() <= p.getX() + p.getHeight() && c.getX() >= p.getX()))) {

				pt.add(true);
			} else {
				pt.add(false);
			}
		}
		return pt.contains(true);

	}
	/*
	 * public boolean isTouching(Character c) { // TODO Auto-generated method stub
	 * return false; }
	 * 
	 * public boolean isTouchingRightOf(Character c) { // TODO Auto-generated method
	 * stub
	 * 
	 * ArrayList<Boolean> pt = new ArrayList<Boolean>(); for (Platform p :
	 * platforms) { if ((c.getX() == p.getX() + p.getWidth()) && (p.getY() +
	 * p.getHeight() >= c.getY() && c.getY() >= p.getY())) { pt.add(true); } else {
	 * pt.add(false); } } return pt.contains(true);
	 * 
	 * }
	 * 
	 * public boolean isTouchingLeftOf(Character c) { // TODO Auto-generated method
	 * stub ArrayList<Boolean> pt = new ArrayList<Boolean>(); for (Platform p :
	 * platforms) { if (c.getX() + c.getWidth() == p.getX() && (p.getY() +
	 * p.getHeight() >= c.getY() && c.getY() >= p.getY())) { pt.add(true); } else {
	 * pt.add(false); } } return pt.contains(true); }
	 * 
	 * public boolean isTouchingBottomOf(Character c) { // TODO Auto-generated
	 * method stub ArrayList<Boolean> pt = new ArrayList<Boolean>(); for (Platform p
	 * : platforms) { if (c.getY() <= p.getY() + c.getHeight() && p.getIsBorder() ==
	 * true && ((c.getX() < p.getX() + p.getHeight() && c.getX() + c.getWidth() >
	 * p.getX()) || (c.getX() < p.getX() + p.getHeight() && c.getX() > p.getX()))) {
	 * 
	 * // ((c.getY() == p.getY() - c.getHeight()) && ((c.getX() <= p.getX() + //
	 * p.getHeight() && c.getX() + c.getWidth() >= p.getX()) // || (c.getX() <=
	 * p.getX() + p.getHeight() && c.getX() >= p.getX())))
	 * 
	 * pt.add(true); } else { pt.add(false); } } return pt.contains(true); }
	 */

}
