package org.luckystars.java8tests.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Utils {
	
	public static ScriptEngine getNashornEngine(){
		ScriptEngineManager manager = new ScriptEngineManager();
		return  manager.getEngineByName("nashorn");
	}
	
}
