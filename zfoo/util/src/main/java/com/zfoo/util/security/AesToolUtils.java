package com.zfoo.util.security;

import com.zfoo.protocol.util.StringUtils;
import io.netty.util.concurrent.FastThreadLocal;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/12/31 0:37
 */
public class AesToolUtils {
    private static final Key KEY;
    private static final String KEY_STR = "uslmcG1ep1gSsBcu";//"=jE[`B],YO24Vt+Akh&}D7@s9l1uLKP)";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";
    private static final FastThreadLocal<Cipher> LOCAL_ENCRYPT_CIPHER = new FastThreadLocal<Cipher>() {
        @Override
        protected Cipher initialValue() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            var cipher = Cipher.getInstance(ALGORITHM_STR);
            cipher.init(Cipher.ENCRYPT_MODE, KEY);
            return cipher;
        }
    };
    private static final FastThreadLocal<Cipher> LOCAL_DECRYPT_CIPHER = new FastThreadLocal<Cipher>() {
        @Override
        protected Cipher initialValue() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            var cipher = Cipher.getInstance(ALGORITHM_STR);
            cipher.init(Cipher.DECRYPT_MODE, KEY);
            return cipher;
        }
    };

    static {
        try {
            KEY = new SecretKeySpec(KEY_STR.getBytes(StringUtils.DEFAULT_CHARSET_NAME), ALGORITHM);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对str进行AES加密
     *
     * @param str 需要加密的字符串
     * @return AES加密后的字符串
     */
    public static String getEncryptString(String str) {
        try {
            var base64Encoder = Base64.getEncoder();
            var strBytes = StringUtils.bytes(str);
            var encryptStrBytes = encrypt(strBytes);
            return base64Encoder.encodeToString(encryptStrBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encrypt(byte[] bytes) {
        try {
            return LOCAL_ENCRYPT_CIPHER.get().doFinal(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对str进行AES解密
     *
     * @param str 需要解密的字符串
     * @return AES解密后的字符串
     */
    public static String getDecryptString(String str) {
        try {
            var base64Decoder = Base64.getDecoder();
            var strBytes = base64Decoder.decode(str);
            var decryptStrBytes = decrypt(strBytes);
            return StringUtils.bytesToString(decryptStrBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(byte[] bytes) {
        try {
            return LOCAL_DECRYPT_CIPHER.get().doFinal(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
