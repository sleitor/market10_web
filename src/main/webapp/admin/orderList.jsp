<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>


<h1>Список заказов, сделанных пользователями.</h1>


<%@ include file="../template/footer.html" %>