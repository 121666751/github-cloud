package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.link;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkX {

	public static void main(String[] args) {
		List<String> linkedList = new LinkedList<String>();
		linkedList.add("It");
		linkedList.add("is");
		linkedList.add("a");
		linkedList.add("door");
		linkedList.add("yes");
		linkedList.add("or");
		linkedList.add("no");
		linkedList.add("?");
		
//		String third = linkedList.get(3);
		print(linkedList);
		linkedList.add(7, "!");
		print(linkedList);
		linkedList.set(7, "?");
		print(linkedList);
		List<String> list = new ArrayList<String>(4);
		list.add("It");
		list.add("is");
		list.add("no");
		list.add("a");
		list.add("door");
		list.add("yes");
		list.add("or");
		list.add("?");
		System.out.println(linkedList.containsAll(list));
		
//		SinglyLinkedList<Integer> slist = new SinglyLinkedList<Integer>();
		System.out.println(300000000/4723228);
	}
	/**
	 * @author LiuTao @date 2020年1月16日 下午4:13:41 
	 * @Title: hanoi 
	 * @Description: TODO(Describe) 
	 * @param n 盘子的数目
     * @param origin 源座
     * @param assist 辅助座
     * @param destination 目的座
	 */
	public static void hanoi(int n, char origin, char assist, char destination) {
        if (n == 1) {
            move(origin, destination);
        } else {
            hanoi(n - 1, origin, destination, assist);
            move(origin, destination);
            hanoi(n - 1, assist, origin, destination);
        }
    }
	//
    private static void move(char origin, char destination) {
        System.out.println("Direction:" + origin + "--->" + destination);
    }
	/**
	 * @author LiuTao @date 2020年1月13日 上午9:29:09 
	 * @throws ParseException 
	 * @Title: LinkX 
	 * @Description: 链表
	 */
	public static void LinkY() throws ParseException{
		Long l = System.currentTimeMillis();
		System.out.println(l);
		Date date = new Date(1471883254888095768L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
		System.out.println(sdf.format(date));
		System.out.println(sdf.parse("10000-12-31 23:59:59"));
		Long year = 99999L;
		Long mouth = 12L;
		Long day = 31L;
		Long hour = 23L;
		Long minut = 59L;
		Long second = 59L;
		Date d = sdf.parse(year+""+mouth+""+day+""+hour+""+minut+""+second);
		
		Long timeString = year*365*24*60*60*1000 +mouth*30*24*60*60*1000+day*24*60*60*1000+hour*60*60*1000+minut*60*1000+second*1000;
		System.out.println(timeString);
//		System.out.println(Calendar );
		System.out.println(d.getTime());
		List<Integer> linkedList = new LinkedList<Integer>();
		linkedList.add(12);
		List<Integer> arrayList = new ArrayList<Integer>();
		List<Integer> listTemp = new ArrayList<Integer>();
		arrayList.add(14);
		arrayList.add(24);
		arrayList.add(34);
		arrayList.add(34);
		arrayList.add(44);
		listTemp.add(14);
		listTemp.add(44);
		System.out.println(arrayList.toString());
//		list.f
		arrayList.remove(3);
		System.out.println(arrayList.toString());
	}
	
	public static void print(List<String> list){
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		
//		iterator.
		System.out.println();
	}
}
