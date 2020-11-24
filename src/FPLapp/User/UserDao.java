package FPLapp.User;

import java.util.ArrayList;

public interface UserDao {
     public ArrayList<User> getAllUsers();
     public void updateUser(User user);
     public void addUser(User user);
     public boolean deleteUser(User user);
}
