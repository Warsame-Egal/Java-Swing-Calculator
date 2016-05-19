/*
 * File name:	CalculatorView.java
 * Author:		Derek McKinnon 040-674-434
 * Course:		CST8221 - JAP
 * Lab Section:	401
 * Assignment:	1, Part 2
 * Date:		2013-10-28
 * Professor:	Svillen Ranev
 * Purpose:		To contain and manage the main GUI for the Calculator
 * Class list:	CalculatorView, Controller
 */
package mcki0131.cst8221.assignment1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mcki0131.cst8221.assignment1.CalculatorModel.OperationMode;
import mcki0131.cst8221.assignment1.CalculatorModel.Operator;
import mcki0131.cst8221.assignment1.CalculatorModel.Precision;

/**
 * This class contains and manages all the components required by the Calculator
 * applications' graphical user interface.
 * 
 * @author Derek McKinnon
 * @version 2.0
 * @see javax.swing.JPanel
 * @since 1.7
 */
// This class is not meant to be serialized, so suppress warnings about the
// missing serialversionUID
@SuppressWarnings("serial")
public class CalculatorView extends JPanel {
	private static final String UNICODE_LEFT_ARROW = "\u2190";
	private static final String UNICODE_PLUS_MINUS = "\u00B1";
	/**
	 * Contains all the values that are displayed in the numeric keypad. "
	 * <code>\u00B1</code>" is the Unicode plus or minus character.
	 */
	private static final String[] NUM_PAD_LABELS = new String[] { "7", "8",
			"9", "/", "4", "5", "6", "*", "1", "2", "3", "-",
			UNICODE_PLUS_MINUS, "0", ".", "+" };
	/**
	 * The calculator display field reference
	 */
	private JTextField display;
	/**
	 * The error display label reference
	 */
	private JLabel error;
	/**
	 * The decimal point (dot) button reference
	 */
	private JButton dotButton;
	/**
	 * The reference to a Controller object that manages the view and model.
	 */
	private Controller controller;

