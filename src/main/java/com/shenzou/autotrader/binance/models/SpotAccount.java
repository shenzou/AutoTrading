package com.shenzou.autotrader.binance.models;

import java.util.Arrays;
import java.util.List;

public class SpotAccount {

	Double makerCommission;
	Double takerCommission;
	Double buyerCommission;
	Double sellerCommission;
	Boolean canTrade;
	Boolean canWithdraw;
	Boolean canDeposit;
	Long updateTime;
	String accountType;
	List<Balances> balances;
	String[] permissions;


	public SpotAccount(Double makerCommission, Double takerCommission, Double buyerCommission, Double sellerCommission,
			Boolean canTrade, Boolean canWithdraw, Boolean canDeposit, Long updateTime, String accountType,
			List<Balances> balances, String[] permissions) {
		super();
		this.makerCommission = makerCommission;
		this.takerCommission = takerCommission;
		this.buyerCommission = buyerCommission;
		this.sellerCommission = sellerCommission;
		this.canTrade = canTrade;
		this.canWithdraw = canWithdraw;
		this.canDeposit = canDeposit;
		this.updateTime = updateTime;
		this.accountType = accountType;
		this.balances = balances;
		this.permissions = permissions;
	}

	public Double getMakerCommission() {
		return makerCommission;
	}

	public void setMakerCommission(Double makerCommission) {
		this.makerCommission = makerCommission;
	}

	public Double getTakerCommission() {
		return takerCommission;
	}

	public void setTakerCommission(Double takerCommission) {
		this.takerCommission = takerCommission;
	}

	public Double getBuyerCommission() {
		return buyerCommission;
	}

	public void setBuyerCommission(Double buyerCommission) {
		this.buyerCommission = buyerCommission;
	}

	public Double getSellerCommission() {
		return sellerCommission;
	}

	public void setSellerCommission(Double sellerCommission) {
		this.sellerCommission = sellerCommission;
	}

	public Boolean getCanTrade() {
		return canTrade;
	}

	public void setCanTrade(Boolean canTrade) {
		this.canTrade = canTrade;
	}

	public Boolean getCanWithdraw() {
		return canWithdraw;
	}

	public void setCanWithdraw(Boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	public Boolean getCanDeposit() {
		return canDeposit;
	}

	public void setCanDeposit(Boolean canDeposit) {
		this.canDeposit = canDeposit;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<Balances> getBalances() {
		return balances;
	}

	public void setBalances(List<Balances> balances) {
		this.balances = balances;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	
	public class Balances {
		String asset;
		String free;
		String locked;
		
		public Balances(String asset, String free, String locked) {
			super();
			this.asset = asset;
			this.free = free;
			this.locked = locked;
		}

		public String getAsset() {
			return asset;
		}

		public void setAsset(String asset) {
			this.asset = asset;
		}

		public String getFree() {
			return free;
		}

		public void setFree(String free) {
			this.free = free;
		}

		public String getLocked() {
			return locked;
		}

		public void setLocked(String locked) {
			this.locked = locked;
		}

		@Override
		public String toString() {
			return "Balances [asset=" + asset + ", free=" + free + ", locked=" + locked + "]";
		}
				
	}

	@Override
	public String toString() {
		return "SpotAccount [makerCommission=" + makerCommission + ", takerCommission=" + takerCommission
				+ ", buyerCommission=" + buyerCommission + ", sellerCommission=" + sellerCommission + ", canTrade="
				+ canTrade + ", canWithdraw=" + canWithdraw + ", canDeposit=" + canDeposit + ", updateTime="
				+ updateTime + ", accountType=" + accountType + ", \nbalances=" + balances.toString() + ", \npermissions="
				+ Arrays.toString(permissions) + "]";
	}
	
	
}
