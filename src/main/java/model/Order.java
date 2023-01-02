package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Order")
public class Order {
    @Id
    private Long id;
    private String userName;
    private String productName;
    private double productPrice;
    private int productQantity;
    private double orderTotalPrice;
    private String pingCode;
    private int orderStatus;
    private LocalDateTime orderDate;

    public Order() {}

    public Order(Long id, String userName, String productName, double productPrice, int productQantity, double orderTotalPrice, String pingCode, int orderStatus, LocalDateTime orderDate) {
        this.id = id;
        this.userName = userName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQantity = productQantity;
        this.orderTotalPrice = orderTotalPrice;
        this.pingCode = pingCode;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQantity() {
        return productQantity;
    }

    public void setProductQantity(int productQantity) {
        this.productQantity = productQantity;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getPingCode() {
        return pingCode;
    }

    public void setPingCode(String pingCode) {
        this.pingCode = pingCode;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
