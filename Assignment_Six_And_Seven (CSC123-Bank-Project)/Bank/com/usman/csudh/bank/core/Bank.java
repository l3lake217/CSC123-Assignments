package com.usman.csudh.bank.core;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit, String accountCurrency) {
		Customer c=new Customer(firstName,lastName, ssn, accountCurrency);
		Account a=new CheckingAccount(c,overdraftLimit,accountCurrency);
		a.changeAccountCurrency(accountCurrency);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn, String accountCurrency) {
		Customer c=new Customer(firstName,lastName, ssn, accountCurrency);
		Account a=new SavingAccount(c,accountCurrency);
		a.changeAccountCurrency(accountCurrency);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}

	
	
	public static Account lookup(int accountNumber) throws NoSuchAccountException{
		if(!accounts.containsKey(accountNumber)) {
			throw new NoSuchAccountException("\nAccount number: "+accountNumber+" nout found!\n\n");
		}
		
		return accounts.get(accountNumber);
	}
	
	
	public static void makeDeposit(int accountNumber, double amount) throws AccountClosedException, NoSuchAccountException{
		
		lookup(accountNumber).deposit(amount);
		
	}
	
	public static void makeWithdrawal(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdraw(amount);
	}
	
	//runs CSV Reader or HTTP reader, gets data stored in file, checks user input for currency, prints message is conditions are not met.
	public static void currencyConverter(String selling, double amount, String buying) {
		CSVReader csv = new CSVReader();
		csv.runHTTPreader(); //(csv.runCSVReader();) for CSV Reader
		Map<String,String> m=csv.MAPcurrencyValues;
		try {
		//convert from USD.
			if(selling.toUpperCase().equalsIgnoreCase("USD")) {
				double exchangeRate = amount/Double.valueOf((m.get(buying.toUpperCase())));
				System.out.println("\nThe exchange rate is "+m.get(buying.toUpperCase())+" and you will get "
									+buying.toUpperCase()+" "+exchangeRate+"\n");
			}
		//convert to USD.
			if(buying.toUpperCase().equalsIgnoreCase("USD")) {
				double exchangeRate = amount*Double.valueOf((m.get(selling.toUpperCase())));
				System.out.println("\nThe exchange rate is "+m.get(selling.toUpperCase())+" and you will get "
									+buying.toUpperCase()+" "+exchangeRate+"\n");
			}
		//USD not used as one of the currencies.
			if(selling.toUpperCase().equalsIgnoreCase("USD")==false){
				if(buying.toUpperCase().equalsIgnoreCase("USD")==false)
					System.out.println("\n*One of the currencies must be USD*\n");
			}
		//catches null(incorrect exchange rate).
		}catch(NullPointerException e) {
			System.out.println("\n*Exchange rate not found*\n");
		}
	}
	
	public static void closeAccount(int accountNumber) throws NoSuchAccountException {
		lookup(accountNumber).close();
	}

	
	public static double getBalance(int accountNumber) throws NoSuchAccountException {
		return lookup(accountNumber).getBalance();
	}

	public static void listAccounts(OutputStream out) throws IOException{
		
		out.write((byte)10);
		Collection<Account> col=accounts.values();
		
		for (Account a:col) {
			out.write(a.toString().getBytes());
			out.write((byte)10);
		}
		
		out.write((byte)10);
		out.flush();
	}
	
	public static void printAccountTransactions(int accountNumber, OutputStream out) throws IOException,NoSuchAccountException{
		
		lookup(accountNumber).printTransactions(out);
	}
	
	public static void printAccountInformation(int accountNumber) throws NoSuchAccountException {
		
		System.out.println("\nAccount Number: "+accountNumber+"\n"+
		"Name: "+lookup(accountNumber).getAccountHolder().getFirstName()+" "+lookup(accountNumber).getAccountHolder().getLastName()+"\n"+
		"SSN: "+lookup(accountNumber).getAccountHolder().getSSN()+"\n"+
		"Currency: "+lookup(accountNumber).USDrate+"\n"+
		"Currency Balance: "+lookup(accountNumber).USDrate+" "+lookup(accountNumber).getBalance()+"\n"+
		"USD Balance: "+lookup(accountNumber).getUSDbalance()+"\n");
	}
				
	
	
	
	
}
