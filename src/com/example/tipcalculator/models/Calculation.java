package com.example.tipcalculator.models;

public class Calculation {
	public static final int DEFAULTPERCENTAGE  = 15;
	public static final Double DEFAULTTRANSACTION = 10.0;
	public static final int MAXSPLITCOUNT = 100;
	public static final int MINSPLITCOUNT = 1;
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
		this.transactionAmount = DEFAULTTRANSACTION;
		this.percentage = DEFAULTPERCENTAGE;
		this.splitNumber = MINSPLITCOUNT;
		calculate();
	}
	
	public Calculation(double transaction, int percent, int peopleCount){
		this.transactionAmount = transaction;
		this.percentage = percent;
		this.splitNumber = peopleCount;
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
		int incValue = this.splitNumber + 1;
		if(incValue <= MAXSPLITCOUNT){
			setSplitNumber(incValue);
		}
	}
	
	public void decSplitNumber(){
		int decValue = this.splitNumber - 1;
		if(decValue >= MINSPLITCOUNT){
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
