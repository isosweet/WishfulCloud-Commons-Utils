package com.wishfulcloud.commons.utils;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	/**
	 * Base64编码
	 * 
	 * @param binaryString
	 * @return
	 */
	public static String encode(String binaryString) {
		return encode(binaryString, "utf-8");
	}

	/**
	 * Base64编码
	 * 
	 * @param binaryString
	 * @param charsetName
	 * @return
	 */
	public static String encode(String binaryString, String charsetName) {
		String base64String = null;
		if (binaryString != null && binaryString.length() > 0) {
			byte[] binaryData = binaryString.getBytes(Charset.forName(charsetName));
			base64String = Base64.encodeBase64String(binaryData);
		}
		return base64String;
	}

	/**
	 * Base64编码
	 * 
	 * @param binaryData
	 * @return
	 */
	public static String encode(byte[] binaryData) {
		String base64String = null;
		if (binaryData != null) {
			base64String = Base64.encodeBase64String(binaryData);
		}
		return base64String;
	}

	/**
	 * Base64解码
	 * 
	 * @param base64string
	 * @return
	 */
	public static String decode(String base64string) {
		return decode(base64string, "utf-8");
	}

	/**
	 * Base64解码
	 * 
	 * @param base64string
	 * @param charsetName
	 * @return
	 */
	public static String decode(String base64string, String charsetName) {
		String binaryString = null;
		try {
			byte[] base64data = Base64.decodeBase64(base64string);
			binaryString = new String(base64data, Charset.forName(charsetName));
		} catch (Exception e) {
		}
		return binaryString;
	}

	/**
	 * Base64解码
	 * 
	 * @param base64string
	 * @return
	 */
	public static byte[] decodeToByteArray(String base64string) {
		byte[] base64data = Base64.decodeBase64(base64string);
		return base64data;
	}

}
