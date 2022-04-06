package ExcelUtil;

import java.util.ArrayList;
import java.util.List;

public interface DataHelp {
	default List<String[]> getData(){
		List<String[]> dataList = new ArrayList<String[]>();
		String[] row1 = new String[] {"1", "cody", "23"};
		String[] row2 = new String[] {"2", "ted", "28"};
		String[] row3 = new String[] {"3", "rex", "28"};
		dataList.add(row1);
		dataList.add(row2);
		dataList.add(row3);
		
		return dataList;
	}
}
