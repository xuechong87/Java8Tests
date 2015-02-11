package org.luckystars.java8tests.cglib;

import java.lang.reflect.Method;
import java.util.function.Consumer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.junit.Test;

public class Test1 {
	private static final void puts(Object x){System.out.println(x);};
	
	public static class SampleClass {
		public String test(String input) {
			return "Hello world!";
		}
	}

	public static void main(String[] args) {

	}

	@Test
	public void testFixedValue() throws Exception {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SampleClass.class);
		
		enhancer.setCallback(new FixedValue() {
			@Override
			public Object loadObject() throws Exception {
				return "Hello cglib!";
			}
		});
		SampleClass proxy = (SampleClass) enhancer.create();
		puts(proxy.test(null));
	}
	
	@Test
	public void testMethodInterceptor() throws Exception {
	  Enhancer enhancer = new Enhancer();
	  enhancer.setSuperclass(SampleClass.class);
	  enhancer.setCallback(new MethodInterceptor() {
	    @Override
	    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
	        throws Throwable {
	      if(method.getDeclaringClass() != Object.class 
	    		  && method.getReturnType() == String.class) {
	        return "Hello cglib!";
	      } else {
	        return proxy.invokeSuper(obj, args);
	      }
	    }
	  });
	  SampleClass proxy = (SampleClass) enhancer.create();
	  String testStr = proxy.test(null);
	  String toStr = proxy.toString();
	  puts(testStr);
	  puts(toStr);
	  puts(proxy.hashCode()); // Does not throw an exception or result in an endless loop.
	}
}
