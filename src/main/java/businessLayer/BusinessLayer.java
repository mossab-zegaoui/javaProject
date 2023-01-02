package businessLayer;

import dataLayer.DataLayer;
import model.*;

import java.util.ArrayList;

public class BusinessLayer implements gestionUsersInterface, gestionProduitsInterface {
    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Product> products = new ArrayList<Product>();
    static ArrayList<Order> listeAchat = new ArrayList<>();

    DataLayer dataLayer = new DataLayer();

    public BusinessLayer() {
    }

    @Override
    public ArrayList<Product> listAllProducts() {
        this.products = dataLayer.listAllProducts();
        return products;
    }

    @Override
    public Product selectProduct(int id) {
        return dataLayer.selectProduct(id);
    }

    @Override
    public boolean saveProduct(Product produit1) {
        return dataLayer.saveProduct(produit1);
    }

    public boolean deleteProduct(int id) {
        return dataLayer.deleteProduct(id);
    }

    @Override
    public void editProduct(Product product) {
        dataLayer.updateProduct(product);
    }

    @Override
    public ArrayList<Product> getProcessingOrders() {
        return dataLayer.getProcessingOrders();
    }

    @Override
    public ArrayList<Product> shippedProducts() {
        return dataLayer.getshippedProducts();
    }


    @Override
    public ArrayList<User> listAllUsers() {
        this.users = dataLayer.listAllUsers();
        return users;
    }

    @Override
    public User selectUser(int id) {
        return dataLayer.selectUser(id);
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


    public ArrayList<Order> listeAllAchats() {

        this.listeAchat = dataLayer.listeAllAchats();
        return listeAchat;
    }

    public ArrayList<Product> listeAchatUser(String login) {
        this.products = dataLayer.listeAchatUser(login);
        return products;
    }

    public boolean existLogin(User user) {
        return dataLayer.existLogin(user);
    }

    public boolean isRegistered(User user) {
        return dataLayer.isRegistered(user);
    }

    public boolean isAdmin(User user) {
        return dataLayer.isAdmin(user);
    }
}
