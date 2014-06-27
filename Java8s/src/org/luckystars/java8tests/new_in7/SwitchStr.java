package org.luckystars.java8tests.new_in7;

/**
 * java7 的 switch增加了对String类型的支持
 * @author xuechong
 */
public class SwitchStr {
	
	public static void main(String[] args) {
		
		String[] strs = {"abc","def","hig","kls"};
		
		int index = (int)(Math.random()*(strs.length-1));
		
		switch (strs[index]) {
		case "abc":
			System.out.println(0);
			break;
		case "def":
			System.out.println(1);
			break;
		case "hig":
			System.out.println(2);
			break;
		case "kls":
			System.out.println(3);
			break;
		default:
			System.out.println("default");
			break;
		}
		
		
	}

}
