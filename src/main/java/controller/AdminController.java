package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.BusinessLayer;
import model.Produit;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/")
public class AdminController extends HttpServlet {
    BusinessLayer businessLayer;
    ArrayList<Produit> productsList;

    @Override
    public void init(ServletConfig config) throws ServletException {
        businessLayer = new BusinessLayer();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateProduct(request, response);
                break;
            case "/search":
                searchProducts(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("product-form.jsp").forward(request, response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produit existingProduct = businessLayer.selectProduct(id);
        request.setAttribute("product", existingProduct);
        request.getRequestDispatcher("product-form.jsp").forward(request, response);
    }


    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productsList = businessLayer.listAllProducts();
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher("admin-index.jsp").forward(request, response);
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        System.out.println(key);
        ArrayList<Produit> searchList;
        searchList = businessLayer.searchProduct(key);
        request.setAttribute("searchList", searchList);
        request.getRequestDispatcher("admin-index.jsp").forward(request, response);
    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        String image = request.getParameter("image");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(nom, description, categorie, prix, image, quantite);
        businessLayer.saveProduct(product);
        request.getRequestDispatcher("admin-index").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        businessLayer.deleteProduct(id);
        request.getRequestDispatcher("admin-index").forward(request, response);

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        String image = request.getParameter("image");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(id, nom, description, categorie, prix, image, quantite);
        businessLayer.editProduct(product);
        request.getRequestDispatcher("admin-index").forward(request, response);
    }
}
