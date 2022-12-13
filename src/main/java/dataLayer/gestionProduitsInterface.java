package dataLayer;

import model.produit;

import java.util.ArrayList;

public interface gestionProduitsInterface {

    public ArrayList<produit> listAllProducts();

    public ArrayList<produit> searchProduct(String key);

    public produit selectProduct(int id);

    public boolean saveProduct(produit produit);

    public boolean deleteProduct(int id);

    public void editProduct(produit p);


}
