<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Users</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="<%=request.getContextPath()%>/new">add user</a>
                    </div>
                    <div class="col-sm-6">
                        <form method="post" action="AdminController">
                            <input type="hidden" name="action" value="listUser">
                            <button type="submit" value="submit">list userr</button>
                        </form>
                    </div>
                    <form action="search" method="get">
                        <label>Search  user</label>
                        <input type="text" name="key">
                        <button type="submit">Search</button>
                    </form>
                    <table class=" table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>login</th>
                            <th>pwd</th>
                            <th>email</th>
                            <th>nom</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${usersList}">
                            <tr>
                                <td>
                                    <img src="img/stress.png">
                                </td>
                                <td>${user.login}</td>
                                <td>${user.pwd}</td>
                                <td>${user.email}</td>
                                <td>$${user.nom} </td>
                                <td>
                                    <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                                    <a href="delete?id=<c:out value='${user.id}' />">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item disabled"><a href="#">Previous</a></li>
                            <li class="page-item"><a href="#" class="page-link">1</a></li>
                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                            <li class="page-item active"><a href="#" class="page-link">3</a></li>
                            <li class="page-item"><a href="#" class="page-link">4</a></li>
                            <li class="page-item"><a href="#" class="page-link">5</a></li>
                            <li class="page-item"><a href="#" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
