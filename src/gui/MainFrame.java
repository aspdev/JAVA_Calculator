package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;


///////////////////// Represents a Controller //////////////////////

public class MainFrame extends JFrame {

	private DisplayPanel displayPanel;
	private ButtonPanel buttonPanel;
	private DigitsPanel digitsPanel;
	private OuterOperatorsPanel outerOperatorsPanel;
	private InnerOperatorsPanel innerOperatorsPanel;
	private JTextField textField;
	private boolean operatorSelected = false;;
	private DataTypeConverter dataTypeConverter;
	private Queue<Double> internalRegisterNumbers;
	private Queue<String> internalRegisterOperators;
	private ArithmeticLogicUnit alu;
	private boolean numberDividedByZero = false;
	private boolean digitClicked = true;
	private String lastClickedOperator;
	private boolean equalsClicked = false;
	private boolean printResult = false;	
	

	
	///////////////////////////////////// Main Frame /////////////////////
	
	public MainFrame() {
		
		super("Calculator");
		
		try {
	           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	       } catch (Exception e) {
	           e.printStackTrace();
	       }

		setLayout(new BorderLayout());

		displayPanel = new DisplayPanel();
		buttonPanel = new ButtonPanel();
		internalRegisterNumbers = new LinkedList<Double>();
		internalRegisterOperators = new LinkedList<String>();
		dataTypeConverter = new DataTypeConverter();
		alu = new ArithmeticLogicUnit();

		add(displayPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);

		setSize(288, 275);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		

		digitsPanel = buttonPanel.getDigitsPanel();
		outerOperatorsPanel = buttonPanel.getOuterOperatorsPanel();
		innerOperatorsPanel = outerOperatorsPanel.getInnerOperatorsPanel();
		textField = displayPanel.getTextField();

		//////////////////////////////// Set ChangeSign Selected Listener ///////////////////////

		innerOperatorsPanel.setChangeSignButtonSelectedListener(new ButtonSelectedListener() {

			public void buttonSelectedOccured(ButtonSelectedEvent e) {

				if (numberDividedByZero == false && !(textField.getText().equals("0")) && operatorSelected == false
						&& !(textField.getText().equals("Infinity") || textField.getText().equals("-Infinity"))) {
					String number = textField.getText();

					if (number.contains("-")) {
												
						dataTypeConverter.setDoubleValue(number);
						double tempNumber = dataTypeConverter.getDoubleValue();
						dataTypeConverter.setStringAfterConversion(alu.multiplyNumbers(tempNumber, -1.0));
						number = dataTypeConverter.getStringAfterConversion();
						textField.setText(number);
						
						
					} else {

						dataTypeConverter.setDoubleValue(number);
						double tempNumber = dataTypeConverter.getDoubleValue();
						dataTypeConverter.setStringAfterConversion(alu.multiplyNumbers(tempNumber, -1.0));
						number = dataTypeConverter.getStringAfterConversion();
						textField.setText(number);
					}

				}

			}

		});

		//////////////////////////////////// Set Digit Selected Listener  //////////////////////////////////// 

		digitsPanel.setDigitPressedListener(new ButtonSelectedListener() {

			public void buttonSelectedOccured(ButtonSelectedEvent ev) {

				String character = ev.getCharacter();

				digitClicked = true;

				if (!numberDividedByZero
						&& !(textField.getText().equals("Infinity") || textField.getText().equals("-Infinity"))) {

					if (operatorSelected == false && printResult == false) {
						displayPanel.appendCharacterBeforeOperatorSelection(character);

					} else {

						if (!(character.equals(","))) {
							displayPanel.appendCharacterAfterOperatorSelection(character);
							operatorSelected = false;
							printResult = false;
						}
					}
				}

			}
		});

		//////////////////////////////////// Set Cancel Selected Listener //////////////////////////////////// 

		innerOperatorsPanel.setCancelButtonPressedListener(new ButtonSelectedListener() {

			public void buttonSelectedOccured(ButtonSelectedEvent ev) {

				displayPanel.clearScreen();
				internalRegisterNumbers.clear();
				internalRegisterOperators.clear();
				textField.setFont(new Font("Arial", Font.PLAIN, 26));
				numberDividedByZero = false;

			}

		});

		////////////////////////////////// Set SqrtRoot Selected Listener  ////////////////////////////////// 

		innerOperatorsPanel.setSqrtRootButtonSelectedListener(new ButtonSelectedListener() {

			public void buttonSelectedOccured(ButtonSelectedEvent ev) {

				if (numberDividedByZero != true
						&& !(textField.getText().equals("Infinity") || textField.getText().equals("-Infinity"))) {

					String value = textField.getText();
					String pattern = "\\A\\D\\d";
					Pattern p = Pattern.compile(pattern);
					Matcher m = p.matcher(value);

					if (m.find()) {
						String message = "Nieprawid³owe dane wejœciowe";
						textField.setFont(new Font("Arial", Font.PLAIN, 14));
						textField.setText(message);
						numberDividedByZero = true;
					} else {

						dataTypeConverter.setDoubleValue(textField.getText());
						internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());
						dataTypeConverter.setStringAfterConversion(alu.squareRoot(internalRegisterNumbers.poll()));
						textField.setText(dataTypeConverter.getStringAfterConversion());
						internalRegisterOperators.clear();
						internalRegisterNumbers.clear();
					}

				}

			}
		});

