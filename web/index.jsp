<%--
Created by IntelliJ IDEA.
User: monitor
Date: 28.10.2019
Time: 8:47
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HISTADVENTURE</title>
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
            <%
                Boolean auth = (Boolean) request.getSession().getAttribute("auth");
                if (auth == null) {
                    out.println("<li class=\"nav-item\">\n" +
                            "                <a class=\"nav-link\" onclick=\"location.href='/register'\">Регистрация</a>\n" +
                            "            </li>\n" +
                            "<li class=\"nav-item\">\n" +
                            "                <a class=\"nav-link\" onclick=\"location.href='/login'\">Вход</a>\n" +
                            "            </li>");
                } else if (auth) {
                    String firstName = (String) request.getSession().getAttribute("firstname");
                    String lastName = (String) request.getSession().getAttribute("lastname");
                    out.println("<li class=\"nav-item\">\n" +
                            "                <a class=\"nav-link\" onclick=\"location.href='/profile'\">" + firstName + " " + lastName + "</a>\n" +
                            "            </li>");
                }
            %>

        </ul>
    </div>
</nav>
<header class="page-header header container-fluid">
    <div class="overlay"></div>
    <div class="description">
        <h1>Добро пожаловать на HISTADVENTURE!</h1>
        <p>На этом сайте вы сможете найти различные мероприятия на историческую тематику</p>
    </div>
</header>
<footer class="page-footer">
    <div class="container">
        <div class="footer-copyright text-center">© 2019 Copyright: MyWebsite.com</div>
    </div>
</footer>
<script src="jquery/jquery-3.4.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
