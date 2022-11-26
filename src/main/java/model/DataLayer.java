package model;

import java.sql.*;
import java.util.ArrayList;

public class DataLayer {
    private Connection myCnx;

    public DataLayer() {

    }

    private void connecter() {

        try {
            String pilote = new String("com.mysql.jdbc.Driver");
            Class.forName(pilote);

            String url = "jdbc:mysql://localhost/magasin?serverTimezone=UTC";
            try {
                this.myCnx = java.sql.DriverManager.getConnection(url, "root", "");
                System.out.println("Connexion établie");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du pilote" + e);
        }
    }


    private void deconnecter() {
        try {
            this.myCnx.close();
            System.out.println("deconnecter");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public boolean addUser(user u) {

        connecter();
        Statement st;
        try {
            st = myCnx.createStatement();
            String requete = "INSERT INTO user VALUES('" + u.getLogin() + "','" + u.getPwd() + "','" + u.getEmail() + "','" + u.getNom() + "')";
            st.executeUpdate(requete);
            deconnecter();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return false;
        }


    }

    public ArrayList<user> getUsers() {
        connecter();
        Statement st;
        ArrayList<user> liste = new ArrayList<>();
        try {
            st = myCnx.createStatement();
            String requete = "SELECT * FROM user";
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                user u = new user(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                liste.add(u);
            }
            deconnecter();
            return liste;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return liste;
        }
    }

    public ArrayList<produit> getProduits() {
        connecter();
        ArrayList<produit> liste = new ArrayList<>();
        String requete = "SELECT * FROM produit";
        try (Statement statement = myCnx.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String description = resultSet.getString(3);
                String categorie = resultSet.getString(4);
                float prix = resultSet.getFloat(5);
                String image = resultSet.getString(6);
                int quantite = resultSet.getInt(7);
                liste.add(new produit(id, nom, description, categorie, prix, image, quantite));
                System.out.println("get produits from data layer");
            }
            deconnecter();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return liste;
        }
    }

    public void saveProduct(produit produit1) {
        connecter();
        try (PreparedStatement preparedStatement =
                     myCnx.prepareStatement("INSERT INTO produit (nom, description, categorie, prix, image, quantite) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, produit1.getNom());
            preparedStatement.setString(2, produit1.getDescription());
            preparedStatement.setString(3, produit1.getCategorie());
            preparedStatement.setFloat(4, produit1.getPrix());
            preparedStatement.setString(5, produit1.getImage());
            preparedStatement.setInt(6, produit1.getQuantite());
            preparedStatement.executeUpdate();
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int id) {
        connecter();
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("DELETE FROM produit where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProduct(produit product) {
        connecter();
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("UPDATE produit SET nom = ?, description = ?, categorie = ?, prix = ?," +
                "prix = ?, image = ?, quantite = ?  WHERE  id = ?")) {

            preparedStatement.setString(1, product.getNom());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getCategorie());
            preparedStatement.setFloat(4, product.getPrix());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setInt(5, product.getQuantite());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
