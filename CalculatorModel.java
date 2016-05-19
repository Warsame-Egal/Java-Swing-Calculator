/*
 * File name:	CalculatorModel.java
 * Author:		Derek McKinnon 040-674-434
 * Course:		CST8221 - JAP
 * Lab Section:	401
 * Assignment:	1, Part 2
 * Date:		2013-10-28
 * Professor:	Svillen Ranev
 * Purpose:		To provide the model component of the Calculator applciation.
 */
package mcki0131.cst8221.assignment1;

/**
 * This class contains the data for the Calculator application and provides the
 * logic therein to perform calculations and return results.
 * 
 * @author Derek McKinnon
 * @version 1.0
 * @see mcki0131.cst8221.assignment1.CalculatorView
 * @see mcki0131.cst8221.assignment1.CalculatorView.Controller
 * @since 1.7
 */
public class CalculatorModel {
	/**
	 * A String that is returned upon error from the calculator. * * {@value}
	 */
	private static final String ERROR_RESULT = "--";

	/**
	 * A variable that holds the value of the first operand.
	 */
	private double operand1;
	/**
	 * A variable that holds the value of the second operand.
	 */
	private double operand2;
	/**
	 * A variable that holds the value of the result.
	 */
	private double result;
	/**
	 * A flag that indicates whether or not the model is in an error state.
	 */
	private boolean errorState;

	/**
	 * A flag that indicates which Operator has been entered into the
	 * calculator.
	 */
	private Operator operator;
	/**
	 * A flag that indicates which OperationMode the calculator is in.
	 */
	private OperationMode operationMode;
	/**
	 * A flag that indicates the Precision mode the calculator is in.
	 */
	private Precision precision;

	/**
	 * The default constructor for the CalculatorModel class that initializes
	 * the model to its default state.
	 */
	public CalculatorModel() {
		operationMode = OperationMode.Float;
		precision = Precision.Double;
		clear();
	}

	/**
	 * Returns the value of the first operand.
	 * 
	 * @return The value of the first operand.
	 */
	public double getOperand1() {
		return operand1;
	}

	/**
	 * Sets the first operand.
	 * 
	 * @param operand
	 *            The value of the first operand.
	 */
	public void setOperand1(double operand) {
		operand1 = operand;
	}

	/**
	 * Returns the value of the second operand.
	 * 
	 * @return The value of the second operand.
	 */
	public double getOperand2() {
		return operand2;
	}

	/**
	 * Sets the second operand.
	 * 
	 * @param operand
	 *            The value of the second operand.
	 */
	public void setOperand2(double operand) {
		operand2 = operand;
	}

	/**
	 * Retrieves the value of the result in String format.
	 * 
	 * @return The result of the calculations or ERROR_RESULT if the model is in
	 *         error state.
	 */
	public String getResult() {
		if (errorState)
			return ERROR_RESULT;

		switch (operationMode) {
		case Integer:
			return String.format("%d", (int) result);
		case Float:
			if (result == 0.0d) {
				return String.format("%.1f", result);
			}

			switch (precision) {
			case Single:
				return String.format("%.1f", result);
			case Double:
				return String.format("%.2f", result);
			case Scientific:
				return String.format("%.6e", result).replace("e", "");
			}
		default:
			return ERROR_RESULT;
		}
	}

	/**
	 * Returns the value of the error state flag.
	 * 
	 * @return The value of the error state flag.
	 */
	public boolean getErrorState() {
		return errorState;
	}

	/**
	 * Allows setting the error state flag.
	 * 
	 * @param errorState
	 *            The error state value.
	 */
	public void setErrorState(boolean errorState) {
		this.errorState = errorState;
	}

	/**
	 * Returns the value of the currently set Operator.
	 * 
	 * @return The current Operator.
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * Allows setting the Operator.
	 * 
	 * @param operator
	 *            The Operator to be set.
	 */
	public void setOperator(Operator operator) {
		if (operator == null)
			throw new NullPointerException();
		this.operator = operator;
	}

	/**
	 * Returns the value of the current OperationMode.
	 * 
	 * @return The value of the current OperationMode.
	 */
	public OperationMode getOperationMode() {
		return operationMode;
	}

	/**
	 * Allows setting the current OperationMode.
	 * 
	 * @param operationMode
	 *            The OperationMode value to be set.
	 */
	public void setOperationMode(OperationMode operationMode) {
		if (operationMode == null)
			throw new NullPointerException();
		this.operationMode = operationMode;
		// Update the variables to reflect the change in mode.
		if (operationMode == OperationMode.Integer) {
			operand1 = (int) operand1;
			operand2 = (int) operand2;
			result = (int) result;
			return;
		}
	}

	/**
	 * Returns the value of the currently set Precision.
	 * 
	 * @return The value of the currently set Precision.
	 */
	public Precision getPrecision() {
		return precision;
	}

	/**
	 * Allows setting the Precision.
	 * 
	 * @param precision
	 *            The Precision value to be set.
	 */
	public void setPrecision(Precision precision) {
		if (precision == null)
			throw new NullPointerException();
		this.precision = precision;
	}

	/**
	 * Clears the model and resets it to default.
	 */
	public void clear() {
		operand1 = operand2 = result = 0.0d;
		operator = Operator.None;
		setErrorState(false);
	}

	/**
	 * Attempts to perform the calculations using the information stored in the
	 * model. On error, sets the error state to error.
	 */
	public void performCalculations() {
		try {
			switch (operator) {
			case Addition:
				result = operand1 + operand2;
				break;
			case Subtraction:
				result = operand1 - operand2;
				break;
			case Multiplcation:
				result = operand1 * operand2;
				break;
			case Division:
				result = operand1 / operand2;
				break;
			default:
				return;
			}
		} catch (Exception e) {
			errorState = true;
			result = 0.0d;
			return;
		}

		if (!isValidResult()) {
			errorState = true;
			result = 0.0d;
		}
	}

	/**
	 * Returns whether or not the result is valid.
	 * 
	 * @return A boolean value indicating result validity.
	 */
	private boolean isValidResult() {
		return !Double.isNaN(result) && !Double.isInfinite(result);
	}

	/**
	 * Clears the model and sets the first operand as the result from the
	 * previous calculation.
	 */
	public void setOperand1FromResult() {
		double result = this.result;
		clear();
		operand1 = result;
	}

	/**
	 * Defines the possible values for operational modes.
	 * 
	 * @author Derek McKinnon
	 * @version 1.0
	 * @since 1.7
	 */
	public enum OperationMode {
		Integer, Float
	}

	/**
	 * Defines the possible values for mathematical operators.
	 * 
	 * @author Derek McKinnon
	 * @version 1.0
	 * @since 1.7
	 */
	public enum Operator {
		None, Addition, Subtraction, Multiplcation, Division
	}

	/**
	 * Defines the possible values for precision modes.
	 * 
	 * @author Derek McKinnon
	 * @version 1.0
	 * @since 1.7
	 */
	public enum Precision {
		Single, Double, Scientific
	}
}
