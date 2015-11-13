package com.abc.accounts;

import com.abc.Account;

public class Checking extends Account{

	@Override
	public double interestEarned() {
		return sumTransactions() * 0.001;
	}

	@Override
	public String getAccountName() {
		return "Checking Account";
	}
}
