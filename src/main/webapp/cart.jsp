<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Корзина");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>
<h2 class="pageName">Корзина</h2>

<c:if test="${sessionScope.cart.size() == 0 }">
    <div class="row spaceBefore70"><h4 class="col-md-offset-1 col-md-10">Корзина пуста.</h4></div>
</c:if>
<c:if test="${sessionScope.cart.size() > 0}">
    <%--<% HashMap<Long,Integer> cart = (HashMap) request.getSession().getAttribute("cart");%>--%>
    <%--id1: <%=cart.get(1)%><br>--%>
    <%--id2: <%=cart.get(2)%><br>--%>
    <div class="container-fluid catalogItems">
        <c:if test="${add != null}">
            <div class="bg-info"><c:out value="${add}"/></div>
            <% session.setAttribute("add", null);%>
        </c:if>
        <c:forEach items="${sessionScope.cartProduct}" var="item">
            <div class="row catalogItem">
                <div class="col-md-3 text-center">
                    <div class="image noBorder">
                        <h1>No image</h1>
                    </div>
                </div>
                <div class="col-md-6 productNameBlock">
                    <h3><c:out value="${item.name}"/></h3>
                    <h4><c:out value="${item.description}"/></h4>
                </div>
                <div class="col-md-3">
                    <h4>
                        <smal>Желаемое количество:</smal>

                        <c:out value="${sessionScope.cart.get(item.uuid)}"/>
                        <small> шт.</small>
                    </h4>
                    <h3>
                        <smal>Цена:</smal>
                        <c:out value="${item.cost}"/>
                        <small> руб.</small>
                    </h3>
                    <button type="button" class="btn btn-primary" onclick="window.location.href='<%=request.getContextPath()%>/cart?id=${item.uuid}&action=add'">Добавить</button>
                </div>

            </div>
        </c:forEach>
        <div class="row">
            <div class="col-md-offset-8 col-md-3">
                <button type="button" class="btn btn-success btn-block"
                        onclick="window.location.href='<%=request.getContextPath()%>/cart/order'">Оформить заказ
                </button>
            </div>
        </div>
    </div>


</c:if>


<%@ include file="template/footer.html" %>