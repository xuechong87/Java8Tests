package org.luckystars.java8tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
	public static void copy(InputStream in, OutputStream out)
			throws IOException {
			byte[] buffer = new byte[1024];
			while (true) {
				int bytesRead = in.read(buffer);
				if (bytesRead == -1) break;
				out.write(buffer, 0, bytesRead);
			}
	}
}
