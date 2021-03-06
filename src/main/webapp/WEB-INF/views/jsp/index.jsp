<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:choose>
                <c:when test="${empty sessionScope.userId}">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/pizza/index">Home</a></li>
                        <li><a href="/pizza/menu">Menu</a></li>
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
                        <li><a href="/pizza/basket/get"><span class="glyphicon glyphicon-shopping-cart"></span>
                            Basket</a></li>
                        <li><a href="/pizza/login"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                        <li><a href="/pizza/register"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/pizza/index">Home</a></li>
                        <li><a href="/pizza/menu">Menu</a></li>
                        <li><a href="/pizza/customer/orders">My orders</a></li>
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
                        <li><a href="/pizza/basket/get"><span class="glyphicon glyphicon-shopping-cart"></span>
                            Basket</a></li>
                        <li><a href="/pizza/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<div class="container">
    <h3> Hello! Here you can read some interesting information, check out special offers and find everything you want!
        Someday ... </h3>
</div>

</body>
</html>
