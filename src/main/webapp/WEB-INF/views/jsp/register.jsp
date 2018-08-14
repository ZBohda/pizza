<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
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
                <li><a href="/pizza/basket/get"><span class="glyphicon glyphicon-shopping-cart"></span> Basket</a></li>
                <li class="active"><a href="/pizza/register"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
                <li><a href="/pizza/login"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2 align="center">It is time to create your new and shiny account!</h2>
    <p align="center">Please fill up all field and submit the account registration form!</p>
    <form:form class="form-horizontal" method="post" modelAttribute="registerFormDTO" action="/pizza/register">

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

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Confirm password</label>
                <div class="col-sm-10">
                    <form:password path="confirmPassword" class="form-control" id="confirmPassword" placeholder="Confirm password" />
                    <form:errors path="confirmPassword" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <form:input path="email" type ="email" class="form-control" id="email" placeholder="email" />
                    <form:errors path="email" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">First name</label>
                <div class="col-sm-10">
                    <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="firstName" />
                    <form:errors path="firstName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Last name</label>
                <div class="col-sm-10">
                    <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="lastName" />
                    <form:errors path="lastName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="phone">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Phone</label>
                <div class="col-sm-10">
                    <form:input path="phone" type="text" class="form-control" id="phone" placeholder="phone" />
                    <form:errors path="phone" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="city">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">City</label>
                <div class="col-sm-10">
                    <form:input path="city" type="text" class="form-control" id="city" placeholder="city" />
                    <form:errors path="city" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Address</label>
                <div class="col-sm-10">
                    <form:input path="address" type="text" class="form-control" id="address" placeholder="address" />
                    <form:errors path="address" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
    </form:form>
</div>
<br>

</body>
</html>