	/**
	 * The default constructor that initializes all the related fields and
	 * properties for this panel
	 */
	public CalculatorView() {
		// Instantiate the controller
		controller = new Controller();

		// Set this panel's layout a new BorderLayout
		this.setLayout(new BorderLayout());
		// Sets this panel's border
		this.setBorder(BorderFactory.createMatteBorder(2, 4, 4, 4, Color.BLACK));

		/* Display and Upper Functions Panel */

		// Create a panel to hold the display and upper functions panels. Uses
		// BorderLayout to display its components
		JPanel displayAndUpperFuncPanel = new JPanel(new BorderLayout());

		// Create a sub-panel that will house the error label, display, and
		// backspace button. Uses the default FlowLayout to display its
		// components
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(Color.BLACK);

		// Instantiate the error label field, setting it to display F
		error = new JLabel("F");
		error.setPreferredSize(new Dimension(25, 25));
		error.setBackground(Color.YELLOW);
		// Set the label to be opaque so that the background actually shows
		error.setOpaque(true);
		// Set the label alignment to center the text
		error.setHorizontalAlignment(JLabel.CENTER);
		displayPanel.add(error);

		// Instantiate the display field with the default display text of 0.0
		// and 16 columns
		display = new JTextField("0.0", 16);
		// Set the text field's height to 30
		display.setPreferredSize(new Dimension(0, 30));
		// Prevent the text field from being editable
		display.setEditable(false);
		// Make the text field's text right justified
		display.setHorizontalAlignment(JTextField.RIGHT);
		displayPanel.add(display);

		// Create the backspace button
		JButton backspaceButton = new JButton(UNICODE_LEFT_ARROW);
		// Make the button transparent
		backspaceButton.setContentAreaFilled(false);
		// Set the button's border
		backspaceButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		// Set the button's foreground color to red so that the arrow is red
		backspaceButton.setForeground(Color.RED);
		// Set the size of the button to 25x25 units
		backspaceButton.setPreferredSize(new Dimension(25, 25));
		// Set the keyboard mnemonic action to be Alt+B keys
		backspaceButton.setMnemonic(KeyEvent.VK_B);
		// Set the button's tool tip text
		backspaceButton.setToolTipText("Backspace (Alt-B)");
		backspaceButton.setActionCommand(UNICODE_LEFT_ARROW);
		backspaceButton.addActionListener(controller);
		displayPanel.add(backspaceButton);

		// Add the display sub-panel to its parent panel
		displayAndUpperFuncPanel.add(displayPanel, BorderLayout.NORTH);

		// Create a panel to hold the upper controls (Int, .0, .00, Sci). Uses
		// the default FlowLayout manager to display its components
		JPanel upperFunctionPanel = new JPanel();
		upperFunctionPanel.setBackground(Color.WHITE);
		// Create a ButtonGroup object that will manage the radio buttons and
		// ensure only one can be selected at a time
		ButtonGroup radioButtons = new ButtonGroup();

		// Create a title for the mode
		String modeTitle = "Int";
		// Create the mode check box
		JCheckBox mode = new JCheckBox(modeTitle);
		mode.setBackground(Color.GREEN);
		mode.setActionCommand(modeTitle);
		mode.addActionListener(controller);
		upperFunctionPanel.add(mode);

		// Create a title for the single precision radio button
		String singlePrecisionTitle = ".0";
		// Create the single precision radio button
		JRadioButton singlePrecision = new JRadioButton(singlePrecisionTitle);
		singlePrecision.setBackground(Color.YELLOW);
		singlePrecision.setActionCommand(singlePrecisionTitle);
		singlePrecision.addActionListener(controller);
		// Add the radio button to the button group
		radioButtons.add(singlePrecision);
		upperFunctionPanel.add(singlePrecision);

		// Create a title for the double precision radio button
		String doublePrecisionTitle = ".00";
		// Create the double precision radio button
		JRadioButton doublePrecision = new JRadioButton(doublePrecisionTitle);
		doublePrecision.setBackground(Color.YELLOW);
		doublePrecision.setActionCommand(doublePrecisionTitle);
		// Make this the selected radio button
		doublePrecision.setSelected(true);
		doublePrecision.addActionListener(controller);
		radioButtons.add(doublePrecision);
		upperFunctionPanel.add(doublePrecision);

		// Create a title for the sci radio button
		String sciTitle = "Sci";
		// Create the sci radio button
		JRadioButton sciBtn = new JRadioButton(sciTitle);
		sciBtn.setBackground(Color.YELLOW);
		sciBtn.setActionCommand(sciTitle);
		sciBtn.addActionListener(controller);
		radioButtons.add(sciBtn);
		upperFunctionPanel.add(sciBtn);

		// Add the function sub-panel to its parent panel
		displayAndUpperFuncPanel.add(upperFunctionPanel, BorderLayout.SOUTH);

		// Add the display and upper function panel to the CalculatorView
		this.add(displayAndUpperFuncPanel, BorderLayout.NORTH);

		/* Number Pad and Lower Function Panel */

		// Create a panel to hold the number pad panel and lower function panel
		JPanel numberPadAndLowerFunctionPanel = new JPanel(new BorderLayout());

		// Create a panel to hold the number pad with a GridLayout manager to
		// manage the components
		JPanel numberpadPanel = new JPanel(new GridLayout(4, 4, 5, 5));
		// Give the panel an empty border to add padding
		numberpadPanel.setBorder(BorderFactory.createEmptyBorder(5, 2, 0, 2));
		// Compile a regular expression pattern to use for seeing if the label
		// is a digit, a period, or the Unicode plus or minus character
		Pattern regex = Pattern.compile("[\\d.\\u00B1]");
		// For each of the number pad labels, create a button with the label and
		// action command, and add it to the number pad panel
		for (String label : NUM_PAD_LABELS) {
			// If the label is a digit, a period, or the Unicode plus or minus
			// character, make the foreground black, otherwise make it yellow
			Color foregroundColor = regex.matcher(label).matches() ? Color.BLACK
					: Color.YELLOW;
			// Create the button by calling the private method and providing the
			// appropriate parameters
			JButton button = createButton(label, label, foregroundColor,
					Color.BLUE, controller);
			// If the label is the period make the dotButton field point to the
			// newly created button
			if (label.equals("."))
				dotButton = button;

			numberpadPanel.add(button);
		}
		// Add the number pad panel to its parent panel, placing it in the
		// center
		numberPadAndLowerFunctionPanel.add(numberpadPanel, BorderLayout.CENTER);

		// Create a panel to hold the lower function buttons with a GridLayout
		JPanel lowerFunctionPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// Give the panel an empty border to add padding
		lowerFunctionPanel.setBorder(BorderFactory
				.createEmptyBorder(3, 2, 5, 2));

		// Create a title for the clear button
		String clearTitle = "C";
		// Create the clear button
		JButton clearButton = createButton(clearTitle, clearTitle, null,
				Color.RED, controller);
		lowerFunctionPanel.add(clearButton);

		// Create a title for the equals button
		String equalsTitle = "=";
		// Create the equals button
		JButton equalsButton = createButton(equalsTitle, equalsTitle, null,
				Color.YELLOW, controller);
		lowerFunctionPanel.add(equalsButton);

		// Add the lower function panel to its parent panel, placing it on the
		// bottom
		numberPadAndLowerFunctionPanel.add(lowerFunctionPanel,
				BorderLayout.SOUTH);

		// Add the number pad and lower function panel to the CalculatorView
		this.add(numberPadAndLowerFunctionPanel, BorderLayout.CENTER);
	} // end of default constructor

