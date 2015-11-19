package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import com.abc.accounts.Checking;
import com.abc.accounts.MaxiSavings;
import com.abc.accounts.Savings;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Checking();
        Account savingsAccount = new Savings();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
        
        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Savings());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar") .openAccount(new Savings());
        oscar.openAccount(new Checking());
        assertEquals(2, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void transferBetweenAccounts(){
    	Account savings = new Savings();
    	Account checking = new Checking();
    	Customer oscar = new Customer("Oscar");
    
    	oscar.openAccount(savings);
    	oscar.openAccount(checking);
    	
    	savings.deposit(200.00);
    	oscar.transfer("Savings Account", "Checking Account", 100.00);
    
    	assertEquals("Statement for Oscar\n" +
    			"\n" + 
    			"Savings Account\n" +
    			"  deposit $200.00\n" +
    			"  withdrawal $100.00\n" +
    			"Total $100.00\n" +
    			"\n" +
    			"Checking Account\n" +
    			"  deposit $100.00\n" +
    			"Total $100.00\n" +
    			"\n" +
    			"Total In All Accounts $200.00", oscar.getStatement());
    }

    @Test
    public void maxiSavingsInterest(){
    	Calendar transactionTime = Calendar.getInstance();
    	transactionTime.add(Calendar.DATE, -15);
    	
    	Customer bob = new Customer("Bob");
    	Account penalty = new MaxiSavings();
    	Account noPenalty = new MaxiSavings();
    	
    	bob.openAccount(penalty);
    	bob.openAccount(noPenalty);
    	penalty.deposit(200);
    	penalty.withdraw(20);
    	noPenalty.deposit(200);
    	noPenalty.withdrawSetDate(20, transactionTime.getTime());
    	
    	assertEquals(28.16, bob.totalInterestEarned(), 0);
    }
}