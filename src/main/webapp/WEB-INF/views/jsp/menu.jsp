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
                <li class="active"><a href="/pizza/menu">Menu</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"> Current currency ${sessionScope.basket.currency.code}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${currencyCodes}" var="entry">
                            <li><a href="/pizza/currency/${entry.key}">${entry.value}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li><a href="/pizza/basket/get"><span class="glyphicon glyphicon-shopping-cart"></span> Basket</a></li>
                <c:choose>
                    <c:when test="${empty sessionScope.userId}">
                        <li><a href="/pizza/login"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                        <li><a href="/pizza/register"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/pizza/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                    </c:otherwise>
                </c:choose>
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
            <th>Quantity</th>
            <th>Total Price</th>
        </tr>
        </thead>

        <c:forEach items="${sessionScope.basket.products}" var="entry">
            <tr>
                <td>${entry.key.product.name}</td>
                <td>${entry.key.product.details}</td>
                <td>${entry.key.currency.code}</td>
                <td>${entry.key.price}</td>
                <td>${entry.value}</td>
                <td>${entry.value * entry.key.price} ${entry.key.currency.code}</td>
            </tr>
        </c:forEach>
    </table>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Picture</th>
            <th>Name</th>
            <th>Details</th>
            <th>Currency</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach var="product" items="${menu}">
            <tr>
                <td><img src="data:image/jpeg;base64,${product.encodedPicture}" width="150" height="150"/></td>
                <td>${product.priceRow.product.name}</td>
                <td>${product.priceRow.product.details}</td>
                <td>${product.priceRow.currency.code}</td>
                <td>${product.priceRow.price}</td>
                <td>
                    <form:form class="form-horizontal" method="post"
                               action="/pizza/basket/product/${product.priceRow.id}/add">
                    <button type="submit" class="btn btn-primary">Add</button>
                    </form:form>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
