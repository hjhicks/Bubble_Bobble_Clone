import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Dictates how level is set up and runs.
 * 
 * @author Gub and Will
 *
 */

public class Level {

	private String name;
	private GraphicalComponent component;
	private JFrame frame;
	private StopWatch stopWatch = new StopWatch();
	private static final int DELAY = 5;
	private static final int OFFSET = 50;

	public Level(String name, GraphicalComponent g, JFrame f) {
		this.name = name;
		this.component = g;

		frame = f;

	}

	public String getName() {
		return name;
	}

	public void setUp() {

		component.reset();

		frame.setTitle(name);
		frame.setSize(1000, 1045);

		String filePath = "leveldata" + '/' + this.name + ".txt";

		Scanner scanner;

		try {
			scanner = new Scanner(new File(filePath));
			int lineCount = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == 'X') {
						component.addPlatform(i * OFFSET, lineCount * OFFSET, true);

					}
					if (line.charAt(i) == 'x') {
						component.addPlatform(i * OFFSET, lineCount * OFFSET, false);

					}

					if (line.charAt(i) == 'P') {
						component.addPlayer(i * OFFSET, lineCount * OFFSET);
					}
					if (line.charAt(i) == 'S') {
						component.addStationaryShooter(i * OFFSET, lineCount * OFFSET, false);
					}
					if (line.charAt(i) == 's') {
						component.addStationaryShooter(i * OFFSET, lineCount * OFFSET, true);
					}
					if (line.charAt(i) == 'm') {
						component.addRunner(i * OFFSET, lineCount * OFFSET, true);
					}
					if (line.charAt(i) == 'M') {
						component.addRunner(i * OFFSET, lineCount * OFFSET, false);
					}
				}
				lineCount++;
			}
			scanner.close();
			component.setCurrentLevel(this);
		} catch (FileNotFoundException e) {
			System.out.println(filePath + " not found");
			e.printStackTrace();

		}

		frame.add(component);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public JFrame getFrame() {
		return frame;
	}

}
