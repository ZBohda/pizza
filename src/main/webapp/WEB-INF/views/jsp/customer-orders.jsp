<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <li><a href="/pizza/menu">Menu</a></li>
                <li class="active"><a href="/pizza/customer/orders">Orders</a></li>
                <li><a href="/pizza/customer/profile">Profile</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/pizza/basket/get"><span class="glyphicon glyphicon-shopping-cart"></span> Basket</a></li>
                <li><a href="/pizza/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Creation time</th>
            <th>Address</th>
            <th>Currency</th>
            <th>Total price</th>
            <th>Order state</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach items="${orders}" var="entry">
            <tr>
                <td>${entry.creationTime}</td>
                <td>${entry.address.city} ${entry.address.address}</td>
                <td>${entry.currency}</td>
                <td>${entry.totalPrice}</td>
                <td>${entry.orderState}</td>
                <td>
                    <form:form class="form-horizontal" method="get"
                               action="/pizza/customer/orders/${entry.id}/details">
                        <button type="submit" class="btn btn-primary">Order details</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
