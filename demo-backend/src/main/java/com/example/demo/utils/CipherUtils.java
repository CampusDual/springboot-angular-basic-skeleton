package com.example.demo.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.exception.DemoException;


/**
 * Clase que maneja el el cifrado y descifrado de contraseñas o textos. Se usa una iteración de 65536 y una longitud de clave de 256 bits. Cifrado
 * simétrico AES con el algoritmo PBKDF2WithHmacSHA256.
 * 
 */
public class CipherUtils {

	/**
	 * Atributo que representa el objeto que maneja el log de la clase.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CipherUtils.class);

	private byte[] salt = "com.campusdual.demo".getBytes();
	private int iterationCount = 100;
	private int keyLength = 256;

	/**
	 * Encripta un String a través de la clave secreta proporcionada.
	 * 
	 * @param secretKey la clave maestra para el cifrado.
	 * @param plainText el texto a cifrar.
	 * @return el texto cifrado.
	 */
	public String encrypt(String secretKey, String plainText) {
		try {
			SecretKey key = getSecretKey(secretKey);
			// Enc process
			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			String charSet = "UTF-8";
			byte[] in = plainText.getBytes(charSet);
			byte[] out = ecipher.doFinal(in);
			String encStr = new String(Base64.getEncoder().encode(out));
			return encStr;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DemoException(e.getMessage(), e);
		}
	}

	/**
	 * Descencripta un String a través de la clave secreta proporcionada.
	 * 
	 * @param secretKey la clave maestra para el descifrado.
	 * @param encryptedText el texto a descifrar.
	 * @return el texto descifrado.
	 */
	public String decrypt(String secretKey, String encryptedText) {
		try {
			SecretKey key = getSecretKey(secretKey);
			// Decryption process; same key will be used for decr
			Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
			dcipher.init(Cipher.DECRYPT_MODE, key);
			byte[] enc = Base64.getDecoder().decode(encryptedText);
			byte[] utf8 = dcipher.doFinal(enc);
			String charSet = "UTF-8";
			String plainStr = new String(utf8, charSet);
			return plainStr;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DemoException(e.getMessage(), e);
		}
	}

	/**
	 * Obtiene la {@link SecretKey} con la clave maestra indicada.
	 * 
	 * @param secretKey la clave maestra para realizar el cifrado/descifrado.
	 * @return {@link SecretKey} obtenido.
	 */
	private SecretKey getSecretKey(String secretKey) {
		try {
			KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount, keyLength);
			SecretKey tmp = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(keySpec);
			return new SecretKeySpec(tmp.getEncoded(), "AES");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DemoException(e.getMessage(), e);
		}
	}

}
