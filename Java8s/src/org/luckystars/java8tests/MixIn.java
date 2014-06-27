package org.luckystars.java8tests;


/**
 * java 8提供了接口的默认实现
 * @author xuechong
 */
public class MixIn {

	public static void main(String[] args) {
		Print p1 = new Print(){};
		p1.printSomeThing("p1");
		
		Print p2 = new Print() {
			@Override
			public void printSomeThing(String str) {
				System.out.println("new impl print:" + str);
			}
		};
		
		p2.printSomeThing("p2");
	}
	
}


interface Print{
	default void printSomeThing(String str){
		System.out.println(str);
	}
}


