package repl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionNode {

	// this stores the current Node's commands
	private Map<String, String> keysAndValues = new HashMap<String, String>();

	// store keys that needs to be deleted upon merging.
	private ArrayList<String> deleteLog = new ArrayList<String>();
	public ArrayList<String> getDeleteLog() {
		return deleteLog;
	}

	public void setDeleteLog(ArrayList<String> deleteLog) {
		this.deleteLog = deleteLog;
	}

	public ArrayList<String> getWriteLog() {
		return writeLog;
	}

	public void setWriteLog(ArrayList<String> writeLog) {
		this.writeLog = writeLog;
	}

	// store keys that needs to be inserted to head branch upon mergin.
	private ArrayList<String> writeLog = new ArrayList<String>();

	// this stores the parent Node
	private TransactionNode previous;

	public TransactionNode(Map<String, String> logOfCommands, TransactionNode previous) {
		this.keysAndValues = logOfCommands;
		this.previous = previous;
	}

	public void setKeysAndValues(Map<String, String> keysandvalues) {
		keysAndValues.putAll(keysandvalues);
	}

	public Map<String, String> getKeysAndValues() {
		return keysAndValues;
	}

	public void setKeyValue(String key, String value) {
		keysAndValues.put(key, value);
	}

	public TransactionNode getPrevious() {
		return previous;
	}

	public void setPrevious(TransactionNode previous) {
		this.previous = previous;
	}
}
