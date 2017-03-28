package com.wishfulcloud.commons.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * 
 * @author LiLei
 *
 */
public class PasswordUtils {

	/**
	 * JAVA6支持以下任意一种算法 PBEWITHMD5ANDDES PBEWITHMD5ANDTRIPLEDES
	 * PBEWITHSHAANDDESEDE PBEWITHSHA1ANDRC2_40 PBKDF2WITHHMACSHA1
	 * */

	/**
	 * 定义使用的算法为:PBEWITHMD5andDES算法
	 */
	public static final String ALGORITHM = "PBEWithMD5AndDES";//加密算法
	
	public static final String SECRET_KEY = "888888"; // 加密KEY
	public static final String Salt = "23#kk&+W";//盐

	/**
	 * 定义迭代次数为1000次
	 */
	private static final int ITERATIONCOUNT = 1000;
	
	
	/**
	 * 加密明文字符串
	 * 
	 * @param plaintext
	 *            待加密的明文字符串
	 *            
	 * @return 加密后的密文字符串
	 * @throws Exception
	 */
	public static String encrypt(String plaintext) {

		byte[] salt = Salt.getBytes();
		Key key = getPBEKey(SECRET_KEY);
		byte[] encipheredData = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

			encipheredData = cipher.doFinal(plaintext.getBytes());
		} catch (Exception e) {
		}
		return bytesToHexString(encipheredData);
	}
	
	/**
	 * 解密密文字符串
	 * 
	 * @param ciphertext
	 *            待解密的密文字符串
	 *            
	 * @return 解密后的明文字符串
	 * @throws Exception
	 */
	public static String decrypt(String ciphertext) {

		Key key = getPBEKey(SECRET_KEY);
		byte[] passDec = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(Salt.getBytes(), ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

			passDec = cipher.doFinal(hexStringToBytes(ciphertext));
		}

		catch (Exception e) {
			// TODO: handle exception
		}
		return new String(passDec);
	}
	
	/**
	 * 根据PBE密码生成一把密钥
	 * 
	 * @param password
	 *            生成密钥时所使用的密码
	 * @return Key PBE算法密钥
	 * */
	private static Key getPBEKey(String password) {
		// 实例化使用的算法
		SecretKeyFactory keyFactory;
		SecretKey secretKey = null;
		try {
			keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			// 设置PBE密钥参数
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			// 生成密钥
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return secretKey;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param src
	 *            字节数组
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 将十六进制字符串转换为字节数组
	 * 
	 * @param hexString
	 *            十六进制字符串
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	
	/**
	 * 加密规则
	 * 
	 * 此工具类用来处理发送内容加密和解密， 我方对其加密，贵方解密
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypContent(String str) {
		// 获取2分钟后的时间戳
		long lastTwoSecordTimeMillis = System.currentTimeMillis() + 2 * 60 * 1000;
		// 将long转换成String
		String strLastTwoSecordTimeMillis = Long.toString(lastTwoSecordTimeMillis);
		// 将2分钟后的时间戳进行反转
		String strReverseLastTwoSecordTimeMillis = new StringBuffer(strLastTwoSecordTimeMillis).reverse().toString();
		// 加密2分钟后的时间戳
		String encryptStrReverseLastTwoSecordTimeMillis = Base64Util.encode(strReverseLastTwoSecordTimeMillis);
		// 将加密字符串的前10位和后10位拆开放到当前字符串的前后两端并且最后加上加密字符串长度
		String newStr = encryptStrReverseLastTwoSecordTimeMillis.subSequence(0, 10) + str + encryptStrReverseLastTwoSecordTimeMillis.subSequence(10, encryptStrReverseLastTwoSecordTimeMillis.length())
				+ encryptStrReverseLastTwoSecordTimeMillis.length();
		// 加密新组合成的字符串
		String encryptNewStr = Base64Util.encode(newStr);
		return encryptNewStr;
	}

	/**
	 * 解密规则
	 * 此工具类用来处理发送内容加密和解密， 我方对其加密，贵方解密
	 * 
	 * @param encryptStr
	 * @return
	 */
	public static String decryptContent(String encryptStr) {
		String zhi;
		// 将加密字符串进行解密
		String str = Base64Util.decode(encryptStr);
		// 截掉解密字符串的后两位
		String zifu = str.substring(0, str.length() - 2);
		// 将解密字符串的前10位和后10位进行拼接
		String newStr = zifu.substring(0, 10) + zifu.substring(zifu.length() - 10, zifu.length());
		String info = zifu.substring(10, zifu.length() - 10);
		// 解密时间戳
		String strReverseTimeMillis = Base64Util.decode(newStr);
		// 将时间戳反转
		String strTimeMillis = new StringBuffer(strReverseTimeMillis).reverse().toString();
		// 转换成long
		long timeMillis = Long.parseLong(strTimeMillis);
		// 获取当前时间的时间戳
		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis <= timeMillis) {
			zhi = info;
		} else {
			zhi = "响应超时";
		}
		return zhi;
	}
	
	public static void main(String[] args) {
		
		
		
		String str = "rtdffgrr234";
		
		try {
			String ciphertext = PasswordUtils.encrypt(str);
			System.out.println("密文:" + ciphertext);
			
			String plaintext = PasswordUtils.decrypt(ciphertext);
			
			System.out.println("明文:" + plaintext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