	/**
	 * This method creates an instance of a JButton using the provided label
	 * text, action command, foreground and background colors, and registers the
	 * provided ActionListener to the button. It returns a reference to the
	 * newly created button.
	 * 
	 * @param text
	 *            The label text that will be displayed to the user. This value
	 *            should not be <code>null</code> but no exception will be
	 *            thrown if it is.
	 * @param ac
	 *            The action command text for the button. This value can be
	 *            <code>null</code>.
	 * @param fg
	 *            The foreground color of the button. This value should not be
	 *            <code>null</code> but no exception will be thrown if it is.
	 * @param bg
	 *            The background color of the button. This value should not be
	 *            <code>null</code> but no exception will be thrown if it is.
	 * @param handler
	 *            An instance of an ActionListener that will be assigned to the
	 *            button. This value can be <code>null</code>.
	 * @return A reference to an instance of a JButton object
	 */
	private JButton createButton(String text, String ac, Color fg, Color bg,
			ActionListener handler) {
		// Instantiate a new JButton object using the specified label text
		JButton button = new JButton(text);

		// If the action command is not null, assign it to the button
		if (ac != null)
			button.setActionCommand(ac);

		// Set the foreground color of the button to the provided value
		button.setForeground(fg);
		// Set the background color of the button to the provided value
		button.setBackground(bg);

		// If the reference to the ActionListener is not null, assign it to the
		// button
		if (handler != null)
			button.addActionListener(handler);

		return button;
	} // end of method createButton()

	/**
	 * A private inner class that implements the <code>ActionListener</code>
	 * interface and handles the interaction of buttons and other controls of
	 * the calculator.
	 * 
	 * @author Derek McKinnon
	 * @version 2.0
	 * @see java.awt.event.ActionListener
	 * @since 1.7
	 */
	private class Controller implements ActionListener {
		/**
		 * A reference to a CalculatorModel object that will perform
		 * calculations and store a result.
		 */
		private CalculatorModel model;
		/**
		 * A flag used to indicate whether or not the first operand has been
		 * entered into the model.
		 */
		private boolean operand1Entered;
		/**
		 * A flag used to indicate whether or not the second operand has been
		 * entered into the model.
		 */
		private boolean operand2Entered;
		/**
		 * A flag used to indicate whether or not the text field is currently
		 * displaying the result of a calculation.
		 */
		private boolean resultDisplayed;
		/**
		 * A flag used to indicated whether or not the calculator has been
		 * cleared and is ready to accept the first operand.
		 */
		private boolean cleared;

		/**
		 * The default constructor for the Controller class that instantiates
		 * the model and prepares the object for use.
		 */
		public Controller() {
			model = new CalculatorModel();
			cleared = true;
		}

