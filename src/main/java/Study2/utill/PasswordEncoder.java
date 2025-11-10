package Study2.utill;

import java.util.Objects;

public class PasswordEncoder {
    public static final String screat = "암호키";
    public static String encode(String str){
        if (str == null){
            return null;
        }

        long hash = Integer.toUnsignedLong(Objects.hash(str));
        long encodedHash = hash + Objects.hash(screat);
        return Long.toHexString(encodedHash);
    }
    public static boolean match(String 평문, String 암호문){
        if (평문 == null || 암호문 == null ){
            return false;
        }
        return encode(평문).equals(암호문);
    }
}
