<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns:th="http://www.thymeleaf.org">
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
                <li><a href="/admin/menu/add"><span class="glyphicon glyphicon-plus"></span>Add a product</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <form:form class="form-horizontal" method="post" enctype="multipart/form-data" modelAttribute="productFormDTO" action="/pizza/admin/menu/add">

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name" placeholder="name" />
                    <form:errors path="name" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="details">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">details</label>
                <div class="col-sm-10">
                    <form:input path="details" type="text" class="form-control " id="details" placeholder="details" />
                    <form:errors path="details" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="file">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">picture</label>
                <div class="col-sm-10">
                    <form:input path="file" type="file" class="form-control " id="file" placeholder="file" />
                    <form:errors path="file" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
    </form:form>
</div>


</body>
</html>
