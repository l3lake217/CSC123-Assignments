package Banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	//Main Method.
	public static void main(String[] args) 
	{
		new Main().run();
	}
	
	//Call this method to run application.
	public  void run() {
		Boolean status=true;
		Scanner intSC=new Scanner(System.in);
		int accntNumber;
		Messages.printGREETING();
		while(status) {
			try {
			Messages.printMenuOptions();
			switch(intSC.nextInt()) {
				case 1: //Open a Checking Account.
					Bank.openChecking();
					break;
				case 2: //Open a Savings Account.
					Bank.openSaving();
					break;
				case 3: //List Accounts. 
					Bank.printAccounts();
					break;
				case 4: //Account Statements.
					Messages.askAccountNumber();
					accntNumber=intSC.nextInt();
					Bank.AccountStatements(accntNumber);
					break;
				case 5: //Deposit Funds.
					Messages.askAccountNumber();
					accntNumber=intSC.nextInt();
					Messages.askDepositAmount();
					int depositAmnt=intSC.nextInt();
					Bank.deposit(accntNumber,depositAmnt);
					break;
				case 6: //Withdraw Funds.
					Messages.askAccountNumber();
					accntNumber=intSC.nextInt();
					Messages.askWithdrawAmount();
					int withdrawAmnt=intSC.nextInt();
					Bank.withdraw(accntNumber,withdrawAmnt);
					break;
				case 7: //Close Account.
					Messages.askAccountNumber();
					accntNumber=intSC.nextInt();
					Bank.closeAccount(accntNumber);
					break;
				case 8: //Exit Bank
					Messages.printExitMSG();
					status=false;
					break;
				default:
					Messages.errorInvalidOptionMSG();
					break;
			}
		
		}catch (InputMismatchException|IllegalStateException e) {
			Messages.errorInvalidOptionMSG();
			intSC=new Scanner(System.in);
		}catch(IndexOutOfBoundsException e){
			Messages.accountNOTfoundMSG();
		}
			
	}
		intSC.close();
	}
}
