package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ArrayX {
	/**
	 * @author LiuTao @date 2020年1月10日 下午3:49:19 
	 * @Title: PostfixExpressioni 
	 * @Description: 解析算数表达式--后缀表达式及其计算
	 */
	public static void PostfixExpression(){
//		int x = 5+4/2+3-1+2*3;// --5423123/*++-+
		/**
		 * 1.定义:
		 * 后缀表达式,指的是不包含括号,----运算符放在两个运算对象的后面,
		 * 所有的计算按运算符出现的顺序,严格从左向右进行(不再考虑运算符的优先规则)
		 * 例如 5+(2+3)*4 转换为后缀表达式为 5 2 3 + 4 * +
		 * 2.计算:
		 * 后缀表达式的计算
		 * 从左向右扫描,遇到数字压栈,遇到操作符,弹出栈顶的两个元素,先弹出的元素在右边,后弹出来的在左边,
		 * 进行计算后,将结果压栈,再往后扫描,直到扫描结束,输出栈顶元素,即为最终结果
		 * 3.例如:
		 * 后缀表达式"15 2 3 + 4 * +":
		 * 从左往右,
		 * 15压栈,2压栈,3压栈,遇到+号,弹出3 2,计算2+3=5,
		 * 将5压栈,此时栈为15 5,将4压栈,遇到*号,弹出4 5,计算5*4=20,
		 * 将20压栈 此时栈为15 20,遇到+号,计算15+20=35,将35压栈,
		 * 扫描结束,输出35
		 * 
		 * 将A+B*(C-D)转换成后缀表达式
		 * 		ABCD-*+
		 */
		String str = "15 2 3 + 4 * +";//Postfix expression 后缀表达式
		String[] array = str.split(" ");
		Stack<Integer> stack = new Stack<Integer>();
		Integer x,y,temp;
		for (int i = 0; i < array.length; i++) {
			String string = array[i];
			switch (string) {
			case "+":
				y = stack.pop();
				x = stack.pop();
				temp = x + y;
				stack.push(temp);
				break;
			case "-":
				y = stack.pop();
				x = stack.pop();
				temp = x - y;
				stack.push(temp);
				break;
			case "*":
				y = stack.pop();
				x = stack.pop();
				temp = x * y;
				stack.push(temp);
				break;
			case "/":
				y = stack.pop();
				x = stack.pop();
				temp = x / y;
				stack.push(temp);
				break;
				
			default:
				temp = Integer.valueOf(string);
				stack.push(temp);
				break;
			}
		}
		System.out.println(stack.peek());
	}
	
	/**
	 * @author LiuTao @date 2019年12月31日 上午9:51:45 
	 * @Title: BubbleSort 
	 * @Description: 冒泡排序
	 */
	public static void BubbleSort(int[] arr){
		System.out.println("最初的数组是:"+print(arr));
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if(arr[j] > arr[j+1]){
					int c = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = c;
				}
			}
			System.out.println("第 "+(i+1)+"次冒泡后:"+print(arr));
		}
		System.out.println("数组冒泡排序:"+print(arr));
