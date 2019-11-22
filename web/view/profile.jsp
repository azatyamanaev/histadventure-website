<%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 28.10.2019
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
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
        </ul>
    </div>
</nav>
<div class="row">
    <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
        <div class="col-sm-12">
            <div class="col-xs-12 col-sm-8">
                <h2>
                    <c:out value="${user.firstName}"/>
                    <c:out value="${' '}"/>
                    <c:out value="${user.lastName}"/>
                </h2>
            </div>
            <div class="btn-group" role="group">
                <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Созданные мероприятия
                </button>
                <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                    <c:choose>
                        <c:when test="${user.createdEvents != null}">
                            <c:forEach var="event" items="${user.createdEvents}">
                                <a class="dropdown-item" onclick="location.href='/event'">
                                    <c:out value="${event.name}"/>
                                </a>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p class="dropdown-item">Нет созданных мероприятий</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="btn-group" role="group">
                <button id="eventsDrop" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"> Понравившиеся мероприятия
                </button>
                <div class="dropdown-menu" aria-labelledby="eventsDrop">
                    <c:choose>
                        <c:when test="${user.subscribedEvents != null}">
                            <c:forEach var="subEvent" items="${user.subscribedEvents}">
                                <a class="dropdown-item" onclick="location.href='/event'">
                                    <c:out value="${subEvent.name}"/>
                                </a>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p class="dropdown-item">Нет понравившихся мероприятий</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <button class="btn btn-info" data-toggle="modal" data-target="#editModal">Создать мероприятие</button>
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
                <form>
                    <div class="form-group">
                        <label for="event-name" class="col-form-label">Название:</label>
                        <input type="text" class="form-control" id="event-name">
                    </div>
                    <div class="form-group">
                        <label for="event-description" class="col-form-label">Описание:</label>
                        <textarea class="form-control" id="event-description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="event-capacity" class="col-form-label">Вместимость:</label>
                        <input type="number" class="form-control" id="event-capacity">
                    </div>
                    <div class="form-group">
                        <label for="event-place" class="col-form-label">Место проведения:</label>
                        <input type="text" class="form-control" id="event-place">
                    </div>
                    <div class="form-group">
                        <label for="event-time-start" class="col-form-label">Начало:</label>
                        <input type="text" class="form-control" id="event-time-start">
                    </div>
                    <div class="form-group">
                        <label for="event-time-end" class="col-form-label">Конец:</label>
                        <input type="text" class="form-control" id="event-time-end">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Создать мероприятие</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
