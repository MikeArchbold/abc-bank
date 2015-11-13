package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<Transaction>();
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

    public abstract double interestEarned();
    public abstract String getAccountName();
    /*public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }*/

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