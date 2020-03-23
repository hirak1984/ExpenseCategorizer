package pvt.hrk.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.junit.jupiter.api.Test;

import pvt.hrk.api.Utils;

public class Testutils {

	@Test
	public void testStringToMoneratyAmt(){
		assertEquals(10.0, Utils.StringToMoneratyAmt("10.0").getNumber().doubleValue());
	}
	@Test
	public void testMoneratyAmtToString(){
		MonetaryAmount amt = Monetary.getDefaultAmountFactory().setNumber(-10.49).setCurrency("CAD").create();
		assertEquals("10.49", Utils.MoneratyAmtToString(amt,true));
		assertEquals("-10.49", Utils.MoneratyAmtToString(amt,false));
	}
}
