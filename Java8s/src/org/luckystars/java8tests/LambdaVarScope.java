package org.luckystars.java8tests;

import java.util.function.Consumer;

public class LambdaVarScope {

	    public int x = 0;

	    class FirstLevel {

	        public int x = 1;

	        void methodInFirstLevel(int x) {
	            
	            // The following statement causes the compiler to generate
	            // the error "local variables referenced from a lambda expression
	            // must be final or effectively final" in statement A:
	            //
	            // x = 99;
	            
	            Consumer<Integer> myConsumer = (y) -> 
	            {
	                System.out.println("x = " + x); // Statement A//result is 23
	                System.out.println("y = " + y);
	                System.out.println("this.x = " + this.x);//result is 1
	                System.out.println("LambdaScopeTest.this.x = " +
	                		LambdaVarScope.this.x);
	            };

	            myConsumer.accept(x);

	        }
	    }

	    public static void main(String... args) {
	    	LambdaVarScope st = new LambdaVarScope();
	    	LambdaVarScope.FirstLevel fl = st.new FirstLevel();
	        fl.methodInFirstLevel(23);
	    }
}
