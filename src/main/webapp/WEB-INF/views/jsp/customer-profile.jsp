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
                <li><a href="/pizza/customer/orders">My orders</a></li>
                <li class="active"><a href="/pizza/customer/profile">My profile</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"> Current
                        currency ${sessionScope.basket.currency.code}<span class="caret"></span></a>
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
            <th>Customer ID</th>
            <th>Login</th>
            <th>Email</th>
            <th>Phone</th>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        </thead>

        <tr>
            <td>${customer.id}</td>
            <td>${customer.account.login}</td>
            <td>${customer.account.email}</td>
            <td>${customer.phone}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
        </tr>
    </table>

    <form:form class="form-horizontal" method="post" modelAttribute="addressFormDTO" action="/pizza/customer/address/add">

        <table class="table table-striped">
            <thead>
            <tr>
                <th>City</th>
                <th>Address</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <spring:bind path="city">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <div class="col-sm-10">
                                <form:input path="city" type="text" class="form-control" id="city"
                                            placeholder="city"/>
                                <form:errors path="city" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>
                </td>
                <td>
                    <spring:bind path="address">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <div class="col-sm-10">
                                <form:input path="address" type="text" class="form-control" id="address"
                                            placeholder="address"/>
                                <form:errors path="address" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>
                </td>
            </tr>
        </table>
        <button type="submit" class="btn-lg btn-primary pull-right">Add new address</button>
    </form:form>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>City</th>
            <th>Address</th>
            <th>Active</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach var="address" items="${customer.addresses}">
            <tr>
                <td>${address.city}</td>
                <td>${address.address}</td>
                <td>${address.active}</td>
                <td>
                    <c:choose>
                        <c:when test="${address.active=='true'}">
                            <form:form class="form-horizontal" method="post"
                                       action="/pizza/customer/address/${address.id}/switch">
                                <button type="submit" class="btn btn-danger">Deactivate</button>
                            </form:form>
                        </c:when>
                        <c:otherwise>
                            <form:form class="form-horizontal" method="post"
                                       action="/pizza/customer/address/${address.id}/switch">
                                <button type="submit" class="btn btn-primary">Activate</button>
                            </form:form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
