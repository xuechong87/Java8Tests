package org.luckystars.java8tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
		jdkFunctionalInterface();
		methodReference();
	}
	
	
	
	/***
	 * There are four kinds of method references:<br>
	 * 共有四种方法引用<br><br>
	 * 
	 *	<b>Reference to a static method :</b><br> 
	 *	<b>引用静态方法 :</b><br> 
	 *	<code>ContainingClass::staticMethodName</code><br><br>
		
	 *	<b>Reference to an instance method of a particular object:</b> <br>
	 *	<b>引用特定对象的实例方法:</b> <br>
	 *	<code>ContainingObject::instanceMethodName</code><br><br>
		
	 *	<b>Reference to an instance method of an arbitrary object of a particular type:</b><br> 
	 *	<b>引用一个特定类型的实例方法:</b><br> 
	 *	<b>:</b><br> 
	 *	<code>ContainingType::methodName </code><br><br>
		
	 *	<b>Reference to a constructor:</b><br>
	 *	<b>引用构造函数:</b><br>
	 *	<code>ClassName::new</code><br><br>
	 */
	static void methodReference(){
		//静态方法
		Function<Integer, String> strValue = String::valueOf;
		System.out.println(strValue.apply(0xffff));
		
		
		
		//引用特定对象的实例方法
		List<String> list = new ArrayList<>();
		Predicate<String> listAdd = list::add;
		listAdd.test("abc");
		listAdd.test("123");
		listAdd.test("xyz");
		System.out.println(list);
		
		
		
		//引用一个特定类型的实例方法:
		list.sort(String::compareTo);
		System.out.println(list);
		
		//这段相当于
		classic:{
			Collections.sort(list,new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			System.out.println(list);
		}

		
	
		//引用构造函数
		Function<char[],String> strCons = String::new;
		String abc = strCons.apply(new char[]{'a','b','c'});
		System.out.println(abc);
		
	}
	
	
	
	/**
	 * 在java8定义了几个标准函数引用类型 他们都是函数接口(@FunctionalInterface)<br>
	 * 在java.util.function包下的:<br>
	 * {@link java.util.function.Consumer} <br>
	 * {@link java.util.function.Supplier} <br>
	 * {@link java.util.function.Predicate} <br>
	 * {@link java.util.function.Function} <br>
	 */
	static void jdkFunctionalInterface(){
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
		Consumer<String> printStrConsumer1 = (str)->System.out.println(str);
		
		//classic style
		Consumer<String> printStrConsumer2 = new Consumer<String>() {@Override
			public void accept(String t) {
				System.out.println(t);
			}	
		};
		
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
