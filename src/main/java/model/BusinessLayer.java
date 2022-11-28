package model;

import java.util.ArrayList;

public class BusinessLayer
        implements gestionUsersInterface, gestionProduitsInterface {
    static ArrayList<User> listeUser = new ArrayList<User>();
    static ArrayList<Produit> listeProduit = new ArrayList<Produit>();
    DataLayer d = new DataLayer();


    public String addUser(User u) {

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
    public ArrayList<User> getUsers() {
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

    public User getUser(String login) {
        this.listeUser = getUsers();
        for (int i = 0; i < listeUser.size(); i++) {
            if (listeUser.get(i).getLogin().equals(login)) return listeUser.get(i);
        }
        return null;
    }

    @Override
    public ArrayList<Produit> listAllProducts() {
        this.listeProduit = d.listAllProducts();
        return listeProduit;
    }

    @Override
    public Produit getProduct(int id) {
        return null;
    }

    @Override
    public boolean saveProduct(Produit produit1) {
        return d.saveProduct(produit1);
    }

    public boolean deleteProduct(Produit produit) {
        return d.deleteProduct(produit);
    }

    @Override
    public void editProduct(Produit product) {
        d.updateProduct(product);
    }

    @Override
    public ArrayList<Produit> getProduitsByKey(String key) {
        return null;
    }

    @Override
    public Produit update(Produit produit) {
        return null;
    }
}
