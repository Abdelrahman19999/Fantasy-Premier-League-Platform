package FPLapp.User;

public interface UserDao {
     public ArrayList<User> getAllUsers();
     public void updateUser(User);
     public void addUser(User);
     public boolean deleteUser(User);
}
