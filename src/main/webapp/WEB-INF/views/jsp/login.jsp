<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
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
                <li class="active"><a href="/pizza/login"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                <li><a href="/pizza/register"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2 align="center">Welcome, wanderer!</h2>
    <p align="center">Please log in or go to a sign up page!</p>
    <form:form class="form-horizontal" method="post" modelAttribute="loginFormDTO" action="/pizza/login">

        <spring:bind path="login">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Login</label>
                <div class="col-sm-10">
                    <form:input path="login" type="text" class="form-control " id="login" placeholder="Login" />
                    <form:errors path="login" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <form:password path="password" class="form-control" id="password" placeholder="password" />
                    <form:errors path="password" class="control-label" />
                </div>
            </div>
        </spring:bind>
        <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
    </form:form>
</div>
<br>

</body>
</html>