import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * Defines the Abstract methods used by all items.
 * 
 * @author Gub and Will
 *
 */

public interface Item {

	public void drawOn(Graphics2D g);

	// public abstract void collidesWith(Character c);

	public Rectangle getBounds();

}
