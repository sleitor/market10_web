<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>
<%--Session: <%=request.getSession()%><br>--%>

<h2 class="pageName">Каталог товаров:</h2>
<div class="container-fluid catalogItems">
    <c:forEach items="${requestScope.products}" var="product">
        <div class="row catalogItem">
            <div class="col-md-3 text-center">
                <div class="image noBorder">
                    <h1>No image</h1>
                </div>
            </div>
            <div class="col-md-6 productNameBlock">
                <h3><a href="product?id=${product.uuid}"><c:out value="${product.name}"></c:out></a></h3>
                <h4><c:out value="${product.description}"></c:out></h4>
            </div>
            <div class="col-md-3">
                <h4>
                    <smal>Количество:</smal>
                    <c:out value="${product.quantity}"></c:out>
                    <small> шт.</small>
                </h4>
                <h3>
                    <smal>Цена:</smal>
                    <c:out value="${product.cost}"></c:out>
                    <small> руб.</small>
                </h3>
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href='<%=request.getContextPath()%>/cart?id=${product.uuid}&action=add'">
                    Купить
                </button>
            </div>

        </div>
    </c:forEach>
</div>

<%@ include file="template/footer.html" %>