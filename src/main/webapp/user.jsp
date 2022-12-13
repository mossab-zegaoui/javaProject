<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/12/2022
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>

<body>
            <c:forEach var="user" items="${usersList}">
                <p>${user.login}</p>
                <p>${user.pwd}</p>
                <p>${user.email}</p>

            </c:forEach>

</body>

</html>
