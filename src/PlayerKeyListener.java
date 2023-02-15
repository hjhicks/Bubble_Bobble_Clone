import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * 
 * @author Will Jordan & Gub
 * 
 *         checks for updates to player movement and actions
 *
 */

public class PlayerKeyListener implements KeyListener {

	private Player p;
	private JFrame f;

	public PlayerKeyListener(Player p, JFrame f) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.f = f;
		this.f.setFocusable(true);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// this.p.handleKeyPressedForce2(e.getKeyCode());
		p.setKeyCode(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		p.handleKeyReleased(e.getKeyCode());
		p.setKeyCode(-1);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
