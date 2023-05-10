package com.usman.csudh.bank.core;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import com.usman.csudh.util.UniqueCounter;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String accountName;
	private Customer accountHolder;
	private ArrayList<Transaction> transactions;
	
	private boolean open=true;
	private int accountNumber;
	private String accountCURRENCY;
	public double ACurrency;
	public String USDrate;

	protected Account(String name, Customer customer, String accountCurrency) {
		accountName=name;
		accountHolder=customer;
		transactions=new ArrayList<Transaction>();
		accountNumber=UniqueCounter.nextValue();
		accountCURRENCY=accountCurrency;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public Customer getAccountHolder() {
		return accountHolder;
	}

	public double getBalance() {
		double workingBalance=0;
		workingBalance*=ACurrency;
		for(Transaction t: transactions) {
			if(t.getType()==Transaction.CREDIT)workingBalance+=t.getAmount();
			else workingBalance-=t.getAmount();
		}
		//workingBalance*=ACurrency;
		return workingBalance;
	}
	
	public double getUSDbalance() {
		double i = getBalance();
		if(accountCURRENCY.equalsIgnoreCase("USD")) {
			return i;
		}
		else
			return i/ACurrency;
	}
	
	
	public void changeAccountCurrency(String currency) {
		CSVReader csv = new CSVReader();
		csv.runHTTPreader();
		Map<String,String> m=csv.MAPcurrencyValues;
		if(m.get(currency.toUpperCase())!=null) {
			if(currency.toUpperCase().equalsIgnoreCase("USD")==false) {
				ACurrency=(1/Double.valueOf(m.get(currency.toUpperCase())));
				System.out.println("*Foreign Currency Found*");
				USDrate=currency.toUpperCase();
			}
			else {
				ACurrency=Double.valueOf(m.get(currency.toUpperCase()));
				System.out.println("*Foreign Currency Found*");
				USDrate=currency.toUpperCase();
			}
		}
		else {
			ACurrency=1;
			System.out.println("*Conversion did not work, Conversion rate is USD*");
			USDrate="USD";
		}
	}
	
	public void deposit(double amount)  throws AccountClosedException{
		double balance=getBalance();
		if(!isOpen()&&balance>=0) {
			throw new AccountClosedException("\nAccount is closed with positive balance, deposit not allowed!\n\n");
		}
		transactions.add(new Transaction(Transaction.CREDIT,amount));
	}
	
	
	
	
	public void withdraw(double amount) throws InsufficientBalanceException {
			
		double balance=getBalance();
			
		if(!isOpen()&&balance<=0) {
			throw new InsufficientBalanceException("\nThe account is closed and balance is: "+balance+"\n\n");
		}
		
		transactions.add(new Transaction(Transaction.DEBIT,amount));
	}
	
	public void close() {
		open=false;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public String toString() {
		String aName=accountNumber+"("+accountName+")"+" : "+accountHolder.toString()+" : "+USDrate+" : "+getBalance()+" : "+getUSDbalance()+" : "+(open?"Account Open":"Account Closed");
		return aName;
	}
	 
	public void printTransactions(OutputStream out) throws IOException {
		
		out.write("\n\n".getBytes());
		out.write("------------------\n".getBytes());
	
		for(Transaction t: transactions) {
			out.write(t.toString().getBytes());
			out.write((byte)10);
		}
		out.write("------------------\n".getBytes());
		out.write(("Balance: "+getBalance()+"\n\n\n").getBytes());
		out.flush();
		
	}
}
