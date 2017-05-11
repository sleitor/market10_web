<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("title", "Главная страница");%>
<c:import url='template/header.jsp' charEncoding='utf-8'/>
<h2 class="pageName"></h2>


    <div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">
            <div class="inner cover">
                <a href='<c:url value="/catalog"/>'><h1 class="cover-heading">Войти в магазин</h1></a>
                <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>
            </div>
        </div>

    </div>

</div>

<%@ include file="template/footer.html" %>