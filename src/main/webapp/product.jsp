<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='template/header.html' charEncoding='utf-8'/>
<div class="row">
    <div class="col-sm-offset-1 col-sm-10">
        <h1><c:out value="${product.name}"></c:out></h1>
        <div class="row spaceBefore70">
            <div class="col-md-3 text-center">
                <div class="image">
                    <h1>No image</h1>
                </div>

                <h4>
                    <smal>Количество:</smal>
                    <c:out value="${product.quantity}"></c:out>
                    <small> шт.</small>
                </h4>
            </div>

            <div class=" col-md-offset-6 col-md-3 text-right">
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

        <div class="row fullDescription">

            <h4 class="col-md-12"><c:out value="${product.description}"></c:out></h4>
        </div>

    </div>
</div>


<%@ include file="template/footer.html" %>