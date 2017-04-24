<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='template/header.html' charEncoding='utf-8'/>

<h2 class="pageName">Корзина</h2>

<c:if test="${sessionScope.cart == null }">
    <div class="row spaceBefore70"><h4 class="col-md-offset-1 col-md-10">Корзина пуста.</h4></div>
</c:if>
<c:if test="${sessionScope.cart != null && sessionScope.cart.size() > 0}">
    <%--<% HashMap<Long,Integer> cart = (HashMap) request.getSession().getAttribute("cart");%>--%>
    <%--id1: <%=cart.get(1)%><br>--%>
    <%--id2: <%=cart.get(2)%><br>--%>
    <div class="container-fluid catalogItems">
        <c:forEach items="${sessionScope.cartProduct}" var="item">
            <div class="row catalogItem">
                <div class="col-md-3 text-center">
                    <div class="image noBorder">
                        <h1>No image</h1>
                    </div>
                </div>
                <div class="col-md-6 productNameBlock">
                    <h3><c:out value="${item.name}"></c:out></h3>
                    <h4><c:out value="${item.description}"></c:out></h4>
                </div>
                <div class="col-md-3">
                    <h4>
                        <smal>Желаемое количество:</smal>
                        <c:out value="${sessionScope.cart.get['1']}"></c:out>
                        <small> шт.</small>
                    </h4>
                    <h3>
                        <smal>Цена:</smal>
                        <c:out value="${item.cost}"></c:out>
                        <small> руб.</small>
                    </h3>
                    <button type="button" class="btn btn-primary">Купить</button>
                </div>

            </div>
        </c:forEach>
    </div>
</c:if>


<%@ include file="template/footer.html" %>