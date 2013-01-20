package fr.isep.jsonobjects;

import fr.isep.jsonobjects.Constants.MoneyAction;

public class RequestJSON {

	private int userId;
	private long transactionNumber;
	private String action, numberOrAlias, pin;
	private double amount;
	private MoneyAction moneyAction;

	// getter and setter methods

	public RequestJSON() {

	}

	
	public RequestJSON(int userId, long transactionNumber, String action,
			String numberOrAlias, String pin, double amount,
			MoneyAction moneyAction) {
		super();
		this.userId = userId;
		this.transactionNumber = transactionNumber;
		this.action = action;
		this.numberOrAlias = numberOrAlias;
		this.pin = pin;
		this.amount = amount;
		this.moneyAction = moneyAction;
	}


	public MoneyAction getMoneyAction() {
		return moneyAction;
	}


	public void setMoneyAction(MoneyAction moneyAction) {
		this.moneyAction = moneyAction;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNumberOrAlias() {
		return numberOrAlias;
	}

	public void setNumberOrAlias(String numberOrAlias) {
		this.numberOrAlias = numberOrAlias;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
