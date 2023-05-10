package Banking;
import java.util.ArrayList;
import java.util.Scanner;
public class Bank {
	private static ArrayList<Account> accounts=new ArrayList<Account>();
	private static int accountNumbers=1000;
	static Scanner intSC=new Scanner(System.in);
	static Scanner stringSC=new Scanner(System.in);
	public Bank() {}
	
	public static Account openAccount(String firstName, String lastName, String SSN, String accountType, int odlimit) {
		Person customer=new Person(firstName, lastName,SSN);
		Account account=new Account(accountNumbers++, accountType, customer,odlimit);
		accounts.add(account);
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
		System.out.println();
		for(Account a: accounts) {
			System.out.println(a);
		}

	}	
	public static  void AccountStatements(int accountNumber) {
		try {
		if(findAccount(accountNumber)!=null) {
			Account account = findAccount(accountNumber);
			double balance = account.balance;
			System.out.print("\n");
			for(String s: account.transactions) {
				System.out.println(s);
			}
			System.out.println("\nBalance: "+balance);
		}
		else
			Messages.accountNOTfoundMSG();
		}catch(IndexOutOfBoundsException e) {
			Messages.errorInvalidOptionMSG();
		}
	}	
	
	
	//The following methods must be implemented
	
	public static  Account findAccount(int accountNumber) {
//		1 - If the account exists then return Account object
//		2 - If the account does not exist then return null
		for(int c=0;c<=accounts.size();c++) {
			if(accountNumber == accounts.get(c).accountNumber) {
				Account a =accounts.get(c);
				//System.out.println("Account Found!");
				return a;
			}
		}
		return null;
	}
	
	public static  boolean deposit(int accountNumber,int amount) {
//		1 - Find account
//		2 - If account not found then return false
//		3 - If account found then deposit money and return the result of the deposit method
		if(findAccount(accountNumber)!=null) {
			findAccount(accountNumber).deposit(amount);
			Account account = findAccount(accountNumber);
			
			
			if(account.deposited) {
				System.out.println(Messages.MSG_DEPOSIT_SUCCESS+account.balance);
			}
			else
				System.out.println(Messages.MSG_DEPOSIT_FAILED+account.balance);
			
			return true;
		}
		else
			return false;
    }
	
//	public static void ifTrue(Account a) {
//		System.out.println(Messages.MSG_DEPOSIT_SUCCESS+a.balance);
//	}
//	public static void ifTrue2(Account a) {
//		if(a)
//		System.out.println(Messages.MSG_WITHDRAWL_SUCCESS+a.balance);
//	}

	public static  boolean withdraw(int accountNumber,int amount) {
//		1 - Find account
//		2 - If account not found then return false
//		3 - If account found then deposit money and return the result of the withdraw method
		if(findAccount(accountNumber)!=null) {
			findAccount(accountNumber).withdraw(amount);
			Account account = findAccount(accountNumber);
			if(account.isOpen()==false) {
				if(account.withdrew==true)
					System.out.println(Messages.MSG_WITHDRAWL_SUCCESS+account.balance);
				if(account.withdrew==false)
					System.out.println(Messages.MSG_OVER_WITHDREW+account.balance);
			}
			if(account.isOpen() && account.withdrew) {
				System.out.println(Messages.MSG_WITHDRAWL_SUCCESS+account.balance);
				}
			if(account.isOpen() && account.withdrew==false) {
				System.out.println(Messages.MSG_WITHDRAWL_FAILED+account.balance);
				}
			return true;
		}
		else
			return false;
    } 
	
	public static boolean closeAccount(int accountNumber) {
//		1 - Find account
//		2 - If account not found then return false
//		3 - If account found then close account and return true
		try {
		if(findAccount(accountNumber)!=null) {
			Account account = findAccount(accountNumber);
			double balance = account.balance;
			account.closeAccount();
			System.out.println("\nAccount closed, current balance is "+balance+" deposits are no longer possible.");
			return true;
		}
		else
			System.out.println(Messages.MSG_ACCOUNT_NOT_FOUND);
			return false;
		}catch(IndexOutOfBoundsException e) {
			System.out.println(Messages.MSG_ACCOUNT_NOT_FOUND);
		}
		return false;
    }
}