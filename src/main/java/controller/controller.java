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
import model.BusinessLayer;
import model.produit;
import model.user;

/**
 * Servlet implementation class controller
 */
@WebServlet("/controler")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		if(request.getParameter("inscription")!= null) {
			String login=request.getParameter("login");
			String mdp=request.getParameter("motdepasse");
			String email=request.getParameter("email");
			String nom=request.getParameter("nom");
			String message ="Veuillez remplir tous les champs";
			
			if(nom.equals("") || login.equals("") || mdp.equals("") || email.equals("")) {
				request.setAttribute("message",message);
				request.getRequestDispatcher("inscription.jsp").forward(request, response);
			}
			else {
				message=inscrire(login,mdp,email,nom);
				request.setAttribute("message",message);
				request.getRequestDispatcher("inscription.jsp").forward(request, response);	
			}
		}
		
		if(request.getParameter("connecter")!= null) {
			String login=request.getParameter("login");
			String mdp=request.getParameter("motdepasse");
			if(connecter(login,mdp)) {
				if(login.equals("admin")) {
					BusinessLayer b=new BusinessLayer();
					user u=b.getUser(login);
					HttpSession session = request.getSession();
					session.setAttribute("login",login);
					session.setAttribute("email",u.getEmail());
					session.setAttribute("nom",u.getNom());
					request.getRequestDispatcher("admin-index.jsp").forward(request, response);
				}else {
				BusinessLayer b=new BusinessLayer();
				user u=b.getUser(login);
				HttpSession session = request.getSession();
				session.setAttribute("login",login);
				session.setAttribute("email",u.getEmail());
				session.setAttribute("nom",u.getNom());
				request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
			else {
				String message="Mot de passe ou Login incorrect";
				request.setAttribute("message",message);
				request.getRequestDispatcher("login.jsp").forward(request, response);	
			}
			
			
			
		}
		
		if(request.getParameter("produit")!=null) {
			BusinessLayer b=new BusinessLayer();
			ArrayList<produit> listeProduits=b.getProduits();
			request.setAttribute("listeProduits",listeProduits);
			request.getRequestDispatcher("index.jsp").forward(request, response);	
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String inscrire(String l,String m,String e,String n) {
		user u=new user(l,m,e,n);
		BusinessLayer b=new BusinessLayer();
		String message=b.addUser(u);
		return message;
	}
	
	private boolean connecter(String login,String mdp) {
		BusinessLayer b=new BusinessLayer();
		return b.estInscris(login, mdp);
	}

}
