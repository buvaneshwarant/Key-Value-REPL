package repl;

import java.util.HashMap;
import java.util.Map;

public class REPL {

	// this is a pointer. This points to current transaction.
	TransactionNode transactions;
	Map<String, String> mainLog = new HashMap<String, String>();

	public void start() {
		TransactionNode currentTransaction;

		if (transactions != null) {
			currentTransaction = new TransactionNode(new HashMap<String, String>(), transactions);

		} else {
			currentTransaction = new TransactionNode(new HashMap<String, String>(), transactions);
		}

		transactions = currentTransaction;
	}

	public void commit() {
		if (transactions == null) {
			return;
		}
		// add keys and values if they are "insert" or "delete"

		if (transactions.getPrevious() == null) {
			// there exists no parent. Set previous node to null.
			for (int i = 0; i < transactions.getWriteLog().size(); i++) {
				String key = transactions.getWriteLog().get(i);
				mainLog.put(key, transactions.getKeysAndValues().get(key));
			}

			for (int i = 0; i < transactions.getDeleteLog().size(); i++) {
				String key = transactions.getDeleteLog().get(i);
				mainLog.put(key, transactions.getKeysAndValues().get(key));
			}
			transactions.setPrevious(null);
		} else {
		
			
			// there exists a parent, Place all key, values in the previous node.
			for(int i = 0; i < transactions.getWriteLog().size(); i++) {
				String key = transactions.getWriteLog().get(i);
				String value = transactions.getKeysAndValues().get(key);
				transactions.getPrevious().setKeyValue(key, value);
			}
			
			for(int i = 0; i < transactions.getDeleteLog().size(); i++) {
				String key = transactions.getDeleteLog().get(i);
				String value = transactions.getKeysAndValues().get(key);
				transactions.getPrevious().setKeyValue(key, value);
				//will set to null or override with null if key is mentioned in delete log
			}
			
			transactions.getPrevious().setKeysAndValues(transactions.getKeysAndValues());
		}

		// pointer will point to the previous
		transactions = transactions.getPrevious();
	}

	public void abort() {
		if (transactions == null) {
			return;
		}

		transactions = transactions.getPrevious();
	}

	public void write(String key, String value) {
		if (transactions != null) {
			transactions.setKeyValue(key, value);
			transactions.getWriteLog().add(key);
		} else {
			mainLog.put(key, value);
		}
	}

	public String read(String key) {
		if (transactions != null) {
			return transactions.getKeysAndValues().get(key);
		}
		return mainLog.get(key);
	}

	public void delete(String key) {
		if (transactions != null) {
			String value;
			if ((value = transactions.getKeysAndValues().get(key)) != null) {
				transactions.getKeysAndValues().remove(key);
			} else {
				transactions.getDeleteLog().add(key);
			}

		} else {
			mainLog.remove(key);
		}
	}

}
