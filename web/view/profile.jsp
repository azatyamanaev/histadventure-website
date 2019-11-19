<%@ page import="java.util.List" %>
<%@ page import="ru.itis.entities.Event" %><%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 28.10.2019
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="row">
    <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
        <div class="col-sm-12">
            <div class="col-xs-12 col-sm-8">
                <%
                    String firstName = (String) request.getSession().getAttribute("firstname");
                    String lastName = (String) request.getSession().getAttribute("lastname");
                    out.print("<h2>" + firstName + " " + lastName + "</h2>");
                %>
            </div>
            <div class="btn-group" role="group">
                <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Созданные мероприятия
                </button>
                <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                    <%
                        List<Event> createdEvents = (List<Event>) request.getSession().getAttribute("createdEvents");
                        if (createdEvents != null) {
                            for (Event event : createdEvents) {
                                out.print("<a class=\"dropdown-item\" onclick=\"location.href='/event'\">" + event.getName() + "</a>");
                            }
                        } else {
                            out.print("<p class=\"dropdown-item\">Нет созданных мероприятий</p>");
                        }
                    %>

                </div>
            </div>
            <div class="btn-group" role="group">
                <button id="eventsDrop" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"> Понравившиеся мероприятия
                </button>
                <div class="dropdown-menu" aria-labelledby="eventsDrop">
                    <%
                        List<Event> subscribedEvents = (List<Event>) request.getSession().getAttribute("subscribedEvents");
                        if (subscribedEvents != null) {
                            for (Event event : subscribedEvents) {
                                out.print("<a class=\"dropdown-item\" onclick=\"location.href='/event'\">" + event.getName() + "</a>");
                            }
                        } else {
                            out.print("<p class=\"dropdown-item\">Нет понравившихся мероприятий</p>");
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
