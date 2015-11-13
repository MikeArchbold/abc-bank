package com.abc.accounts;

import com.abc.Account;

public class MaxiSavings extends Account{

	//simply adding ints makes the code inflexibile
	//may not be worth the flexibility though since its more verbose and complex
	@Override
	public double interestEarned() {
		double lowerInterest = 0.02;
		double higherInterest = 0.05;
		double baseInterest = 0.01;
		int lowerBound = 1000;
		int higherBound = 2000;
		
		double amount = sumTransactions();
		if (amount < lowerBound){
			return amount * lowerInterest;
		}
		else if (amount < higherInterest){
			return (lowerBound * lowerInterest) + ((amount - lowerBound) * higherInterest);
		}
		else{
			return (lowerBound * lowerInterest) + ( (higherBound - lowerBound) * higherInterest) + 
					((amount - higherBound) * baseInterest);
		}
	}

	@Override
	public String getAccountName() {
		return "Maxi Savings Account";
	}

}