		/////////////////////////////////////// Set Equals Selected Listener /////////////////////////////////////// 

		innerOperatorsPanel.setEqualsButtonSelectedListener(new ButtonSelectedListener() {

			public void buttonSelectedOccured(ButtonSelectedEvent ev) {

				printResult = true;
				
				
				if (equalsClicked == false && !(textField.getText().equals("Infinity") || textField.getText().equals("-Infinity"))) {
					equalsClicked = true;
					dataTypeConverter.setDoubleValue(textField.getText());
					internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());

					if (internalRegisterNumbers.size() == 0 || internalRegisterNumbers.size() == 1) {
						// do nothing
					} else if (internalRegisterNumbers.size() == 2) {

						switch (internalRegisterOperators.poll()) {

						case "+":
							dataTypeConverter.setStringAfterConversion(
									alu.addNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
							textField.setText(dataTypeConverter.getStringAfterConversion());
							dataTypeConverter.setDoubleValue(textField.getText());
							internalRegisterNumbers.clear();
							break;
						case "-":
							dataTypeConverter.setStringAfterConversion(alu
									.subtractNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
							textField.setText(dataTypeConverter.getStringAfterConversion());
							dataTypeConverter.setDoubleValue(textField.getText());
							internalRegisterNumbers.clear();
							break;
						case "\u00D7":
							dataTypeConverter.setStringAfterConversion(alu
									.multiplyNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
							textField.setText(dataTypeConverter.getStringAfterConversion());
							dataTypeConverter.setDoubleValue(textField.getText());
							internalRegisterNumbers.clear();
							break;
						case "/":
							if (((double) (internalRegisterNumbers.toArray())[1] == 0)) {

								String message = "Nie mo¿na dzieliæ przez 0";
								textField.setFont(new Font("Arial", Font.PLAIN, 14));
								textField.setText(message);
								numberDividedByZero = true;

							} else {
								dataTypeConverter.setStringAfterConversion(alu
										.divideNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
								textField.setText(dataTypeConverter.getStringAfterConversion());
								dataTypeConverter.setDoubleValue(textField.getText());
								internalRegisterNumbers.clear();
								break;
							}

						}

					}

				}
			}

		});

		//////////////////////////////////////  Set Operator Selected Listener  //////////////////////////////////////

		innerOperatorsPanel.setOperatorPressedListener(new ButtonSelectedListener() {

			public void buttonSelectedOccured(ButtonSelectedEvent ev) {

				String operator = ev.getCharacter();
				lastClickedOperator = operator;
				operatorSelected = true;

				if (internalRegisterOperators.peek() != null && digitClicked == false) {

					if (!(operator.equals(internalRegisterOperators.peek())) && !(operator.equals("="))) {
						internalRegisterOperators.clear();
						internalRegisterOperators.add(operator);

					}

				}

				if ((!numberDividedByZero && digitClicked == true
						&& !(textField.getText().equals("Infinity") || textField.getText().equals("-Infinity")))) {

					if (!(lastClickedOperator == "=" || lastClickedOperator == "\u221A")) {

						digitClicked = false;
						equalsClicked = false;

					}

					internalRegisterOperators.add(operator);
					dataTypeConverter.setDoubleValue(textField.getText());
					internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());

					if (internalRegisterNumbers.size() == 2) {

						switch (internalRegisterOperators.poll()) {

						case "+":
							dataTypeConverter.setStringAfterConversion(
									alu.addNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
							textField.setText(dataTypeConverter.getStringAfterConversion());
							dataTypeConverter.setDoubleValue(textField.getText());
							internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());
							break;
						case "-":
							dataTypeConverter.setStringAfterConversion(alu
									.subtractNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
							textField.setText(dataTypeConverter.getStringAfterConversion());
							dataTypeConverter.setDoubleValue(textField.getText());
							internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());
							break;
						case "\u00D7":
							dataTypeConverter.setStringAfterConversion(alu
									.multiplyNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
							textField.setText(dataTypeConverter.getStringAfterConversion());
							dataTypeConverter.setDoubleValue(textField.getText());
							internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());
							break;
						case "/":
							if (((double) (internalRegisterNumbers.toArray())[1] == 0)) {

								String message = "Nie mo¿na dzieliæ przez 0";
								textField.setFont(new Font("Arial", Font.PLAIN, 14));
								textField.setText(message);
								numberDividedByZero = true;

							} else {
								dataTypeConverter.setStringAfterConversion(alu
										.divideNumbers(internalRegisterNumbers.poll(), internalRegisterNumbers.poll()));
								textField.setText(dataTypeConverter.getStringAfterConversion());
								dataTypeConverter.setDoubleValue(textField.getText());
								internalRegisterNumbers.add(dataTypeConverter.getDoubleValue());
								break;
							}

						}

					}

				}

			}
		});

	}


	
	
	
	
	

}
