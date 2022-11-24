package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataLayer {
	private Connection myCnx;

	public DataLayer() {
	
	}
	
	private void connecter() {

		try {
			String pilote=new String("com.mysql.jdbc.Driver");
			Class.forName(pilote);
                	
		String url="jdbc:mysql://localhost/magasin?serverTimezone=UTC";
	       try {
			this.myCnx=java.sql.DriverManager.getConnection(url,"root", "");
			 System.out.println("Aggecté");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       System.out.println("Connexion établie");
	      
		}catch(ClassNotFoundException e) {System.err.println("Erreur lors du chargement du pilote"+e);}
	}
	

	
	private void deconnecter() {
		 try {
			this.myCnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connexion fermé");
	}


	public boolean addUser(user u) {
	
		connecter();
		Statement st;
		try {
			st = myCnx.createStatement();
			String requete="INSERT INTO user VALUES('"+u.getLogin()+"','"+u.getPwd()+"','"+u.getEmail()+"','"+u.getNom()+"')";
			st.executeUpdate(requete);
			deconnecter();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());		
			deconnecter();
			return false;
		}
	
		
	}
	public ArrayList<produit> getProduits(){
		
		connecter();
		Statement st;
		ArrayList<produit> liste=new ArrayList<>();
		try {
			st = myCnx.createStatement();
			String requete="SELECT * FROM produit";
		ResultSet rs=st.executeQuery(requete);
		while(rs.next()) {
			produit u=new produit(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7)));
			liste.add(u);
		}
		deconnecter();
		return liste;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			deconnecter();
			return liste;
		}
	}

	
	public ArrayList<user> getUsers() {
		connecter();
		Statement st;
		ArrayList<user> liste=new ArrayList<>();
		try {
			st = myCnx.createStatement();
			String requete="SELECT * FROM user";
		ResultSet rs=st.executeQuery(requete);
		while(rs.next()) {
			user u=new user(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			liste.add(u);
		}
		deconnecter();
		return liste;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			deconnecter();
			return liste;
		}
	}
	



			

	
	
	
}
