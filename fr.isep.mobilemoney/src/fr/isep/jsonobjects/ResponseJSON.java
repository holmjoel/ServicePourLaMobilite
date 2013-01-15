package fr.isep.jsonobjects;

import java.util.List;

public class ResponseJSON {

	private boolean success, userExisted;
	private long transactionNumber;
	private String message;
	private double balance, amount;
	private List<Transaction> listOfTransactions;

	// getter and setter methods
	public ResponseJSON() {

	}

	public ResponseJSON(boolean success, boolean userExisted,
			long transactionNumber, String message, double balance,
			double amount, List<Transaction> listOfTransactions) {
		super();
		this.success = success;
		this.userExisted = userExisted;
		this.transactionNumber = transactionNumber;
		this.message = message;
		this.balance = balance;
		this.amount = amount;
		this.listOfTransactions = listOfTransactions;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isUserExisted() {
		return userExisted;
	}

	public void setUserExisted(boolean userExisted) {
		this.userExisted = userExisted;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<Transaction> getListOfTransactions() {
		return listOfTransactions;
	}

	public void setListOfTransactions(List<Transaction> listOfTransactions) {
		this.listOfTransactions = listOfTransactions;
	}

	/*
	 * @Override public String toString() { return "ResponseJSON [success=" +
	 * this.success + ", recieverIsNew=" + this.receiverIsNew +
	 * ", transactionNumber=" + this.transactionNumber + ", accountBalance=" +
	 * this.accountBalance + ", message=" + this.message + "]"; }
	 */

}
