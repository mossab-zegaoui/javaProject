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
                        <li class="breadcrumb-item">user</li>
                        <li class="breadcrumb-item active">user list</li>
                    </ol>
                </div>
            </div>
        </div>
        <div class="row">
            <!-- Individual column searching (text inputs) Starts-->
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body">
                        <a href="AdminController?action=newUser" class="btn btn-outline-primary mb-2"> Add
                            user</a>
                        <div class="table-responsive user-table">
                            <table class="display" id="basic-1">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Date</th>
                                    <th>Login</th>
                                    <th>Password</th>
                                    <th>Email</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.created_at}</td>
                                        <td>${user.login}</td>
                                        <td>${user.password}</td>
                                        <td>${user.email}</td>
                                        <td>
                                            <a href="AdminController?action=editUser&id=${user.id}">
                                                <i class="icon-pencil btn btn-primary"></i>
                                            </a>

                                        </td>
                                        <td>
                                            <form action="AdminController" method="post">
                                                <input type="hidden" name="action" value="deleteUser">
                                                <input type="hidden" name="login" value="${user.login}">
                                                <button type="submit" class="btn btn-danger delete-confirm"
                                                        data-original-title="btn btn-danger btn-xs"
                                                        title="delete"
                                                        onclick="show_confirm(${user.id})"
                                                        data-index-number="${user.id}"><i
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