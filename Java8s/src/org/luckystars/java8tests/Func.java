package org.luckystars.java8tests;



public class Func {
	public static void main(String[] args) {
		beforeJava8();
		newFeature();
	}
	
	static void beforeJava8(){
		Func1 func = new Func1() {
			@Override
			public void dosomeThing() {
				System.out.println("The old style");
			}
		};
		FuncWithParam pa = new FuncWithParam() {
			@Override
			public String dosomeThing(String a, String b) {
				String result  = a + b;
				System.out.println("old param :" + result );
				return result;
			}
		};
		
		func.dosomeThing();
		
		
	}
	
	
	static void newFeature(){
		
		Func1 func = ()->{System.out.println("new style");};
		func.dosomeThing();
		
		FuncWithParam pa1 = (String a,String b)->{
			String result  = a + b;
			System.out.println("old param :" + result );
			return result;
		};
		
		FuncWithParam pa = (a,b)->{//类型推断
			String result  = a + b;
			System.out.println("old param :" + result );
			return result;
		};
		
		//OldInterface old = ()->{System.out.println("aaa");};这会报错
	}
}
/**
 * 这是一个 函数式接口
 * @author xuechong
 */
interface Func1{
	void dosomeThing();
}
/**
 * 带参数和返回值的
 * @author xuechong
 */
@FunctionalInterface
interface FuncWithParam {
	String dosomeThing(String a,String b);
}

//@FunctionalInterface这样是不行的
interface MixInInterface{
	default void mix(){
		//有默认实现的接口也不可以成为函数接口
	};
}



//@FunctionalInterface 
//函数式接口。如果一个接口定义
//!!!唯一 一个 !!!
//抽象方法，那么这个接口就成为函数式接口。多个方法会报错
interface OldInterface{
	
	void doSomeOld();
	void doSomeOld2();
}




//@FunctionalInterface
//这样也是不行的
abstract class FuncNotRight{
	
	abstract void someFunction();
	
	void impleted(){System.out.println("imple");}
}






