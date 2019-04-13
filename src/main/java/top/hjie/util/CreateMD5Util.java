package top.hjie.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateMD5Util {

	public static MessageDigest messagedigest = null;
	public static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
			char c1 = hexDigits[bytes[l] & 0xf];
			stringbuffer.append(c0);
			stringbuffer.append(c1);
		}
		return stringbuffer.toString();
	}
	
	
	public static String getMD5ByString(String str) throws NoSuchAlgorithmException{
		byte[] bytes = str.getBytes();
		messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest(), 0, messagedigest.digest().length);
	}
	
	public static String getMD5ByBytes(byte[] bytes) throws NoSuchAlgorithmException{
		messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest(), 0, messagedigest.digest().length);
	}
	
}
