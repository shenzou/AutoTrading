package com.shenzou.autotrader.binance;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;

import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shenzou.autotrader.binance.BinanceInitializer.BinanceConfig;
import com.shenzou.autotrader.binance.models.SpotAccount;

public class BinanceMain {
	
	SpotClientImpl client;

	public BinanceMain(String config) {
		BinanceConfig conf;
		if(config.toLowerCase().equals("prod")) {
			conf = BinanceConfig.PROD;
		}
		else {
			conf = BinanceConfig.TESTNET;
		}
		
		client = new SpotClientImpl(BinanceInitializer.getApiKey(conf), BinanceInitializer.getSecretKey(conf), BinanceInitializer.getUrl(conf));
		
//		LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
//		String result = client.createMarket().time();
//		System.out.println(result);
	}
	
	public void runAutoMode() {
		
	}
	
	public void runManualMode(String stableCoin, List<String> coins) {
		for(String coin: coins) {
			if(checkCurrencyPairValidity(coin, stableCoin)) {
				int lastPrice = loadLatestBoughtPriceFromDB(coin, stableCoin);
				
			}	
		}
	}
	
	private boolean checkCurrencyPairValidity(String firstCurr, String secondCurr) {
		String pair = firstCurr.concat(secondCurr);
		System.out.println("Checking existence of pair " + pair);
		LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
		parameters.put("symbol", pair);
		String result = "";
		try {
			result = client.createMarket().exchangeInfo(parameters);
			System.out.println("Pair is valid.");
			return true;
		} catch(BinanceClientException bce) {
			System.out.println("Pair is not valid.");
			bce.printStackTrace();
		}
		System.out.println(result);
		return false;
	}
	
	//TODO
	public int loadLatestBoughtPriceFromDB(String firstCurr, String secondCurr) {
		return 0;
	}
	
	public boolean getCurrentPairPrice(String firstCurr, String secondCurr) {
		if(checkCurrencyPairValidity(firstCurr, secondCurr)) {
			String pair = firstCurr.concat(secondCurr);
			LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
			parameters.put("symbol", pair);
			String result = "";
			try {
				result = client.createMarket().tickerSymbol(parameters);
				JSONObject obj = new JSONObject(result);
				String pairFromJson = obj.getString("symbol");
				String priceFromJson = obj.getString("price");
				System.out.println("Pair: " + pairFromJson + ", Price: " + priceFromJson + " " + secondCurr);
				return true;
			} catch(BinanceClientException bce) {
				System.out.println("Error while retrieving the pair price for the pair: " + pair);
				bce.printStackTrace();
			}
		}
		return false;
	}
	
	public SpotAccount getCurrentAssetFunding() {
		LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
		String result = "";
		Gson gson = new GsonBuilder().create();
		try {
			result = client.createTrade().account(parameters);
			SpotAccount res = gson.fromJson(result, SpotAccount.class);
			return res;
		}
		catch(BinanceClientException bce) {
			System.out.println("Error while retrieving asset funding");
			bce.printStackTrace();
		}
		return null;
	}

}
