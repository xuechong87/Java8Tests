package org.luckystars.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeTests {

	private static Unsafe unsafe;
	static {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Data tem = new Data();
		Data d1 = new Data("d1");
		Long d1Addr = toAddress(d1);
//		System.out.println(d1Addr);
//		Long dataSize = sizeoOf(d1);
//		System.out.println(dataSize);
//		Long d2Addr = getUnsafe().allocateMemory(dataSize);
//		
//		getUnsafe().copyMemory(d1Addr, d2Addr, dataSize);
//		
//		Data d2 = (Data) fromAddress(d2Addr);
//		System.out.println(d2.getId());
	}

	public static Unsafe getUnsafe() {
		return unsafe;
	}


	public static long sizeOf(Object object) {
		return getUnsafe().getAddress(
				normalize(getUnsafe().getInt(object, 4L)) + 12L);
	}

	private static long normalize(int value) {
		if (value >= 0)
			return value;
		return (~0L >>> 32) & value;
	}

	public static long toAddress(Object obj) {
		Object[] array = new Object[] { obj };
		long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
		return normalize(getUnsafe().getInt(array, baseOffset));
	}

	static Object fromAddress(long address) {
	    Object[] array = new Object[] {null};
	    long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
	    getUnsafe().putLong(array, baseOffset, address);
	    return array[0];
	}
}
