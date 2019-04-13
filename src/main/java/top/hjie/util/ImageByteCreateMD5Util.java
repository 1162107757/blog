package top.hjie.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * 文件byteMD5加密
 * @author 何杰
 *
 */
public class ImageByteCreateMD5Util {

	public static MessageDigest messagedigest = null;
	public static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public String imageCreateMD5(File file) throws Exception{
		InputStream inputStream = null;
		byte[] byteArray = null; 
		try {
			inputStream = new FileInputStream(file);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[2048];    
			int len = 0;    
			while( (len=inputStream.read(buffer)) != -1 ){
			    outStream.write(buffer, 0, len);    
			}    
			inputStream.close();
			byteArray =  outStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(byteArray);
		return bufferToHex(messagedigest.digest(),0,messagedigest.digest().length);
	}
	
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
	
}
