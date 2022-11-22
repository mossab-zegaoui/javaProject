package model;

import java.sql.SQLException;
import java.util.ArrayList;
import model.DataLayer;
public class BusinessLayer implements gestionUsersInterface {
	static ArrayList<user> liste=new ArrayList<user>();


	
	
	public String addUser(user u) {
	
		this.liste=getUsers();	
		
		for(int i=0;i<liste.size();i++) {
			
			if(u.getLogin().equals(liste.get(i).getLogin())) {return "Le login est déjà utilisé";}
			if(u.getEmail().equals(liste.get(i).getEmail())) return "Email déjà utilisé";
		}
		DataLayer d;
		d = new DataLayer();
		if(d.addUser(u)) {
			return"Vous étes bien inscrit";
			
		}else {
			return"Erreur d'inscription";
		}		
	}

	public BusinessLayer() {
		
		
	}

	@Override
	public ArrayList<user> getUsers() {
		DataLayer d=new DataLayer();
		
		return d.getUsers();
	}

	@Override
	public boolean estInscris(String login, String pwd) {
		this.liste=getUsers();
		for(int i=0;i<liste.size();i++) {
			if(liste.get(i).getLogin().equals(login) && liste.get(i).getPwd().equals(pwd)) return true;
		}
		return false;
	}
	public user getUser(String login) {
		this.liste=getUsers();
		for(int i=0;i<liste.size();i++) {
			if(liste.get(i).getLogin().equals(login)) return liste.get(i);
		}
		return null;
		
		
		
	}

}
