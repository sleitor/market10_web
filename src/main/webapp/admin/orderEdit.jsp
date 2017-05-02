<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Редактирование заказа");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>

<h1>Редактирование заказа.</h1>

<div class="registrationForm">
    <br>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">ID</label>
            <div class="col-sm-10">
                <p class="form-control-static">${order.uuid}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Пользователь</label>
            <div class="col-sm-10">
                <p class="form-control-static">${order.uuid_user}</p>
            </div>
        </div>
        <div class="form-group">
            <label for="quantity" class="col-sm-2 control-label">Дата заказа</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="quantity" id="quantity" value="${order.date}"
                       placeholder="Дата заказа" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="status" class="col-sm-2 control-label">Статус</label>
            <div class="col-sm-10">
                <select name="status" id="status" class="form-control" required>
                    <option>...</option>
                    <option value="Новый" <c:if test="${order.status == 'Новый'}">selected</c:if> >Новый</option>
                    <option value="Обработка" <c:if test="${order.status == 'Обработка'}">selected</c:if> >Обработка</option>
                    <option value="Доставка" <c:if test="${order.status == 'Доставка'}">selected</c:if> >Доставка</option>
                    <option value="Выполнен" <c:if test="${order.status == 'Выполнен'}">selected</c:if> >Выполнен</option>
                    <option value="Отменен" <c:if test="${order.status == 'Отменен'}">selected</c:if> >Отменен</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="cost" class="col-sm-2 control-label">Стоимость</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="cost" id="cost" value="${order.cost}"
                       placeholder="Цена" required="" pattern="[\d]{1,4}" min="1" max="9000"
                       title="Число. От 1 до 9000." disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="products" class="col-sm-2 control-label">Заказанные продукты</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="products" id="products" value="${order.orderProducts}"
                       placeholder="Продукты">
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="uuid" value="${order.uuid}">
                <input type="hidden" name="uuid_name" value="${order.uuid_name}">
                <button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </div>
    </form>
</div>


<%@ include file="../template/footer.html" %>