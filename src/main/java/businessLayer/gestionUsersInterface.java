package businessLayer;

import models.User;

import java.util.ArrayList;

public interface gestionUsersInterface {

	public ArrayList<User> listAllUsers();

	public User selectUser(int id);

	public boolean saveUser(User user);

	public boolean deleteUser(String login);

	public void editUser(User user);

    void addUserDetails(User user);
}
