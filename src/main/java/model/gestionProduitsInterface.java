package model;

import java.util.ArrayList;

public interface gestionProduitsInterface {

    public ArrayList<Produit> listAllProducts();

    public ArrayList<Produit> searchProduct(String key);

    public Produit selectProduct(int id);

    public boolean saveProduct(Produit produit);

    public boolean deleteProduct(int id);

    public void editProduct(Produit p);


}
