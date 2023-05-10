package Banking;

public class Messages {
	public static String MSG_GREETING = "Welcome to the bank,";
	public static String MSG_MENU_PROMPT ="\nPlease select an option below\r\n"
										+"------------------------------\r\n"
										+"1 – Open a Checking account\r\n"
										+ "2 – Open Saving Account\r\n"
										+ "3 – List Accounts\r\n"
										+ "4 – Account Statement\r\n"
										+ "5 – Deposit funds\r\n"
										+ "6 – Withdraw funds\r\n"
										+ "7 – Close an account\r\n"
										+ "8 – Exit\r\n\n"
										+ "Choice: ";
	public static String MSG_Exit_Bank = "\nExiting Banking Application, Thank You.";
	public static String MSG_ask_FirstN = "Enter first name: ";
	public static String MSG_ask_LastN = "Enter last name: ";
	public static String MSG_ask_SSN = "Enter social security number: ";
	public static String MSG_ask_ODLimit = "Enter overdraft limit: "; 
	public static String MSG_ask_EMAIL = "Enter email address: "; 
	public static String MSG_ask_ACCOUNT_NUM = "\nEnter account number: "; 
	public static String MSG_ask_AMOUNT_WITHDRAW = "Enter the amount to witdraw: "; 
	public static String MSG_ask_AMOUNT_DEPOSIT = "Enter the amount to deposit: "; 
	public static String MSG_WITHDRAWL_SUCCESS = "\nWithdrawl successful, the new balance is: "; 
	public static String MSG_DEPOSIT_SUCCESS = "\nDeposit successful, the new balance is: "; 
	public static String MSG_THANK_YOU = "\nThank you, the account number is "; 
	public static String MSG_ACCOUNT_NOT_FOUND = "Account not found."; 
	public static String MSG_INVALID_OPTION = "I'm sorry, but that isn't an option. Please select again."; 
	public static String MSG_WITHDRAWL_FAILED = "\nWithdraw failed, the current balance is: "; 
	public static String MSG_DEPOSIT_FAILED = "\nDeposit failed, the current balance is: "; 
	public static String MSG_OVER_WITHDREW = "Withdrawl exceeds limit, the current balance is: "; 
	
	public static void accountNOTfoundMSG() {
		System.out.println(MSG_ACCOUNT_NOT_FOUND);
		System.out.println();
	}
	
	public static void errorInvalidOptionMSG() {
		System.out.println(MSG_INVALID_OPTION);
		System.out.println();
	}
	public static void askWithdrawAmount() {
		System.out.print(MSG_ask_AMOUNT_WITHDRAW);
	}
	public static void askDepositAmount() {
		System.out.print(MSG_ask_AMOUNT_DEPOSIT);
	}
	
	public static void askAccountNumber() {
		System.out.print(MSG_ask_ACCOUNT_NUM);
	}
	public static void printGREETING() {
		System.out.println(MSG_GREETING);
	}
	public static void askFirstName() {
		System.out.print(MSG_ask_FirstN);
	}
	
	public static void askLastName() {
		System.out.print(MSG_ask_LastN);
	}
	
	public static void askODLimit() {
		System.out.print(MSG_ask_ODLimit);
	}
	public static void askSSN() {
		System.out.print(MSG_ask_SSN);
	}
	public static void askEmail() {
		System.out.print(MSG_ask_EMAIL);
	}
	public static void printMenuOptions() {
		System.out.print(MSG_MENU_PROMPT);
	}
	
	public static void printExitMSG() {
		System.out.print(MSG_Exit_Bank);
	}
}
