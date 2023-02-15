import java.util.Random;

/**
 * 
 * @author Will Jordan & Gub This is where it creates a Runner monster type and
 *         dictates its behavior
 *
 */
public class Runner extends Monster {

	private static final double SPEED = 3;
	private boolean isShooter;

	public Runner(double x, double y, GraphicalComponent g, boolean s) {
		super(x, y, g);
		isShooter = s;
	}

	@Override
	public void moveType() {
		// TODO Auto-generated method stub

		if (isShooter) {
			Random rand = new Random();
			int n = rand.nextInt(10000);
			if (n <= 10) {
				launchProjectile();
			}
		}
		if (this.doesIntersectWithX(this) && (!this.isLeft(this, this.getSpec()))) {
			updateXDir(-1);
			updateX(10);
		}
		if (this.doesIntersectWithX(this) && (!this.isRight(this, this.getSpec()))) {
			updateXDir(1);
			updateX(10);
		}
		updateX(SPEED);
	}

}
