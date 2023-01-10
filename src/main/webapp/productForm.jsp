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
                        <li class="breadcrumb-item"><a href="#"> <i data-feather="home"></i></a>
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
                        <c:if test="${product != null}">
                            <h5>Edit Product</h5>
                        </c:if>
                        <c:if test="${product == null}">
                            <h3>Add new Product </h3>
                        </c:if>
                    </div>
                    <div class="card-body">
                        <c:if test="${product != null}">
                        <form action="AdminController" method="post" enctype="multipart/form-data" id="form">
                            <input type="hidden" name="action" value="updateProduct">
                            <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
                            </c:if>
                            <c:if test="${product == null}">
                            <form action="AdminController" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="insertProduct">
                                </c:if>
                                <div class="row">
                                    <div class="col">
                                        <div class="mb-3 row">
                                            <label class="col-sm-3 col-form-label">Name</label>
                                            <div class="col-sm-9">
                                                <input type="text" value="<c:out value='${product.name}' />"
                                                       class="form-control" type="text"
                                                       placeholder="***************" name="name">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label class="col-sm-3 col-form-label">Price</label>
                                            <div class="col-sm-9">
                                                <input type="number"
                                                       value="<c:out value='${product.price}' />"
                                                       class="form-control"
                                                       placeholder="***************" name="price"
                                                       inputmode="decimal">
                                            </div>
                                        </div>

                                        <div class="mb-3 row">
                                            <label class="col-sm-3 col-form-label">Category</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" name="category">
                                                    <c:forEach var="category" items="${categories}">
                                                        <option value="${category}">${category.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="mb-3 row mt-4">
                                            <label class="col-sm-3 col-form-label">Quantity</label>
                                            <div class="col-sm-9">
                                                <input value="<c:out value='${product.quantity}' />"
                                                       class="form-control " type="number"
                                                       placeholder="***************" name="quantity">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label class="col-sm-3 col-form-label">Image</label>
                                            <div class="col-sm-9">
                                                <input class="form-control" type="file"
                                                       value="<c:out value= '${product.image}' />"
                                                       aria-label="file example"
                                                       data-bs-original-title="" title="" name="image">
                                            </div>
                                            <c:if test="${product != null}">
                                                <div class="col-sm-9">
                                                    <img src="images/${product.image}" style="width: 150px"
                                                         height="auto">
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="mb-3 row">
                                        <label class="col-sm-3 col-form-label">Description</label>
                                        <div class="col-sm-9">
                                            <textarea type="text"
                                                      class="form-control" rows="5" cols="5" type="text"
                                                      placeholder="***************"
                                                      name="description"><c:out
                                                    value='${product.description}'/></textarea>
                                        </div>
                                    </div>
                                    <div class="mb-3 row">
                                        <label class="col-sm-3 col-form-label">START DATE</label>
                                        <div class="col-sm-9">
                                            <input type="date"
                                                   value="<c:out value='${product.created_at}' />"
                                                   class="form-control"
                                                   placeholder="***************"
                                                   name="description">
                                        </div>
                                    </div>
                                    <div class="card-footer text-end">
                                        <c:if test="${product == null}">
                                            <button type="submit" class="btn btn-success">add
                                                product
                                            </button>
                                        </c:if>
                                        <c:if test="${product != null}">
                                            <button type="submit" class="btn btn-primary">update
                                                product
                                            </button>
                                        </c:if>
                                        <a href="AdminController?action=listProducts" class="btn btn-light">Cancel</a>
                                    </div>
                                </div>
                            </form>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="scripts.jsp" %>
