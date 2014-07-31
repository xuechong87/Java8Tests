package org.luckystars.java8tests.nashorn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;


public class InvokeJavaInjs {
	
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		Thread.currentThread().getContextClassLoader().getResourceAsStream("InvokeJava.js");
		ScriptEngine engine = Utils.getNashornEngine();
		
		engine.eval(FileInputStream);
		
		
	}
}
