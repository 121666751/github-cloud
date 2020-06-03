package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.stack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class QueueX {
	/**
	 * @author LiuTao @date 2020年1月10日 上午10:09:24 
	 * @Title: QueueX 
	 * @Description: 队列------优先级队列
	 */
	public QueueX(){
		Queue<Integer> queue = new Queue<Integer>() {
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<Integer> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addAll(Collection<? extends Integer> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean add(Integer e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean offer(Integer e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Integer remove() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer poll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer element() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer peek() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		queue.add(23);
	}	
}
