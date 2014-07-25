package org.luckystars.java8tests.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 使用nashorn解释js
 * @author xuechong
 * @Date 2014年7月24日
 */
public class Test1 {
	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		String js = "(function(){print('Hello World!1111');})();";
		try {
			engine.eval(js);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
