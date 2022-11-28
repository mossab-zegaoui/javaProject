package model;

import java.util.ArrayList;

public interface gestionUsersInterface {

	public String addUser(User u);
	public ArrayList<User> getUsers();
	public boolean estInscris(String login,String pwd);
}
