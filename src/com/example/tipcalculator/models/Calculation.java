package com.example.tipcalculator.models;

public class Calculation {
	
	private Double transactionAmount;
	private Integer percentage;
	private Integer splitNumber;
	
	private Double total;
	private Double individualTotal;
	private Double tipAmount;
	private Double individualTipAmount;
	
	public static Calculation instance;
	
	public static Calculation getInstance(){
		if(instance == null){
			instance = new Calculation();
		}
		return instance;
	}
	
	private Calculation(){
		Settings s = Settings.getInstance();
		this.transactionAmount = s.getDefaultTransaction();
		this.percentage = s.getDefaultPercentage();
		this.splitNumber = s.getMinSplitCount();
		calculate();
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
		calculate();
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
		calculate();
	}

	public Integer getSplitNumber() {
		return splitNumber;
	}

	public void incSplitNumber(){
		Settings s = Settings.getInstance();
		int incValue = this.splitNumber + 1;
		if(incValue <= s.getMaxSplitCount()){
			setSplitNumber(incValue);
		}
	}
	
	public void decSplitNumber(){
		Settings s = Settings.getInstance();
		int decValue = this.splitNumber - 1;
		if(decValue >= s.getMinSplitCount()){
			setSplitNumber(decValue);
		}
	}
	
	public void setSplitNumber(int splitNumber) {
		this.splitNumber = splitNumber;
		calculate();
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
		calculate();
	}
	
	public void calculate(){
		this.tipAmount = (this.transactionAmount * (this.percentage)/100);
		this.total = this.transactionAmount + this.tipAmount;
		this.individualTotal = this.total / this.splitNumber;
		this.individualTipAmount = this.tipAmount / this.splitNumber;
	}

	public Double getIndividualTotal() {
		return individualTotal;
	}

	public void setIndividualTotal(Double individualTotal) {
		this.individualTotal = individualTotal;
	}

	public Double getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(Double tipAmount) {
		this.tipAmount = tipAmount;
	}
	
	public Double getIndividualTipAmount() {
		return individualTipAmount;
	}

	public void setIndividualTipAmount(Double individualTipAmount) {
		this.individualTipAmount = individualTipAmount;
	}
	
	
}
