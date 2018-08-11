<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li><a href="/pizza/index">Home</a></li>
                <li class="active"><a href="/pizza/menu">Menu</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/pizza/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="/pizza/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Details</th>
            <th>Currency</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach var="product" items="${priceRows}">
            <tr>
                <td>${product.product.name}</td>
                <td>${product.product.details}</td>
                <td>${product.currency.code}</td>
                <td>${product.price}</td>
                <td>${product.price}</td>
                <td><button class="btn btn-primary" onclick="location.href='/pizza/product/${product.id}/add'">Add</button>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
