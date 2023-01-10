<%@include file="head.jsp" %>
<%@include file="admin_sidebar.jsp" %>
<div class="page-body">
    <div class="container-fluid">
        <div class="page-title">
            <div class="row">
                <div class="col-6">
                </div>
                <div class="col-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href=""> <i data-feather="home"></i></a>
                        </li>
                        <li class="breadcrumb-item active">Edit</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <!-- Container-fluid starts-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">
                        <h3> user info</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <div class="mb-3 row">
                                    <label class="col-sm-3 col-form-label">Login</label>
                                    <div class="col-sm-9">
                                        <input type="text" value="<c:out value='${user.login}' />"
                                               class="form-control"
                                               placeholder="Sasir votre login" name="login" disabled>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-sm-3 col-form-label">Password</label>
                                    <div class="col-sm-9">
                                        <input value="<c:out value='${user.password}' />"
                                               class="form-control" type="password"
                                               placeholder="saisir password "
                                               name="password" disabled>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-sm-3 col-form-label">Email</label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               value="<c:out value='${user.email}' />"
                                               class="form-control"
                                               placeholder="saisir email de l'utilisateur "
                                               name="email" disabled>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-sm-3 col-form-label">Nom</label>
                                    <div class="col-sm-9">
                                        <input value="<c:out value='${user.name}' />"
                                               class="form-control" type="text"
                                               placeholder="saisir name de l'utilisateur" name="name" disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">
                        <h3> ordered products</h3>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive product-table">
                            <table class="display" id="basic-1">
                                <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Nom</th>
                                    <th>Categorie</th>
                                    <th>Prix</th>
                                    <th style="text-align: center">Quantite</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="product" items="${productsList}">

                                    <tr>
                                        <td><img src="img/${product.image}" width="50" height="50"></td>
                                        <td>
                                            <h6> ${product.name}</h6>
                                        </td>
                                        <td>${product.categorie}</td>
                                        <td class="font-success">$${product.price} </td>
                                        <td style="text-align: center">${product.quantite}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
<%@include file="scripts.jsp" %>
