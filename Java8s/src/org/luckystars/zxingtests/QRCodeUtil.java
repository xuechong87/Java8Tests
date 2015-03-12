package org.luckystars.zxingtests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeUtil {
	
	public static final int DEFAULT_WIDTH = 320;
	public static final int DEFAULT_HEIGHT = 320;
	public static final String DEFAULT_IMG_FORMAT = "png";
	
	/**
	 * 
	 * @param content
	 * @param format image format
	 * @param target
	 * @author xc
	 */
	public static void writeToFile(String content,File target){
		writeToFile(content,DEFAULT_IMG_FORMAT,target,DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	public static void writeToFile(String content,String format,File target,int width,int height){
		try {
			MatrixToImageWriter.writeToFile(generateCode(content,width,height), format,target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToStream(String content,OutputStream out){
		writeToStream(content,out,DEFAULT_IMG_FORMAT,DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	public static void writeToStream(String content,OutputStream out,String format,int width,int height){
		try {
			MatrixToImageWriter.writeToStream(generateCode(content,width,height), format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage toBufferedImage(String content,int width,int height ){
		return MatrixToImageWriter.toBufferedImage(generateCode(content,width,height));
	}
	
	public static BufferedImage toBufferedImage(String content){
		return toBufferedImage(content,DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
	
	private static BitMatrix generateCode(String content,int width,int length){
		BitMatrix result = null;
		QRCodeWriter writer = new QRCodeWriter();
		try {
			Map<EncodeHintType,String> hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			result = writer.encode(content, BarcodeFormat.QR_CODE, width, length,hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
