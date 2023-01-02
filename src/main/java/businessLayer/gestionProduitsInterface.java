package businessLayer;

import model.Product;

import java.util.ArrayList;

public interface gestionProduitsInterface {

    public ArrayList<Product> listAllProducts();

    public Product selectProduct(int id);

    public boolean saveProduct(Product produit);

    public boolean deleteProduct(int id);

    public void editProduct(Product p);

    public ArrayList<Product> getProcessingOrders();


    ArrayList<Product> shippedProducts();
}
