package businessLayer;

import dataLayer.DataLayer;
import dataLayer.gestionProduitsInterface;
import dataLayer.gestionUsersInterface;
import model.*;

import java.util.ArrayList;

public class BusinessLayer implements gestionUsersInterface, gestionProduitsInterface {
    static ArrayList<user> listeUser = new ArrayList<user>();
    static ArrayList<produit> listeProduit = new ArrayList<produit>();
    static ArrayList<Commande> listeAchat = new ArrayList<>();

    DataLayer dataLayer = new DataLayer();

    public BusinessLayer() {
    }

    @Override
    public ArrayList<produit> listAllProducts() {
        this.listeProduit = dataLayer.listAllProducts();
        return listeProduit;
    }

    @Override
    public produit selectProduct(int id) {
        return dataLayer.selectProduct(id);
    }

    @Override
    public boolean saveProduct(produit produit1) {
        return dataLayer.saveProduct(produit1);
    }

    public boolean deleteProduct(int id) {
        return dataLayer.deleteProduct(id);
    }

    @Override
    public void editProduct(produit product) {
        dataLayer.updateProduct(product);
    }

    @Override
    public ArrayList<produit> searchProduct(String key) {
        return dataLayer.searchProduct(key);
    }

    @Override
    public ArrayList<user> listAllUsers() {
        this.listeUser = dataLayer.listAllUsers();
        return listeUser;
    }

    @Override
    public user selectUser(String login) {
        return dataLayer.selectUser(login);
    }

    @Override
    public boolean saveUser(user user) {
        return dataLayer.saveUser(user);
    }

    @Override
    public boolean deleteUser(String login) {
        return dataLayer.deleteUser(login);
    }

    @Override
    public void editUser(user user) {
        dataLayer.updateUser(user);
    }

    public user getUser(String login) {
        return null;
    }

    public String addUser(user u) {
        return null;
    }

    public boolean estInscris(String login, String mdp) {
        return false;
    }


    public ArrayList<Commande> listeAllAchats() {

        this.listeAchat = dataLayer.listeAllAchats();
        return listeAchat;
    }
}
