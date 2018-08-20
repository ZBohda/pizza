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
                <li class="active"><a href="/pizza/customer/orders">My orders</a></li>
                <li><a href="/pizza/customer/profile">My profile</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"> Current currency ${sessionScope.basket.currency.code}<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${currencyCodes}" var="entry">
                            <li><a href="/pizza/currency/${entry.key}">${entry.value}</a></li>
                        </c:forEach>
                    </ul>
                </li>
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
        </tr>
        </thead>
        <tr>
            <td>${order.creationTime}</td>
            <td>${order.address.city} ${order.address.address}</td>
            <td>${order.currency.code}</td>
            <td>${order.totalPrice}</td>
            <td>${order.orderState}</td>
        </tr>
    </table>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Product</th>
            <th>Currency</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        </thead>

        <c:forEach items="${orderEntry}" var="entry">
            <tr>
                <td>${entry.product.name}</td>
                <td>${entry.priceRow.currency.code}</td>
                <td>${entry.priceRow.price}</td>
                <td>${entry.quantity}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
