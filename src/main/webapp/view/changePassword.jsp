<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Смена пароля</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <p>
                <label>Логин: ${sessionScope.loggedUser.username}</label>
            </p>
            <p>
                <label for="oldPassword">Введите старый пароль:</label>
                <input type="password" name="oldPassword" id="oldPassword" placeholder="старый пароль">
            </p>
            <p>
                <label for="password">Введите новый пароль:</label>
                <input type="password" name="password" id="password" placeholder="новый пароль">
            </p>
            <br>
            <p>
                <button type="submit" name="command" value="changePassword" class="buttonClass">ОБНОВИТЬ</button>
                <button type="submit" name="command" value="main" class="buttonClass">ОТМЕНА</button>
            </p>
        </form>

    </div>
</div>
</body>

</html>
