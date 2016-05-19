import java.awt.EventQueue;
import javax.swing.JFrame;

public class Calculator {
	/**
	 * The main method that runs at program launch.
	 * 
	 * @param args
	 *            A <code>String</code> array of values that are passed into the
	 *            application. Unused.
	 */
	public static void main(String[] args) {
		// Create a splash screen and display it for 5 seconds
		new CalculatorSplashScreen(5).showSplashWindow();

		// Register a new anonymous type that implements the Runnable interface
		// to be placed in the dispatch thread. This type's run() method will be
		// called after all pending events in the queue are handled.
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Create an instance of a JFrame object with the title
				// "Calculator"
				JFrame frame = new JFrame("Calculator");
				// Set the frame's size
				frame.setSize(270, 400);
				// Make the frame non-resizable
				frame.setResizable(false);
				// Create an instance of the CalculatorView and add it to the
				// frame
				frame.add(new CalculatorView());
				// Make the program exit when the close button is pressed
				// on the frame
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// Set the frame's location to the center of the screen
				frame.setLocationRelativeTo(null);
				// Make the frame appear on the screen
				frame.setVisible(true);
			}
		});
	} // end of method main()
} // end of class Calculator
