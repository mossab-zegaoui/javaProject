package businessLayer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import models.Product;

import java.util.ArrayList;

public interface gestionProduitsInterface {

    public ArrayList<Product> listAllProducts();

    public Product selectProduct(int id);

    public boolean saveProduct(Product produit);

    public boolean deleteProduct(int id);

    public void editProduct(Product p);

    public ArrayList<Product> getProcessingOrders();


    ArrayList<Product> shippedOrders();

    ArrayList<Product> getUserOrders(int id);

    ArrayList<Product> cancelledOrders();

    ArrayList<Category> listAllCategories();
}
