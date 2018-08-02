<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
            <a class="navbar-brand">Espit Chupitos</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/pizza/admin/menu">Menu</a></li>
                <li><a href="/pizza/admin/users">Users</a></li>
                <li><a href="/pizza/admin/orders">Orders</a></li>
                <li><a href="/pizza/admin/currencies">Currency</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/pizza/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/pizza/admin/products/{productId}/price/add"><span class="glyphicon glyphicon-plus"></span>Add a price</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Currency</th>
            <th>Price</th>
            <th>Actions</th>
            <th>Active</th>
        </tr>
        </thead>

        <c:forEach var="priceRow" items="${priceRows}">
            <tr>
                <td>${priceRow.id}</td>
                <td>${priceRow.currency.name}</td>
                <td>${priceRow.price}</td>
                <td>
                    <spring:url value="/pizza/admin/product/${priceRow.id}/update" var="updateUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                    <c:choose>
                    <c:when test="${priceRow.active==true}">
                    <spring:url value="/pizza/admin/product/prices/${priceRow.id}/deactive" var="deactivateUrl" />
                    <button class="btn btn-danger" onclick="location.href='${deactivateUrl}'">Deactivate</button></td>
                </c:when>
                <c:otherwise>
                    <spring:url value="/pizza/admin/product/prices/${priceRow.id}/activate" var="activateUrl" />
                    <button class="btn btn-primary" onclick="location.href='${activateUrl}'">Activate</button>
                </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>