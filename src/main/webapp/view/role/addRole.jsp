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
        <span class="span">Добавить роль</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <p>
                <label for="name">Введите название роли:</label>
                <input name="name" id="name" placeholder="роль">
            </p>

            <p>
                <button type="submit" name="command" value="newRole" class="buttonClass">СОХРАНИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>