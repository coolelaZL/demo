package cn.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
	public static String md5(String src){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(md5("123"));
		System.out.println(md5("123456"));
		System.out.println(md5("123").length());
		System.out.println(md5("123456").length());
	}
	public static String createId() {
		String id = UUID.randomUUID().toString();
		return id;
	}
}
