<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Список пользователей");%>
<c:import url='../template/header.jsp' charEncoding='utf-8'/>

<h1>Список пользователей</h1>

<div class="container-fluid catalogItems">
    <c:if test="${edit != null}">
        <div class="bg-info"><c:out value="${edit}"/></div>
        <% session.setAttribute("add", null);%>
    </c:if>
    <div class="row catalogItem">
        <div class="col-md-2"><h4>Имя</h4><h4>Email</h4></div>
        <div class="col-md-3"><h4>ИОФ</h4></div>
        <div class="col-md-3"><h4>Адресс</h4></div>
        <div class="col-md-1"><h4>Права доступа</h4></div>
        <div class="col-md-3"><h4>Изменения</h4></div>
    </div>
    <c:forEach items="${requestScope.users}" var="user">
        <div class="row catalogItem">
            <div class="col-md-2"><c:out value="${user.userName}"/><br><c:out value="${user.email}"/></div>
            <div class="col-md-3"><c:out value="${user.firstName}"/> <c:out value="${user.secondName}"/> <c:out
                    value="${user.lastName}"/></div>
            <div class="col-md-3"><c:out value="${user.address}"/></div>
            <div class="col-md-1">

                <button type="button"
                        onclick="window.location.href='<%=request.getContextPath()%>/admin/userEdit?id=${user.uuid}&action=access'"
                        <c:if test="${!user.role}">
                            class="btn btn-warning">
                            Повысить
                        </c:if>
                        <c:if test="${user.role}">
                            class="btn btn-info">
                            Понизить
                        </c:if>
                </button>
            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href='<%=request.getContextPath()%>/admin/userEdit?id=${user.uuid}&action=edit'">
                    Изменить
                </button>
                <button type="button" class="btn btn-danger"
                        onclick="window.location.href='<%=request.getContextPath()%>/admin/userEdit?id=${user.uuid}&action=delete'">
                    Удалить
                </button>

            </div>
        </div>
        <br>
    </c:forEach>
    <div class="row">
        <button type="submit" class="btn btn-success col-md-offset-8"
                onclick="window.location.href='<%=request.getContextPath()%>/admin/userEdit?action=add'">Добавить
            пользователя
        </button>
    </div>

</div>

<%@ include file="../template/footer.html" %>