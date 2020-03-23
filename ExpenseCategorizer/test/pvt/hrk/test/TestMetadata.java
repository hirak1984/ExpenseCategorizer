package pvt.hrk.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import pvt.hrk.api.MetadataInputCsvFormat;
import pvt.hrk.main.MyMain;
import pvt.hrk.model.Metadata;

class TestMetadata {

	private InputStream readFileFromClasspath(String fileName) {
		return MyMain.class.getResourceAsStream(fileName);
	}

	@Test
	void testCSV() {
		Metadata metadata = new Metadata();
		try {
			metadata = MetadataInputCsvFormat.parseMetadata(readFileFromClasspath("/metadata.csv"));
		} catch (IOException e) {
			fail(e);
		}
		assertEquals(metadata.toString(),
				"Metadata [categories=[Category [name=Everyday, subCategories=[SubCategory [name=Groceries, keywordContainsIgnoreCase=[cobs, rabba, UBER EATS, NOFRILLS, IQBAL, LABBAIK]], SubCategory [name=Restaurants, keywordContainsIgnoreCase=[chaska, drux, PITA LITE, grabb, BAR B Q TONITE, CHALET, KABOB, SKIPTHEDISHES]], SubCategory [name=Personal supplies, keywordContainsIgnoreCase=[BEAUTY]], SubCategory [name=Clothes, keywordContainsIgnoreCase=[]], SubCategory [name=Laundry/dry cleaning, keywordContainsIgnoreCase=[COINAMATIC]], SubCategory [name=Personal Care, keywordContainsIgnoreCase=[]], SubCategory [name=Subscriptions, keywordContainsIgnoreCase=[]], SubCategory [name=Other, keywordContainsIgnoreCase=[PREMIUM SWEETS]], SubCategory [name=Entertainment, keywordContainsIgnoreCase=[]]]], Category [name=Payment, subCategories=[SubCategory [name=Payment, keywordContainsIgnoreCase=[PAYMENT]]]], Category [name=Home, subCategories=[SubCategory [name=Rent/mortgage, keywordContainsIgnoreCase=[]], SubCategory [name=taxes, keywordContainsIgnoreCase=[INTUIT]], SubCategory [name=Furnishings, keywordContainsIgnoreCase=[]], SubCategory [name=Lawn/garden, keywordContainsIgnoreCase=[]], SubCategory [name=Supplies, keywordContainsIgnoreCase=[]], SubCategory [name=Maintenance, keywordContainsIgnoreCase=[]], SubCategory [name=Improvements, keywordContainsIgnoreCase=[]], SubCategory [name=Moving, keywordContainsIgnoreCase=[]]]], Category [name=Insurance, subCategories=[SubCategory [name=Car, keywordContainsIgnoreCase=[]], SubCategory [name=Health, keywordContainsIgnoreCase=[]], SubCategory [name=Home, keywordContainsIgnoreCase=[square]], SubCategory [name=Life, keywordContainsIgnoreCase=[]]]], Category [name=Technology, subCategories=[SubCategory [name=Domains & hosting, keywordContainsIgnoreCase=[AWS]], SubCategory [name=Online services, keywordContainsIgnoreCase=[]], SubCategory [name=Hardware, keywordContainsIgnoreCase=[]], SubCategory [name=Software, keywordContainsIgnoreCase=[BITWARDEN]]]], Category [name=Health, subCategories=[SubCategory [name=Doctors/dental/vision, keywordContainsIgnoreCase=[JUNCTIONWEST]], SubCategory [name=Specialty care, keywordContainsIgnoreCase=[]], SubCategory [name=Pharmacy, keywordContainsIgnoreCase=[PHARMACY]], SubCategory [name=Emergency, keywordContainsIgnoreCase=[AMBULANCE]]]], Category [name=Children, subCategories=[SubCategory [name=Other, keywordContainsIgnoreCase=[STAPLES]], SubCategory [name=Activities, keywordContainsIgnoreCase=[]], SubCategory [name=Allowance, keywordContainsIgnoreCase=[]], SubCategory [name=Medical, keywordContainsIgnoreCase=[]], SubCategory [name=Childcare, keywordContainsIgnoreCase=[]], SubCategory [name=Clothing, keywordContainsIgnoreCase=[OSHKOSH]], SubCategory [name=School, keywordContainsIgnoreCase=[]], SubCategory [name=Toys, keywordContainsIgnoreCase=[]]]], Category [name=Utilities, subCategories=[SubCategory [name=Phone, keywordContainsIgnoreCase=[fido, chatr]], SubCategory [name=TV, keywordContainsIgnoreCase=[]], SubCategory [name=Internet, keywordContainsIgnoreCase=[fido home]], SubCategory [name=Electricity, keywordContainsIgnoreCase=[]], SubCategory [name=Heat/gas, keywordContainsIgnoreCase=[]], SubCategory [name=Water, keywordContainsIgnoreCase=[]], SubCategory [name=Trash, keywordContainsIgnoreCase=[]]]], Category [name=Transportation, subCategories=[SubCategory [name=Other, keywordContainsIgnoreCase=[AIR CAN]], SubCategory [name=Fuel, keywordContainsIgnoreCase=[]], SubCategory [name=Car payments, keywordContainsIgnoreCase=[]], SubCategory [name=Repairs, keywordContainsIgnoreCase=[]], SubCategory [name=Registration/license, keywordContainsIgnoreCase=[]], SubCategory [name=Supplies, keywordContainsIgnoreCase=[]], SubCategory [name=Public transit, keywordContainsIgnoreCase=[UBER, lyft, presto, VRTUCAR]]]]]]");

	}
}
