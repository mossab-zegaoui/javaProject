package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@MultipartConfig
@WebServlet(name = "ImageServlet", value = "/ImageServlet")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/magasin?serverTimezone=UTC", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT fileName from imageTable");
            while (resultSet.next()) {
                imageName = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("imageSource", imageName);
        request.getRequestDispatcher("imageFile.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("int he servlet immage");
        Part file = request.getPart("image");
        System.out.println("file= " + file);
        String imageName = file.getSubmittedFileName();
        System.out.println("image file name = " + imageName);
        String uploadPath = "D:/JavaProjects/eCommerceApp/src/main/webapp/img/" + imageName;
        System.out.println("upload file path = " + uploadPath);
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/magasin?serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO imageTable(fileName) VALUES (?)");
            preparedStatement.setString(1, imageName);
            int row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
