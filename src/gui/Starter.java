package gui;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.SwingUtilities;

public class Starter {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() {
				
				new MainFrame();
			}
			
		});

	}

}
