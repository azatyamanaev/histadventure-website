<%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 31.10.2019
  Time: 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
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
            <li class="nav-item">
                <a class="nav-link" onclick="location.href='/events'">Мероприятия</a>
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
        </ul>
    </div>
</nav>
<div class="row">
    <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
        <div class="col-sm-12">
            <div class="col-xs-12 col-sm-8">
                <h2>Название мероприятия:<c:out value="${event.name}"/></h2>
                <p><strong>Описание: </strong><c:out value="${event.description}"/></p>
                <p><strong>Учаcтники: </strong>
                    <c:forEach var="participant" items="${event.participants}">
                <form role="form" method="post" action="/other-profile" id="user-form">
                    <c:out value="${participant.login}"/>
                    <button type="submit" form="user-form" name="but-user" value="${participant.login}"
                            class="btn btn-info"><i
                            class="fas fa-user"></i>
                    </button>
                </form>
                </c:forEach></p>
                <p><strong>Максимальное число участвующих: </strong><c:out value="${event.capacity}"/></p>
                <p><strong>Руководитель: </strong><c:out value="${event.host}"/></p>
                <p><strong>Состояние: </strong><c:choose>
                    <c:when test="${event.active == true}">
                        <c:out value="${'активно'}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${'не активно'}"/>
                    </c:otherwise>
                </c:choose></p>
                <p><strong>Место проведения: </strong><c:out value="${event.place}"/></p>
                <p><strong>Начало: </strong><c:out value="${event.timeStart}"/></p>
                <p><strong>Конец: </strong><c:out value="${event.timeEnd}"/></p>
                <p><strong>Количество лайков: </strong><c:out value="${event.countLike}"/></p>
                <c:if test="${auth == true}">
                    <button class="btn btn-info" onclick="location.href='/participate'">Принять участие</button>
                </c:if>
                <c:if test="${user.role == 'ADMIN' || user.login == event.host}">
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#editModal"><i
                            class="fas fa-edit"></i></button>
                </c:if>
                <div id="data"></div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Редактирование мероприятия</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form role="form" action="/event" method="post" id="editEventInfo">
                    <div class="form-group">
                        <label for="event-name" class="col-form-label">Название:</label>
                        <input type="text" name="name" class="form-control" id="event-name">
                    </div>
                    <div class="form-group">
                        <label for="event-description" class="col-form-label">Описание:</label>
                        <textarea name="description" class="form-control" id="event-description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="event-capacity" class="col-form-label">Вместимость:</label>
                        <input type="number" name="capacity" class="form-control" id="event-capacity">
                    </div>
                    <div class="form-group">
                        <label for="event-host" class="col-form-label">Организатор:</label>
                        <input type="text" name="host" class="form-control" id="event-host">
                    </div>
                    <div class="form-group">
                        <label for="event-active" class="col-form-label">Состояние:</label>
                        <input type="checkbox" name="active" class="form-control" id="event-active">
                    </div>
                    <div class="form-group">
                        <label for="event-place" class="col-form-label">Место проведения:</label>
                        <input type="text" name="place" class="form-control" id="event-place">
                    </div>
                    <div class="form-group">
                        <label for="event-time-start" class="col-form-label">Начало:</label>
                        <input type="text" name="time-start" class="form-control" id="event-time-start">
                    </div>
                    <div class="form-group">
                        <label for="event-time-end" class="col-form-label">Конец:</label>
                        <input type="text" name="time-end" class="form-control" id="event-time-end">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btn-ev" type="submit" form="editEventInfo" class="btn btn-primary">Сохранить изменения
                </button>
            </div>
        </div>
    </div>
</div>
<script src="jquery/jquery-3.4.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/event-ajax.js"></script>
</body>
</html>
