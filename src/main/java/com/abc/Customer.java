package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    public void transfer(String type1, String type2, Double amount){
    	Account withdrawAccount = null;
    	Account depositAccount = null;
    	
    	for (Account a : accounts){
    		if (type1.equals(a.getAccountName())){
    			withdrawAccount = a;
    			break;
    		}
    	}
    	
    	for (Account a : accounts){
    		if (type2.equals(a.getAccountName())){
    			depositAccount = a;
    			break;
    		}
    	}
    	
    	if (withdrawAccount == null || depositAccount == null){
    		throw new IllegalArgumentException("account not found");
    	} else{
    		withdrawAccount.withdraw(amount);
    		depositAccount.deposit(amount);
    	}
    }
    
    private String statementForAccount(Account a) {
    	String s = a.getAccountName() + "\n";
        
        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
