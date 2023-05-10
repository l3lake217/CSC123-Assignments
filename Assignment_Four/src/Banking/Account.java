package Banking;

import java.util.ArrayList;

public class Account {
	//Fields
	
	public int accountNumber;
	private String type;
	private boolean accountOpen;
	public double balance;
	private Person accountHolder;
	private String status;
	private int transactionID=1;
	public ArrayList<String> transactions=new ArrayList<String>();
	public boolean withdrew;
	public boolean deposited;
	public int ODLimit; 
	
	//Constructor
	public Account(int accountNumber, String type, Person accountHolder,int odlimit) {
			this.accountNumber = accountNumber;
			this.type = type;
			this.accountHolder = accountHolder;
			this.ODLimit = odlimit;
			accountOpen=true;
			accountStatus();
			System.out.print(Messages.MSG_THANK_YOU+accountNumber+"\n");
		}
	
		public boolean withdraw(double amount) {
			if(this.balance-amount<this.ODLimit*-1) {
				this.withdrew=false;
					return false;
				}
			if(accountOpen==false&&this.balance-amount<0) {
				this.withdrew=false;
					return false;
				}
		
			if((this.balance-amount>=this.ODLimit*-1&&accountOpen)) {
				this.balance=this.balance-amount;
				String tempS = this.transactionID+" : "+"(DEBIT)" +" : "+amount;
				transactions.add(tempS);
					this.transactionID++;
					this.withdrew=true;
					return true;
				}
			
			if(accountOpen==false&&this.balance>0&&this.balance-amount>=0) {
				this.balance=this.balance-amount;
				String tempS = this.transactionID+" : "+"(DEBIT)" +" : "+amount;
				transactions.add(tempS);
					this.transactionID++;
					this.withdrew=true;
					return true;
				}
			return true;
		}
	
		public boolean deposit(double amount) {
			if((accountOpen==false&&this.balance>0)) {
				this.deposited=false;
			}
			if(amount<0) return false;
			if(accountOpen) {
				this.balance=this.balance+amount;
				String tempS = this.transactionID+" : "+"(CREDIT)" +" : "+amount;
				transactions.add(tempS);
				this.transactionID++;
				this.deposited=true;
			}
			if(accountOpen==false&&this.balance<0) {
				this.balance=this.balance+amount;
				String tempS = this.transactionID+" : "+"(CREDIT)" +" : "+amount;
				transactions.add(tempS);
				this.transactionID++;
				this.deposited=true;
				return true;
			}
			return true;
		}
	
	public void isPositive() {
		if(this.balance>0) {
			
		}
	}
	
	public boolean isOpen() {
		return this.accountOpen;
	}
	
	public void closeAccount() {
		this.accountOpen=false;
		accountStatus();
	}
	
	public double getODLimit() {
		return this.ODLimit;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void accountStatus() {
		if(this.accountOpen) {
			this.status="Account Open";
		}
		else {
			this.status="Account Closed";
		}
	}
	
	public String toString() {
		return this.accountNumber+"("+type+") : "+this.accountHolder.toString()+" : "+this.balance+" : "+this.status;
	}
	

}