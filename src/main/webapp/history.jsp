<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "История моих заказов");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>


<h1>История моих заказов.</h1>

<div class="container-fluid catalogItems">
    <c:if test="${edit != null}">
    <div class="bg-info"><c:out value="${edit}"/></div>
            <% session.setAttribute("edit", null);%>
    </c:if>
    <c:if test="${requestScope.orders.size() == 0 }">
    <div class="row spaceBefore70"><h4 class="col-md-offset-1 col-md-10">У Вас еще нет заказов.</h4></div>
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
        <div class="col-md-3"><c:out value="${order.login_user}"/></div>
        <div class="col-md-2"><c:out value="${order.date}"/></div>
        <div class="col-md-1"><!--<c:out value="$ {order.products}"/>--></div>
        <div class="col-md-1"><c:out value="${order.cost}"/></div>
        <div class="col-md-1"><c:out value="${order.status}"/></div>
        <div class="col-md-3">
            <button type="button" class="btn btn-primary"
                    onclick="window.location.href='<%=request.getContextPath()%>/orderView?id=${order.uuid}'">
                Подробнее
            </button>
        </div>
    </div>
    <br>
    </c:forEach>
    <div class="row">


<%@ include file="template/footer.html" %>