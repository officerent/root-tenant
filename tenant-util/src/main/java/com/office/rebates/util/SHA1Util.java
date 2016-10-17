package com.office.rebates.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {

	public static final String SecurityAlgorithm = "SHA-1";
	
	public static byte[] SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance(SecurityAlgorithm);
			digest.update(decript.getBytes());
			byte[] messageDigest = digest.digest();
			return messageDigest;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String SHA1HexString(String decript) {
		byte[] bytes = SHA1(decript);
		if(bytes == null) {
			return null;
		}
		StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < bytes.length; i++) {
            String shaHex = Integer.toHexString(bytes[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
	}
	
}
