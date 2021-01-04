package FPLapp;

import FPLapp.Player.Player;

import java.io.IOException;
import java.util.ArrayList;
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

	public static void squadInput(AppFlow appflow , Scanner scanner){
        if(appflow.canAddSquad()) {
            ArrayList<Player> pList = appflow.getPlayersList();
            for(int i = 0; i < pList.size() ; i++){
                System.out.println(pList.get(i).getID() + "-" + pList.get(i).getName() + "("+pList.get(i).getPos()+")");
            }
            int[] IDsList = new int[15];
            boolean invalidInput = false;
            for(int i = 0; i < 15; i++){
                if(invalidInput){
                    System.out.println("**..Invalid ID number, please try again..**");
                    i--;
                    invalidInput = false;
                }
                System.out.println("Enter player ("+(i+1)+") ID to add to your squad: ");
                try {
                    IDsList[i] = Integer.parseInt(scanner.nextLine());
                }
                catch (Exception e){
                    invalidInput = true;
                }
            }
            appflow.addSquad(IDsList);
            System.out.println("Squad added successfully!\n");
        }
        else
            System.out.println("User already has one squad!\n");
    }

    public static void playerInput(AppFlow appflow, Scanner scanner){
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
        if (appflow.addNewPlayer(name, nationality, pos, club, cost)) {
            System.out.println("Player successfully added!\n\n");
        } else {
            System.out.println("Invalid info.\n\n");
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
        
        do {
			if (appflow.getLoggedUser().getRole().compareTo("coach") == 0) {
				System.out.println("1- Add new player to the system\n");
				System.out.println("2- Create new squad\n");
				/*...view squad score option...*/
				System.out.println("3- Exit\n\n");
				System.out.println("Enter your choice: ");
				option = scanner.nextLine();

				switch (option) {
					case "1":
						playerInput(appflow, scanner);
						break;
					case "2":
                        squadInput(appflow, scanner);
						break;
					case "3":
						break;
					default:
						System.out.println("Invalid option.\n");
						break;
				}
			}
			else{
				System.out.println("1- Add new event\n");
                System.out.println("2- Add new admin\n");
                System.out.println("3- Add new player to the system\n");
                System.out.println("4- Create new squad\n");
				System.out.println("5- Exit\n\n");
				System.out.println("Enter your choice: ");
				option = scanner.nextLine();
				switch(option){
					case "1":
						//appflow.addEvent();
						break;
					case "2":
						System.out.println("please enter user email:");
						String email = scanner.nextLine();
						appflow.addNewAdmin(email);
						break;
					case "3":
                        playerInput(appflow, scanner);
                        break;
                    case "4":
                        squadInput(appflow, scanner);
                        break;
                    case "5":
                        break;
					default:
						System.out.println("Invalid option.\n");
						break;
				}
			}
		}
        while(option.compareTo("3") != 0 && option.compareTo("5") != 0);
        
        scanner.close();
        
    }
    
    public static void main(String[] args) {
        run();
    }
}
