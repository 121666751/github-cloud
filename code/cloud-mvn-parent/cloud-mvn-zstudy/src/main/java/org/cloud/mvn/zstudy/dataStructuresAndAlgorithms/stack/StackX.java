package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.stack;

import java.util.Stack;
/**
 * @author LiuTao @date 2020年1月10日 下午3:49:13 
 * @Title: StackX 
 * @Description: 栈
 */
public class StackX<E> {
	
	public static void main(String[] args){
		/**
		 * 栈
		 * Stack<E> 这里的E表示为对象的类
		 */
		Stack<Long> stack = new Stack<Long>();
		Long[] array = new Long[4];
		stack.add(1L);
		stack.add(2, 2L);
		stack.addAll(stack);
		stack.addAll(5, stack);
		stack.addElement(9L);
		stack.capacity();
		stack.clear();
		stack.clone();
		stack.contains(2L);
		stack.containsAll(stack);
		stack.copyInto(array);
		stack.elementAt(2);
		stack.elements();
		stack.empty();
		stack.ensureCapacity(3);
		stack.forEach(null);
		stack.iterator();
		stack.peek();
		stack.pop();
		stack.push(10L);
		stack.remove(2);
		stack.remove(array);
	}
	
	public static void StackY(){
		Stack<Integer> stack = new Stack<Integer>();
//		stack.setSize(10);
		stack.push(20);
		stack.push(21);
		stack.push(22);
		stack.push(23);
		stack.push(24);
		stack.push(25);
		stack.push(26);
		stack.push(27);
		stack.push(28);
		stack.push(29);
		stack.push(30);
		
		for (Integer integer : stack) {
			System.out.print(integer+" ");
		}
	}
}
