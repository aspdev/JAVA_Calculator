package gui;

import java.math.BigDecimal;

//////////////////////////// Represents an object which performs basic calculations ///////////////////////////

public class ArithmeticLogicUnit {

	public double addNumbers(double numA, double numB) {
		return numA + numB;
	}

	public double subtractNumbers(double numA, double numB) {
		return numA - numB;
	}

	public double multiplyNumbers(double numA, double numB) {

		return numA * numB;
	}

	public double divideNumbers(double numA, double numB) {

		return numA / numB;
	}

	public double squareRoot(double numA) {

		return Math.sqrt(numA);
	}
}
