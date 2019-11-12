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
    <title></title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
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
        </div>
    </div>
</div>
</div>


</body>
</html>
