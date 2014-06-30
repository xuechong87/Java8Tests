package org.luckystars.java8tests;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;



/***
 * You can obtain the names of the formal parameters of any method or constructor <br>
 * with the method java.lang.reflect.Executable.getParameters(). <br>
 * (The classes Method and Constructor extend the class Executable <br>
 * and therefore inherit the method Executable.getParameters().)<br>
 * 现在你可以通过 java.lang.reflect.Executable.getParameters()方法<br>
 * 获得任何方法或构造函数的参数<br>
 * 由于.class文件默认没有保存参数名称的信息.所以想使用此特性时<br>
 * 请记住在编译时给编译器加上 -parameters 参数
 * 
 * @author xuechong
 */
public class ParameterReflection {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		showParams();
	}
	
	
	static void showParams() throws NoSuchMethodException, SecurityException{
		
		for (Executable method : ParameterReflection.class.getDeclaredMethods()) {
			for (Parameter param : method.getParameters()) {
				System.out.println(param.getName());
				System.out.println(param.getType());
			}
		}
		
	}
	
	static void testMethod1(String paramStr,Integer paramInt){
		
	}
	
	
}
