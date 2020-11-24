package FPLapp;

import java.util.Scanner;

public class AppManager {
    private Authenticator authentic;
    private boolean allowed;
    public AppManager(){}

    public void run(){
        int option;
        System.out.println("Welcome to FPL App, enter your option");
        System.out.println("1. Login to an existed account");
        System.out.println("2. Register a new FPL account");
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();
        switch (option) {
            case 1:
                allowed = authentic.checkAuthority();
                break;
            case 2:
                allowed = authentic.checkAvailabilty();
                break;
            default:
                System.out.println("Invalid option.");
        }
        if(allowed){
            //do functionalities
        }
        else {
            // do not allow to do functionalities.. try again
        }
    }
}
