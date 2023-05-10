package Banking;

 class AccountClosedException extends Exception{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountClosedException(String message) {
		 super(message);
	 }
	 
//	public AccountClosedException (boolean accountOpen)  {
//		if(accountOpen == false){   
//			// throw an object of user defined exception
//			Messages.accountClosedException();
//		}  
//       else {   
//    	   System.out.println("Deposit, Success?");
//        }   
}

