package org.cloud.mvn.zstudy.javaVirtualMachine.garbageCollection;

public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	public void isAlive(){
		System.out.println("yes, I am still alive :)");
	}
	
	@Override
	protected void finalize() throws Throwable {//对象在gc判定时会执行对象的finalize()方法一次,后续不会执行
		super.finalize();
		System.out.println("finalize meheod executed!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new FinalizeEscapeGC();
		//第一次自救成功
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else {
			System.out.println("no, I am dead :(");
		}
		System.out.println(SAVE_HOOK);
		//第二次自救失败
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else {
			System.out.println("no, I am dead :(");
		}
		System.out.println(SAVE_HOOK);
	}
}
