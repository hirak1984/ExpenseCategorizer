package pt.hrk.model;

import java.util.LinkedList;
import java.util.List;

public class Statement {
	private List<Transaction> transactions;

	public boolean addTransaction(Transaction txn) {
		if (transactions == null) {
			transactions = new LinkedList<>();
		}
		return transactions.add(txn);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
