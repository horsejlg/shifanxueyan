package cn.qlt.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * @ClassName: XOR
 * @Description: (加解密工具类)
 * @author 江立国
 * @date 2014年2月27日 上午9:31:37
 * 
 */
public class XOR {

	public static final byte[] encrypt(byte[] data, String strKey) {
		byte[] keyData = strKey.getBytes();
		int keyIndex = 0;
		for (int x = 0; x < data.length; x++) {
			data[x] = (byte) (data[x] ^ keyData[keyIndex]);
			if (++keyIndex == keyData.length) {
				keyIndex = 0;
			}
		}
		return data;
	}

	/**
	 * @Title: encoder
	 * @author 江立国
	 * @Description: (加密方法)
	 * @param str
	 *            要加密的字符串
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 *             String
	 * @throws
	 */
	public static final String encoder(String str, String key) throws Exception {
		if(str.matches("[0-9a-zA-Z]{32}"))
			return str;
		return new String(CryptUtil.encryptMD5((str+key).getBytes()));
	}

	public static byte[] bytearraycopy(byte[] first, byte[] second) {

		byte[] hash = new byte[first.length + second.length];

		System.arraycopy(first, 0, hash, 0, first.length);
		System.arraycopy(second, 0, hash, first.length, second.length);

		return hash;
	}


	/**
	 * @ClassName: CryptUtil
	 * @Description: (通用加密编码工具类，支持md5\sha\base64加密)
	 * @author 江立国
	 * @date 2014年2月27日 上午9:32:20
	 * 
	 */
	public static class CryptUtil {
		private static final String KEY_MD5 = "MD5";
		private static final String KEY_SHA = "SHA";
		/**
		 * MAC算法可选以下多种算法
		 * 
		 * <pre>
		 * 
		 * HmacMD5  
		 * HmacSHA1  
		 * HmacSHA256  
		 * HmacSHA384  
		 * HmacSHA512
		 * </pre>
		 */
		public static final String KEY_MAC = "HmacMD5";

		/**
		 * BASE64解密
		 * 
		 * @param key
		 * @return
		 * @throws Exception
		 */
		public static byte[] decryptBASE64(String key) throws Exception {
			return Base64.getDecoder().decode(key);
		}

		/**
		 * BASE64 加密
		 * 
		 * @param key
		 * @return
		 * @throws Exception
		 */
		public static String encryptBASE64(byte[] key) throws Exception {
			return new String(Base64.getEncoder().encode(key));
		}

		/**
		 * MD5加密
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		public static byte[] encryptMD5(byte[] data) throws Exception {

			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(data);

			return md5.digest();

		}

		/**
		 * SHA加密
		 * 
		 * @param data
		 * @return
		 * @throws Exception
		 */
		public static byte[] encryptSHA(byte[] data) throws Exception {

			MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
			sha.update(data);

			return sha.digest();

		}

		/**
		 * 初始化HMAC密钥
		 * 
		 * @return
		 * @throws Exception
		 */
		public static String initMacKey() throws Exception {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
			SecretKey secretKey = keyGenerator.generateKey();
			return encryptBASE64(secretKey.getEncoded());
		}

		/**
		 * HMAC 加密
		 * 
		 * @param data
		 * @param key
		 * @return
		 * @throws Exception
		 */
		public static byte[] encryptHMAC(byte[] data, String key)
				throws Exception {
			SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			return mac.doFinal(data);
		}

		/**
		 * DES 算法 <br>
		 * 可替换为以下任意一种算法，同时key值的size相应改变。
		 * 
		 * <pre>
		 * DES                  key size must be equal to 56 
		 * DESede(TripleDES)    key size must be equal to 112 or 168 
		 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
		 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
		 * RC2                  key size must be between 40 and 1024 bits 
		 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
		 * </pre>
		 */
		public static final String ALGORITHM = "DES";

		/**
		 * DES 算法转换密钥<br>
		 * 
		 * @param key
		 * @return
		 * @throws Exception
		 */
		private static Key toKey(byte[] key) throws Exception {
			SecretKey secretKey = null;
			if (ALGORITHM.equals("DES") || ALGORITHM.equals("DESede")) {
				DESKeySpec dks = new DESKeySpec(key);
				SecretKeyFactory keyFactory = SecretKeyFactory
						.getInstance(ALGORITHM);
				secretKey = keyFactory.generateSecret(dks);
			} else {
				// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
				secretKey = new SecretKeySpec(key, ALGORITHM);
			}
			return secretKey;
		}

		/**
		 * DES 算法解密
		 * 
		 * @param data
		 * @param key
		 * @return
		 * @throws Exception
		 */
		public static byte[] decrypt(byte[] data, String key) throws Exception {
			Key k = toKey(decryptBASE64(key));
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, k);
			return cipher.doFinal(data);
		}

		/**
		 * DES 算法加密
		 * 
		 * @param data
		 * @param key
		 * @return
		 * @throws Exception
		 */
		public static byte[] encrypt(byte[] data, String key) throws Exception {
			Key k = toKey(decryptBASE64(key));
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, k);
			return cipher.doFinal(data);
		}

		/**
		 * DES 算法生成密钥
		 * 
		 * @return
		 * @throws Exception
		 */
		public static String initKey() throws Exception {
			return initKey(null);
		}

		/**
		 * DES 算法生成密钥
		 * 
		 * @param seed
		 * @return
		 * @throws Exception
		 */
		public static String initKey(String seed) throws Exception {
			SecureRandom secureRandom = null;
			if (seed != null) {
				secureRandom = new SecureRandom(decryptBASE64(seed));
			} else {
				secureRandom = new SecureRandom();
			}
			KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
			kg.init(secureRandom);
			SecretKey secretKey = kg.generateKey();
			return encryptBASE64(secretKey.getEncoded());
		}

	}
}
