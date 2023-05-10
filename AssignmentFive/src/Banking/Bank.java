package Banking;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Bank {
	private static int accountNumbers=1000;
	static Scanner intSC=new Scanner(System.in);
	static Scanner stringSC=new Scanner(System.in);
	private static Map<Integer,Account> MAPaccounts=new HashMap<Integer,Account>();
	
	private Bank() {}
	
	public static Account openAccount(String firstName, String lastName, String SSN, String accountType, int odlimit) {
		Person customer=new Person(firstName, lastName,SSN);
		Account account=new Account(accountNumbers++, accountType, customer,odlimit);
		MAPaccounts.put(account.accountNumber, account);
		return account;
	}
	
	public static void openChecking() {
		Messages.askFirstName();
		String fname=stringSC.nextLine();
		Messages.askLastName();
		String lname=stringSC.nextLine();
		Messages.askSSN();
		String ssn=stringSC.nextLine();
		Messages.askODLimit();
		int odlimit=intSC.nextInt();
		Bank.openAccount(fname, lname, ssn, "Checking",odlimit);
	}
	public static void openSaving() {
		Messages.askFirstName();
		String fname=stringSC.nextLine();
		Messages.askLastName();
		String lname=stringSC.nextLine();
		Messages.askSSN();
		String ssn=stringSC.nextLine();
		Bank.openAccount(fname, lname, ssn, "Saving",0);
	}
	
	public static void printAccounts() {
		if(MAPaccounts!=null) {
			for(int a1=1000;a1<MAPaccounts.size()+1000;a1++) {
				System.out.println(MAPaccounts.get(a1)); }		
		}
		try {
			if(MAPaccounts.isEmpty()) { 
				throw new NoSuchAccountException("Currently no accounts with the bank.");}
		}catch(NoSuchAccountException e) {
			System.out.println(e+"\n"); }
	}	
	
	public static  void AccountStatements(int accountNumber) {
		try {
			if(findAccount(accountNumber)!=null) {
				Account account = findAccount(accountNumber);
				double balance = account.balance;
				System.out.print("\n");
				for(String s: account.transactions) {
					System.out.println(s); }
				System.out.println("\nBalance: "+balance);
			}
			else
				Messages.accountNOTfoundMSG();
		}catch(IndexOutOfBoundsException e) {
			try {
				throw new NoSuchAccountException("Account could not be found.");
			}catch(Exception b) {
				System.out.print(b+"\n");
			}
		}
	}	
	
	
	//The following methods must be implemented
	
	public static  Account findAccount(int accountNumber) {
			try {
				if(MAPaccounts.get(accountNumber)==null)
					throw new NoSuchAccountException("Account could not be found.");
			}catch(NoSuchAccountException e) {
				System.out.println(e+"\n");
			}
			
			try {
				for(int c=1000;c<=MAPaccounts.size()+1000;c++) {
					if(accountNumber == MAPaccounts.get(c).accountNumber) {
						Account a =MAPaccounts.get(c);
						return a;
					}
				}
			}catch(NullPointerException e) {
			}
		return null;
	}
	
	public static  boolean deposit(int accountNumber,int amount) {
		if(findAccount(accountNumber)!=null) {
			findAccount(accountNumber).deposit(amount);
			Account account = findAccount(accountNumber);
			
			try {
			if(account.deposited) {
				System.out.println(Messages.MSG_DEPOSIT_SUCCESS+account.balance);
			}
			else {
				throw new AccountClosedException("Account has been closed, Deposit failed.");
			}
			}catch(Exception e) {
				System.out.print(e+"\n");
			}
			return true;
		}
		else 
			return false;
    }

	public static  boolean withdraw(int accountNumber,int amount) {
		if(findAccount(accountNumber)!=null) {
			findAccount(accountNumber).withdraw(amount);
			Account account = findAccount(accountNumber);
			if(account.isOpen()==false) {
				try {
				if(account.withdrew==true)
					System.out.println(Messages.MSG_WITHDRAWL_SUCCESS+account.balance);
				if(account.withdrew==false) 
					throw new AccountClosedException("Account has been closed, Withdraw failed.");
				}catch(Exception e) {
					System.out.print(e+"\n");
				}
			}
			try {
			if(account.isOpen() && account.withdrew) {
				System.out.println(Messages.MSG_WITHDRAWL_SUCCESS+account.balance);
				}
			if(account.isOpen() && account.withdrew==false) {
				throw new InsufficientBalanceException("Not enough funds to withdrawal.");
				}
			}catch(Exception e) {
				System.out.print(e+"\n");
			}
			return true;
		}
		else
			return false;
    } 
	
	public static boolean closeAccount(int accountNumber) {
		if(findAccount(accountNumber)!=null) {
			Account account = findAccount(accountNumber);
			double balance = account.balance;
			account.closeAccount();
			System.out.println("Account closed, current balance is "+balance);
			return true;
		}
		else
			return false;
		
    }
	
	public static  void saveTransactions(int accountNumber) {
		try {
		if(findAccount(accountNumber)!=null) {
			Account account = findAccount(accountNumber);
			double balance = account.balance;
			ArrayList<String> saveTransaction=new ArrayList<String>();
			System.out.print("\n");
			for(String s: account.transactions) {
				saveTransaction.add(s);
			}
			saveTransaction.add("\nBalance: "+balance);
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
				for(int c=0;c<saveTransaction.size();c++){
					writer.write(saveTransaction.get(c));
					writer.write("\n");
				}
				writer.close();
			}catch(IOException e) {
					System.out.println("Could not save to file");
			}
		}
		else
			Messages.accountNOTfoundMSG();
		}catch(IndexOutOfBoundsException e) {
			try {throw new NoSuchAccountException("Account could not be found.");
			}
				catch(Exception b) {System.out.print(b+"\n");
				}
		}
	}	
	
}