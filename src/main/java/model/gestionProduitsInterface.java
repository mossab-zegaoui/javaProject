package model;

import java.awt.*;
import java.util.ArrayList;

public interface gestionProduitsInterface {

    public ArrayList<Produit> listAllProducts();
    public Produit getProduct(int id);

    public boolean saveProduct(Produit produit);

    public boolean deleteProduct(Produit produit);

    public void editProduct(Produit p);

    public ArrayList<Produit> getProduitsByKey(String key);

    public Produit update(Produit produit);

}
