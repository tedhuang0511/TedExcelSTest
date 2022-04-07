package main.java.ExcelUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Test3 {

	public static void main(String[] args) {
		String sql = "select * from CUSTOMERS";
		String presql = sql + " LIMIT %d ,%d";
		String sql2 = String.format(presql, 10, 10);
		System.out.println(sql2);
	}

}