		/**
		 * This method is called when a registered control is acted upon and
		 * performs the corresponding action linked with the control.
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the command string associated with the control
			String actionCommand = e.getActionCommand();

			switch (actionCommand) {
			case "C":
				clear();
				return;
			case "Int":
				toggleOperationMode();
				return;
			case ".0":
				setPrecision(Precision.Single);
				return;
			case ".00":
				setPrecision(Precision.Double);
				return;
			case "Sci":
				setPrecision(Precision.Scientific);
				return;
			}

			// If the model is in error state, ignore any other button press
			if (model.getErrorState())
				return;

			switch (actionCommand) {
			case "=":
				performCalculations();
				return;
			case UNICODE_LEFT_ARROW:
				performBackspace();
				return;
			case UNICODE_PLUS_MINUS:
				toggleSign();
				return;
			case ".":
				appendDecimal();
				return;
			}

			// Attempt to parse the action command as an operator
			Operator operator = getOperator(actionCommand);
			// If the action command represents a valid operator, enter it
			if (operator != null) {
				enterOperator(operator);
				return;
			}

			// Attempt to parse the action command as a number key
			Integer numberKey = getNumberKey(actionCommand);
			// If the action command represents a valid integer, enter it
			if (numberKey != null) {
				enterNumberKey(numberKey);
				return;
			}

		}// end of method actionPerformed()

		/**
		 * Accepts a reference to a <code>String</code> object and attempts to
		 * parse it as an <code>Operator</code>. If the conversion fails, a
		 * <code>null</code> object is returned.
		 * 
		 * @param s
		 *            The <code>String</code> object that represents an
		 *            operator.
		 * @return The <code>Operator</code> represented by the
		 *         <code>String</code> or <code>null</code> if the
		 *         <code>String</code> is invalid.
		 */
		private Operator getOperator(String s) {
			switch (s) {
			case "+":
				return Operator.Addition;
			case "-":
				return Operator.Subtraction;
			case "*":
				return Operator.Multiplcation;
			case "/":
				return Operator.Division;
			default:
				return null;
			}
		}

		/**
		 * Facilitates the entry of an operator into the model and updates the
		 * display and calculator state correspondingly.
		 * 
		 * @param operator
		 *            A reference to an <code>Operator</code> to be entered into
		 *            the model.
		 */
		private void enterOperator(Operator operator) {
			// If the result is being displayed on the screen, make it the first
			// operand
			if (resultDisplayed) {
				model.setOperand1FromResult();
				resultDisplayed = false;
				operand1Entered = true;
				operand2Entered = false;
				cleared = false;
			}

			// If the screen is cleared, set the first operand equal to 0
			if (cleared) {
				model.setOperand1(0);
				operand1Entered = true;
				cleared = false;
			}

			// If the first operand has not been entered, enter it
			if (!operand1Entered) {
				double operand = Double.parseDouble(display.getText());
				model.setOperand1(operand);
				operand1Entered = true;
			}

			// If the second operand has been entered already, perform the
			// calculation, update the display, and, if no errors resulted from
			// the calculations, assign the result to the first operand
			if (operand2Entered) {
				model.performCalculations();
				display.setText(model.getResult());
				if (model.getErrorState())
					return;
				model.setOperand1FromResult();
				operand1Entered = true;
				operand2Entered = false;
			}

			// Enter the operator into the model
			model.setOperator(operator);
			// Clear the screen
			display.setText("");
		}

		/**
		 * Accepts a reference to a <code>String</code> object and attempts to
		 * parse it as an <code>Integer</code> object. If the conversion fails,
		 * a <code>null</code> object is returned.
		 * 
		 * @param s
		 *            The input string containing a number.
		 * @return A reference to an <code>Integer</code> object parsed from the
		 *         input string or <code>null</code> on failure.
		 */
		private Integer getNumberKey(String s) {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return null;
			}
		}

		/**
		 * Facilitates the entry of a number key into the calculator screen.
		 * 
		 * @param numberKey
		 *            The number to be entered into the screen.
		 */
		private void enterNumberKey(int numberKey) {
			// If a result is showing, clear it and replace the display text
			// with the number entered
			if (resultDisplayed)
				clear();

			// If the calculator has been cleared, replace the display text with
			// the number entered
			if (cleared) {
				display.setText(String.valueOf(numberKey));
				cleared = false;
				return;
			}

			// Append the number to the display
			display.setText(display.getText() + numberKey);
		}

