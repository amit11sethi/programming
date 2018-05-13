package systemdesign.inmemorydb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DBActionSimulation {

	public static void main(String[] args) throws Exception {
		Set<String> cols = new HashSet<>(Arrays.asList("EmpName","Department" ));
		Table t = new Table("Employee", "EmpId", cols );
		Map<String, String> row = new HashMap<>();
		row.put("EmpName","David");
		row.put("EmpId","1");
		row.put("Department","Sales");
		System.out.println(t.addRow(row));
		TableFilter filter = new TableFilter.Builder().addFilter("EmpId", "1").build();
		System.out.println(t.query(filter));

	}

}
