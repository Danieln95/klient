package sdk.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;
import java.security.MessageDigest;



public class Digester {

    /**
     * Declaring the salt and key variables.
     */
    private final static String SALT = "n0zaCTADRUuTb@JUp01n%5@(l@IAaLlZ";
    private final static String KEY = "40674244454045cb9a70040a30e1c007";
    private static MessageDigest digester;


    //Opretter objekt, som benyttes af MD5 (hashfunktion)
    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hash string with MD5 hashing
     * @param str
     * @return MD5 hash of string
     */

    /**
     * @param str
     * @return Hashing begins.
     */


    /**
     * @param str
     * @return Hash with salt begins.
     */
    public static String hashWithSalt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }

        str = str + Digester.SALT;

        return Digester._hash(str);
    }

    /**
     * Converting hash values into hexadecimal.
     */

    private static String _hash(String str) {
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }

}

