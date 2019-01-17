package org.penpi.test.commons;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.junit.Test;

public class Base64Test {

	@Test
	public void toBase64() throws Exception {
		String[] values = new String[] { "http://192.168.1.196:81/fe/uchiagehanabi.aac" };
		Encoder encoder = Base64.getEncoder();
		for (String value : values) {
			String encodeStr = encoder.encodeToString(value.getBytes("UTF-8"));
			Decoder decoder = Base64.getDecoder();
			System.out.println("编码串:" + encodeStr + "\n");
			System.out.println("解码串:" + new String(decoder.decode(encodeStr), "UTF-8") + "\n");
		}

		// BASE64Decoder decoder = new BASE64Decoder();
		// System.out.println(new
		// String(decoder.decodeBuffer("5Y+R54OnLOaEn+WGkizlkrPll70s5rWB5oSf"),"UTF-8"));
	}
}