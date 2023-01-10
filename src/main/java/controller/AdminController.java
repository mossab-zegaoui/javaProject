package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import businessLayer.BusinessLayer;
import models.Category;
import models.Product;
import models.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "AdminController", value = "/AdminController")
public class AdminController extends HttpServlet {
    BusinessLayer businessLayer;
    ArrayList<Product> products;
    ArrayList<User> userList;
    ArrayList<Category> categories;

    @Override
    public void init(ServletConfig config) throws ServletException {
        businessLayer = new BusinessLayer();
        products = new ArrayList<>();
        userList = new ArrayList<>();
        categories = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
//  Users
            case "listUsers":
                listUsers(request, response);
                break;
            case "newUser":
                showNewFormUser(request, response);
                break;
            case "editUser":
                showEditFormUser(request, response);
                break;
//  Products
            case "listProducts":
                listProducts(request, response);
                break;
            case "newProduct":
                showNewFormProduct(request, response);
                break;
            case "editProduct":
                showEditFormProduct(request, response);
                break;
            case "showProducts":
                showProducts(request, response);
                break;
//  Orders
            case "listOrders":
                listOrders(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
//  Products
            case "insertProduct":
                insertProduct(request, response);
                break;
            case "updateProduct":
                updateProduct(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
//  Users
            case "insertUser":
                insertUser(request, response);
                break;
            case "updateUser":
                updateUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
        }
    }

    //    Orders
    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> processingOrders;
        ArrayList<Product> shippedOrders;
        ArrayList<Product> cancelledOrders;
        processingOrders = businessLayer.getProcessingOrders();
        shippedOrders = businessLayer.shippedOrders();
        cancelledOrders = businessLayer.cancelledOrders();
        request.setAttribute("processingOrders", processingOrders);
        request.setAttribute("shippedOrders", shippedOrders);
        request.setAttribute("cancelledOrders", cancelledOrders);
        request.getRequestDispatcher("orderList.jsp").forward(request, response);

    }

    //    Users
    private void showNewFormUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("userForm.jsp").forward(request, response);
    }

    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = businessLayer.selectUser(id);
        request.setAttribute("user", existingUser);
        ArrayList<Product> userOrders = businessLayer.getUserOrders(id);
        request.setAttribute("userOrders", userOrders);
        request.getRequestDispatcher("userForm.jsp").forward(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userList = businessLayer.listAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pasword = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = new User(login, pasword, email, firstName, lastName);
        businessLayer.saveUser(user);
        request.setAttribute("successMessage", "user has been added");
        listUsers(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = new User(id, login, password, email, firstName, lastName);
        businessLayer.editUser(user);
        request.setAttribute("successMessage", "user has been updated");
        listUsers(request, response);
    }


    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        businessLayer.deleteUser(login);
        request.setAttribute("deleteMessage", "user has been deleted");
        listUsers(request, response);
    }

    //    Products
    private void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        products = businessLayer.listAllProducts();
        request.setAttribute("products", products);
        listCategories(request, response);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    private void showNewFormProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listCategories(request, response);
        request.getRequestDispatcher("productForm.jsp").forward(request, response);
    }

    private void showEditFormProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = businessLayer.selectProduct(id);
        request.setAttribute("product", existingProduct);
        listCategories(request, response);
        request.getRequestDispatcher("productForm.jsp").forward(request, response);
    }


    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        products = businessLayer.listAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part file = request.getPart("image");
        String imageName = file.getSubmittedFileName();
        String uploadPath = "D:/JavaProjects/eCommerceApp/src/main/webapp/images/" + imageName;
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
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        Product product = new Product(name, price, category, quantity, description, imageName);
        businessLayer.saveProduct(product);
        request.setAttribute("products", products);
        request.setAttribute("successMessage", "product inserted successfully");
        listProducts(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part file = request.getPart("image");
        String imageName = file.getSubmittedFileName();
        String uploadPath = "D:/JavaProjects/eCommerceApp/src/main/webapp/images/" + imageName;
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Double price = Double.valueOf(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = new Product(id, name, price, category, quantity, description, imageName);
        businessLayer.editProduct(product);
        request.setAttribute("successMessage", "Product update successfully ");
        listProducts(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        businessLayer.deleteProduct(id);
        request.setAttribute("dangerMessage", "Product deleted");
        listProducts(request, response);
    }

    //    Categories
    private void listCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categories = businessLayer.listAllCategories();
        request.setAttribute("categories", categories);
    }
}
