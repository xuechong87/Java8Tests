package org.luckystars.java8tests.new_in7;

/**
 * java7中 整数类型(byte, short, int, long)可以用二进制表示
 * @author xuechong
 */
public class BinaryLiterals {
	public static void main(String[] args) {
		
		byte b = 0B01;
		short s = 0B10;
		int i = 0b11;
		long l = 0b100;

		System.out.println("b = " + b);
		System.out.println("s = " + s);
		System.out.println("i = " + i);
		System.out.println("l = " + l);
	}
}
