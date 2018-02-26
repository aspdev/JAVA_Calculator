package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class OuterOperatorsPanel extends JPanel {

	private InnerOperatorsPanel innerOperatorsPanel;

	public OuterOperatorsPanel() {

		setLayout(new BorderLayout());
		innerOperatorsPanel = new InnerOperatorsPanel();
		add(innerOperatorsPanel, BorderLayout.WEST);
	}

	public InnerOperatorsPanel getInnerOperatorsPanel() {
		return innerOperatorsPanel;
	}

}
