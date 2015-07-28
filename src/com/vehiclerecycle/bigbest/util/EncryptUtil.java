package com.vehiclerecycle.bigbest.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	public static String encrypt(String info) {
		String result="";
		char[] encryptChars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			md.update(info.getBytes());
			byte[] eBytes=md.digest();
			char[] resultChars=new char[eBytes.length*2];
			int k=0;			
			for (int i = 0; i < eBytes.length; i++) {
				resultChars[k++]=encryptChars[eBytes[i]>>>4&0xf];
				resultChars[k++]=encryptChars[eBytes[i]&0xf];
			}
			result=new String(resultChars);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}				
		return result;
	}
}
