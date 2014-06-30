package org.luckystars.java8tests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class TypeAnnotation {
	public static void main(String[] args) {
		
		
		@NotNull String str =  null;
		System.out.println(str);
	}
	
	
}


@Target(value={ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
@interface NotNull{
	
}

