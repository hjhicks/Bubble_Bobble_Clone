import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and methods.
 * 
 * @author <Gub, Will Jordan>
 *
 */
public class MainMenu {

	/**
	 * @param args
	 */
	private HashMap<String, Level> levels = new HashMap<String, Level>();
	private Timer timer;
	private JFrame menu;

	public static void main(String[] args) {
		new MainMenu();

	}

	public MainMenu() {

		this.menu = new JFrame("Menu");
		menu.setSize(500, 500);

		// System.out.println(menu.getFocusableWindowState());
		JPanel options = new JPanel();
		JLabel score = new JLabel("Score: ");
		JLabel space = new JLabel("                                                                  ");
		JLabel lives = new JLabel("Lives: 3");
		JLabel space2 = new JLabel("                                                                  ");
		JLabel pUp = new JLabel("PowerUp Available");
		JPanel tracking = new JPanel();

		tracking.add(score);
		tracking.add(space);
		tracking.add(lives);
		tracking.add(space2);
		tracking.add(pUp);

		setUpLevels(menu, score, lives, pUp);

		JButton play = new JButton("Play Game");

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handlePlayGame();
				menu.add(tracking, BorderLayout.NORTH);
				menu.remove(options);

			}

		});

		detectLevelChange(menu);

		JButton levelSelect = new JButton("Select Level");

		JButton help = new JButton("Help");

		options.add(play);
		options.add(levelSelect);
		options.add(help);

		menu.add(options, BorderLayout.SOUTH);

		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);

	}

	public void detectLevelChange(JFrame f) {

		f.setFocusable(true);

		// System.out.println("in method");
		// System.out.println(KeyEvent.VK_U);
		MyKeyListener listen = new MyKeyListener(levels, f, this);
		f.addKeyListener(listen);
	}

	public void setUpLevels(JFrame f, JLabel s, JLabel l, JLabel p) {
		GraphicalComponent g = new GraphicalComponent(f, s, l, p);
		TimerListener listener = new TimerListener(g, levels, this);
		Timer timer = new Timer(5, listener);
		this.timer = timer;
		timer.start();
		levels.put("Level1", new Level("Level1", g, f));
		levels.put("Level2", new Level("Level2", g, f));
		levels.put("Level3", new Level("Level3", g, f));

	}

	protected void handlePlayGame() {
		levels.get("Level1").setUp();

	}

	public static void loseScreen() throws IOException {

		JFrame f = new JFrame("YOU LOSE!");
		String path = "Images/losescreen.png";
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		JLabel label = new JLabel(new ImageIcon(image));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(label);
		f.pack();
		f.setVisible(true);
	}

	public void disposeLoss(Level current) {
		timer.stop();
		current.getFrame().dispose();
		try {
			MainMenu.loseScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disposeWin() {
		timer.stop();
		menu.dispose();
		try {
			MainMenu.WinScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void WinScreen() throws IOException {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("YOU WIN!");
		String path = "Images/winscreen.jpg";
		File file = new File(path);
		BufferedImage image;
		image = ImageIO.read(file);
		JLabel label = new JLabel(new ImageIcon(image));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(label);
		f.pack();
		f.setVisible(true);

	}

}
