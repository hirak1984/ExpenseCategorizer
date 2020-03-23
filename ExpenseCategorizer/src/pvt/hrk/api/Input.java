package pvt.hrk.api;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.money.MonetaryAmount;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import pvt.hrk.model.Category;
import pvt.hrk.model.Metadata;
import pvt.hrk.model.Statement;
import pvt.hrk.model.SubCategory;
import pvt.hrk.model.Transaction;

public class Input {

	public static List<Statement> parseFrom(Metadata metadata,File[] statementFiles)
			throws IOException, NumberFormatException, ParseException {
		int len = statementFiles.length;
		List<Statement> statements = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			statements.add(Input.parseFrom(metadata, statementFiles[i]));
		}
		return statements;
	}
	
	/**
	 * Format of input headers - Posted Date, Payee, Address, Amount
	 * 
	 * @param metadata
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */

	public static Statement parseFrom(Metadata metadata, File file)
			throws IOException, NumberFormatException, ParseException {
		Statement statement = new Statement();
		Reader in = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {

			String postedDateS = record.get(0);
			String PayeeS = record.get(1);
			String AmountS = record.get(3);
			Date postedDate = Utils.sdf.parse(postedDateS.trim());
			MonetaryAmount cad = Utils.StringToMoneratyAmt(AmountS);
			Transaction txn = new Transaction(postedDate, PayeeS.trim(), cad);
			updateCategoryDetails(metadata, txn);
			statement.addTransaction(txn);
		}

		return statement;
	}

	private static Transaction updateCategoryDetails(Metadata mappings, Transaction txn) {

		List<Category> categories = mappings.categories;
		for (Category category : categories) {
			List<SubCategory> subCategories = category.subCategories;
			for (SubCategory sc : subCategories) {

				if (categorizable(sc.keywordContainsIgnoreCase, txn)) {
					txn.setCategory(category.name);
					txn.setSubCategory(sc.name);
					return txn;
				}
			}
		}
		return txn;
	}

	private static final boolean categorizable(List<String> keywords, Transaction txn) {
		if (txn == null || keywords == null) {
			return false;
		}
		String payeeLowerCase = txn.getPayee().toLowerCase().trim();
		MonetaryAmount amount = txn.getAmount();
		for (String keyword : keywords) {
			if (!keyword.isEmpty()) {
				keyword = keyword.trim().toLowerCase();
				if (keyword.startsWith("$")) {
					MonetaryAmount keywordAmt = Utils.StringToMoneratyAmt(keyword.substring(1));// remove the dollar
																								// sign.
					if (keywordAmt.abs().equals(amount.abs())) {
						return true;
					}
				} else {
					if (payeeLowerCase.contains(keyword)) {
						return true;
					}
				}
			}

		}

		return false;
	}

}
