<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Вход в систему</span>

        <form class="validate-form" action="auth" method="post">
            <p>
                <label for="username">Введите логин:</label>
                <input name="username" id="username" placeholder="имя пользователя">
            </p>
            <p>
                <label for="password">Введите пароль:</label>
                <input type="password" name="password" id="password" placeholder="пароль">
            </p>

            <p>
                <button type="submit" name="command" value="login" class="buttonClass">ВОЙТИ</button>
            </p>

            <p>
                <label>Нет аккаунта?</label>
                <button type="submit" name="command" value="registration" class="buttonClass" formmethod="get">РЕГИСТРАЦИЯ</button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
