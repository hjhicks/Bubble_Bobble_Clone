/**
 * A simple timer class based on section 14 of BigJava, 2nd ed., by Cay
 * Horstmann.
 *
 * @author Curt Clifton
 *
 *         Adapted from previous in class project to suit our needs.
 */
public class StopWatch {
	// Instance Variables
	private long startTime, elapsedTime;
	private boolean isRunning;

	// Constructor
	/**
	 * Constructs a new stop watch that is reset.
	 */
	public StopWatch() {
		reset();
	}

	// Methods
	/**
	 * Starts this stop watch, but does not reset the elapsed time accumulated.
	 */
	public void start() {
		if (this.isRunning)
			return;
		this.isRunning = true;
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * @return the total elapsed time accumulated by this stop watch.
	 */
	public long getElapsedTime() {
		if (this.isRunning) {
			long endTime = System.currentTimeMillis();
			return this.elapsedTime + (endTime - this.startTime);
		}
		return this.elapsedTime;
	}

	/**
	 * Steps this stop watch and resets the accumulated time to zero.
	 */
	public void reset() {
		this.elapsedTime = 0;
		this.isRunning = false;
	}

	// Getters and Setters
	public boolean getIsRunning() {
		return this.isRunning;
	}
}
