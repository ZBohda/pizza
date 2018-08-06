<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Espit Chupitos</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Espit Chupitos</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/pizza/admin/menu">Menu</a></li>
                <li class="active"><a href="/pizza/admin/users">Users</a></li>
                <li><a href="/pizza/admin/orders">Orders</a></li>
                <li><a href="/pizza/admin/currencies">Currency</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/pizza/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Account ID</th>
            <th>Customer ID</th>
            <th>Login</th>
            <th>Email</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone</th>
        </tr>
        </thead>

        <tr>
            <td>${account.id}</td>
            <td>${account.customer.id}</td>
            <td>${account.login}</td>
            <td>${account.email}</td>
            <td>${account.customer.firstName}</td>
            <td>${account.customer.lastName}</td>
            <td>${account.customer.phone}</td>
        </tr>
    </table>


    <table class="table table-striped">
        <thead>
        <tr>
            <th>Address ID</th>
            <th>City</th>
            <th>Address</th>
        </tr>
        </thead>

        <c:forEach var="item" items="${addresses}">
            <tr>
                <td>${item.id}</td>
                <td>${item.city}</td>
                <td>${item.address}</td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
