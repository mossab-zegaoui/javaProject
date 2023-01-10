package dataLayer;

import models.*;

import java.sql.*;
import java.util.ArrayList;

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
        String query = "SELECT * FROM product ORDER BY created_at desc LIMIT 9";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(Double.valueOf(resultSet.getDouble("price")));
                product.setImage(resultSet.getString("image"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setCreated_at(resultSet.getDate("created_at"));
                products.add(product);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name, price, description, category, image, quantity) VALUES (?, ?, ?, ?, ?, ?)")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name = ?, description = ?, category = ?," + " price = ?, image = ?, quantity = ?  WHERE  id = ?")) {

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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM product WHERE id = ? ORDER BY created_at DESC")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setId(id);
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(Double.valueOf(resultSet.getDouble("price")));
                product.setImage(resultSet.getString("image"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setCreated_at(resultSet.getDate("created_at"));
            }
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public ArrayList<Product> getCartProducts(ArrayList<Cart> cartList) {
        connectionDB();
        ArrayList<Product> products = new ArrayList<>();
        if (cartList.size() > 0) {
            for (Cart item : cartList) {
                String query = "SELECT * FROM product WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, item.getItem().getId());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Product product = new Product();
                        product.setId(resultSet.getInt("id"));
                        product.setName(resultSet.getString("name"));
                        product.setPrice(resultSet.getDouble("price"));
                        product.setQuantity(item.getQuantity());
                        product.setImage(resultSet.getString("image"));
                        products.add(product);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        disconnectionDB();
        return products;
    }

    public ArrayList<User> listAllUsers() {
        connectionDB();
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user ORDER BY created_at desc ";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setCreated_at(resultSet.getDate("created_at"));
                users.add(user);
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
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO user (login, password, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ?, email = ?, first_name = ?, last_name = ?, login = ?,  WHERE id = ?")) {
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
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM user where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
            }
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User selectUser(String login) {
        connectionDB();
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM user WHERE login = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setPostalCode(resultSet.getString("postal_code"));
                user.setCountry(resultSet.getString("country"));
            }
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public boolean existLogin(User user) {
        connectionDB();
        boolean rowChecked = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ?")) {
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) rowChecked = true;
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
            if (resultSet.next()) rowChecked = true;
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
            if (resultSet.next()) rowChecked = true;
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowChecked;
    }

    public ArrayList<Product> getProcessingOrders() {
        connectionDB();
        ArrayList<Product> order_items = new ArrayList<>();
        String request = "SELECT p.name,m.order_total_price, p.quantity, m.order_date FROM order_items o, product p, magasin.order m WHERE p.id = o.product_id AND o.order_id = m.id AND m.status = 'pending' ORDER BY m.order_date DESC ";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("order_total_price"));
                product.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
                product.setCreated_at(resultSet.getDate("order_date"));
                order_items.add(product);
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_items;
    }

    public ArrayList<Product> getShippedOrders() {
        connectionDB();
        ArrayList<Product> shipped_items = new ArrayList<>();
        String request = "SELECT p.name,m.order_total_price, p.quantity, m.order_date FROM order_items o, product p, magasin.order m WHERE p.id = o.product_id AND o.order_id = m.id AND m.status = 'completed' ORDER BY m.order_date desc";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("order_total_price"));
                product.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
                product.setCreated_at(resultSet.getDate("order_date"));
                shipped_items.add(product);
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipped_items;
    }

    public ArrayList<Product> getcancelledOrders() {
        connectionDB();
        ArrayList<Product> cancelledOrder = new ArrayList<>();
        String request = "SELECT p.name,m.order_total_price, p.quantity, m.order_date FROM order_items o, product p, magasin.order m WHERE p.id = o.product_id AND o.order_id = m.id AND m.status = 'cancelled' ORDER BY m.order_date DESC ";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("order_total_price"));
                product.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
                product.setCreated_at(resultSet.getDate("order_date"));
                cancelledOrder.add(product);
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cancelledOrder;
    }

    public ArrayList<Product> getUserOrders(int id) {
        connectionDB();
        ArrayList<Product> userOrders = new ArrayList<>();
        String request = "SELECT p.name, p.status, m.order_date, m.order_total_price FROM order_items o, product p, magasin.order m WHERE p.id = o.product_id  AND o.order_id = m.id AND m.user_id = ? ORDER BY m.order_date DESC ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setCreated_at(resultSet.getDate("order_date"));
                product.setPrice(resultSet.getDouble("order_total_price"));
                product.setStatus(resultSet.getString("status"));
                userOrders.add(product);
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOrders;
    }

    public ArrayList<Category> listAllCategories() {
        connectionDB();
        ArrayList<Category> categories = new ArrayList<>();
        String query = "SELECT * from category";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
            disconnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void addUserDetails(User user) {
        connectionDB();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET first_name = ?, last_name = ?, email = ?, phone = ?, postal_code = ?, country = ?, address = ?   WHERE id = ?")) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getPostalCode());
            preparedStatement.setString(6, user.getCountry());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
            disconnectionDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOrder(Order order) {
        connectionDB();
        int id;
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO magasin.order(user_id, order_total_price) VALUES (?, ?)")) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setDouble(2, order.getOrderTotalPrice());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT id FROM magasin.order WHERE user_id = ?");
            preparedStatement2.setInt(1, order.getId());
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            disconnectionDB();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