//		print(arr);
	}
	
	/**
	 * @author LiuTao @date 2019年12月31日 上午9:57:27 
	 * @Title: SelectionSort 
	 * @Description: 选择排序
	 */
	public static void SelectionSort(int[] arr) {
		System.out.println("最初的数组是:"+print(arr));
		for (int i = 0; i < arr.length-1; i++) {
			int min = i;
			for (int j = i; j < arr.length-1; j++) {
				if (arr[min] > arr[j + 1]) {
					min = j + 1;
                }
			}
			swap(arr, i, min);
			System.out.println("第 "+(i+1)+"次选择后:"+print(arr));
		}
		System.out.println("数组选择排序:"+print(arr));
	} 
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	/**
	 * @author LiuTao @date 2020年1月3日 下午3:00:46 
	 * @Title: InsertionSort 
	 * @Description: 插入排序 ------对象排序
	 */
	public static void InsertionSort(int[] arr) {
		System.out.println("最初的数组是:" + print(arr));
		for (int i = 1; i < arr.length; i++) {
			int preIndex = i - 1;
			int current = arr[i];
			while (preIndex >= 0 && arr[preIndex] > current) {
				arr[preIndex + 1] = arr[preIndex];
				preIndex--;
			}
			arr[preIndex + 1] = current;
			System.out.println("第 "+(i)+"次插入后:"+print(arr));
		}
		System.out.println("排序后数组是:" + print(arr));
	}
	
	public static String print(int[] arrary){
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < arrary.length; i++) {
			if(i == 0)
				sb.append(arrary[i]);
			else
				sb.append(", "+arrary[i]);
		}
		sb.append("]");
		return sb.toString();
	}
	/**
	 * @author LiuTao @date 2020年1月10日 上午11:21:54 
	 * @Title: Test 
	 * @Description: TODO(Describe)
	 */
	public static void Test(){
		int[] arrary = new int[10];
		arrary[0] = 12;
		arrary[1] = 4;
		arrary[2] = 22;
		arrary[3] = 15;
		arrary[4] = 24;
		arrary[5] = 23;
		arrary[6] = 8;
		arrary[7] = 6;
		arrary[8] = 21;
		arrary[9] = 13;
		System.out.println(arrary.length);
		print(arrary);
		for (int i = 0; i < arrary.length; i++) {
			for (int j = 0; j < arrary.length - 1; j++) {
				int o;
				if(arrary[i] > arrary[i+1]){
					o = arrary[i+1];
					arrary[i+1] = arrary[i];
					arrary[i] = o;
				}
			}
			print(arrary);
		}
		print(arrary);
	}

	public static void test001() {
		String str = "";
		StringBuilder result = new StringBuilder();
		if (str != null && str.length() > 0) {
			// 将第一个字符处理成大写
			result.append(str.substring(0, 1)/* .toLowerCase() */);
			// 循环处理其余字符
			for (int i = 1; i < str.length(); i++) {
				String s = str.substring(i, i + 1);
				// 在大写字母前添加下划线除首字母大写外
				if (s.equals(s.toUpperCase())
						&& !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s/* .toLowerCase() */);
			}
		}
		System.out.println(result.toString().toLowerCase());
	}
}



/**
 * @author LiuTao @date 2020年1月20日 上午9:17:28
 * @ClassName: postfix 
 * @Description: TODO(Describe)
 */
class postfix {
	
