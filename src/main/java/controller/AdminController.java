package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import businessLayer.BusinessLayer;
import model.Commande;
import model.Produit;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "AdminController", value = "/AdminController")
public class AdminController extends HttpServlet {
    BusinessLayer businessLayer;
    ArrayList<Produit> productsList;
    ArrayList<User> usersList;
    ArrayList<Commande> achatsList;

    @Override
    public void init(ServletConfig config) throws ServletException {
        businessLayer = new BusinessLayer();
        usersList = businessLayer.listAllUsers();
        productsList = businessLayer.listAllProducts();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "newProduct":
                showNewForm(request, response);
                break;
            case "insertProduct":
                insertProduct(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "editProduct":
                showEditForm(request, response);
                break;
            case "updateProduct":
                updateProduct(request, response);
                break;
            case "searchProduct":
                searchProducts(request, response);
                break;
            case "listProducts":
                listProducts(request, response);
                break;
            case "listUsers":
                listUsers(request, response);
                break;
            case "newUser":
                showNewFormUser(request, response);
                break;
            case "insertUser":
                insertUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
            case "editUser":
                showEditFormUser(request, response);
                break;
            case "updateUser":
                updateUser(request, response);
                break;
            case "listAchats":
                listAchats(request, response);
                break;
        }
    }

    private void listAchats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        achatsList = businessLayer.listeAllAchats();
        request.setAttribute("achatsList", achatsList);
        request.getRequestDispatcher("achatList.jsp").forward(request, response);

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String nom = request.getParameter("nom");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(login, password, email, nom);
        businessLayer.editUser(user);
        usersList = businessLayer.listAllUsers();
        request.setAttribute("usersList", usersList);
        request.setAttribute("successMessage", "user has been updated");
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        User existingUser = businessLayer.selectUser(login);
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("userForm.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        businessLayer.deleteUser(login);
        usersList = businessLayer.listAllUsers();
        request.setAttribute("usersList", usersList);
        request.setAttribute("deleteMessage","user has been deleted");
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String nom = request.getParameter("nom");
        String pasword = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(login, pasword, email, nom);
        businessLayer.saveUser(user);
        usersList = businessLayer.listAllUsers();
        request.setAttribute("usersList", usersList);
        request.setAttribute("successMessage","user has been added");
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    private void showNewFormUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("userFrom.jsp");
    }

    //          USER
    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        usersList = businessLayer.listAllUsers();
        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }


    //    Products
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("product-form.jsp").forward(request, response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produit existingProduct = businessLayer.selectProduct(id);
        request.setAttribute("product", existingProduct);
        request.getRequestDispatcher("productForm.jsp").forward(request, response);
    }


    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productsList = businessLayer.listAllProducts();
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        ArrayList<Produit> searchList;
        searchList = businessLayer.searchProduct(key);
        request.setAttribute("searchList", searchList);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part file = request.getPart("image");
        String imageName = file.getSubmittedFileName();
        String uploadPath = "D:/JavaProjects/eCommerceApp/src/main/webapp/img/" + imageName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(uploadPath);
            InputStream inputStream = file.getInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(nom, description, categorie, prix, imageName, quantite);
        businessLayer.saveProduct(product);
        productsList = businessLayer.listAllProducts();
        request.setAttribute("productsList", productsList);
        request.setAttribute("successMessage", "product inserted successfully");
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        businessLayer.deleteProduct(id);
        productsList = businessLayer.listAllProducts();
        request.setAttribute("productsList", productsList);
        request.setAttribute("dangerMessage", "Product deleted");
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part file = request.getPart("image");
        String imageName = file.getSubmittedFileName();
        String uploadPath = "D:/JavaProjects/eCommerceApp/src/main/webapp/img/" + imageName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(uploadPath);
            InputStream inputStream = file.getInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(id, nom, description, categorie, prix, imageName, quantite);
        businessLayer.editProduct(product);
        productsList = businessLayer.listAllProducts();
        request.setAttribute("productsList", productsList);
        request.setAttribute("successMessage", "Product update successfully ");
        request.getRequestDispatcher("productList.jsp").forward(request, response);

    }
}
