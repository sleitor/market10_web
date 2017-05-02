<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>


<h1>Список заказов, сделанных пользователями.</h1>

<div class="container-fluid catalogItems">
    <c:if test="${edit != null}">
    <div class="bg-info"><c:out value="${edit}"/></div>
            <% session.setAttribute("edit", null);%>
    </c:if>
    <div class="row catalogItem">
        <div class="col-md-1"><h4>№ заказа</h4></div>
        <div class="col-md-3"><h4>Пользователь</h4></div>
        <div class="col-md-2"><h4>Дата</h4></div>
        <div class="col-md-1"><h4>Продукты</h4></div>
        <div class="col-md-1"><h4>Стоимость</h4></div>
        <div class="col-md-1"><h4>Статус</h4></div>
        <div class="col-md-3"><h4>Действия</h4></div>
    </div>
    <c:forEach items="${requestScope.orders}" var="order">
    <div class="row catalogItem">
        <div class="col-md-1"><c:out value="${order.uuid}"/></div>
        <div class="col-md-3"><c:out value="${order.uuid_user}"/></div>
        <div class="col-md-2"><c:out value="${order.date}"/></div>
        <div class="col-md-1"><!--<c:out value="$ {order.products}"/>--></div>
        <div class="col-md-1"><c:out value="${order.cost}"/></div>
        <div class="col-md-1"><c:out value="${order.status}"/></div>
        <div class="col-md-3">
            <button type="button" class="btn btn-primary"
                    onclick="window.location.href='<%=request.getContextPath()%>/admin/orderEdit?id=${order.uuid}&action=edit'">
                Изменить
            </button>
            <button type="button" class="btn btn-danger"
                    onclick="window.location.href='<%=request.getContextPath()%>/admin/orderEdit?id=${order.uuid}&action=delete'">
                Удалить
            </button>
        </div>
    </div>
    <br>
    </c:forEach>
    <div class="row">


<%@ include file="../template/footer.html" %>