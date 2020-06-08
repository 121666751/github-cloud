package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.sort;

public class SortX {
	public static void main(String[] args) {
//		ShellSortAPI();
		PartitionSortAPI();
	}
	
	/**
	 * @Author LiuTao @Date 2020年6月5日 上午11:14:03 
	 * @Title: ShellSortAPI 
	 * @Description: 希尔排序
	 */
	public static void ShellSortAPI(){
		int maxSize = 1000;
		ArrayShell arr = new ArrayShell(maxSize);
		for (int i = 0; i < maxSize; i++) {
			long value = (long) (Math.random() * 99);
			arr.insert(value);
		}
		arr.display();
		arr.shellSort();
		arr.display();
	}
	/**
	 * @Author LiuTao @Date 2020年6月5日 下午1:44:54 
	 * @Title: PartitionSortAPI 
	 * @Description: 划分
	 */
	public static void PartitionSortAPI(){
		int maxSize = 16;
		ArrayPartition arr = new ArrayPartition(maxSize);
		for (int i = 0; i < maxSize; i++) {
			long value = (long) (Math.random() * 199);
			arr.insert(value);
		}
		arr.display();
		long pivot = 99;
		System.out.print("Piovt is " + pivot);
		int size = arr.size();
		int partDex = arr.partitionSort(0, size - 1, 99);
		System.out.println(", Partition is at index " + partDex);
		arr.display();
	}
}

/**
 * @Author LiuTao @Date 2020年6月5日 下午1:44:41
 * @ClassName: ArrayParent 
 * @Description: TODO(Describe)
 */
class ArrayParent{
	protected long[] theArray;
	protected int nElems;
	public void insert(long value) {
		theArray[nElems] = value;
		nElems++;
	}
	public void display() {
		System.out.print("Array = [ ");
		for (int i = 0; i < nElems; i++) {
			System.out.print(theArray[i] + " ");
		}
		System.out.print("]");
		System.out.println();
	}
	public int size(){
		return nElems;
	}
}

/**
 * @Author LiuTao @Date 2020年6月5日 上午11:13:58
 * @ClassName: ArrayShell 
 * @Description: TODO(Describe)
 */
class ArrayShell extends ArrayParent{
	public ArrayShell(int max){
		theArray = new long[max];
		nElems = 0;
	}
	public void shellSort(){
		int inner, outer;
		long temp;
		int h = 1;
		while(h <= nElems/3)
			h = h * 3 + 1;
		while(h > 0){
			for(outer = h; outer < nElems; outer++){
				temp = theArray[outer];
				inner = outer;
				while(inner > h - 1 && theArray[inner - h] >= temp){
					theArray[inner] = theArray[inner - h];
					inner -= h;
				}
				theArray[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}
}
/**
 * @Author LiuTao @Date 2020年6月5日 上午11:13:58
 * @ClassName: ArrayShell 
 * @Description: TODO(Describe)
 */
class ArrayPartition extends ArrayParent{
	public ArrayPartition(int max){
		theArray = new long[max];
		nElems = 0;
	}
	public int partitionSort(int left, int right, long pivot){
		int leftPtr = left - 1;
		int rightPtr = right + 1;
		while(true){
			while(leftPtr < right && theArray[leftPtr] < pivot)
				++leftPtr;
			while(rightPtr > left && theArray[rightPtr] > pivot)
				--rightPtr;
			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		return leftPtr;
	}
	public void swap(int dex1, int dex2){
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
		
	}
}
