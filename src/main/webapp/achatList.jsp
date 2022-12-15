<%@ page import="model.Commande" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
          content="Cuba admin is super flexible, powerful, clean &amp; modern responsive bootstrap 5 admin template with unlimited possibilities.">
    <meta name="keywords"
          content="admin template, Cuba admin template, dashboard template, flat admin template, responsive admin template, web app">
    <meta name="author" content="pixelstrap">
    <link rel="icon" href="assets/images/favicon.png" type="image/x-icon">
    <link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon">
    <!-- Google font-->
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,400i,500,500i,700,700i&amp;display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i,900&amp;display=swap"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
    <!-- ico-font-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/icofont.css">
    <!-- Themify icon-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/themify.css">
    <!-- Flag icon-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/flag-icon.css">
    <!-- Feather icon-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/feather-icon.css">
    <!-- Plugins css start-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/scrollbar.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/datatables.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/owlcarousel.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/rating.css">
    <!-- Plugins css Ends-->
    <!-- Bootstrap css-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/bootstrap.css">
    <!-- App css-->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link id="color" rel="stylesheet" href="assets/css/color-1.css" media="screen">
    <!-- Responsive css-->
    <link rel="stylesheet" type="text/css" href="assets/css/responsive.css">
</head>

<body>

<!-- tap on top starts-->
<div class="tap-top"><i data-feather="chevrons-up"></i></div>
<!-- tap on tap ends-->
<!-- page-wrapper Start-->
<div class="page-wrapper compact-wrapper" id="pageWrapper">
    <!-- Page Header Start-->
    <div class="page-header">
        <div class="header-wrapper row m-0">
            <form class="form-inline search-full col" action="#" method="get">
                <div class="form-group w-100">
                    <div class="Typeahead Typeahead--twitterUsers">
                        <div class="u-posRelative">
                            <input class="demo-input Typeahead-input form-control-plaintext w-100" type="text"
                                   placeholder="Search Cuba .." name="q" title="" autofocus>
                            <div class="spinner-border Typeahead-spinner" role="status"><span
                                    class="sr-only">Loading...</span>
                            </div>
                            <i class="close-search" data-feather="x"></i>
                        </div>
                        <div class="Typeahead-menu"></div>
                    </div>
                </div>
            </form>
            <div class="header-logo-wrapper col-auto p-0">
                <div class="logo-wrapper"><a href="adminIndex.jsp"><img class="img-fluid"
                                                                        src="assets/images/logo/logo.png" alt=""></a>
                </div>
                <div class="toggle-sidebar"><i class="status_toggle middle sidebar-toggle"
                                               data-feather="align-center"></i>
                </div>
            </div>
            <div class="left-header col horizontal-wrapper ps-0">
            </div>
            <div class="nav-right col-8 pull-right right-header p-0">
                <ul class="nav-menus">
                    <li><span class="header-search"><i data-feather="search"></i></span></li>
                    <li class="onhover-dropdown">
                        <div class="notification-box"><i data-feather="bell"> </i><span
                                class="badge rounded-pill badge-secondary">4 </span></div>
                    </li>
                    <li class="profile-nav onhover-dropdown p-0 me-0">
                        <div class="media profile-media"><img class="b-r-10"
                                                              src="assets/images/dashboard/profile.jpg" alt="">
                            <div class="media-body"><span>Admin</span>
                                <p class="mb-0 font-roboto"><i class="middle fa fa-angle-down"></i></p>
                            </div>
                        </div>
                        <ul class="profile-dropdown onhover-show-div">
                            <li><a href="#"><i data-feather="user"></i><span>Account </span></a></li>
                            <li><a href="#"><i data-feather="log-in"> </i><span>Log in</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <script class="result-template" type="text/x-handlebars-template">
                <div class="ProfileCard u-cf">
                    <div class="ProfileCard-avatar">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                             class="feather feather-airplay m-0">
                            <path d="M5 17H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-1"></path>
                            <polygon points="12 15 17 21 7 21 12 15"></polygon>
                        </svg>
                    </div>
                    <div class="ProfileCard-details">
                        <div class="ProfileCard-realName">{{name}}</div>
                    </div>
                </div>
            </script>
            <script class="empty-template"
                    type="text/x-handlebars-template">
                <div class="EmptyMessage">Your search turned up 0 results. This most likely means the backend is down,
                    yikes!
                </div></script>
        </div>
    </div>
    <!-- Page Header Ends                              -->
    <!-- Page Body Start-->
    <div class="page-body-wrapper">
        <!-- Page Sidebar Start-->
        <div class="sidebar-wrapper">
            <div>
                <div class="logo-wrapper pb-5">
                    <div class="toggle-sidebar">

                    </div>
                </div>
                <div class="logo-icon-wrapper"><a href="adminIndex.jsp">
                    <img class="img-fluid"
                         src="assets/images/logo/logo-icon.png"
                         alt=""></a></div>
                <nav class="sidebar-main">
                    <div class="left-arrow" id="left-arrow"><i data-feather="arrow-left"></i></div>
                    <div id="sidebar-menu">
                        <ul class="sidebar-links" id="simple-bar">
                            <li class="back-btn"><a href="adminIndex.jsp"><img class="img-fluid"
                                                                               src="assets/images/logo/logo-icon.png"
                                                                               alt=""></a>
                                <div class="mobile-back text-end"><span>Back</span><i class="fa fa-angle-right ps-2"
                                                                                      aria-hidden="true"></i></div>
                            </li>
                            <li class="sidebar-list"><a class="sidebar-link sidebar-title" href="#"><i
                                    data-feather="shopping-bag"></i><span>Product</span></a>
                                <ul class="sidebar-submenu">

                                    <form action="AdminController" method="post">
                                        <input type="hidden" name="action" value="listProducts">
                                        <li>
                                            <button type="submit" class="btn bg-transparent"> list Products</button>
                                        </li>
                                    </form>
                                </ul>

                            </li>
                            <li class="sidebar-list"><a class="sidebar-link sidebar-title" href="#"><i
                                    data-feather="users"></i><span>Users</span></a>
                                <ul class="sidebar-submenu">

                                    <form action="AdminController" method="post">
                                        <input type="hidden" name="action" value="listUsers">
                                        <li>
                                            <button type="submit" class="btn bg-transparent"> list Users</button>
                                        </li>
                                    </form>
                                </ul>
                            </li>
                            <li class="sidebar-list"><a class="sidebar-link sidebar-title" href="#"><i
                                    data-feather="shopping-bag"></i><span>Achats</span></a>
                                <ul class="sidebar-submenu">
                                    <form action="AdminController" method="post">
                                        <input type="hidden" name="action" value="listAchats">
                                        <button type="submit" class="btn bg-transparent">liste Achats</button>
                                    </form>

                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Page Sidebar Ends-->
        <div class="page-body">
            <br>
            <div class="container-fluid">
                <div class="page-title">
                    <div class="row">
                        <div class="col-6">
                        </div>
                        <div class="col-6">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="adminIndex.jsp"> <i data-feather="home"></i></a>
                                </li>
                                <li class="breadcrumb-item">Achat</li>
                                <li class="breadcrumb-item active">achat list</li>
                            </ol>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- Individual column searching (text inputs) Starts-->
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive user-table">
                                    <table class="display" id="basic-1">
                                        <thead>
                                        <tr>
                                            <th>Client</th>
                                            <th>Date</th>
                                            <th>Produits</th>
                                            <th>livrer</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <%
                                            ArrayList<Commande> listeAchats = (ArrayList<Commande>) request.getAttribute("achatsList");
                                            ArrayList<produit> listeProduits = (ArrayList<produit>) request.getAttribute("productsList");

                                            for (int i = 0; i < listeAchats.size(); i++) {

                                                for (int j = 0; j < listeProduits.size(); j++) {
                                                    if (listeProduits.get(j).getId() == listeAchats.get(i).getNum()) {
                                                        out.print("<tr>\n" +
                                                                "<td>" + listeAchats.get(i).getClient() + "</td>\n" + "</n>" +
                                                                "<td>" + listeAchats.get(i).getDate() + "</td>\n" + "</n>");
                                                        out.print("<td>" + listeProduits.get(j).getNom() + "</td>");
                                                        out.print("<td> <button class='btn btn-success'> <i class ='fa fa-check'> </i></button> </td></tr>");
                                                        break;
                                                    }
                                                }
                                            }
                                        %>
                                        <tr>
                                            <td>${achat.client}</td>
                                            <td>${achat.date}</td>
                                            <td>${achat.num}
                                            </td>
                                            <td></td>
                                        </tr>
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
        <!-- footer start-->
        <footer class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12 footer-copyright text-center">
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<!-- latest jquery-->
<script src="assets/js/jquery-3.5.1.min.js"></script>
<!-- Bootstrap js-->
<script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- feather icon js-->
<script src="assets/js/icons/feather-icon/feather.min.js"></script>
<script src="assets/js/icons/feather-icon/feather-icon.js"></script>
<!-- scrollbar js-->
<script src="assets/js/scrollbar/simplebar.js"></script>
<script src="assets/js/scrollbar/custom.js"></script>
<!-- Sidebar jquery-->
<script src="assets/js/config.js"></script>
<!-- Plugins JS start-->
<script src="assets/js/sidebar-menu.js"></script>
<script src="assets/js/datatable/datatables/jquery.dataTables.min.js"></script>
<script src="assets/js/rating/jquery.barrating.js"></script>
<script src="assets/js/rating/rating-script.js"></script>
<script src="assets/js/owlcarousel/owl.carousel.js"></script>
<script src="assets/js/ecommerce.js"></script>
<script src="assets/js/product-list-custom.js"></script>
<script src="assets/js/tooltip-init.js"></script>
<!-- Plugins JS Ends-->
<!-- Theme js-->
<script src="assets/js/script.js"></script>
<!-- login js-->
<!-- Plugin used-->
</body>

</html>
