package org.luckystars.java8tests;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * java 7中的try catch
 * @author xuechong
 */
public class TryCatch {

	public static void main(String[] args) throws MalformedURLException, IOException {
		oldStyle();
		newFeature();
	}
	
	static void oldStyle() throws MalformedURLException, IOException{
		URLConnection conn = new URL("https://www.google.com.hk").openConnection();
		
		InputStream in  = null;
		try {
			in = conn.getInputStream();
			conn.connect();
			Util.copy(in, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in !=null){
				in.close();
			}
		}
		
	}
	
	static void newFeature() throws MalformedURLException, IOException{
		URLConnection conn = new URL("https://www.google.com.hk").openConnection();
		
		try (InputStream in = conn.getInputStream();){//此处定义的对象必须实现 java.lang.AutoCloseable 接口
			conn.connect();
			Util.copy(in, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//in.close();不再需要关闭
		}
		
	}
	
	
}
