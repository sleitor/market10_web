<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Kaталог");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>
<br>
<h1>
    <c:if test="${action == 'edit'}">Редактирование продукта</c:if>
    <c:if test="${action == 'add'}">Создание продукта</c:if>

</h1>
<br>
<div class="registrationForm">
    <c:if test="${product.uuid == null}"><c:set var="product.uuid"></c:set></c:if>
    <c:if test="${error != null}">
        <div class="bg-danger"><c:out value="${error}"/></div>
    </c:if>
    <br>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Название</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" id="name" value="${product.name}"
                       placeholder="Название" required="" pattern="[\D]{2,50}"
                       title="Длинна строки должна быть от 2 до 50 символов. Цифры недопустимы.">
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">Описание</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="description" id="description"
                       value="${product.description}" placeholder="Описание" required="" pattern="[A-Za-zА-Яа-яЁё ,.\d]{2,50}"
                       title="Длинна строки должна быть от 2 до 50 символов.">
            </div>
        </div>
        <div class="form-group">
            <label for="quantity" class="col-sm-2 control-label">Количество</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="quantity" id="quantity" value="${product.quantity}"
                       placeholder="Количество" required="" pattern="[\d]{1,4}" min="0" max="900"
                       title="Число. От 0 до 900.">
            </div>
        </div>
        <div class="form-group">
            <label for="cost" class="col-sm-2 control-label">Цена</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="cost" id="cost" value="${product.cost}"
                       placeholder="Цена" required="" pattern="[\d]{1,4}" min="1" max="9000" step="0.1"
                       title="Число. От 1 до 9000.">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id"  value="${product.uuid}${id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </div>
    </form>
</div>


<%@ include file="../template/footer.html" %>