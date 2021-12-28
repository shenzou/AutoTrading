package com.shenzou.autotrader.binance;

import java.io.File;

import com.shenzou.autotrader.utils.Encrypt;

public class BinanceInitializer {

	public enum BinanceConfig {
		PROD, TESTNET
	}

	private static String API_KEY = "";
	private static String SECRET_KEY = "";
	private static String BASE_URL = "";
	private static String TESTNET_API_KEY = "";
	private static String TESTNET_SECRET_KEY = "";

	public static void decryptKeys() {
		try {
			String keyFile = System.getProperty("keyfile");
			if (keyFile == null) {
	            throw new IllegalArgumentException("Run with -Dkeyfile=keyFileURL");
	        }
			String password = System.getProperty("password");
			if (password == null) {
	            throw new IllegalArgumentException("Run with -Dpassword=password");
	        }
			File encryptedfile = new File(keyFile);
			String result = Encrypt.decryptInMemory(password, encryptedfile);
			String[] lines = result.split("\\r?\\n");
			for (String line : lines) {
				String[] elements = line.split("=");
				if (elements.length != 2)
					continue;
				switch (elements[0]) {
				case "API_KEY":
					API_KEY = elements[1];
					break;
				case "SECRET_KEY":
					SECRET_KEY = elements[1];
					break;
				case "BASE_URL":
					BASE_URL = elements[1];
					break;
				case "TESTNET_API_KEY":
					TESTNET_API_KEY = elements[1];
					break;
				case "TESTNET_SECRET_KEY":
					TESTNET_SECRET_KEY = elements[1];
					break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String getApiKey(BinanceConfig conf) {
		decryptKeys();
		switch (conf) {
		case PROD:
			return API_KEY;
		case TESTNET:
			return TESTNET_API_KEY;
		default:
			return TESTNET_API_KEY;
		}
	}

	public static String getSecretKey(BinanceConfig conf) {
		decryptKeys();
		switch (conf) {
		case PROD:
			return SECRET_KEY;
		case TESTNET:
			return TESTNET_SECRET_KEY;
		default:
			return TESTNET_SECRET_KEY;
		}
	}

	public static String getUrl(BinanceConfig conf) {
		decryptKeys();
		switch (conf) {
		case PROD:
			return BASE_URL;
		case TESTNET:
			return BASE_URL;
		default:
			return BASE_URL;
		}
	}
}
