import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.Component;

/**
 * 
 * @author Will Jordan & Gub
 * 
 *         This is the timer where everything is updated for the game and it
 *         checks things that need to be updated
 * 
 *
 */

public class TimerListener implements ActionListener {
	private GraphicalComponent gameComponent;
	private Player player;
	private ArrayList<Character> characters;
	private ArrayList<Projectile> projectiles;

	private Bubble b;
	private ArrayList<Fruit> fruits;
	private HashMap<String, Level> levels;
	private Level current;
	private MainMenu menu;

	public TimerListener(GraphicalComponent levelComponent, HashMap<String, Level> l, MainMenu mainMenu) {
		this.gameComponent = levelComponent;
		this.player = (Player) levelComponent.getPlayer();
		this.characters = levelComponent.getCharacters();
		this.projectiles = levelComponent.getProjectiles();
		this.player = (Player) levelComponent.getPlayer();
		this.fruits = levelComponent.getFruits();
		this.levels = l;
		this.current = levelComponent.getCurrentLevel();
		this.menu = mainMenu;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (gameComponent.getEnd()) {
			menu.disposeLoss(this.current);

		}

		else {

			advanceOneTick();
		}
	}

	private void advanceOneTick() {

		b = gameComponent.getBubble();
		this.gameComponent.repaint();

		for (Character character : characters) {

			if (character.getType().equals("player")) {
				player = (Player) character;
				player.handleKeyPressedForce2((player).getKeyCode());
				character.move();

			}
			if (character.getType().equals("monster")) {
				if (player == null) {
					character.move();
				} else {

					((Monster) character).movement(player);
				}
			}

			character.outOfBounds();

		}

		for (Projectile p : projectiles) {
			p.move(characters);
		}

		gameComponent.updateLabel();
		current = gameComponent.getCurrentLevel();
		if (gameComponent.detectChangeLevel()) {
			char num = current.getName().charAt(current.getName().length() - 1);
			if (num == '3') {

			} else if (num == '2') {

				levels.get("Level3").setUp();
			} else if (num == '1') {
				levels.get("Level2").setUp();
			} else if (num == '4') {
				menu.disposeWin();
			}
		}

		gameComponent.updateLives();

	}
}
