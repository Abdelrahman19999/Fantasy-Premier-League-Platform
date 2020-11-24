package FPLapp;

import FPLapp.User.User;
import FPLapp.User.UserDao;
import FPLapp.User.UserDaoFile;

import java.util.Scanner;

public class Authenticator {
	
    private UserDao userdata;
    
    public Authenticator() {
        userdata = new UserDaoFile();
    }
    
    public boolean checkAuthority(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for(User user : userdata.getAllUsers()) {
            if(user.getEmail().compareTo(email) == 0) {
            	//scanner.close();
                return user.getPassword().equals(password);
            }
        }
       // scanner.close();
        return false;
    }
    
    public boolean checkAvailabilty(Scanner scanner){
        //Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String newname = scanner.nextLine();
        System.out.print("Email: ");
        String newemail = scanner.nextLine();
        System.out.print("Password: ");
        String newpassword = scanner.nextLine();
        int newID = userdata.getAllUsers().size();
        for (User user : userdata.getAllUsers()){
            if(user.getEmail().compareTo(newemail) == 0) {
            	//scanner.close();
                return false;
            }
        }
        userdata.addUser(newname, newemail, newpassword, newID);
        //scanner.close();
        return true;
    }
}
