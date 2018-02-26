package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InnerOperatorsPanel extends JPanel {

	private JButton btnEquals;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnTimes;
	private JButton btnDivided;
	private JButton btnCancel;
	private JButton btnSquareRoot;
	private JButton btnChangeSign;
	private ButtonSelectedListener buttonSelectedListener;
	private ButtonSelectedListener cancelButtonSelectedListener;
	private ButtonSelectedListener sqrtRootButtonSelectedListener;
	private ButtonSelectedListener changeSignButtonSelectedListener;
	private ButtonSelectedListener equalsButtonSelectedListener;

	public InnerOperatorsPanel() {
		btnEquals = new JButton("=");
		btnEquals.setFont(new Font("Arial", Font.PLAIN, 26));
		btnPlus = new JButton("+");
		btnPlus.setFont(new Font("Arial", Font.PLAIN, 26));
		btnMinus = new JButton("-");
		btnMinus.setFont(new Font("Arial", Font.PLAIN, 26));
		btnTimes = new JButton("\u00D7");
		btnTimes.setFont(new Font("Arial", Font.PLAIN, 26));
		btnDivided = new JButton("/");
		btnDivided.setFont(new Font("Arial", Font.PLAIN, 26));
		btnCancel = new JButton("c");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 26));
		btnSquareRoot = new JButton("\u221A");
		btnSquareRoot.setFont(new Font("Arial", Font.PLAIN, 26));
		btnChangeSign = new JButton("\u00B1");
		btnChangeSign.setFont(new Font("Arial", Font.PLAIN, 26));

		////////////////////////// Equals ActionListener ////////////////////////
		
		btnEquals.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String text = btnEquals.getText();
				ButtonSelectedEvent ev = new ButtonSelectedEvent(this, text);

				if (equalsButtonSelectedListener != null) {
					equalsButtonSelectedListener.buttonSelectedOccured(ev);
				}
			}

		});

		/////////////////////////// ChangeSign ActionListener ////////////////////
		
		btnChangeSign.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String text = btnChangeSign.getText();
				ButtonSelectedEvent ev = new ButtonSelectedEvent(this, text);

				if (changeSignButtonSelectedListener != null) {
					changeSignButtonSelectedListener.buttonSelectedOccured(ev);
				}
			}

		});

		///////////////////////////// CancelButton ActionListener ////////////////////
		
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String text = btnCancel.getText();
				ButtonSelectedEvent ev = new ButtonSelectedEvent(this, text);

				if (cancelButtonSelectedListener != null) {
					cancelButtonSelectedListener.buttonSelectedOccured(ev);
				}
			}

		});
		
		/////////////////////////// SqrtRoot ActionListener ///////////////////////////

		btnSquareRoot.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String text = btnSquareRoot.getText();
				ButtonSelectedEvent ev = new ButtonSelectedEvent(this, text);

				if (sqrtRootButtonSelectedListener != null) {
					sqrtRootButtonSelectedListener.buttonSelectedOccured(ev);
				}
			}

		});
		
		////////////////////////// Arithmetic Operator ActionListener ///////////////////

		JButton[] arrayOfButtons = { btnPlus, btnMinus, btnTimes, btnDivided };

		for (JButton btn : arrayOfButtons) {

			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String operator = btn.getText();
					ButtonSelectedEvent ev = new ButtonSelectedEvent(this, operator);

					if (buttonSelectedListener != null) {
						buttonSelectedListener.buttonSelectedOccured(ev);
					}

				}

			});

		}
		
		

		setLayout(new GridBagLayout());

		/////////////// First Row ////////////////////////

		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(btnCancel, gc);

		gc.insets = new Insets(0, 0, 5, 5);
		gc.gridx = 1;
		add(btnSquareRoot, gc);

		////////////// Second Row ///////////////////////

		gc.insets = new Insets(0, 0, 5, 5);
		gc.gridy = 1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(btnDivided, gc);

		gc.gridx = 1;
		add(btnChangeSign, gc);

		///////////// Third Row /////////////////////////

		gc.insets = new Insets(0, 0, 5, 5);
		gc.gridy = 2;
		gc.gridx = 0;
		add(btnMinus, gc);

		// gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;
		add(btnTimes, gc);

		///////////// Fourth Row ///////////////////////

		gc.gridy = 3;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnPlus, gc);

		gc.gridy = 3;
		gc.gridx = 1;
		add(btnEquals, gc);
		
	/////////////////////////////  Reference setting functions //////////////////////////

	}

	public void setOperatorPressedListener(ButtonSelectedListener buttonSelectedListener) {
		this.buttonSelectedListener = buttonSelectedListener;
	}

	public void setCancelButtonPressedListener(ButtonSelectedListener cancelButtonSelectedListener) {
		this.cancelButtonSelectedListener = cancelButtonSelectedListener;
	}

	public void setSqrtRootButtonSelectedListener(ButtonSelectedListener sqrtRootButtonSelectedListener) {
		this.sqrtRootButtonSelectedListener = sqrtRootButtonSelectedListener;
	}

	public void setChangeSignButtonSelectedListener(ButtonSelectedListener changeSignButtonSelectedListener) {
		this.changeSignButtonSelectedListener = changeSignButtonSelectedListener;
	}

	public void setEqualsButtonSelectedListener(ButtonSelectedListener equalsButtonSelectedListener) {
		this.equalsButtonSelectedListener = equalsButtonSelectedListener;
	}

}
