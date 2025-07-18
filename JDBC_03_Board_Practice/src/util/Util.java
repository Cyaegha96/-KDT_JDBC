package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class Util {
	public static String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            byte[] digest = md.digest(bytes);
            
            StringBuilder builder = new StringBuilder();
            for (byte b : digest) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 암호화 실패", e);
        }
    }
	
	public static String timeStampFormatString(long currentTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일 hh:mm");
		return sdf.format(currentTime);
	}
	
	
}