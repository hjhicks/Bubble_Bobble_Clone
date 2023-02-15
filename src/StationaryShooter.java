import java.util.Random;

/**
 * 
 * @author Will Jordan & Gub This is where it creates a stationary shooter
 *         monster type and dictates its behavior
 *
 */

public class StationaryShooter extends Monster {

	private boolean doesFloat;

	public StationaryShooter(double x, double y, GraphicalComponent g, boolean f) {
		super(x, y, g);
		doesFloat = f;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveType() {
		// TODO Auto-generated method stub

		if (doesFloat) {
			setFall(false);
		}
		Random rand = new Random();
		int n = rand.nextInt(2);
		if (n == 0) {
			this.updateXDir(1);

		} else {
			this.updateXDir(-1);

		}

		Random r = new Random();
		int c = rand.nextInt(1000);
		if (c <= 10) {
			launchProjectile();
		}

	}

}
