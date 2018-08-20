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
            <th>Actions</th>
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
                <td>
                    <button class="btn btn-success"
                            onclick="location.href='/pizza/basket/product/${entry.key.id}/increase'">Increase
                    </button>
                    <button class="btn btn-warning"
                            onclick="location.href='/pizza/basket/product/${entry.key.id}/decrease'">Decrease
                    </button>
                    <button class="btn btn-danger"
                            onclick="location.href='/pizza/basket/product/${entry.key.id}/remove'">Remove a position
                    </button>
            </tr>
        </c:forEach>
    </table>

    <div class="container">
        <c:choose>
            <c:when test="${empty sessionScope.userId}">
                <h2 align="center">You are not registered customer! Please, fill all field and submit to confirm your
                    order!</h2>
                <form:form class="form-horizontal" method="post" modelAttribute="unregisteredUserFormDTO"
                           action="/pizza/basket/place">

                    <spring:bind path="firstName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label">First name</label>
                            <div class="col-sm-10">
                                <form:input path="firstName" type="text" class="form-control" id="firstName"
                                            placeholder="firstName"/>
                                <form:errors path="firstName" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label">Last name</label>
                            <div class="col-sm-10">
                                <form:input path="lastName" type="text" class="form-control" id="lastName"
                                            placeholder="lastName"/>
                                <form:errors path="lastName" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="phone">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label">Phone</label>
                            <div class="col-sm-10">
                                <form:input path="phone" type="text" class="form-control" id="phone"
                                            placeholder="phone"/>
                                <form:errors path="phone" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="city">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label">City</label>
                            <div class="col-sm-10">
                                <form:input path="city" type="text" class="form-control" id="city" placeholder="city"/>
                                <form:errors path="city" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="address">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label">Address</label>
                            <div class="col-sm-10">
                                <form:input path="address" type="text" class="form-control" id="address"
                                            placeholder="address"/>
                                <form:errors path="address" class="control-label"/>
                            </div>
                        </div>
                    </spring:bind>

                    <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
                </form:form>
            </c:when>
            <c:otherwise>
                <form:form class="form-horizontal" method="post" modelAttribute="registeredUserFormDTO"
                           action="/pizza/basket/place">
                    <spring:bind path="addressId">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label>Addresses for a delivery: </label>
                            <form:select path="addressId">
                                <form:options items="${addressesMap}"/>
                            </form:select>
                        </div>
                    </spring:bind>
                    <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
                </form:form>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
