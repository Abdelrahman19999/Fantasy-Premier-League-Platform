package FPLapp.User;

import java.util.ArrayList;

public interface UserDao {
     ArrayList<User> getAllUsers();
     void updateUser(User user);
     void addUser(User user);
     boolean deleteUser(User user);
     void addUser(String newname, String newemail, String newpassword, int newID);
}