	/**
	 * @author LiuTao @date 2020年1月10日 下午4:44:05 
	 * @Title: outPostfix 
	 * @Description: TODO(Describe) 
	 * @param infix
	 * @return
	 */
    public static String[] outPostfix(String[] infix){
        String[] postfix = new String[100];
        int index = 0;
        int bracket = 0; //标记括号的对数
        Map<String, Object> operatorMap = new HashMap<String, Object>(); // 用hashmap存储每个运算符的优先度
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 2);
        operatorMap.put("/", 2);
        Stack<String> postfixStack = new Stack<>(); //用于存储后缀算法的运算符
        for (int i = 0; i < infix.length && infix[i] != null; i++) {
            String current = infix[i];
            if (current.charAt(0) >= '0' && current.charAt(0) <= '9'){ //遇到数字直接放入后缀数组
                postfix[index++] = current;
            }else {
                if (postfixStack.isEmpty()){ //保证后缀运算符栈不为空
                    postfixStack.push(current);
                }
                /**
                 * 如果括号已经全部处理,而且当前字符不是括号,
                 * 后缀运算符栈顶不是括号,并且栈顶运算符优先度小于当前运算符优先度,
                 * 将当前遍历运算符后边的数字放入后缀数组中,再将当前运算符放入后缀数组中。
                 */
                else if (bracket == 0 && !isBracket(current) &&
                        !isBracket(postfixStack.peek()) &&
                        Integer.parseInt(String.valueOf(operatorMap.get(postfixStack.peek()))) < Integer.parseInt(String.valueOf(operatorMap.get(current)))){
                    postfix[index++] = infix[i + 1];
                    postfix[index++] = infix[i];
                    i++;
                }
                /**
                 * 同理 栈顶运算符优先度大于等于当前运算符优先度,
                 * 将栈顶运算符放入后缀数组中,再将当前遍历的运算符放入栈中
                 */
                else if (bracket == 0 && !isBracket(current) &&
                        !isBracket(postfixStack.peek()) &&
                        Integer.parseInt(String.valueOf(operatorMap.get(postfixStack.peek()))) >= Integer.parseInt(String.valueOf(operatorMap.get(current)))){
                    postfix[index++] = postfixStack.pop();
                    postfixStack.push(current);
                }
                else {
                    if (current.equals("(")) bracket++; // 若遇到左括号,bracket加1
                    postfixStack.push(current);
                    if (postfixStack.peek().equals(")")){ //遇到右括号,bracket减1,同时将栈中括号间的运算符出栈
                        bracket--;
                        postfixStack.pop();
                        while (!postfixStack.peek().equals("(")){
                            postfix[index++] = postfixStack.pop();
                        }
                        postfixStack.pop();
                    }
                }
            }
        }
        while (!postfixStack.isEmpty()) postfix[index++] = postfixStack.pop();
        return postfix;
    }
    public static double result(String[] postfix){
        double result = 0;
        Stack<Double> computeStack = new Stack<Double>();
        for (int i = 0; i < postfix.length && postfix[i] != null; i++) {
            String current = postfix[i];
            //当前字符为数字,则入栈
            if (current.charAt(0) >= '0' && current.charAt(0) <= '9'){
                computeStack.push(todouble(current));
            }
            //当前字符为运算符,从栈顶取出两个数字运算出结果,并放入栈中
            else {
                switch (current){
                    case "+": result = computeStack.pop() + computeStack.pop();
                    computeStack.push(result);break;
                    case "-": result = -(computeStack.pop() - computeStack.pop());
                    computeStack.push(result);break;
                    case "*": result = computeStack.pop() * computeStack.pop();
                    computeStack.push(result);break;
                    case "/": result = 1 / (computeStack.pop() / computeStack.pop());
                    computeStack.push(result);break;
                    default: break;
                }
            }
        }
        return result;
    }
    public static double todouble(String str){
        double num = 0;
        num = Double.valueOf(Integer.parseInt(str));
        return num;
    }
    public static boolean isBracket(String str){
        if (str.equals("(") || str.equals(")")) return true;
        else return false;
    }
    public static void main(String[] args){
        String[] infix = new String[]{"9", "+", "(", "3",  "-", "1", ")", "*", "3", "+", "10", "/", "2"};
        String[] infix1 = new String[100];
        int i = 0;
        Scanner in=new Scanner(System.in);
        while (in.hasNext()){
            infix1[i++] = in.nextLine();
        }
        in.close();
        infix = outPostfix(infix1);
        for (i = 0; i < infix.length && infix[i] != null; i++) {
            System.out.print(infix[i] + " ");
        }
        System.out.print("\n");
        System.out.println(result(infix));
    }
    
    public static void xiPaiSuanFa(){
//    	for (var i = this.rowCount * this.colCount - 1; i >= 0 ; i--){
//    		  var iX = parseInt(i / this.colCount);
//    		  var iY = i % this.colCount;
//
//    		  var randNumber = this.rangeRandom(0, i + 1);
//
//    		  var randX = parseInt(randNumber / this.colCount);
//    		  var randY = randNumber % this.colCount;
//
//    		 //交换两个位置
//    		  var temp = tmpMineMap[iX][iY];
//    		  tmpMineMap[iX][iY] = tmpMineMap[randX][randY];
//    		  tmpMineMap[randX][randY] = temp;
//    		}
    }
}