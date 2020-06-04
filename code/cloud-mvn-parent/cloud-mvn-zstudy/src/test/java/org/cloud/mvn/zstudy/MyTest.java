package org.cloud.mvn.zstudy;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("(");
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		for (String str : list) {
			sb.append(str+"|");
		}
		sb.deleteCharAt(sb.length() - 1).append(")$");
		
		System.out.println(sb.toString());
		
		String s = "70000_001_29379_212";
		System.out.println(s.substring(0, 9));
	}
}
