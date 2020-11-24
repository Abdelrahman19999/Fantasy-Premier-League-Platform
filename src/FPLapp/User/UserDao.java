package FPLapp.User;

import java.util.ArrayList;

public interface UserDao {
     public ArrayList<User> getAllUsers();
     public void updateUser();
     public void addUser();
     public boolean deleteUser();

}
