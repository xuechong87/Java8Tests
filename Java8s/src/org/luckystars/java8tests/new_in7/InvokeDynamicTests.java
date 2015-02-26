package org.luckystars.java8tests.new_in7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.annotations.*;

public class InvokeDynamicTests {
	
	
	
	public static void main(String[] args)throws Throwable {
//		reflect();
//		dyn();
		Options opt = new OptionsBuilder()
        .include(".*" + InvokeDynamicTests.class.getSimpleName() + ".*")
        .forks(1)
        .build();
		new Runner(opt).run();
	}
	
	public static Long test(){
		return System.currentTimeMillis();
	}
	
	
	
	
	static Method method = null;
	static {
		try {
			method = InvokeDynamicTests.class.getDeclaredMethod("test");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	@Benchmark
	public  void reflect() throws Throwable{
		Long result = (Long) method.invoke(null);
	}
	
	static MethodHandles.Lookup lookup = MethodHandles.lookup();
	static MethodType mType = MethodType.methodType(Long.class);
	static MethodHandle mHandle = null;
	static{try {
		mHandle = lookup.findStatic(InvokeDynamicTests.class, "test", mType);
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}}
	
	@Benchmark
	public  void dyn() throws Throwable {
		Long result = (Long) mHandle.invoke();
	}
	
	
	
//	public static void main(String[] args) {
////Object x, y; String s; int i;
////MethodType mt; MethodHandle mh;
////MethodHandles.Lookup lookup = MethodHandles.lookup();
////// mt is (char,char)String
////mt = MethodType.methodType(String.class, char.class, char.class);
////mh = lookup.findVirtual(String.class, "replace", mt);
////s = (String) mh.invokeExact("daddy",'d','n');
////System.out.println(s);
////// invokeExact(Ljava/lang/String;CC)Ljava/lang/String;
////// weakly typed invocation (using MHs.invoke)
////s = (String) mh.invokeWithArguments("sappy", 'p', 'v');
////System.out.println(s);
//
//}
	
}
