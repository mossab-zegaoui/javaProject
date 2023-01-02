package dataLayer;

import model.Order;
import model.Product;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DataLayer {
    private Connection connection;

    public DataLayer() {
    }

    private void connectionDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void disconnectionDB() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Product> listAllProducts() {
        connectionDB();
        ArrayList<Product> products = new ArrayList<>();
        String requete = "SELECT * FROM product";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                Date created_at = resultSet.getDate("created_at");
                products.add(new Product(id, name, price, category, quantity, description, image, created_at));
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean saveProduct(Product product) {
        connectionDB();
        boolean rowInserted = false;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO product (name, price, description, category, image, quantity) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setInt(6, product.getQuantity());
            rowInserted = preparedStatement.executeUpdate() > 0;
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public boolean deleteProduct(int id) {
        connectionDB();
        boolean rowDeleted;
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product where id = ?")) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public void updateProduct(Product product) {
        connectionDB();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name = ?, description = ?, category = ?," +
                " price = ?, image = ?, quantity = ?  WHERE  id = ?")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setInt(6, product.getQuantity());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product selectProduct(int id) {
        connectionDB();
        Product product = new Product();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM product where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String category = resultSet.getString("category");
                Double price = Double.valueOf(resultSet.getDouble("price"));
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                Date created_at = resultSet.getDate("created_at");
                product = new Product(id, name, price, category, quantity, description, image, created_at);
            }
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public ArrayList<User> listAllUsers() {
        connectionDB();
        ArrayList<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                users.add(new User(id, login, password, email, firstName, lastName));
            }
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean saveUser(User user) {
        connectionDB();
        boolean rowInserted = false;
        try (PreparedStatement prepareStatement =
                     connection.prepareStatement("INSERT INTO user (login, password, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)")) {
            prepareStatement.setString(1, user.getLogin());
            prepareStatement.setString(2, user.getPassword());
            prepareStatement.setString(3, user.getEmail());
            prepareStatement.setString(4, user.getFirstName());
            prepareStatement.setString(5, user.getLastName());
            rowInserted = prepareStatement.executeUpdate() > 0;
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowInserted;
    }

    public boolean deleteUser(String login) {
        connectionDB();
        boolean rowDeleted;
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user where login = ?")) {
            preparedStatement.setString(1, login);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public void updateUser(User user) {
        connectionDB();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ?, email = ?, first_name = ?, last_name = ?, login = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User selectUser(int id) {
        connectionDB();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM user where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                user = new User(id, login, password, email, firstName, lastName);
            }
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public ArrayList<Order> listeAllAchats() {
        connectionDB();
        ArrayList<Order> liste = new ArrayList<Order>();
        String requete = "SELECT * FROM achat where status = 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                Date date = resultSet.getDate(3);
                int quantite = resultSet.getInt(4);
                System.out.println(id + "\n" + login + "\n" + date + "\n" + quantite);
                liste.add(new Order());
            }
            disconnectionDB();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            disconnectionDB();
            return liste;
        }
    }

    public ArrayList<Product> listeAchatUser(String login) {
        connectionDB();
        ArrayList<Product> liste = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT produit.* FROM produit INNER JOIN achat ON produit.id = achat.id_produit and achat.login = ? and achat.status = 1")) {
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
                liste.add(new Product());
            }
            disconnectionDB();
            return liste;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            disconnectionDB();
            return liste;
        }

    }

    public boolean existLogin(User user) {
        connectionDB();
        boolean rowChecked = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ?")) {
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                rowChecked = true;
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowChecked;
    }

    public boolean isRegistered(User user) {
        connectionDB();
        boolean rowChecked = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ? and password = ?")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                rowChecked = true;
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowChecked;

    }

    public boolean isAdmin(User user) {
        connectionDB();
        boolean rowChecked = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ? and password = ? and isAdmin = 1")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                rowChecked = true;
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowChecked;
    }

    public ArrayList<Product> getProcessingOrders() {
        connectionDB();
        ArrayList<Product> order_items = new ArrayList<>();
        String request = "SELECT  p.* FROM order_items o, product p, magasin.order m WHERE p.id = o.product_id AND o.order_id = m.id AND m.status = 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("description");
                int quantity = Integer.parseInt(resultSet.getString("quantity"));
                order_items.add(new Product(id, name, price, quantity, image));
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_items;
    }

    public ArrayList<Product> getshippedProducts() {
        connectionDB();
        ArrayList<Product> shipped_items = new ArrayList<>();
        String request = "SELECT  p.* FROM order_items o, product p, magasin.order m WHERE p.id = o.product_id AND o.order_id = m.id AND m.status = 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("description");
                int quantity = Integer.parseInt(resultSet.getString("quantity"));
                shipped_items.add(new Product(id, name, price, quantity, image));
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipped_items;
    }
}
