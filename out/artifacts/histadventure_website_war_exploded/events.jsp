<%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 31.10.2019
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
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
                <a class="nav-link" href='/events'>Мероприятия</a>+
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">О сайте</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Мой профиль</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="register.jsp">Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="login.jsp">Вход</a>
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
            <%
                List<String> names = (List<String>) request.getAttribute("events");

                if (names != null && !names.isEmpty()) {
                    out.println("<ul class=\"list-group\">");
                    for (String s : names) {
                        out.println("<li class=\"list-group-item\">" + s + "</li>");
                    }
                    out.println("</ul>");

                } else out.println("<div class=\"container card rounded\">\n" +
                        "   <h5 class=\"text-center\">Пока что нет ни одного мероприятия!</h5>\n" +
                        "</div>");
            %>
        </div>
    </div>
    <div class="overlay"></div>
</header>
<footer class="page-footer">
    <div class="container">
        <!--
        <div class="row">
             <div class="col-lg-8 col-md-8 col-sm-12">
                 <h6 class="text-uppercase font-weight-bold">Additional Information</h6>
                 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque interdum quam odio, quis placerat
                     ante luctus eu. Sed aliquet dolor id sapien rutrum, id vulputate quam iaculis.</p>
                 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque interdum quam odio, quis placerat
                     ante luctus eu. Sed aliquet dolor id sapien rutrum, id vulputate quam iaculis.</p>
             </div>
         </div>
         -->
        <div class="footer-copyright text-center">© 2019 Copyright: MyWebsite.com</div>
    </div>
</footer>
<script src="jquery/jquery-3.4.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
