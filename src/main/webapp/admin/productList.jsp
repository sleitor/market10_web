<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Список продуктов");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>

<h1>Список продуктов</h1>

<div class="container-fluid catalogItems">
    <c:if test="${edit != null}">
        <div class="bg-info"><c:out value="${edit}"/></div>
        <% session.setAttribute("add", null);%>
    </c:if>
    <c:forEach items="${requestScope.products}" var="product">
        <div class="row catalogItem">
            <div class="col-md-3 text-center">
                <div class="image noBorder">
                    <h1>No image</h1>
                </div>
            </div>
            <div class="col-md-6 productNameBlock">
                <h3><c:out value="${product.name}"/></h3>
                <h4><c:out value="${product.description}"/></h4>
            </div>
            <div class="col-md-3">
                <h4>
                    <smal>Количество:</smal>
                    <c:out value="${product.quantity}"/>
                    <small> шт.</small>
                </h4>
                <h3>
                    <smal>Цена:</smal>
                    <c:out value="${product.cost}"/>
                    <small> руб.</small>
                </h3>
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href='<%=request.getContextPath()%>/admin/productEdit?id=${product.uuid}&action=edit'">
                    Изменить
                </button>
                <button type="button" class="btn btn-danger"
                        onclick="window.location.href='<%=request.getContextPath()%>/admin/productEdit?id=${product.uuid}&action=delete'">
                    Удалить
                </button>
            </div>

        </div>
    </c:forEach>
    <div class="row catalogItem">
        <button type="submit" class="btn btn-success" onclick="window.location.href='<%=request.getContextPath()%>/admin/productEdit?action=add'">Добавить продукт</button>
    </div>

</div>


<%@ include file="../template/footer.html" %>