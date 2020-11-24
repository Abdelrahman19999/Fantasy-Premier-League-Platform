package FPLapp;

import java.util.Scanner;

public class AppManager {
    
    private static Authenticator authentic;
    
    public static void run(){
    	authentic = new Authenticator();
        Scanner scanner = new Scanner(System.in);
        boolean allowed = false;
        String option;
        
        while(!allowed)
        {
        	try
        	{
	        	System.out.println("Welcome to FPL App, enter your option");
	            System.out.println("1. Login to an existed account");
	            System.out.println("2. Register a new FPL account");
	            System.out.println("3. Exit");
		        option = scanner.nextLine();
		        switch (option) {
		            case "1":
		                allowed = authentic.checkAuthority(scanner);
		                if(allowed)
		                {
		                	System.out.println("\nLogged in! Welcome to FPL\n");
		                }
		                else
		                {
		                	System.out.println("\nWrong password or invalid email\n");
		                }
		                break;
		            case "2":
		                allowed = authentic.checkAvailabilty(scanner);
		                if(allowed)
		                {
		                	System.out.println("\nSigned up! Welcome to FPL\n");
		                }
		                else
		                {
		                	System.out.println("\nEntered email is already registered to an account\n");
		                }
		                break;
		            case "3":
		            	return;
		            default:
		                System.out.println("Invalid option.\n");
		        }
        	}
        	catch(Exception e)
        	{
        		System.out.println("Invalid option.\n");
        	}
        }
        
        scanner.close();
        
        // go to rest of program features, in different class.
    }
    
    public static void main(String[] args) {
        run();
    }
}
