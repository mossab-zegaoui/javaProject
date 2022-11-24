package model;

import java.sql.SQLException;
import java.util.ArrayList;
import model.DataLayer;
public class BusinessLayer implements gestionUsersInterface,gestionProduitsInterface {
	static ArrayList<user> listeUser=new ArrayList<user>();
	static ArrayList<produit> listeProduit=new ArrayList<produit>();


	
	
	public String addUser(user u) {
	
		this.listeUser=getUsers();	
		
		for(int i=0;i<listeUser.size();i++) {
			
			if(u.getLogin().equals(listeUser.get(i).getLogin())) {return "Le login est déjà utilisé";}
			if(u.getEmail().equals(listeUser.get(i).getEmail())) return "Email déjà utilisé";
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
		this.listeUser=getUsers();
		for(int i=0;i<listeUser.size();i++) {
			if(listeUser.get(i).getLogin().equals(login) && listeUser.get(i).getPwd().equals(pwd)) return true;
		}
		return false;
	}
	public user getUser(String login) {
		this.listeUser=getUsers();
		for(int i=0;i<listeUser.size();i++) {
			if(listeUser.get(i).getLogin().equals(login)) return listeUser.get(i);
		}
		return null;
		
		
		
	}

	@Override
	public ArrayList<produit> getProduits() {
		DataLayer d=new DataLayer();
		this.listeProduit=d.getProduits();
		
		return listeProduit;
	}

}
