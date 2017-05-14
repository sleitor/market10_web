<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>
<br>
<h1>Редактирование пользователя</h1>
<br>
<div class="registrationForm">
    <c:if test="${error != null}">
        <div class="bg-danger"><c:out value="${error}"></c:out></div>
    </c:if>
    <br>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">login</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="login"
                       required="" pattern="[a-zA-Z\d]{3,15}"
                       title="Только латиница и цифры. Длинна строки должна быть от 3 до 15 символов"
                       value="${user.userName}" <c:if test="${user.userName != null}"> disabled</c:if> >
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" name="email" id="email" placeholder="E-mail" required=""
                       pattern="^\S+@\S+\.\S+$" title="Введите правильный email"
                       value="${user.email}">
            </div>
        </div>
        <div class="form-group">
            <label for="firstName" class="col-sm-2 control-label">Имя</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Имя"
                       required="" pattern="[\D]{2,50}"
                       title="Длинна строки должна быть от 2 до 50 символов. Цифры недопустимы."
                       value="${user.firstName}">
            </div>
        </div>
        <div class="form-group">
            <label for="secondName" class="col-sm-2 control-label">Отчество</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="secondName" id="secondName" placeholder="Отчество"
                       required="" pattern="[\D]{2,50}"
                       title="Длинна строки должна быть от 2 до 50 символов. Цифры недопустимы."
                       value="${user.secondName}">
            </div>
        </div>
        <div class="form-group">
            <label for="lastName" class="col-sm-2 control-label">Фамилия</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Фамилия"
                       required="" pattern="[\D]{2,50}"
                       title="Длинна строки должна быть от 2 до 50 символов. Цифры недопустимы."
                       value="${user.lastName}">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-2 control-label">Адрес</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="address" id="address" placeholder="Адрес" required=""
                       pattern="[\S., ]{5,150}" title="Длинна строки должна быть от 5 до 150 символов"
                       value="${user.address}">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Пароль</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="Пароль"
                       required="" maxlength="350" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$"
                       title="Пароль должен содердать минимум 1 латинскую букву и цифру. Длинна пароля от 8 до 16 символов"
                       value="${user.password}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${user.userName == null}"><input type="userName" name="uuid"
                                                             value="${user.uuid}${id}"></c:if>
                <input type="hidden" name="uuid" value="${user.uuid}${id}">
                <button type="submit" class="btn btn-default col-md-offset-8">Сохранить</button>
            </div>
        </div>
    </form>
</div>


<%@ include file="../template/footer.html" %>