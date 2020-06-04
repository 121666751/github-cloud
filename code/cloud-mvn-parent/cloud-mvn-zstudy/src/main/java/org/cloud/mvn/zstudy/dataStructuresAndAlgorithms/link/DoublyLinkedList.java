package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.link;

/**
 * @ClassName: DoublyLinkedList 
 * @Description: 双向链表 
 * @param <E>
 */
public class DoublyLinkedList<E> {
	
	private Node<E> first;
	private Node<E> last;
	
	private int size = 0;
	
	public void addLast(E e) {
		if(last==null) {
			last = new Node<E>(last,e,null);
			first = last;
		}else {
			Node<E> newNode = new Node<E>(last,e,null);
			last.next = newNode;
			last = newNode;
		}
		size++;
	}
	
	public E remove(int index) {
		Node<E> x = node(index);
		Node<E> prev = x.prev;
		Node<E> next = x.next;
		E item = x.item;
		
		if(prev == null) {
			next.prev = null;
			first = next;
		}else {
			prev.next = next;
		}
		
		if(next == null) {
			prev.next = null;
			last = prev;
		}else {
			next.prev = prev;
		}
		size--;
		return item;
	}
	
	
	public E getFirst() {
		return first.item;
	}
	
	public E getLast() {
		return last.item;
	}
	
	public Node<E> node(int index){
		
		if(index < (size>>1)) {
			Node<E> node = first;
			for(int i=0;i<index;i++)
				node = node.next;
			return node;
		}else {
			Node<E> node = last;
			for(int i=size-1;i>index;i--)
				node = node.prev;
			return node;
		}
	}
	
	public E getIndex(int index) {
		return node(index).item;
	}

	public void show() {
		Node<E> p = first;
		for(int i=0;i<size;i++) {
			System.out.println(p.item);
			p = p.next;
		}
	}
	
	private static class Node<E>{
		E item;
		Node<E> next;
		Node<E> prev;
		Node (Node<E> prev, E item, Node<E> next){
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	
	
	public static void main(String args[]) {
//		SinglyLinkedList<String> list = new SingleList<String>();
//		list.addLast("first");
//		list.addLast("second");
//		list.addLast("third");
//		list.addLast("last");
//		list.show();
//		System.out.println("------------------");
//		list.remove(2);
//		list.show();
//		System.out.println("------------------");
//		System.out.println(list.getFirst());
//		System.out.println(list.getLast());
	}
}
