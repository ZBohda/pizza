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
                <li><a href="/pizza/admin/users">Users</a></li>
                <li class="active"><a href="/pizza/admin/orders">Orders</a></li>
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
            <th>Id</th>
            <th>Creation Time</th>
            <th>Customer Id</th>
            <th>Currency</th>
            <th>Total Price</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Order State</th>
        </tr>
        </thead>
        <tr>
            <td>${order.id}</td>
            <td>${order.creationTime}</td>
            <td>${order.customer.id}</td>
            <td>${order.currency.code}</td>
            <td>${order.totalPrice}</td>
            <td>${order.address.city} ${order.address.address}</td>
            <td>${order.customer.phone}</td>
            <td>
                <c:choose>
                    <c:when test="${order.orderState == 'FINISHED'}">
                        <span class="label label-success">${order.orderState}</span>
                    </c:when>
                    <c:when test="${order.orderState == 'CANCELED'}">
                        <span class="label label-danger"> ${order.orderState}</span>
                    </c:when>
                    <c:otherwise>
                        <div class="dropdown">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                                Current state: ${order.orderState}<span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <c:forEach items="${orderStates}" var="entry">
                                    <li>
                                        <form:form class="form-horizontal" method="post" action="/pizza/admin/order/${order.id}/state/${entry.key}/change">
                                            <button type="submit" class="btn btn-primary btn-block">${entry.value}</button>
                                        </form:form>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Price</th>
        </tr>
        </thead>
        <c:forEach items="${order.entries}" var="entry">
            <tr>
                <td>${entry.id}</td>
                <td>${entry.product.id}</td>
                <td>${entry.product.name}</td>
                <td>${entry.quantity}</td>
                <td>${entry.priceRow.price}</td>
                <td>${entry.priceRow.price * entry.quantity}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
