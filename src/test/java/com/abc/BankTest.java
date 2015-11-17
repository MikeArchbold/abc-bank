package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.accounts.Checking;
import com.abc.accounts.MaxiSavings;
import com.abc.accounts.Savings;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Checking());
        bank.addCustomer(john);
        
        System.out.println(bank.customerSummary());
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    //tests capabilties of bank instead of customers like previous tests
    //more in line with what current features are included
    @Test
    public void totalInterest(){
    	Bank bank = new Bank();
        
    	Account checkingAccount = new Checking();
        Account savingsAccount = new Savings();
        Account maxiSavingsAccount = new MaxiSavings();
        
        Customer chris = new Customer("Chris").openAccount(checkingAccount);
        Customer selena = new Customer("Selena").openAccount(savingsAccount);
        Customer max = new Customer("Max").openAccount(maxiSavingsAccount);
        
        bank.addCustomer(chris);
        bank.addCustomer(selena);
        bank.addCustomer(max);
        
        checkingAccount.deposit(100.0);
        savingsAccount.deposit(1500.0);
        maxiSavingsAccount.deposit(3000.0);

        assertEquals(172.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}