package com.lines_of_code.gsa.stockwatcher.client;

public class StockPrice {

	private String symbol;
	private double price;
	private double change;

	public String getSymbol() {
		return symbol;
	}

	public double getPrice() {
		return price;
	}

	public double getChange() {
		return change;
	}

	public double getChangePercent() {
		return 10.0 * this.change / this.price;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setChange(double change) {
		this.change = change;
	}

}