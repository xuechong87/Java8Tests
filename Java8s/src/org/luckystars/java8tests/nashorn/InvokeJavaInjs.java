package org.luckystars.java8tests.nashorn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class InvokeJavaInjs {

	public static void main(String[] args) throws FileNotFoundException,
			ScriptException, URISyntaxException {
		
		File f = new File(Thread.currentThread().getContextClassLoader()
				.getResource("InvokeJava.js").toURI());
		
		ScriptEngine engine = Utils.getNashornEngine();
		engine.eval(new FileReader(f));
		
	}
}
