package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DigitsPanel extends JPanel {

	private JButton btnZero;
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JButton btnFive;
	private JButton btnSix;
	private JButton btnSeven;
	private JButton btnEight;
	private JButton btnNine;
	private JButton btnPoint;

	private ButtonSelectedListener buttonPressedListener;

	public DigitsPanel() {
		btnZero = new JButton("0");
		btnZero.setFont(new Font("Arial", Font.PLAIN, 28));
		btnOne = new JButton("1");
		btnOne.setFont(new Font("Arial", Font.PLAIN, 28));
		btnTwo = new JButton("2");
		btnTwo.setFont(new Font("Arial", Font.PLAIN, 28));
		btnThree = new JButton("3");
		btnThree.setFont(new Font("Arial", Font.PLAIN, 28));
		btnFour = new JButton("4");
		btnFour.setFont(new Font("Arial", Font.PLAIN, 28));
		btnFive = new JButton("5");
		btnFive.setFont(new Font("Arial", Font.PLAIN, 28));
		btnSix = new JButton("6");
		btnSix.setFont(new Font("Arial", Font.PLAIN, 28));
		btnSeven = new JButton("7");
		btnSeven.setFont(new Font("Arial", Font.PLAIN, 28));
		btnEight = new JButton("8");
		btnEight.setFont(new Font("Arial", Font.PLAIN, 28));
		btnNine = new JButton("9");
		btnNine.setFont(new Font("Arial", Font.PLAIN, 28));
		btnPoint = new JButton(",");
		btnPoint.setFont(new Font("Arial", Font.PLAIN, 28));

		JButton[] arrayOfButtons = { btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight,
				btnNine, btnPoint };

		for (JButton btn : arrayOfButtons) {

			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String digit = btn.getText();
					ButtonSelectedEvent ev = new ButtonSelectedEvent(this, digit);

					if (buttonPressedListener != null) {
						buttonPressedListener.buttonSelectedOccured(ev);
					}

				}

			});
		}

		setLayout(new GridBagLayout());

		//////////////// First Row ///////////////////

		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 5, 5);
		add(btnSeven, gc);

		gc.gridx = 1;
		add(btnEight, gc);

		gc.gridx = 2;
		add(btnNine, gc);

		//////////////// Second Row ///////////////////

		gc.gridy = 1;
		gc.gridx = 0;
		add(btnFour, gc);

		gc.gridx = 1;
		add(btnFive, gc);

		gc.gridx = 2;
		add(btnSix, gc);

		/////////////// Third Row ////////////////////

		gc.gridy = 2;
		gc.gridx = 0;
		add(btnOne, gc);

		gc.gridx = 1;
		add(btnTwo, gc);

		gc.gridx = 2;
		add(btnThree, gc);

		/////////////// Fourth Row ///////////////////

		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.gridy = 3;
		gc.gridx = 0;
		add(btnZero, gc);

		// gc.gridwidth = 1;
		gc.gridx = 2;
		add(btnPoint, gc);

	}

	///////////////////////// Reference Setting Function /////////////////////////////
	
	public void setDigitPressedListener(ButtonSelectedListener buttonPressedListener) {
		this.buttonPressedListener = buttonPressedListener;
	}

}
