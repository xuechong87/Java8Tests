/**
 * call 
 * $jjs InvokeJava.js 
 * in command line
 * **/
var createHashMap = function(){
	var HashMapType = Java.type("java.util.HashMap");
	var mapInstance = new HashMapType();
	mapInstance.put("aaaaa","aaaaaaa");
	return mapInstance;
};


var printMap = function(){
	var map = createHashMap();
	print(map);
	print(map.entrySet());
	
	for each(var e in map.entrySet()){
		print(e.getKey() + ":" +e.getValue());
	}
}

printMap();