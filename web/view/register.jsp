<%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 31.10.2019
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-sm-6 col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Регистрация</h3>
                </div>
                <div class="panel-body">
                    <div class="col-xl-12 col-sm-12 col-md-12 login-box">
                        <form role="form" method="post" id="registerForm">
                            <div class="input-group">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-user"></span></span>
                                <input type="text" name="firstname" class="form-control" placeholder="Имя" required
                                       autofocus/>
                            </div>
                            <div class="input-group">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-user"></span></span>
                                <input type="text" name="lastname" class="form-control" placeholder="Фамилия" required
                                       autofocus/>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-envelope"></span></span>
                                <input type="email" name="email" class="form-control" placeholder="Электронная почта"
                                       required autofocus/>
                            </div>
                            <div class="input-group">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-asterisk"></span></span>
                                <input type="text" name="login" class="form-control" placeholder="Логин" required
                                       autofocus/>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" name="password" class="form-control" placeholder="Пароль"
                                       required autofocus/>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xl-8 col-sm-8 col-md-8">
                            <button type="submit" form="registerForm" class="btn btn-labeled btn-success">
                                <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span>Зарегистрироваться
                            </button>
                        </div>
                        <div class="col-xl-4 col-sm-4 col-md-4">
                            <button type="button" onclick="location.href='..'"
                                    class="btn btn-labeled btn-danger">
                                <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>На главную
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
