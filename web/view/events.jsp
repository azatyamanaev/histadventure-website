<%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 31.10.2019
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" href="../fontawesome-free-5.11.2-web/css/all.css">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="#">HISTADVENTURE</a>
    <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="main-navigation">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <c:if test="${auth == null}">
                <li class="nav-item">
                    <a class="nav-link" onclick="location.href='/register'">Регистрация</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" onclick="location.href='/login'">Вход</a>
                </li>
            </c:if>
            <c:if test="${auth == true}">
                <li class="nav-item">
                    <a class="nav-link" onclick="location.href='/profile'">
                        <c:out value="${user.firstName}"/>
                        <c:out value="${' '}"/>
                        <c:out value="${user.lastName}"/>
                    </a>
                </li>
            </c:if>
            </li>
        </ul>
    </div>
</nav>
<header class="page-header header container-fluid">
    <div class="container align-content-center border-bottom">
        <div class="card">
            <div class="container btn-info">
                <h2 class="text-center">Мероприятия</h2>
            </div>
            <c:set var="number" value="0"></c:set>
            <c:choose>
                <c:when test="${events != null}">
                    <ul class="list-group">
                        <c:forEach var="event" items="${events}">
                            <c:set var="number" value="${number + 1}"/>
                            <li class="list-group-item">
                                <c:out value="${event}"/>
                                <div style="float: right" class="btn-group" role="group" aria-label="Basic example">
                                    <button type="button" class="btn btn-info"><i class="fas fa-star"></i></button>
                                    <button id="button<c:out value="${number}"/>" value="<c:out value="${event}"/>"
                                            type="button" class="btn btn-info" onclick="submit(id)">
                                        <i class="fas fa-info"></i></button>
                                </div>
                                <div id="data"></div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <div class="container card rounded">
                        <h5 class="text-center">Пока что нет ни одного мероприятия</h5>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
<footer class="page-footer">
    <div class="container">
        <div class="footer-copyright text-center">© 2019 Copyright: MyWebsite.com</div>
    </div>
</footer>
<script src="../jquery/jquery-3.4.1.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../js/main.js"></script>
<script src="../js/events.js"></script>
</body>
</html>
