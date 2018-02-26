package gui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//////////////////////////////// Converts data from String to Double and vice versa /////////////////////

public class DataTypeConverter {

	private double doubleValue;
	private String stringAfterConversion;
	

	public double getDoubleValue() {
		return doubleValue;
	}

	public String getStringAfterConversion() {
		return stringAfterConversion;
	}
	
	///////////////////////////// From String to Double ////////////////////////

	public void setDoubleValue(String dataToConvert) {

		if (dataToConvert.contains(",")) {
			String str = dataToConvert.replace(',', '.');
			this.doubleValue = Double.parseDouble(str);
			

		} else {
			this.doubleValue = Double.parseDouble(dataToConvert);
		}
	}
	
	/////////////////////////// From Double to String /////////////////////////////////

	public void setStringAfterConversion(double aluReturnedData) {

		String number = new BigDecimal(String.valueOf(aluReturnedData)).setScale(100, BigDecimal.ROUND_UP).toString();
		
		
		String patternOne = "\\.0+$";
		Pattern pOne = Pattern.compile(patternOne);
		Matcher mOne = pOne.matcher(number);

		String patternTwo = "^(-?[1-9]\\d*)(\\.)(\\d+E-\\d+)";
		Pattern pTwo = Pattern.compile(patternTwo);
		Matcher mTwo = pTwo.matcher(number);
		
		String patternThree = "E-9[0-9]";
		Pattern pThree = Pattern.compile(patternThree);
		Matcher mThree = pThree.matcher(number);

		String patternFour = "^(-?0|-?[1-9]\\d*)\\.(0*[1-9]+){0,}";
		Pattern pFour = Pattern.compile(patternFour);
		Matcher mFour = pFour.matcher(number);
		
		

		////////// aluReturnedData == ########.000000000000000 ( decimal point followed by zeroes)  	////////////////////

		if (number.equals("0E-100")) {
			this.stringAfterConversion = "0";
		}

		else if (mOne.find()) {

			int index = number.indexOf(".");

			if (number.substring(0, index).length() > 13) {
				
								
				String str = number.substring(0, index);
				BigDecimal bd = new BigDecimal(str);
				DecimalFormat df = new DecimalFormat("0.####E0");
				
											
				String patternFive = "^[1-9]\\,?\\d*E9[0-9]";
				Pattern pFive = Pattern.compile(patternFive);
				Matcher mFive = pFive.matcher(df.format(bd));
				
				String patternSix = "^-[1-9]\\,?\\d*E9[0-9]"; 
				Pattern pSix = Pattern.compile(patternSix);
				Matcher mSix = pSix.matcher(df.format(bd));
				
				if (mFive.find()) {
					
					this.stringAfterConversion = String.valueOf(Double.POSITIVE_INFINITY);
					
				}
				
				else if (mSix.find()) {
					
					this.stringAfterConversion = String.valueOf(Double.NEGATIVE_INFINITY);
				}
				
				else {
				
					this.stringAfterConversion = df.format(bd);
										
				}
				
			} else {

				this.stringAfterConversion = number.substring(0, index);

			}
		}

		else if (mTwo.find()) {
			
			if (mThree.find()) {
				
				this.stringAfterConversion = String.valueOf("0");
				//Zaimplementowaæ zdarzenie, które powiadomi klasê MainFrame o tym, ¿e ci¹g wartoœci zbli¿a siê nieskoñczenie do zera
				
			} else {
				
				BigDecimal bd = new BigDecimal(number);
				DecimalFormat df = new DecimalFormat("0.####E0");
				this.stringAfterConversion = df.format(bd);

			}
			
		}

		////////// aluReturnedData == ######.###### (decimal point followed by zero and non-zero digits /////////////////

		else if (mFour.find()) {

			String str = number.substring(mFour.start(), mFour.end());

			if (str.length() > 13) {
				
				this.stringAfterConversion = str.substring(0, 14).replace('.', ',');
			}

			else {

				this.stringAfterConversion = str.replace('.', ',');
			}

		}

	}
	
	

}
