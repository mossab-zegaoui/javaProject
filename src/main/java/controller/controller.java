package controller;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import businessLayer.BusinessLayer;
import model.Produit;
import model.User;

/**
 * Servlet implementation class controller
 */
@WebServlet("/controler")
public class controller
        extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BusinessLayer b = new BusinessLayer();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String testVariable = request.getParameter("testVariable");
        String action = response.getHeader("action");
        if (request.getParameter("inscription") != null) {
            String login = request.getParameter("login");
            String mdp = request.getParameter("motdepasse");
            String email = request.getParameter("email");
            String nom = request.getParameter("nom");
            String message = "Veuillez remplir tous les champs";
            if (nom.equals("") || login.equals("") || mdp.equals("") || email.equals("")) {
                request.setAttribute("message", message);
                request.getRequestDispatcher("inscription.jsp").forward(request, response);
            } else {
                message = inscrire(login, mdp, email, nom);
                request.setAttribute("message", message);
                request.getRequestDispatcher("inscription.jsp").forward(request, response);
            }
        }
        if (request.getParameter("connecter") != null) {
            String login = request.getParameter("login");
            String mdp = request.getParameter("motdepasse");
            if (connecter(login, mdp)) {
                if (login.equals("admin")) {
                    User u = b.getUser(login);
                    HttpSession session = request.getSession();
                    session.setAttribute("login", login);
                    session.setAttribute("email", u.getEmail());
                    session.setAttribute("nom", u.getNom());
                    ArrayList<Produit> listeProduits = b.listAllProducts();
                    request.setAttribute("listeProduits", listeProduits);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                } else {
                    User u = b.getUser(login);
                    HttpSession session = request.getSession();
                    session.setAttribute("login", login);
                    session.setAttribute("email", u.getEmail());
                    session.setAttribute("nom", u.getNom());
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                String message = "Mot de passe ou Login incorrect";
                request.setAttribute("message", message);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        if (request.getParameter("produit") != null) {
            ArrayList<Produit> listeProduits = b.listAllProducts();
            request.setAttribute("listeProduits", listeProduits);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (testVariable != null) {
            switch (testVariable) {
                case "add":
                    addProduct(request, response);
                case "delete":
                    deleteProduct(request, response);
                default:

            }
        }

    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String categorie = request.getParameter("categorie");
        Float prix = Float.valueOf(request.getParameter("prix"));
        String image = request.getParameter("image");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit product = new Produit(nom, description, categorie, prix, image, quantite);
        b.saveProduct(product);
        response.sendRedirect("admin-index.jsp");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        b.deleteProduct(id);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String inscrire(String l, String m, String e, String n) {
        User u = new User(l, m, e, n);
        String message = b.addUser(u);
        return message;
    }

    private boolean connecter(String login, String mdp) {
        return b.estInscris(login, mdp);
    }


}
