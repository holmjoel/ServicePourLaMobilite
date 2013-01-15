package fr.isep.jsonobjects;

import java.sql.Date;

public class Transaction {

	public static enum TypeOfTransaction{Recharge, Withdrawal, Receipt, Sending};
	private Date date;
	private TypeOfTransaction typeOfTransaction;
	private double amount;
	
	public Transaction(Date date, TypeOfTransaction typeOfTransaction,
			double amount) {
		super();
		this.date = date;
		this.typeOfTransaction = typeOfTransaction;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeOfTransaction getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public void setTypeOfTransaction(TypeOfTransaction typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
