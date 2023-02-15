
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the component that is used to display the game.
 * 
 * @author Gub and Will
 *
 */

@SuppressWarnings("serial")
public class GraphicalComponent extends JComponent {

	protected ArrayList<Platform> platforms = new ArrayList<Platform>();
	protected ArrayList<Character> characters = new ArrayList<Character>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	protected ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	private boolean reset = false;
	private Bubble bubble;
	private Player player;
	private JFrame f;
	private JLabel s;
	private JLabel lives;
	private JLabel pUp;
	private int pLives = 3;
	private int score = 0;
	private Level currentLvl;
	private boolean getEnd = false;
	private boolean win = false;
	private boolean pUpReady = true;

	public GraphicalComponent(JFrame f, JLabel s, JLabel lives, JLabel pUp) {
		this.f = f;
		this.s = s;
		this.lives = lives;
		this.pUp = pUp;

	}

	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D graphics2 = (Graphics2D) graphics;

		graphics2.setColor(Color.WHITE);
		graphics2.draw(new Rectangle2D.Double(0, 0, 1000, 1045));

		for (Platform p : platforms) {
			p.drawOn(graphics2);
		}
		ArrayList<Character> mToR = new ArrayList<Character>();

		for (Character c : characters) {
			if (c.getType().contentEquals("monster")) {
				if (((Monster) c).getIsMarked()) {
					mToR.add(c);
					this.score += 100;
					fruits.add(new Fruit(c.getX(), c.getY()));
				}
			}
			c.drawOn(graphics2);
		}
		for (Character m : mToR) {
			characters.remove(m);
		}
		mToR.removeAll(mToR);

		ArrayList<Projectile> pToR = new ArrayList<Projectile>();
		for (Projectile p : projectiles) {

			if (!projectiles.isEmpty()) {
				if (p.getX() > 1000 || p.getX() < 0 || p.getMarkedToRemove() || p.getY() > 1000 || p.getY() < 0) {
					pToR.add(p);
				}

				p.drawOn(graphics2);

			}
		}

		for (Projectile p : pToR) {
			projectiles.remove(p);
		}
		pToR.removeAll(pToR);

		if (bubble != null) {
			bubble.drawOn(graphics2);
		}
		ArrayList<Fruit> fToR = new ArrayList<Fruit>();
		for (Fruit f : fruits) {
			if (f.getMark()) {
				this.score += 50;
				fToR.add(f);
			}
			f.drawOn(graphics2);
		}
		for (Fruit f : fToR) {
			fruits.remove(f);
		}
		fToR.removeAll(fToR);

	}

	public void addPlatform(double x, double y, boolean isBorder) {
		platforms.add(new Platform(x, y, isBorder));

	}

	public void setBubble(Bubble b) {
		this.bubble = b;
	}

	public Bubble getBubble() {
		return this.bubble;
	}

	public void addPlayer(double x, double y) {
		player = new Player(x + 5, y + 20, this);
		characters.add(player);

		this.setFocusable(true);

		this.f.addKeyListener(new PlayerKeyListener(player, f));

	}

	/*
	 * public void addMonster(double x, double y) { characters.add(new Monster(x, y,
	 * this)); }
	 */
	public void addRunner(double x, double y, boolean s) {
		characters.add(new Runner(x, y, this, s));
	}

	public void addStationaryShooter(double x, double y, boolean s) {
		characters.add(new StationaryShooter(x, y, this, s));
	}

	public void addProjectile(Projectile p) {
		projectiles.add(p);

	}

	public void setFocusable(boolean b) {
		super.setFocusable(b);
	}

	public void reset() {
		platforms.removeAll(platforms);
		characters.removeAll(characters);
		projectiles.removeAll(projectiles);
		fruits.removeAll(fruits);
		bubble = null;
		player = null;
		pUp.setText("PowerUp available");
	}

	public Level getCurrentLevel() {
		return this.currentLvl;
	}

	public void setCurrentLevel(Level l) {
		currentLvl = l;
	}

	public boolean detectChangeLevel() {
		return characters.size() == 1;
	}

	public void updateLabel() {

		s.setText("Score: " + score);
	}

	public void updateLives() {
		if (player != null) {

			// player.setLives(pLives);
			lives.setText("Lives: " + player.getLives());

			if (player.getLives() <= 0) {
				getEnd = true;

			}
		}
	}

	public void UpdatePUp() {
		pUp.setText("PowerUp unavailable");

	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}

	public Character getPlayer() {
		return this.player;
	}

	public JFrame getFrame() {
		return f;

	}

	public ArrayList<Fruit> getFruits() {
		// TODO Auto-generated method stub
		return this.fruits;
	}

	public boolean getEnd() {

		// TODO Auto-generated method stub
		return getEnd;
	}

	public double getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	public void setEnd(boolean b) {
		// TODO Auto-generated method stub
		this.getEnd = b;
	}

	public boolean getWin() {
		return this.win;
	}

	public void setWin(boolean b) {
		this.win = b;
	}

}
