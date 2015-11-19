package com.abc.accounts;

import com.abc.Account;

public class Savings extends Account {

	//made everything variable for easier changes for the rates
	@Override
	public double interestEarned() {
		final double lowerRate = 0.001;
		final double higherRate = 0.002;
		final int lowerBound = 1000;
		
		double amount = sumTransactions();
		if (amount < lowerBound){ 
			return roundMoney(amount * compoundInterest(lowerRate));
		}
		else{
			return roundMoney((lowerBound * compoundInterest(lowerRate))
					+ ((amount-lowerBound) * compoundInterest(higherRate)));
		}
	}

	@Override
	public String getAccountName() {
		return "Savings Account";
	}
}