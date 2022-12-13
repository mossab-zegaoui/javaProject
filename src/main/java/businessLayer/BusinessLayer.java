package businessLayer;

import dataLayer.DataLayer;
import dataLayer.gestionProduitsInterface;
import dataLayer.gestionUsersInterface;
import model.*;

import java.util.ArrayList;

public class BusinessLayer implements gestionUsersInterface, gestionProduitsInterface {
    static ArrayList<User> listeUser = new ArrayList<User>();
    static ArrayList<Produit> listeProduit = new ArrayList<Produit>();
    static ArrayList<Commande> listeAchat = new ArrayList<>();

    DataLayer dataLayer = new DataLayer();

    public BusinessLayer() {
    }

    @Override
    public ArrayList<Produit> listAllProducts() {
        this.listeProduit = dataLayer.listAllProducts();
        return listeProduit;
    }

    @Override
    public Produit selectProduct(int id) {
        return dataLayer.selectProduct(id);
    }

    @Override
    public boolean saveProduct(Produit produit1) {
        return dataLayer.saveProduct(produit1);
    }

    public boolean deleteProduct(int id) {
        return dataLayer.deleteProduct(id);
    }

    @Override
    public void editProduct(Produit product) {
        dataLayer.updateProduct(product);
    }

    @Override
    public ArrayList<Produit> searchProduct(String key) {
        return dataLayer.searchProduct(key);
    }

    @Override
    public ArrayList<User> listAllUsers() {
        this.listeUser = dataLayer.listAllUsers();
        return listeUser;
    }

    @Override
    public User selectUser(String login) {
        return dataLayer.selectUser(login);
    }

    @Override
    public boolean saveUser(User user) {
        return dataLayer.saveUser(user);
    }

    @Override
    public boolean deleteUser(String login) {
        return dataLayer.deleteUser(login);
    }

    @Override
    public void editUser(User user) {
        dataLayer.updateUser(user);
    }

    public User getUser(String login) {
        return null;
    }

    public String addUser(User u) {
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
