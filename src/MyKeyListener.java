import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;

/**
 * Key listener specifically used for changing levels.
 * 
 * @author Gub and Will
 *
 */

public class MyKeyListener implements KeyListener {

	private HashMap<String, Level> levels;
	private JFrame f;
	private MainMenu menu;
	private Level current;

	public MyKeyListener(HashMap<String, Level> l, JFrame f, MainMenu menu) {
		this.levels = l;
		this.menu = menu;
		this.f = f;
		this.f.setFocusable(true);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int k = e.getKeyCode();

		if (k == KeyEvent.VK_U) {
			// System.out.println("U");
			char num = f.getTitle().charAt(f.getTitle().length() - 1);
			if (num == '3') {
				menu.disposeWin();
			} else if (num == '2') {

				levels.get("Level3").setUp();
			} else if (num == '1') {
				levels.get("Level2").setUp();
			}
		}
		if (k == KeyEvent.VK_D) {
			// System.out.println("D");
			char num = f.getTitle().charAt(f.getTitle().length() - 1);
			if (num == '3') {
				levels.get("Level2").setUp();

			} else if (num == '2') {
				levels.get("Level1").setUp();
			} else if (num == '1') {

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
