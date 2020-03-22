package pt.hrk.api;

import java.text.SimpleDateFormat;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class Utils {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
	public static MonetaryAmount StringToMoneratyAmt(String amountString) {
		if(amountString!=null && !amountString.trim().isEmpty()) {
			Double amtDouble = Double.valueOf(amountString.trim());
			return Monetary.getDefaultAmountFactory().setNumber(amtDouble).setCurrency("CAD").create();
		}
		return null;
	}
	
	public static String MoneratyAmtToString(MonetaryAmount amt,boolean absoluteOnly) {
		
		if(absoluteOnly) {
			amt =  amt.abs();
		}
		return String.valueOf(amt.getNumber().doubleValue());
	}
}
