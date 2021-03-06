package FPLapp;

import FPLapp.Player.Player;
import FPLapp.User.Authenticator;

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
        Scanner scanInt = new Scanner(System.in);
        boolean allowed = false;
        String option;
        String nestedOption;
        
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
        
        if (appflow.getLoggedUser().getRole().compareTo("coach") == 0) {
        	do {
				System.out.println("1- Add new player to the system\n");
				System.out.println("2- Create new squad\n");
				System.out.println("3- View squad score\n");
				System.out.println("4- Exit\n\n");
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
						System.out.println("Enter gameweek: ");
						int gameweek = scanInt.nextInt();
						appflow.showSquadScore(gameweek);
						break;
					case "4":
						break;
					default:
						System.out.println("Invalid option.\n");
						break;
				}
			}
        	while(option.compareTo("4") != 0);
        }
			
        else{
        	do {
				System.out.println("1- Add new event\n");
                System.out.println("2- Add new admin\n");
                System.out.println("3- Add new player to the system\n");
                System.out.println("4- Create new squad\n");
                System.out.println("5- View squad score\n");
				System.out.println("6- Exit\n\n");
				System.out.println("Enter your choice: ");
				option = scanner.nextLine();
				switch(option){
					case "1":
						System.out.println("Enter game week: ");
						int gameweek;
						try 
						{
							gameweek = Integer.parseInt(scanner.nextLine());
						}
						catch(Exception e)
						{
							break;
						}
						appflow.initiateEvent(gameweek);
						do
						{
							System.out.println("\n1- Create a match\n");
			                System.out.println("2- Start match\n");
			                System.out.println("3- Add event to ongoing match\n");
			                System.out.println("4- End match\n");
							System.out.println("5- Return\n\n");
							System.out.println("Enter your choice: ");
							nestedOption = scanner.nextLine();
							switch(nestedOption)
							{
								case "1":
									System.out.println("Enter first team: ");
									String team1 = scanner.nextLine();
									System.out.println("Enter second team: ");
									String team2 = scanner.nextLine();
									appflow.createMatch(team1, team2);
									break;
								case "2":
									if(appflow.startMatch())
									{
										System.out.println("Match started!");
									}
									else
									{
										System.out.println("Match is already in progress, or no match has been created!");
									}
									break;
								case "3":
			                        if(appflow.createEvent())
			                        {
			                        	System.out.println("Event created successfully!");
			                        }
			                        else
			                        {
			                        	System.out.println("Invalid input in event creation, or invalid match state!");
			                        }
			                        break;
			                    case "4":
			                    	if(appflow.endMatch())
									{
										System.out.println("Match ended!");
									}
									else
									{
										System.out.println("Match has already ended, or no match has been created!");
									}
			                        break;
			                    case "5":
			                        break;
								default:
									System.out.println("Invalid option.\n");
									break;
							}
						}
						while(nestedOption.compareTo("5") != 0);
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
                    	System.out.println("Enter gameweek: ");
						int gweek = scanInt.nextInt();
						appflow.showSquadScore(gweek);
						break;
                    case "6":
                        break;
					default:
						System.out.println("Invalid option.\n");
						break;
				}
			}
        	while(option.compareTo("6") != 0);
		}
        
        scanner.close();
        scanInt.close();
        
    }
    
    public static void main(String[] args) {
        run();
    }
}
