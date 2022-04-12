package com.koreait.idev.model;

import java.sql.Date;

public class Order {
	private String id;
	private int amount;
	private Date devDate;
	public String getId() {
		return id;
	}

	public Order(String id, int amount, Date devDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.devDate = devDate;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDevDate() {
		return devDate;
	}
	public void setDevDate(Date devDate) {
		this.devDate = devDate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

