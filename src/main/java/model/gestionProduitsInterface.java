package model;

import java.util.ArrayList;

public interface gestionProduitsInterface {

    public ArrayList<produit> getProduits();

    public void saveProduct(produit p);

    public void deleteProduct(int id);

    public void editProduct(produit p);

}
