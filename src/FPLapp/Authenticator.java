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
 
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for(User user : userdata.getAllUsers()) {
            if(user.getEmail().compareTo(email) == 0) {
                return user.getPassword().equals(password);
            }
        }
      
        return false;
    }
    
    public boolean checkAvailabilty(Scanner scanner){
        System.out.print("Name: ");
        String newname = scanner.nextLine();
        System.out.print("Email: ");
        String newemail = scanner.nextLine();
        System.out.print("Password: ");
        String newpassword = scanner.nextLine();
        System.out.print("Favourite Team: ");
        String newFavTeam = scanner.nextLine();
        int newID = userdata.getAllUsers().size();
        for (User user : userdata.getAllUsers()){
            if(user.getEmail().compareTo(newemail) == 0) {
                return false;
            }
        }
        userdata.addUser(newname, newemail, newpassword, newFavTeam, newID);
        return true;
    }
}
