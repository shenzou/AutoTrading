package com.shenzou.autotrader.persistence;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String pair;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date ts;
	
	private Double price;
	
	private Double quantity;

	public Transaction() {

	}

	public Transaction(String pair, Date ts) {
		this.pair = pair;
		this.ts = ts;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long index) {
		this.id = index;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public Date getTimestamp() {
		return ts;
	}

	public void setTimestamp(Date timestamp) {
		this.ts = timestamp;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
