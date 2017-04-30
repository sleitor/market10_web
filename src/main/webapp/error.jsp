<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Страница с ошибкой");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>
<br>
<br>
<h1>Ошибка доступа.<br>
    <small><a href="/market/">Главная страница</a></small>
</h1>
<br>
<c:out value="${requestScope.error}"></c:out>


<%@ include file="template/footer.html" %>