		/**
		 * Switches the operation mode from Integer to Float and vice versa,
		 * updating the model and view as necessary
		 */
		private void toggleOperationMode() {
			// Get the display text from the display
			String displayText = display.getText();
			if (model.getOperationMode() == OperationMode.Integer) {
				model.setOperationMode(OperationMode.Float);
				dotButton.setEnabled(true);
				if (!model.getErrorState()) {
					dotButton.setBackground(Color.BLUE);
					error.setText("F");
					error.setBackground(Color.YELLOW);
					// Reformat the text in the display if applicable
					if (!displayText.isEmpty()) {
						double d = Double.valueOf(displayText);
						display.setText(String.valueOf(d));
					}
				}
			} else {
				model.setOperationMode(OperationMode.Integer);
				dotButton.setEnabled(false);
				if (!model.getErrorState()) {
					dotButton.setBackground(Color.GRAY);
					error.setText("I");
					error.setBackground(Color.GREEN);
					// Reformat the text in the display if applicable
					if (!displayText.isEmpty()) {
						double d = Double.valueOf(displayText);
						int i = (int) d;
						display.setText(String.valueOf(i));
					}
				}
			}
		}

		/**
		 * Facilitates setting the precision for calculations in the model.
		 * 
		 * @param precision
		 *            The Precision enumerated value to be set.
		 */
		private void setPrecision(Precision precision) {
			model.setPrecision(precision);
		}

		/**
		 * Clears the model and the view, resetting all fields and flags to
		 * their default configurations.
		 */
		private void clear() {
			model.clear();
			operand1Entered = operand2Entered = resultDisplayed = false;
			cleared = true;
			display.setText(model.getResult());
			if (model.getOperationMode() == OperationMode.Float) {
				dotButton.setEnabled(true);
				dotButton.setBackground(Color.BLUE);
				error.setText("F");
				error.setBackground(Color.YELLOW);
				model.setOperationMode(OperationMode.Float);
			} else {
				dotButton.setEnabled(false);
				dotButton.setBackground(Color.GRAY);
				error.setText("I");
				error.setBackground(Color.GREEN);
				model.setOperationMode(OperationMode.Integer);
			}
		}

		/**
		 * Queries the model to perform the calculations and updates the view.
		 */
		private void performCalculations() {
			// If the second operand has not been entered, set it
			if (!operand2Entered) {
				// Get the display text
				String displayText = display.getText();
				// Declare a variable to hold the second operand
				double operand2;
				// If the display is not empty, enter the second operand
				if (!displayText.isEmpty()) {
					// If the result is in scientific notation, we must add an
					// 'E' that was removed from the output originally
					if (displayText.contains("+")) {
						String[] parts = displayText.split("\\+");
						displayText = parts[0] + "E+" + parts[1];
					}
					operand2 = Double.valueOf(displayText);
				} else {
					// Otherwise copy the value of the first operand into it
					operand2 = model.getOperand1();
				}
				model.setOperand2(operand2);
			}
			model.performCalculations();
			display.setText(model.getResult());
			resultDisplayed = true;
			if (model.getErrorState()) {
				error.setText("E");
				error.setBackground(Color.RED);
			}
		}

		/**
		 * Provides a way to delete input from the calculator one character at a
		 * time.
		 */
		private void performBackspace() {
			// If the model is in error state or the result of a calculation is
			// currently displayed, ignore this command
			if (model.getErrorState() || resultDisplayed)
				return;

			// Get the current operand from the display
			String displayText = display.getText();
			// Keep everything but the last character using substring()
			if (!displayText.isEmpty())
				displayText = displayText
						.substring(0, displayText.length() - 1);

			// If only a negative sign remains, remove it too
			if (displayText.equals("-")) {
				displayText = (model.getOperationMode() == OperationMode.Integer) ? "0"
						: "0.0";
			}

			// Update the display
			display.setText(displayText);
		}

		/**
		 * Provides a way to enter a decimal point to the view.
		 */
		private void appendDecimal() {
			// Get the number from the display
			String displayText = display.getText();
			// If the display text is empty, append zero to the beginning of the
			// string
			if (displayText.isEmpty())
				displayText = "0";
			// If the display text does not contain a decimal, append one to the
			// end of the string
			if (!displayText.contains("."))
				displayText += ".";

			display.setText(displayText);
		}

		/**
		 * Provides a mechanism for changing the input's sign from positive to
		 * negative.
		 */
		private void toggleSign() {
			// Get the number from the display
			String number = display.getText();
			// If the number contains a negative sign, remove it.
			// Otherwise, add one to the beginning of the string.
			if (number.contains("-")) {
				number = number.substring(1, number.length());
			} else {
				number = "-" + number;
			}
			// Update the display
			display.setText(number);
			// Since we've altered the number, the calculator is no longer
			// cleared or showing a result
			cleared = resultDisplayed = false;
		}
	} // end of class Controller
} // end of class CalculatorView