<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script defer src="js/main.js"></script>
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Products</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${product != null}">
            <form action="update" method="post" id="form">
                </c:if>
                <c:if test="${product == null}">
                <form action="insert" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${product != null}">
                                Edit Product
                            </c:if>
                            <c:if test="${product == null}">
                                Add New Product
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${product != null}">
                        <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>User Name</label>
                        <input type="text" value="<c:out value='${product.nom}' />" class="form-control" name="nom" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Description</label>
                        <input type="text" value="<c:out value='${product.description}' />" class="form-control" name="description" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Categorie</label>
                        <input type="text" value="<c:out value='${product.categorie}' />" class="form-control" name="categorie" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Prix</label>
                        <input type="number" value="<c:out value='${product.prix}' />" class="form-control" name="prix" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Image</label>
                        <input type="text" value="<c:out value='${product.image}' />" class="form-control" name="image" required>
                    </fieldset>
                    <fieldset class="form-group">
                        <label>quantite</label>
                        <input type="number" value="<c:out value='${product.quantite}' />" class="form-control" name="quantite" required>
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
