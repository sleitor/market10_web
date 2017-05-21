<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Редактирование заказа");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>

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
                <p class="form-control-static">${order.login_user}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Дата заказа</label>
            <div class="col-sm-10">
                <p class="form-control-static">${order.date}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Статус</label>
            <div class="col-sm-10">
                <p class="form-control-static">${order.status}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Стоимость</label>
            <div class="col-sm-10">
                <p class="form-control-static">${order.cost}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Заказанные продукты</label>
            <div class="col-sm-10">
                <div class="row catalogItem">
                    <div class="col-xs-4"><p class="form-control-static">Продукт</p></div>
                    <div class="col-xs-4"><p class="form-control-static">Количество</p></div>
                    <div class="col-xs-4"><p class="form-control-static">Цена</p></div>

                </div>
                <c:forEach items="${order.orderProducts}" var="product">
                    <div class="row catalogItem">
                        <div class="col-xs-4"><p class="form-control-static">${product.name_product}</p></div>
                        <div class="col-xs-4"><p class="form-control-static">${product.count}</p></div>
                        <div class="col-xs-4"><p class="form-control-static">${product.cost}</p></div>
                    </div>
                </c:forEach>

            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="uuid" value="${order.uuid}">
                <input type="hidden" name="uuid_user" value="${order.uuid_user}">
                <input type="hidden" class="form-control" name="date" value="${order.date}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" class="form-control" name="cost" value="${order.cost}">
                <button onclick="window.history.back();return false;" class="btn btn-success">Назад</button>
            </div>
        </div>
    </form>
</div>


<%@ include file="template/footer.html" %>