package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.recursion;

public class Recursion {
	public static void main(String[] args) {
//		int total = Total(20, 0, 0);
//		System.out.println(total);
//		System.out.println(tuzhi(13));
		System.out.println(power(2,31));//256
		System.out.println(65536*65536);
	}
	
	/**
	 * @author LiuTao @date 2020年1月19日 下午4:54:46 
	 * @Title: tuzhi 
	 * @Description: TODO(Describe)
	 * 一般而言,兔子在出生两个月后,就有繁殖能力,一对兔子每个月能生出一对小兔子来.如果所有兔子都不死,那么一年以后可以繁殖多少对兔子?
	 * 分析如下:
	 * 第一个月小兔子没有繁殖能力,所以还是一对；
	 * 两个月后,生下一对小兔子,总数共有两对；
	 * 三个月以后,老兔子又生下一对,因为小兔子还没有繁殖能力,总数共是三对；
	 *   ……
	 * 依次类推可以列出下表:
	 * 可以得出:
	 * 斐波纳契数列,又称黄金分割数列,指的是这样一个数列：1、1、2、3、5、8、13、21、……
	 */
	public static int tuzhi(int m) {
		if(m<2)
			return 1;
		else 
			return tuzhi(m - 1)+tuzhi(m - 2);
		
	}
	/**
	 * @author LiuTao @date 2020年1月19日 下午4:43:31 
	 * @Title: Total 
	 * @Description: 
	 * 一个人买汽水,一块钱一瓶汽水,三个瓶盖可以换一瓶汽水,两个空瓶可以换一瓶汽水,问20块钱可以买多少汽水?
	 * 首先分析:
	 * 一瓶汽水:1soda = 1cap +1bottle;
	 * 两个空瓶:2bottle = 1soda;
	 * 三个瓶盖:3cap = 1soda;
	 * 那么递归退出条件就是: cup<3&&bottle<2 &&soda<1
	 * 因为在这个过程中,三个元素soda, cap, bottle都有连续的变化,所以递归调用时要将三个参数都传进去
	 * @param Total
	 * @param caps
	 * @param bottles
	 * @return
	 */
	public static int Total(int Total, int caps, int bottles) {
		caps %= 3;
		bottles %= 2;
		caps += Total;
		bottles += Total;
		if (caps < 3 && bottles < 2) {
			return Total;
		} else {
			return Total(caps / 3 + bottles / 2, caps, bottles) + Total;
		}
	}
	
	/**
	 * @author LiuTao @date 2020年1月16日 下午2:09:45 
	 * @Title: Recursion 
	 * @Description: 三角数字
	 */
	public static int triangle(int n){
		if(n == 1)
			return n;
		else
			return n + triangle(n-1);
	}
	/**
	 * @author LiuTao @date 2020年1月16日 下午2:22:02 
	 * @Title: factorial 
	 * @Description: 阶乘 
	 * @return
	 */
	public static int factorial(int n){
		if(n == 0)
			return 1;
		else
			return n * factorial(n-1);
	}
	/**
	 * @author LiuTao @date 2020年1月19日 下午5:32:54 
	 * @Title: power 
	 * @Description: 乘方 
	 * @return
	 */
	public static int power(int m, int n) {
		if(m < 2 || n == 0)
			return 1;
		else 
			return m * power(m,  n-1);
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
}
