# AutoTrading

Vm args to pass :
- -Dkeyfile : file containing the keys (keys visible in com.shenzou.autotrader.binance.BinanceInitializer)
- -Dpassword : AES key to decipher the keyfile

Structure of the key file:
keyName=key

Launch arguments:
- args[0] : Service to use (binance or coinbase) (default: binance), coinbase not implemented
- args[1] : Configuration (testnet or prod) 
