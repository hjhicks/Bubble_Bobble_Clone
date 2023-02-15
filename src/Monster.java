import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Abstract class that inherits Character and dictates how a Monster should
 * behave.
 * 
 * @author Gub and Will
 *
 */

public abstract class Monster extends Character {

	private static final double WIDTH = 40;
	private static final double HEIGHT = 40;
	private boolean doesFall = true;

	private boolean isInBubble = false;
	// private boolean isShooter;
	private boolean marked = false;
	private boolean prevCaught = false;

	public Monster(double x, double y, GraphicalComponent g) {
		super(x, y, g);
		// this.isShooter = isShooter;

		Random rand = new Random();
		int n = rand.nextInt(2);
		if (n == 0) {
			this.updateXDir(1);

		} else {
			this.updateXDir(-1);

		}

	}

	public void setFall(boolean s) {
		doesFall = s;
	}

	public void movement(Player p) {

		if (this.getBounds().intersects(p.getBounds()) && p != null && !isInBubble) {
			p.resetPos();
			p.updateLives(-1);
		} else if (this.getBounds().intersects(p.getBounds()) && isInBubble) {

			marked = true;
		}
		if (isInBubble) {
			if (y <= HEIGHT) {
				updateYDir(0);
			} else {

				prevCaught = true;
				updateXDir(0);
				updateYDir(-1);
				updateY(1);
			}

		} else {
			if (prevCaught) {
				Random rand = new Random();
				int n = rand.nextInt(2);
				if (n == 0) {
					this.updateXDir(1);

				} else {
					this.updateXDir(-1);

				}
				prevCaught = false;
			}
			this.moveType();
			if (doesFall) {

				super.move();
			}

		}
	}

	@Override
	public void drawOn(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		Rectangle2D p = new Rectangle2D.Double(this.x, this.y, WIDTH, HEIGHT);
		g.draw(p);
		g.fill(p);
	}

	@Override
	public Rectangle getBoundsL() {
		// TODO Auto-generated method stub
		return new Rectangle((int) this.x - 5, (int) this.y - 5, (int) WIDTH + 5, (int) HEIGHT + 5);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) this.x - 5, (int) this.y - 5, (int) WIDTH, (int) HEIGHT);
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public void launchProjectile() {
		// TODO Auto-generated method stub
		this.getComponent().addProjectile(new Bullet(getX(), getY(), getXDir()));
	}

	public boolean hitPlayer(Player p) {
		if (this.getBounds().intersects(p.getBounds())) {
			return true;
		} else {
			return false;
		}
	}

	public abstract void moveType();

	// @Override
//	public void move() {
	/*
	 * else { if (prevCaught) { Random rand = new Random(); int n = rand.nextInt(2);
	 * if (n == 0) { this.updateXDir(1);
	 * 
	 * } else { this.updateXDir(-1);
	 * 
	 * } prevCaught = false; } else {
	 * 
	 * if (isShooter) { Random rand = new Random(); int n = rand.nextInt(10000); if
	 * (n <= 10) { launchProjectile(); } }
	 * 
	 * if (this.doesIntersectWithX(this) && (!this.isLeft(this, this.getSpec()))) {
	 * updateXDir(-1); updateX(10); } if (this.doesIntersectWithX(this) &&
	 * (!this.isRight(this, this.getSpec()))) { updateXDir(1); updateX(10); }
	 * updateX(SPEED); } super.move();
	 * 
	 * }
	 */
	// }

	@Override
	public boolean getIsPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "monster";
	}

	public void collidesWith(Character c) {
		// TODO Auto-generated method stub
		if (c.getType().equals("player")) {
			c.resetPos();
		}
	}

	public boolean getIsInBubble() {
		return isInBubble;
	}

	public void setIsInBubble(boolean b) {
		isInBubble = b;
	}

	public boolean getIsMarked() {
		return marked;
	}

	public void setIsMarked(boolean b) {
		marked = b;
	}

}
