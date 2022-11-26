package model;

import java.util.ArrayList;

public class BusinessLayer
        implements gestionUsersInterface, gestionProduitsInterface {
    static ArrayList<user> listeUser = new ArrayList<user>();
    static ArrayList<produit> listeProduit = new ArrayList<produit>();
    DataLayer d = new DataLayer();


    public String addUser(user u) {

        this.listeUser = getUsers();

        for (int i = 0; i < listeUser.size(); i++) {

            if (u.getLogin().equals(listeUser.get(i).getLogin())) {
                return "Le login est déjà utilisé";
            }
            if (u.getEmail().equals(listeUser.get(i).getEmail())) return "Email déjà utilisé";
        }
        DataLayer d;
        d = new DataLayer();
        if (d.addUser(u)) {
            return "Vous étes bien inscrit";

        } else {
            return "Erreur d'inscription";
        }
    }

    public BusinessLayer() {


    }

    @Override
    public ArrayList<user> getUsers() {
        return d.getUsers();
    }

    @Override
    public boolean estInscris(String login, String pwd) {
        this.listeUser = getUsers();
        for (int i = 0; i < listeUser.size(); i++) {
            if (listeUser.get(i).getLogin().equals(login) && listeUser.get(i).getPwd().equals(pwd)) return true;
        }
        return false;
    }

    public user getUser(String login) {
        this.listeUser = getUsers();
        for (int i = 0; i < listeUser.size(); i++) {
            if (listeUser.get(i).getLogin().equals(login)) return listeUser.get(i);
        }
        return null;
    }

    @Override
    public ArrayList<produit> getProduits() {
        this.listeProduit = d.getProduits();
        return listeProduit;
    }

    @Override
    public void saveProduct(produit produit1) {
        listeProduit = d.getProduits();
        listeProduit.add(produit1);
        d.saveProduct(produit1);
    }

    public void deleteProduct(int id) {
        d.deleteProduct(id);
    }

    @Override
    public void editProduct(produit product) {
        d.editProduct(product);
    }
}
