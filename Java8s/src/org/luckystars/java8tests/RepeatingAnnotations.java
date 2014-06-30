package org.luckystars.java8tests;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RepeatingAnnotations {

	
	public static void main(String[] args) {
		repeatAnno();
	}
	
	static void  repeatAnno(){
		ConfigGroup confGroup = AnnoTestClass.class.getAnnotation(ConfigGroup.class);
		for (Config conf : confGroup.value()) {
			System.out.println(conf.value());
		}		
		
	}
	
}


@Config("conf1")
@Config("conf2")
@Config("conf3")
class AnnoTestClass{
	
	
}



@Documented
@Target(ElementType.TYPE)
@Repeatable(ConfigGroup.class)//ConfigGroup必须存在Config[] value();
@Retention(RetentionPolicy.RUNTIME)
@interface Config{
	String value() default "default";
}



@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ConfigGroup{
	Config[] value();
}
