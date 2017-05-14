<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Логин");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>
<h2 class="pageName"></h2>

    <form class="form-signin" method="post" action="login">
        <c:if test="${error != null}"><div class="bg-danger"><c:out value="${error}"></c:out></div></c:if>
        <h2 class="form-signin-heading">Пожалуйста, авторизуйтесь</h2>
        <label for="inputLogin" class="sr-only">login</label>
        <input type="text" name="login" value="admin" id="inputLogin" class="form-control" placeholder="login"
               required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" value="admin" id="inputPassword" class="form-control"
               placeholder="Password" required="">
        <div class="checkbox">
            <label>
                <input type="checkbox" name="remember-me" value="true"> Remember me
            </label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

<%@ include file="template/footer.html" %>