package systemdesign.inmemorydb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Table {
	String tableName;
	long created;
	String pk;
	ConcurrentMap<String, Row> rows;
	Set<String> cols;
	public Table(String tableName, String pk, Set<String> cols) {
		this.tableName = tableName;
		this.pk = pk;
		this.cols = cols;
		rows = new ConcurrentHashMap<>();
	}
	
	public Row addRow(Map<String, String> cols) throws Exception {
		String key = cols.get(pk);
		if(rows.containsKey(key)) throw new Exception("Primary Key Violation");
		Row r = new Row(cols);
		rows.put(key, r);
		return r;
	}
	// Query supporting only on index key.
	// TODO : Extend it to query non index keys
	public Row query(TableFilter t) throws Exception {
		if(!t.filter.containsKey(pk)) throw new Exception("Index Key Column is missing");
		if(rows.containsKey(t.filter.get(pk))) return rows.get(t.filter.get(pk));
		else return new Row(new HashMap<>());
	}
	
}

class Row {
	Map<String, String> cols;
	Row(Map<String, String> cols) {
		// check all the columns are in the cols
		// create RowBuilder to build the cols as per the data types
		this.cols = cols;
	}
	@Override
	public	String toString() {
		String res = "";
		for(Map.Entry<String, String> col : cols.entrySet()) {
			res += "  " + col.getKey() + " : " + col.getValue();
		}
	return res;	
	}
}

class TableFilter {
	Map<String, String> filter;
	
	private TableFilter(Builder b) {
		this.filter = b.filter;
	}
	
	static final class Builder {
		Map<String, String> filter;
		Builder() {
			filter = new HashMap<>();
		}
		Builder addFilter(String colName, String colValue) {
			filter.put(colName, colValue);
			return this;
		}
		TableFilter build() {
			return new TableFilter(this);
		}
		
	}
	
}



