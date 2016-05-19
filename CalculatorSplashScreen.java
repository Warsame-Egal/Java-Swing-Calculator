/*
 * File name:	CalculatorSplashScreen.java
 * Author:		Derek McKinnon 040-674-434
 * Course:		CST8221 - JAP
 * Lab Section:	401
 * Assignment:	1, Part 2
 * Date:		2013-10-28
 * Professor:	Svillen Ranev
 * Purpose:		To provide a splash screen for the Calculator application
 */
package mcki0131.cst8221.assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates a JFrame and JPanel containing an image and the author's
 * student information and displays it for an amount of seconds specified in the
 * constructor.
 * 
 * @author Derek McKinnon
 * @version 1.0
 * @see javax.swing.JFrame
 * @see javax.swing.JPanel
 * @since 1.7
 */
public class CalculatorSplashScreen {
	/**
	 * The duration in seconds that the splash screen will be visible.
	 */
	private int durationInSeconds;

	/**
	 * The default constructor that accepts the user specified duration in
	 * seconds.
	 * 
	 * @param durationInSeconds
	 *            The time in seconds that the splash screen will be visible.
	 */
	public CalculatorSplashScreen(int durationInSeconds) {
		// Copies the duration specified by the parameter into this instance's
		// duration.
		this.durationInSeconds = durationInSeconds;
	} // end of default constructor

	/**
	 * Initializes the frame and related components and displays the splash
	 * screen for the duration specified when the class was created.
	 * 
	 * Calculator image taken from: <a href=
	 * "http://i.walmartimages.com/i/p/00/03/33/17/19/0003331719206_500X500.jpg"
	 * >Walmart</a>
	 */
	public void showSplashWindow() {
		// Create an instance of a JFrame object
		JFrame frame = new JFrame();
		// Remove the border, buttons, and title bar
		frame.setUndecorated(true);
		// Set the size of the frame
		frame.setSize(400, 520);
		// Make the frame appear in the center of the screen
		frame.setLocationRelativeTo(null);
		// Create an instance of a JPanel object that will hold the image and
		// author info
		JPanel panel = new JPanel(new BorderLayout());
		// Add a border to the panel so that it is more easily visible
		panel.setBorder(BorderFactory
				.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		// Create the image icon label
		JLabel imageLabel = new JLabel(new ImageIcon("ti-84-calculator.jpg"));
		// Add the label to the panel
		panel.add(imageLabel, BorderLayout.CENTER);
		// Create a label with the author information
		JLabel studentInfo = new JLabel("Derek McKinnon 040-674-434",
				JLabel.CENTER);
		// Add the label to the panel
		panel.add(studentInfo, BorderLayout.SOUTH);
		// Add the panel to the frame
		frame.add(panel);
		// Make the frame visible
		frame.setVisible(true);
		try {
			// Pause the program for the specified seconds
			TimeUnit.SECONDS.sleep(durationInSeconds);
		} catch (InterruptedException ex) {
			ex.printStackTrace(); // Print the error to the console
		} finally {
			// Hide the frame
			frame.setVisible(false);
			// Release all resources used by the frame
			frame.dispose();
		}
	} // end of method showSplashWindow()
} // end of class CalculatorSplashScreen
