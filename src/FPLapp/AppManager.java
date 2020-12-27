package FPLapp;

import java.io.IOException;
import java.util.Scanner;

public class AppManager {

    private static Authenticator authentic;
    private static AppFlow appflow;
    
    public static void cls() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} 
		catch (IOException | InterruptedException ex) {
		}
	}
    
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
		        		System.out.println("Email: ");
		                String email = scanner.nextLine();
		                System.out.println("Password: ");
		                String password = scanner.nextLine();
		                allowed = authentic.checkAuthority(email, password);
		                if(allowed)
		                {
		                	cls();
		                	System.out.println("\nLogged in! Welcome to FPL\n");
		                	appflow = new AppFlow(authentic.getUser(email, password));
		                }
		                else
		                {
		                	System.out.println("\nWrong password or invalid email\n");
		                }
		                break;
		            
		            case "2":
		            	System.out.println("Name: ");
		                String newname = scanner.nextLine();
		                System.out.println("Email: ");
		                String newemail = scanner.nextLine();
		                System.out.println("Password: ");
		                String newpassword = scanner.nextLine();
		                System.out.println("Favourite Team: ");
		                String newfavteam = scanner.nextLine();
		                allowed = authentic.checkAvailabilty(newname, newemail, newpassword, newfavteam);
		                if(allowed)
		                {
		                	cls();
		                	System.out.println("\nSigned up! Welcome to FPL\n");
		                	appflow = new AppFlow(authentic.getUser(newemail, newpassword));
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
        
        do
        {
        	System.out.println("1- Add new player to the system\n");
        	System.out.println("2- Create new squad\n");
        	System.out.println("3- Exit\n\n");
        	System.out.println("Enter your choice: ");
        	option = scanner.nextLine();
        	
        	switch(option)
        	{
        		case "1":
        			System.out.println("Name: ");
        	        String name = scanner.nextLine();
        	        System.out.println("Nationality: ");
        	        String nationality = scanner.nextLine();
        	        System.out.println("Club: ");
        	        String club = scanner.nextLine();
        	        System.out.println("Position (GK - DF - FW - MF): ");
        	        String pos = scanner.nextLine();
        	        System.out.println("Initial Cost: ");
        	        int cost = Integer.parseInt(scanner.nextLine());
        			if(appflow.addNewPlayer(name, nationality, pos , club , cost))
        			{
        				System.out.println("Player successfully added!\n\n");
        			}
        			else
        			{
        				System.out.println("Invalid info.\n\n");
        			}
        			break;
        		case "2":
        			// squad
        			break;
        		case "3":
        			break;
        		default:
	                System.out.println("Invalid option.\n");
	                break;
        	}
        }
        while(option.compareTo("3") != 0);
        
        scanner.close();
        
    }
    
    public static void main(String[] args) {
        run();
    }
}
