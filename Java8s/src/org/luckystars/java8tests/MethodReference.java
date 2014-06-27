package org.luckystars.java8tests;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * 对方法的引用
 * @author xuechong
 */
public class MethodReference {

	public static void main(String[] args) {
		jdkMethodReference();
		
	}
	
	
	/**
	 * 在java8定义了几个标准函数引用类型 他们都是函数接口(@FunctionalInterface)<br>
	 * 在java.util.function包下的:<br>
	 * {@link java.util.function.Consumer} <br>
	 * {@link java.util.function.Supplier} <br>
	 * {@link java.util.function.Predicate} <br>
	 * {@link java.util.function.Function} <br>
	 */
	static void jdkMethodReference(){
		consumer();
		supplier();
		predicate();
		function();
	}
	
	/**
	 * Consumer&lt;T&gt; <br>
	 * accept(T t) 消费一个T类型对象 返回void
	 */
	static void consumer(){
		//use method reference
		Consumer<String> printStrConsumer = System.out :: println;
		
		//lambda style
		//Consumer<String> printStrConsumer1 = (str)->System.out.println(str);
		
		//classic style
		//Consumer<String> printStrConsumer2 = new Consumer<String>() {@Override
		//	public void accept(String t) {
		//		System.out.println(t);
		//	}	
		//};
		
		printStrConsumer.accept("printStrConsumer speaking");
	}
	
	/**
	 * Supplier&lt;T&gt;<br>
	 * T get(); 生产一个T类型对象
	 */
	static void supplier(){
		Supplier<Long> curTimeSupplier = System :: currentTimeMillis;
		long curTime = curTimeSupplier.get();
		System.out.println("supplier says:" + curTime);
	}
	
	/**
	 * Predicate&lt;T&gt;<br>
	 * boolean test(T t); 判断传入的参数是否符合条件 返回boolean类型
	 */
	static void predicate(){
		String[] bools = {"true","false"};
		String boolStr = bools[(Math.random()>0.5f)?0:1];
		
		Predicate<String> pre = Boolean::getBoolean;
		boolean value = pre.test(boolStr);
		System.out.println("predicate says:" + value);
	}
	
	/**
	 * Function&lt;T, R&gt;<br>
	 * R apply(T t); 接受T类型 返回 R类型
	 */
	static void function(){
		Function<Object, String> toStr = String::valueOf;
		String result = toStr.apply(0xFF);
		System.out.println("function says:" + result);
	}
	
	
	
	
	
	
	
	
	
}
