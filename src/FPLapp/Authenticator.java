package FPLapp;

import FPLapp.User.User;
import FPLapp.User.UserDao;
import FPLapp.User.UserDaoFile;

public class Authenticator {
	
    private UserDao userdata;
    
    public Authenticator() {
        userdata = new UserDaoFile();
    }
    
    public boolean checkAuthority(String email, String password) {
        for(User user : userdata.getAllUsers()) {
            if(user.getEmail().compareTo(email) == 0) {
                return user.getPassword().equals(password);
            }
        }
        return false;
    }
    
    public boolean checkAvailabilty(String newname, String newemail, String newpassword, String newfavteam){
        int newID = userdata.getAllUsers().size();
        for (User user : userdata.getAllUsers()){
            if(user.getEmail().compareTo(newemail) == 0) {
                return false;
            }
        }
        userdata.addUser(newname, newemail, newpassword, newfavteam, newID);
        return true;
    }
}