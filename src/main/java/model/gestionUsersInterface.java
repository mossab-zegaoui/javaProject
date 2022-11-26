package model;

import java.util.ArrayList;

public interface gestionUsersInterface {

	public String addUser(user u);
	public ArrayList<user> getUsers();
	public boolean estInscris(String login,String pwd);
}
