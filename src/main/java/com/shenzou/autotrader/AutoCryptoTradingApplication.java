package com.shenzou.autotrader;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.shenzou.autotrader.binance.BinanceMain;
import com.shenzou.autotrader.dao.TransactionRepository;
import com.shenzou.autotrader.persistence.Transaction;


@SpringBootApplication
public class AutoCryptoTradingApplication {
	
	private static ApplicationContext context;
	
	static BinanceMain binanceMain;
	static Scanner userInput;

	/*
	 * Parameters: 
	 * - args[0] : Service to use (binance or coinbase) (default: binance) 
	 * - args[1] : Configuration (testnet or prod) 
	 */
	public static void main(String[] args) {
		context = SpringApplication.run(AutoCryptoTradingApplication.class, args);

		String service = args[0].toLowerCase();
		String config = args[1].toLowerCase();

		userInput = new Scanner(System.in);
		println("Please select what you want to do: ");
		println("1: Start trading in automatic mode (Will use every currency pairs from the DB)");
		println("2: Start trading in manual mode");
		println("3: Get pair current price");
		println("4: Put an order (buy/sell)");
		println("5: Get spot assets balance");

		String choice = userInput.nextLine();

		switch (service) {
		case "binance":
			runBinance(config, choice);
			break;
		case "coinbase":
			break;
		default:
			runBinance(config, choice);
			break;
		}

	}

	public static void runBinance(String config, String choice) {
		println("Running Binance application...");
		binanceMain = new BinanceMain(config);

		switch (choice.toLowerCase()) {
		case "1":
			println("The application will start in automatic mode.");
			binanceMain.runAutoMode();
			break;
		case "2":
			println("The application will start in manual mode.");
			println("Please type the stablecoin to use (ex: USDT): ");
			String stablecoin = userInput.nextLine();
			println("Please type the cryptos you want to trade (hyphen separated, ex: BTC-ETH-AXS): ");
			String cryptos = userInput.nextLine();
			String[] coins = cryptos.split("-");
			println(
					"The application will start in manual mode with the following parameters: \n- Stable coin: "
							+ stablecoin + "\n- Trading coins: " + cryptos);
			binanceMain.runManualMode(stablecoin, Arrays.asList(coins));
			break;
		case "3":
			println("Please type the first currency: ");
			String firstCurr = userInput.nextLine();
			println("Please type the second currency: ");
			String secondCurr = userInput.nextLine();
			binanceMain.getCurrentPairPrice(firstCurr, secondCurr);
			break;
		case "4":
			break;
		case "5":
			println(binanceMain.getCurrentAssetFunding().toString());
			break;
		case "inserttransac":
			println("Pair:");
			String pair = userInput.nextLine();
			println("Choosen pair: " + pair);
			Date date = new Date();
			Transaction transac = new Transaction();
			transac.setPair(pair);
			transac.setTimestamp(date);
			TransactionRepository repo = context.getBean(TransactionRepository.class);
			transac = repo.save(transac);
		default:
			break;
		}

	}
	
	private static void println(Object obj) {
		System.out.println(obj);
	}
}
