<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="template/style.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <title><%=request.getAttribute("title")%>
    </title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Магазин продуктов</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/market/catalog">Каталог</a></li>
                    <li><a href="/market/cart">Корзина</a></li>
                    <c:if test="${userLogin == null}">
                        <li><a href="/market/registration">Регистрация</a></li>
                    </c:if>
                    <c:if test="${userAdmin}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">Администрирование <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/market/admin/productList">Продукты</a></li>
                                <li><a href="/market/admin/productEdit?action=add">Добавить продукт</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="/market/admin/orderList">Заказы</a></li>
                                <li class="dropdown-header">Пользователи</li>
                                <li><a href="/market/admin/userList">Список</a></li>
                                <li><a href="/market/admin/userEdit?action=add">Добавить</a></li>
                            </ul>
                        </li>
                    </c:if>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${userLogin != null}">
                        <li><a>Здравствуйте, <c:out value="${userLogin}"></c:out></a></li>
                        <li class="active"><a href="/market/login?action=logout"><strong>Log Out</strong><span
                                class="sr-only">(current)</span></a></li>
                    </c:if>
                    <c:if test="${userLogin == null}">
                        <li class="active"><a href="/market/login"><strong>Log In</strong> <span class="sr-only">(current)</span></a>
                        </li>
                    </c:if>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
