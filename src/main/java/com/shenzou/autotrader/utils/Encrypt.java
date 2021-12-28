package com.shenzou.autotrader.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";
	
	public static void main(String[] args) throws URISyntaxException, EncryptException {
		String key = System.getProperty("password");
		if (key == null) {
            throw new IllegalArgumentException("Run with -Dpassword=<password>");
        }
		File inputFile = new File("PUT URL HERE");
		File outputFile = new File("PUT URL HERE");
				
		encrypt(key, inputFile, outputFile);
	}

	public static void encrypt(String key, File inputFile, File outputFile) throws EncryptException {
		doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
	}

	public static void decrypt(String key, File inputFile, File outputFile) throws EncryptException {
		doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
	}
	
	public static String decryptInMemory(String key, File inputFile) throws Exception {
		Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		FileInputStream inputStream = new FileInputStream(inputFile);
		byte[] inputBytes = new byte[(int) inputFile.length()];
		inputStream.read(inputBytes);

		byte[] outputBytes = cipher.doFinal(inputBytes);
		String output = new String(outputBytes, StandardCharsets.UTF_8);

		inputStream.close();
		return output;
	}

	private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws EncryptException {
		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new EncryptException("Error encrypting/decrypting file", ex);
		}
	}
}
