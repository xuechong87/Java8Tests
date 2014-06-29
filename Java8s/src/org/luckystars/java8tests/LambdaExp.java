package org.luckystars.java8tests;

import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExp {
	 
	public static void main(String[] args) {
		syntax();
	}
	
	
	static void syntax(){
		
		Predicate<String> strLong = s -> s.length()>10;//只有一个参数时可以不用括号
		Predicate<String> strLong2 = (s) -> s.length()>10;
		
		
		Predicate<String> strLong3 = (s) -> {return s.length()>10;};
		Predicate<String> strLong4 = (String s) -> {return s.length()>10;};
		System.out.println(strLong.test("asddddddddddd"));
		
		TwoParam strLong5 = (s1,s2) -> s1 + s2;//多个参数必须用括号
		
	}  
	
	
	static void varScope(){
		int x = 10;
		
		Function<Integer, Integer> pulsX = num-> x+num;
	}	
	
}

interface TwoParam{
	String doSome(String str1,String str2);
}
