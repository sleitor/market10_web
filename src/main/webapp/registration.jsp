<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='template/header.html' charEncoding='utf-8'/>

<div class="registrationForm">
    <br>
    <h1>Регистрация</h1>
    <br>
    <br>

    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">login</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="login" required="">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" name="email" id="email" placeholder="E-mail" required="">
            </div>
        </div>
        <div class="form-group">
            <label for="firstName" class="col-sm-2 control-label">Имя</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Имя" required="">
            </div>
        </div>
        <div class="form-group">
            <label for="secondName" class="col-sm-2 control-label">Отчество</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="secondName" id="secondName" placeholder="Отчество" required="">
            </div>
        </div>
        <div class="form-group">
            <label for="lastName" class="col-sm-2 control-label">Фамилия</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Фамилия" required="">
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-2 control-label">Адрес</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="address" id="address" placeholder="Адрес" required="">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Пароль</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="Пароль" required="">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Регистрация</button>
            </div>
        </div>
    </form>
</div>

<%@ include file="template/footer.html" %>