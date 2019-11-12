<%--
  Created by IntelliJ IDEA.
  User: monitor
  Date: 31.10.2019
  Time: 2:01
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
                        Вход</h3>
                </div>
                <div class="panel-body">
                    <div class="col-xl-12 col-sm-12 col-md-12 login-box">
                        <form role="form" method="post" id="loginForm">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                                <input type="email" name="email" class="form-control" placeholder="Электронная почта" required autofocus />
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" name="password" class="form-control" placeholder="Пароль" required />
                            </div>
                            <p>
                               <!-- <a href="#">Забыли свой пароль?</a></p> -->
                            У вас нет аккаунта? <a onclick="location.href='/register'">Регистрация</a>
                        </form>
                    </div>

                </div>
                <div class="panel-footer">
                    <div class="row">
                        <!-- <div class="col-xl-6 col-sm-6 col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="checkbox" value="Remember">
                                    Запомнить меня
                                </label>
                            </div>
                        </div> -->
                        <div class="col-xl-6 col-sm-6 col-md-6">
                            <button type="submit" form="loginForm" class="btn btn-labeled btn-success">
                                <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span>Войти</button>
                            <button type="button" onclick="location.href='..'" class="btn btn-labeled btn-danger">
                                <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Выход</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
