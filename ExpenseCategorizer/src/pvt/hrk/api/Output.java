package pvt.hrk.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.money.MonetaryAmount;

import pvt.hrk.model.Metadata;
import pvt.hrk.model.Pair;
import pvt.hrk.model.Statement;
import pvt.hrk.model.Transaction;

/**
 * After the output files are written, check the transactions file to find which rows are still unclassified.
 * Update the Metadata file with keywords from those payees and re run the application.
 * Or manually classify them and add the amounts to the Aggregated file (for example Amazon/Walmart etc where there are items from multiple categories in one transaction).
 * I usually save the receipts for one month for this manual classification need.
 * 
 * @author hchatterjee
 *
 */
public class Output {
	private static String aggregatedOutput = "Aggregated.csv";
	private static String aggregatedOHeaders = "Category,SubCategory,Amount" + System.lineSeparator();
	private static String transactionsOutput = "Transactions.csv";
	private static String transactionsHeaders = "PostedDate,Payee,Amount,Category,SubCategory" + System.lineSeparator();

	public static Map<Pair<String,String>, MonetaryAmount> printTransactionsAndReturnClassifiedsAggregated(File destinationDirectory,
			List<Statement> statements) throws FileNotFoundException, IOException {
		Map<Pair<String,String>, MonetaryAmount> outputMap = new HashMap<Pair<String,String>, MonetaryAmount>();
		List<Transaction> flattenedTransactions = new LinkedList<>();
		for (Statement statement : statements) {
			List<Transaction> transactions = statement.getTransactions();
			for (Transaction txn : transactions) {
				flattenedTransactions.add(txn);
				Pair<String,String> pair = new Pair<String,String>(txn.getCategory(), txn.getSubCategory());

				MonetaryAmount amount = txn.getAmount();
				if (outputMap.containsKey(pair)) {
					amount = amount.add(outputMap.get(pair));
				}
				outputMap.put(pair, amount);
			}
		}

		outputTransactionsTo(flattenedTransactions,
				new FileOutputStream(new File(destinationDirectory, transactionsOutput)));
		return outputMap;
	}

	public static void processResults(File destinationDirectory, Metadata metadata, List<Statement> statements)
			throws FileNotFoundException, IOException {
		Map<Pair<String,String>, MonetaryAmount> outputMap = new TreeMap<Pair<String,String>, MonetaryAmount>((p1, p2) -> {
			int c = p1.getA().compareTo(p2.getA());
			if (c == 0) {
				c = p1.getB().compareTo(p2.getB());
			}
			return c;
		});
		Map<Pair<String,String>, MonetaryAmount> classifiedsFoundMap = printTransactionsAndReturnClassifiedsAggregated(
				destinationDirectory, statements);
		metadata.categories.forEach(category -> {
			category.subCategories.forEach(subCategory -> {
				Pair<String,String> pair = new Pair<String,String>(category.name, subCategory.name);
				MonetaryAmount amt = classifiedsFoundMap.get(pair);
				if (amt == null) {
					amt =Utils.StringToMoneratyAmt("0.0");
				}
				outputMap.put(pair, amt);
			});
		});
		outputAggregatedResultsTo(outputMap, new FileOutputStream(new File(destinationDirectory, aggregatedOutput)));
	}

	private static void outputTransactionsTo(List<Transaction> unclassifiedTransactions, OutputStream os)
			throws IOException {
		os.write(transactionsHeaders.getBytes());

		for (Transaction txn : unclassifiedTransactions) {
			StringBuilder sb = new StringBuilder();
			sb.append(Utils.sdf.format(txn.getPostedDate())).append(",").append(txn.getPayee()).append(",")
					.append(Utils.MoneratyAmtToString(txn.getAmount(),false)).append(",").append(txn.getCategory()).append(",")
					.append(txn.getSubCategory()).append(System.lineSeparator());

			os.write(sb.toString().getBytes());
		}

	}

	private static void outputAggregatedResultsTo(Map<Pair<String,String>, MonetaryAmount> outputMap, OutputStream os) throws IOException {
		os.write(aggregatedOHeaders.getBytes());
		Iterator<Pair<String,String>> it = outputMap.keySet().iterator();
		while (it.hasNext()) {
			Pair<String,String> key = it.next();
			StringBuilder sb = new StringBuilder();
			sb.append(key.getA()).append(",").append(key.getB()).append(",").append(Utils.MoneratyAmtToString(outputMap.get(key),true))
					.append(System.lineSeparator());

			os.write(sb.toString().getBytes());
		}
	}
}
