package com.abc.accounts;

import com.abc.Account;

public class Checking extends Account{

	@Override
	public double interestEarned() {
		Double amount = sumTransactions() * compoundInterest(0.001);
		return roundMoney(amount);
	}

	@Override
	public String getAccountName() {
		return "Checking Account";
	}
}
