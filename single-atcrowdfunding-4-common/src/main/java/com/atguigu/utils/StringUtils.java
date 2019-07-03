package com.atguigu.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.atguigu.constant.MessageConstant;

public class StringUtils {
	
	/**
	 * 方法作用：验证字符串是否有效
	 * @param source 待验证字符串
	 * @return 验证结果，true为有效，false为无效
	 * @author 封捷
	 */
	public static boolean stringValidate(String source) {
		return source != null && source.length() > 0;
	}
	
	/**
	 * 方法作用：执行MD5加密
	 * @param source 明文
	 * @return 密文
	 * @author 封捷
	 */
	public static String md5(String source) {
		
		// 1.字符串校验
		if(!stringValidate(source)) {
			throw new RuntimeException(MessageConstant.STRING_IS_INVALIDATE_MESSAGE);
		}
		
		// 2.获取source的字节数组
		byte[] sourceByteArray = source.getBytes();
		
		try {
			
			// 3.获取MessageDigest类的实例对象
			String algorithm = "MD5";
	
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			
			// 4.执行加密
			byte[] targetByteArray = digest.digest(sourceByteArray);
			
			// 5.对加密得到的数组进行进一步加工
			// ①准备字符数组
			char[] charArray = 
					new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			
			// ②准备StringBuilder对象
			StringBuilder builder = new StringBuilder();
			
			// ③遍历加密得到的数组
			for (int i = 0; i < targetByteArray.length; i++) {
				
				// ④获取当前字节的值
				byte b = targetByteArray[i];
				
				// ⑤获取当前字节数值的低四位
				int lowValue = b & 15;
				
				// ⑥获取当前字节数值的高四位
				int highValue = (b >> 4) & 15;
				
				// ⑦从字符数组中获取对应的值
				char lowChar = charArray[lowValue];
				char highChar = charArray[highValue];
				
				// ⑧拼字符串
				builder.append(highChar).append(lowChar);
			}
			
			return builder.toString();
			
		} catch (NoSuchAlgorithmException e) {/*根本不可能抛出异常*/}
		
		return null;
	}

}
