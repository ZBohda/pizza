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
                <li><a href="/pizza/admin/products/${productId}/price/add"><span class="glyphicon glyphicon-plus"></span>Add a price</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <form:form class="form-horizontal" method="post" modelAttribute="priceRowFormDTO" action="/pizza/admin/product/${productId}/update/${priceRowId}">

        <spring:bind path="price">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Price</label>
                <div class="col-sm-10">
                    <form:input path="price" type="text" class="form-control" id="price" placeholder="price"/>
                    <form:errors path="price" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="currencyCode">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Currency</label>
                <form:select path="currencyCode">
                    <form:options items="${currencyCodes}"/>
                </form:select>
            </div>
        </spring:bind>

        <spring:bind path="active">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:radiobutton path="active" value="true"/>Active
                <br>
                <form:radiobutton path="active" value="false"/>Inactive
            </div>
        </spring:bind>

        <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
    </form:form>
</div>

</body>
</html>
