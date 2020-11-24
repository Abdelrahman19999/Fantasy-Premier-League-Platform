package FPLapp;

import FPLapp.User.User;
import FPLapp.User.UserDao;

import java.util.Scanner;

public class Authenticator {
    private UserDao userdata;
    public Authenticator(UserDao userdata){
        this.userdata = userdata;
    }
    public boolean checkAuthority() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for(User user : userdata.getAllUsers()){
            if(user.getEmail().compareTo(email) == 0){
                return user.comparePassword(password);
            }
        }
        return false;
    }
    public boolean checkAvailabilty(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String newname = scanner.nextLine();
        System.out.print("Email: ");
        String newemail = scanner.nextLine();
        System.out.print("Password: ");
        String newpassword = scanner.nextLine();
        System.out.print("ID: ");
        int newID = scanner.nextInt();
        for (User user : userdata.getAllUsers()){
            if(user.getEmail().compareTo(newemail) == 0)
                return false;
        }
        userdata.addUser(newname, newemail, newpassword, newID);
        return true;
    }
}
