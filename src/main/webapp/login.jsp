<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Логин");%>
<c:import url='template/header.html' charEncoding='utf-8'/>
<h2 class="pageName"></h2>

    <form class="form-signin" method="post">
        <h2 class="form-signin-heading">Пожалуйста, авторизуйтесь</h2>
        <label for="inputLogin" class="sr-only">login</label>
        <input type="text" name="login" id="inputLogin" class="form-control" placeholder="login" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required="">
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

<%@ include file="template/footer.html" %>