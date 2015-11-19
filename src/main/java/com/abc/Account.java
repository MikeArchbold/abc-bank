package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account {

    protected List<Transaction> transactions;

    public abstract double interestEarned();
    public abstract String getAccountName();
    
    public Account() {
        this.transactions = new ArrayList<Transaction>();
    }
    
    protected double roundMoney(double money){
    	BigDecimal convert = new BigDecimal(money).setScale(2, RoundingMode.HALF_UP);
		return convert.doubleValue();	
    }
    
    protected double compoundInterest(double interest){
    	return Math.pow(1+ interest/365, 365) - 1;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }
    
    //overdrafting could be possible but it would make interest
    //calculations more complicated
    public void withdraw(double amount) {
    	if (amount <= 0 || amount > sumTransactions()){
    		throw new IllegalArgumentException("amount must be greater than zero"
    				+ " and the account cannot overdraft");
    	}else{
    		transactions.add(new Transaction(-amount));
    	}
    }
    
    public void withdrawSetDate(double amount, Date transactionDate){
    	if (amount <= 0 || amount > sumTransactions()){
    		throw new IllegalArgumentException("amount must be greater than zero"
    				+ " and the account cannot overdraft");
    	}else{
    		transactions.add(new Transaction(-amount, transactionDate));
    	}
    }
    
    public double sumTransactions() {
    	double sum = 0.0;
        for (Transaction t: transactions)
            sum += t.getAmount();
        return sum;
    }
    
    public List<Transaction> getTransactions(){
    	return transactions;
    }
}