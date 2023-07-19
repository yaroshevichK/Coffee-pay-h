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
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <label for="username">Логин: ${username}</label>
                <input type="hidden" name="username" id="username" placeholder="логин" value="${username}">
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
                <button type="submit" name="command" value="editUserPassword" class="buttonClass">ОБНОВИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
