import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Will Jordan & Gub Creates a player and determines movement and
 *         actions of player
 *
 */

public class Player extends Character {
	private double gravity = 5;
	protected ArrayList<Platform> platforms = new ArrayList<Platform>();
	protected ArrayList<Character> characters = new ArrayList<Character>();
	private int keyCode;
	private boolean canFire = true;
	private static final double WIDTH = 40;
	protected static final double HEIGHT = 40;
	private ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	private StopWatch bubbleWatch = new StopWatch();
	private int lives = 3;
	private GraphicalComponent g;
	private boolean pUp = true;
	private double speed = 5;

	public Player(double x, double y, GraphicalComponent g) {
		super(x, y, g);
		platforms = g.getPlatforms();
		characters = g.getCharacters();
		fruits = g.getFruits();
		this.g = g;
	}

	public void handleKeyPressedForce2(int key) {

		if (!fruits.isEmpty()) {
			for (Fruit f : fruits) {
				if (f.getBounds().intersects(getBounds())) {
					f.setMark(true);
				}
			}
		}

		if (key == KeyEvent.VK_RIGHT) {
			double a = 0;
			a += speed;
			if (this.doesIntersectWithX(this) && (!this.isLeft(this, this.getSpec())))
				a -= 15;
			this.updateXDir(1);
			this.updateX(a);

		} else if (key == KeyEvent.VK_LEFT) {
			double a = 0;
			a += speed;
			if (this.doesIntersectWithX(this) && (!this.isRight(this, this.getSpec())))
				a -= 15;

			// this.addXVelocity(a);
			this.updateXDir(-1);
			this.updateX(a);

		} else if (key == KeyEvent.VK_DOWN) {

		} else if (key == KeyEvent.VK_SPACE && this.canFire) {
			this.launchProjectile();
		} else if (key == KeyEvent.VK_B) {
			activatePUp();
		}
	}

	private void activatePUp() {
		if (pUp) {
			pUp = false;
			g.UpdatePUp();
			Random rand = new Random();
			int n = rand.nextInt(6);

			switch (n) {

			case (0):
				lives = 1;
				System.out.println("0");
				break;
			case (1):
				lives = 20;
				System.out.println("1");
				break;
			case (2):
				setJumpHeight(300);
				System.out.println("2");
				break;
			case (3):
				speed = 10;
				System.out.println("3");
				break;
			case (4):
				speed = 2.5;
				System.out.println("4");
				break;
			case (5):
				lives = 0;
				System.out.println("5");
				break;
//			case (6):
//				
//				System.out.println("6");
//				break;
//			case (7):
//				System.out.println("7");
//				break;
//			case (8):
//				System.out.println("8");
//				break;
//			case (9):
//				System.out.println("9");
//				break;
			}

		}
	}

	public void handleKeyReleased(int key) {
		// TODO Auto-generated method stub
		if (key == KeyEvent.VK_UP) {
			this.jump();
		}
	}

	@Override
	public void drawOn(Graphics2D g) {
		// TODO Auto-generated method stub
		{

			g.setColor(Color.BLACK);

			Rectangle2D p = new Rectangle2D.Double(this.x, this.y, WIDTH, HEIGHT);
			g.draw(p);

			g.setColor(Color.RED);

			g.fill(p);

		}

	}

	@Override
	public Rectangle getBoundsL() {
		return new Rectangle((int) this.x - 5, (int) this.y - 5, (int) WIDTH + 5, (int) HEIGHT + 5);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) this.x - 5, (int) this.y - 5, (int) WIDTH, (int) HEIGHT);

	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public void launchProjectile() {

		if (!this.bubbleWatch.getIsRunning()) {
			this.getComponent().addProjectile(new Bubble(getX(), getY(), this.getXDir()));
		}
		this.bubbleWatch.start();
		if (this.bubbleWatch.getElapsedTime() <= 350) {

		} else {
			this.bubbleWatch.reset();
		}

	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

	@Override
	public boolean getIsPlayer() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setKeyCode(int i) {
		this.keyCode = i;

	}

	public int getKeyCode() {
		return this.keyCode;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "player";
	}

	public void updateLives(int i) {
		lives += i;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int l) {
		lives = l;
	}

}
