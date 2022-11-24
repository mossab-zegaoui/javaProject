<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>quantite</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="product" items="${listProducts}">
                <tr>
                    <td><c:out value="${product.nom}"/></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.prix}"/></td>
                    <td><c:out value="${product.quantite}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
