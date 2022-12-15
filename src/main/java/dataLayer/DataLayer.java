package dataLayer;

import model.Commande;
import model.produit;
import model.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

    public ArrayList<produit> listAllProducts() {
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
            }
            deconnecter();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return liste;
        }
    }

    public Boolean saveProduct(produit produit1) {
        connecter();
        boolean rowInserted;
        try (PreparedStatement preparedStatement1 =
                     myCnx.prepareStatement("INSERT INTO produit (nom, description, categorie, prix, image, quantite) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement1.setString(1, produit1.getNom());
            preparedStatement1.setString(2, produit1.getDescription());
            preparedStatement1.setString(3, produit1.getCategorie());
            preparedStatement1.setFloat(4, produit1.getPrix());
            preparedStatement1.setString(5, produit1.getImage());
            preparedStatement1.setInt(6, produit1.getQuantite());
            rowInserted = preparedStatement1.executeUpdate() > 0;
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public boolean deleteProduct(int id) {
        connecter();
        boolean rowDeleted;
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("DELETE FROM produit where id = ?")) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public void updateProduct(produit product) {
        connecter();
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("UPDATE produit SET nom = ?, description = ?, categorie = ?," +
                " prix = ?, image = ?, quantite = ?  WHERE  id = ?")) {

            preparedStatement.setString(1, product.getNom());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getCategorie());
            preparedStatement.setFloat(4, product.getPrix());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setInt(6, product.getQuantite());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public produit selectProduct(int id) {
        connecter();
        produit product = null;
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("SELECT  * FROM produit where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                String categorie = resultSet.getString("categorie");
                Float prix = Float.valueOf(resultSet.getFloat("prix"));
                String image = resultSet.getString("image");
                int quantite = resultSet.getInt("quantite");
                product = new produit(id, nom, description, categorie, prix, image, quantite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        deconnecter();
        return product;
    }

    public ArrayList<produit> searchProduct(String key) {
        connecter();
        ArrayList<produit> products = new ArrayList<>();
        try (PreparedStatement preparedStatementstatement = myCnx.prepareStatement("SELECT * FROM produit WHERE nom =  ?");
             ResultSet resultSet = preparedStatementstatement.executeQuery()) {
            preparedStatementstatement.setString(1, key);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String description = resultSet.getString(3);
                String categorie = resultSet.getString(4);
                float prix = resultSet.getFloat(5);
                String image = resultSet.getString(6);
                int quantite = resultSet.getInt(7);
                products.add(new produit(id, nom, description, categorie, prix, image, quantite));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        deconnecter();
        return products;
    }

    public ArrayList<user> listAllUsers() {
        connecter();
        ArrayList<user> liste = new ArrayList<>();
        String requete = "SELECT * FROM user";
        try (Statement statement = myCnx.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {
            while (resultSet.next()) {
                String login = resultSet.getString(1);
                String pwd = resultSet.getString(2);
                String email = resultSet.getString(3);
                String nom = resultSet.getString(4);
                liste.add(new user(login, pwd, email, nom));
            }
            deconnecter();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return liste;
        }
    }

    public boolean saveUser(user user) {
        connecter();
        boolean rowInserted;
        try (PreparedStatement preparedStatement1 =
                     myCnx.prepareStatement("INSERT INTO user (login, pwd, email, nom) VALUES (?, ?, ?, ?)")) {
            preparedStatement1.setString(1, user.getLogin());
            preparedStatement1.setString(2, user.getPwd());
            preparedStatement1.setString(3, user.getEmail());
            preparedStatement1.setString(4, user.getNom());
            rowInserted = preparedStatement1.executeUpdate() > 0;
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public boolean deleteUser(String login) {
        connecter();
        boolean rowDeleted;
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("DELETE FROM user where login = ?")) {
            preparedStatement.setString(1, login);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public void updateUser(user user) {
        connecter();
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("UPDATE user SET pwd = ?, email = ?," +
                " nom = ?  WHERE  login = ?")) {
            preparedStatement.setString(1, user.getPwd());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getNom());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.executeUpdate();
            deconnecter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public user selectUser(String login) {
        connecter();
        user user = null;
        try (PreparedStatement preparedStatement = myCnx.prepareStatement("SELECT  * FROM user where login = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("pwd");
                String email = resultSet.getString("email");
                String nom = resultSet.getString("nom");
                user = new user(login, password, email, nom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        deconnecter();
        return user;
    }

    public ArrayList<Commande> listeAllAchats() {
        connecter();
        ArrayList<Commande> liste = new ArrayList<Commande>();
        String requete = "SELECT * FROM achat where status = 1";
        try (Statement statement = myCnx.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                Date date = resultSet.getDate(3);
                int quantite = resultSet.getInt(4);
                System.out.println(id + "\n" + login + "\n" + date + "\n" + quantite);
                liste.add(new Commande(id, login, date, quantite));
            }
            deconnecter();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return liste;
        }
    }

    public ArrayList<produit> listeAchatUser(String login) {
        connecter();
        ArrayList<produit> liste = new ArrayList<>();
        try (PreparedStatement statement = myCnx.prepareStatement("SELECT produit.* FROM produit INNER JOIN achat ON produit.id = achat.id_produit and achat.login = ? and achat.status = 1")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String description = resultSet.getString(3);
                String categorie = resultSet.getString(4);
                float prix = resultSet.getFloat(5);
                String image = resultSet.getString(6);
                int quantite = resultSet.getInt(7);
                System.out.println(id + "\n" + nom + "\n" + prix);
                liste.add(new produit(id, nom, description, categorie, prix, image, quantite));
            }
            deconnecter();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            deconnecter();
            return liste;
        }

    }

}
