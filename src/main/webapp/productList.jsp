<%@include file="head.jsp" %>
<%@include file="admin_sidebar.jsp" %>
<div class="page-body">

    <br>
    <div class="container-fluid">
        <div class="page-title">
            <div class="row">
                <div class="col-6">
                </div>
                <div class="col-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="admin_index.jsp"> <i data-feather="home"></i></a>
                        </li>
                        <li class="breadcrumb-item">Product</li>
                        <li class="breadcrumb-item active">Product list</li>
                    </ol>
                </div>
            </div>
        </div>
        <div class="row">
            <!-- Individual column searching (text inputs) Starts-->
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body">
                        <a href="AdminController?action=newProduct" class="btn btn-primary-gradien mb-2">add product</a>
                        <div class="table-responsive product-table">
                            <table class="display" id="basic-1">
                                <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Details</th>
                                    <th>Price</th>
                                    <th>Stock</th>
                                    <th>Start date</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="product" items="${products}">

                                    <tr>
                                        <td><img src="images/${product.image}" width="50" height="50"></td>
                                        <td>
                                            <p> ${product.description}</p>
                                        </td>
                                        <td>$ ${product.price}</td>
                                        <td>
                                            <c:if test="${(product.quantity > 30) }">
                                                <p class="font-success">In stock</p>
                                            </c:if>
                                            <c:if test="${(product.quantity <= 30)  && (product.quantity  > 20)}">
                                                <p class="font-primary">Low on stock</p>
                                            </c:if>
                                            <c:if test="${(product.quantity <= 20)}">
                                                <p class="font-danger">out of stock</p>
                                            </c:if>

                                        </td>
                                        <td class="col-lg-2">${product.created_at} </td>
                                        <td>
                                            <a href="AdminController?action=editProduct&id=${product.id}">
                                                <i class="icon-pencil btn btn-primary"></i>
                                            </a>
                                        </td>
                                        <td>
                                            <form action="AdminController" method="post">
                                                <input type="hidden" name="action" value="deleteProduct">

                                                <input type="hidden" name="id" value="${product.id}">
                                                <button type="submit" class="btn btn-danger"
                                                        data-original-title="btn btn-danger btn-xs" title=""><i
                                                        class="icon-trash"></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Individual column searching (text inputs) Ends-->
        </div>
    </div>
    <!-- Container-fluid Ends-->
</div>
<%@include file="scripts.jsp" %>
