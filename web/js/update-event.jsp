<%@ page import="ru.itis.entities.Event" %>
<%@ page import="ru.itis.repositories.EventsRepository" %>
<%@ page import="ru.itis.repositories.EventsRepositoryJdbcImpl" %>
<%@ page import="ru.itis.models.ConnectionCreator" %><%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 22.11.2019
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String) request.getAttribute("name");
    String description = (String) request.getAttribute("description");
    String c = (String) request.getAttribute("capacity");
    Integer capacity = Integer.parseInt(c);
    String host = (String) request.getAttribute("host");
    String place = (String) request.getAttribute("place");
    String timeStart = (String) request.getAttribute("time-start");
    String timeEnd = (String) request.getAttribute("time-end");
    Boolean active = (Boolean) request.getAttribute("active");
    Long id = Long.valueOf(4);
    Event event = new Event(id, name, description, capacity, host, active, place, timeStart, timeEnd, 0);
    EventsRepository eventsRepository = new EventsRepositoryJdbcImpl(ConnectionCreator.getConnection());
    eventsRepository.update(event);
%>
