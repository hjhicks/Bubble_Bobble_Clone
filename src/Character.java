import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * Abstract class that holds the methods that both Player and Monster use.
 * 
 * @author Gub and Will
 *
 */

public abstract class Character extends Touchable {

	protected double x;
	protected double y;
	private double initialX;
	private double initialY;
	private boolean markedToRemove;

	private static final int X_VELOCITY = 20, FALL_VELOCITY = 10;
	private int xDirection = 1;
	private int yDirection = 1;
	private boolean canJump = true;
	private GraphicalComponent g;
	private int jumpH = 200;

	public Character(double x, double y, GraphicalComponent g) {
		super(g);
		this.initialX = x;
		this.initialY = y;
		this.g = g;
		this.x = x;
		this.y = y;

	}

	public abstract void drawOn(Graphics2D g);

	public void resetPos() {

		this.x = this.initialX;
		this.y = this.initialY;
	}

	public void setJumpHeight(int h) {
		jumpH = h;
	}

	public void updateXDir(int xD) {
		xDirection = xD;
	}

	public void updateX(double d) {
		this.x += d * xDirection;
	}

	public void updateYDir(int yD) {
		yDirection = yD;
	}

	public void updateY(double d) {
		this.y = y + d * yDirection;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getXDir() {
		return xDirection;
	}

	public double getYDir() {
		return yDirection;
	}

	public void setMarkedToRemove(boolean m) {
		markedToRemove = m;
	}

	public boolean getMarkedToRemove() {
		return markedToRemove;
	}

	public abstract Rectangle getBoundsL();

	public abstract Rectangle getBounds();

	public void jump() {

		if (this.canJump) {

			double jumpStartY = this.getY();
			this.setCanJump(false);
			for (int i = 0; i < jumpH; i++) {
				updateYDir(-1);
				updateY(1);
				g.repaint();
			}
			g.repaint();
			/*
			 * while (jumpStartY - this.getY() <= 200) { this.y -= 5; g.repaint(); }
			 * g.repaint();
			 */

		}

	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	private int gravity = 5;

	private boolean isJumping = false;

	public abstract double getWidth();

	public abstract double getHeight();

	public boolean getIsJumping() {
		return this.isJumping;
	}

	public void setIsJumping(boolean jump) {
		this.isJumping = jump;

	}

	public void move() {
		/*
		 * if (this.isTouchingBottomOf(this) &&
		 * this.doesIntersectWithX(this)&&!this.isOnTopOf(this)) { updateYDir(1);
		 * updateY(1); canJump = false; //gravity(); }
		 */

		if (this.isOnTopOf(this)) {
			this.setCanJump(true);
		}

		if (!this.isOnTopOf(this)) {
			this.gravity = 5;
			this.gravity();
//			this.setCanJump(false);

		} else if (this.getIsJumping() && !this.isOnTopOf(this)) {
//			this.setCanJump(false);
			this.gravity();
		} else {
			this.gravity = 0;
			this.updateY(0);

//			this.setCanJump(true);
		}

	}

	public void gravity() {
		// this.setIsJumping(true);
		this.updateYDir(-1);
		this.y += gravity;

	}

	public GraphicalComponent getComponent() {
		return this.g;
	}

	public abstract void launchProjectile();

	public abstract boolean getIsPlayer();

	public abstract String getType();

	public void outOfBounds() {
		if (this.x > 1010 || this.x < -10 || this.y > 1010) {
			this.resetPos();
			System.out.println("Cheaters never win");
		}
	}

}
