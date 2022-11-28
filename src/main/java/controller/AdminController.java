package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.BusinessLayer;
import model.Produit;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminController", value = "/AdminController")
public class AdminController

        extends HttpServlet {
    BusinessLayer businessLayer = new BusinessLayer();
    ArrayList<Produit> productsList;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testVariable = request.getParameter("testVariable");
        if (testVariable == null) {
            listProducts(request, response);
        } else {
            switch (testVariable) {
                case "add":
                    addProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "edit":
                    editProduct(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productsList = businessLayer.listAllProducts();
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher("admin-index.jsp").forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        String image = request.getParameter("image");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(nom, description, categorie, prix, image, quantite);
        businessLayer.saveProduct(product);
        listProducts(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produit produit = new Produit(id);
        businessLayer.deleteProduct(produit);
        listProducts(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        String image = request.getParameter("image");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(id, nom, description, categorie, prix, image, quantite);
        businessLayer.editProduct(product);
        listProducts(request, response);
    }


}
