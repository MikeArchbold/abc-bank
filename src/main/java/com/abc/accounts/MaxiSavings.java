package com.abc.accounts;

import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;

import com.abc.Account;
import com.abc.Transaction;

public class MaxiSavings extends Account{
	
	//simply adding ints makes the code inflexibile
	//may not be worth the flexibility though since its more verbose and complex
	@Override
	public double interestEarned() {	
		final double lowerInterest = 0.05;
		final double upperInterest = 0.1;
		Transaction transaction = null;
		
		Calendar datePenality = Calendar.getInstance();
    	datePenality.add(Calendar.DATE, -10);
    	Date penalty = datePenality.getTime();
    	Double amount = sumTransactions();
    	
		ListIterator<Transaction> iterate = transactions.listIterator(transactions.size());
		
		while (iterate.hasPrevious()){
			if (iterate.previous().getAmount() < 0){
				transaction = iterate.next();
				break;
			}
		}
		
    	if (penalty.compareTo(transaction.getDate()) > 0){
			return amount * upperInterest;
		}
    	else{
    		return amount * lowerInterest;
    	}
	}

	@Override
	public String getAccountName() {
		return "Maxi Savings Account";
	}
}