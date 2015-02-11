package org.luckystars.java8tests.new_in7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class InvokeDynamicTests {
	public static void main(String[] args) throws Throwable{
		Object x, y; String s; int i;
		MethodType mt; MethodHandle mh;
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		// mt is (char,char)String
		mt = MethodType.methodType(String.class, char.class, char.class);
		mh = lookup.findVirtual(String.class, "replace", mt);
		s = (String) mh.invokeExact("daddy",'d','n');
		System.out.println(s);
		// invokeExact(Ljava/lang/String;CC)Ljava/lang/String;
		// weakly typed invocation (using MHs.invoke)
		s = (String) mh.invokeWithArguments("sappy", 'p', 'v');
		System.out.println(s);

	}
}
