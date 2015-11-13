package com.abc.accounts;

import com.abc.Account;

public class Savings extends Account {

	//made everything variable for easier changes for the rates
	@Override
	public double interestEarned() {
		final double lowerRate = 0.01;
		final double higherRate = 0.02;
		final int lowerBound = 1000;
		
		double amount = sumTransactions();
		if (amount < lowerBound)
			return amount * lowerRate;
		else{
			return (lowerBound * lowerRate) + ((amount-lowerBound) * higherRate);
		}
	}

	@Override
	public String getAccountName() {
		return "Savings Account";
	}
}