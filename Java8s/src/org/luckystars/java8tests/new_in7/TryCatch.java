package org.luckystars.java8tests.new_in7;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.luckystars.java8tests.Util;

/**
 * java 7中的try catch
 * @author xuechong
 */
public class TryCatch {

	public static void main(String[] args) throws MalformedURLException, IOException {
		oldStyle();
		autoClose();
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
	
	/**
	 * auto close
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	static void autoClose() throws MalformedURLException, IOException{
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
	
	/**
	 * 多重catch
	 * 可以在一个catch中处理多种异常
	 */
	static void multipleCatch(){
		try {
			ex();
		} catch (NullPointerException | IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	static void ex()throws IOException,NullPointerException,IllegalArgumentException
	{}
	
	
	
	
}
