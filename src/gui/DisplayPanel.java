package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class DisplayPanel extends JPanel {

	private JTextField textField;

	public DisplayPanel() {
		
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border innerBorder = BorderFactory.createLineBorder(new Color(0, 191, 255));
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		Dimension dim = getPreferredSize();
		dim.height = 50;

		textField = new JTextField();
		textField.setEditable(false);
		textField.setPreferredSize(dim);
		textField.setBackground(new Color(255, 255, 255));
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Arial", Font.PLAIN, 26));
		textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setLayout(new BorderLayout());
		add(textField, BorderLayout.CENTER);
	}
	
	
	///////////////////////// Append Character to TextField Before Operator Is Selected ///////////////////////

	public void appendCharacterBeforeOperatorSelection(String character) {

		String number = textField.getText();
		StringBuilder sb = new StringBuilder();

		if (number.equals("0") && character.equals(",")) {
			sb.append(number);
			sb.append(character);
			textField.setText(sb.toString());
		} else if (number.equals("0") && !(character.equals(","))) {
			textField.setText(character);
		} else if (number.length() == 15) {
			character = null;
		} else if (number.contains(",") && character.equals(",")) {
			character = null;
		} else {
			sb.append(number);
			sb.append(character);
			textField.setText(sb.toString());
		}

	}
	
	/////////////////////// Append Character to TextField  After Operator Is Selected /////////////////////////

	public void appendCharacterAfterOperatorSelection(String character) {

		StringBuilder sb = new StringBuilder();
		sb.append(character);
		textField.setText(sb.toString());

	}

	public void clearScreen() {
		textField.setText("0");
	}

	public JTextField getTextField() {
		return textField;
	}
	
	

}
