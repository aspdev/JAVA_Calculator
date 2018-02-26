package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


///////////////////////////////////// Contains DigitsPanel and OuterOperatorsPanel /////////////////////////

public class ButtonPanel extends JPanel {

	private DigitsPanel digitsPanel;
	private OuterOperatorsPanel outerOperatorsPanel;

	public ButtonPanel() {

		Border outerBorder = BorderFactory.createEmptyBorder(0, 10, 10, 10);
		Border innerBorder = BorderFactory.createLineBorder(new Color(0, 191, 255));
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		digitsPanel = new DigitsPanel();
		outerOperatorsPanel = new OuterOperatorsPanel();

		setLayout(new BorderLayout());

		add(digitsPanel, BorderLayout.WEST);
		add(outerOperatorsPanel, BorderLayout.CENTER);

	}

	public DigitsPanel getDigitsPanel() {
		return digitsPanel;
	}

	public OuterOperatorsPanel getOuterOperatorsPanel() {
		return outerOperatorsPanel;
	}

}
