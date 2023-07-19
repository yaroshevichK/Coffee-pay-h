<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-form-style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<div class="container">
    <div class="div">
        <span class="span">Профиль</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="customerId" value="${requestScope.customer.id}">
            <p>
                <label for="name">Введите имя:</label>
                <input name="name" id="name" placeholder="имя" value="${requestScope.customer.name}">
            </p>
            <p>
                <label for="surname">Введите фамилию:</label>
                <input name="surname" id="surname" placeholder="фамилия" value="${requestScope.customer.surname}">
            </p>

            <p>
                <label for="phone">Введите телефон:</label>
                <input type="tel" name="phone" id="phone" placeholder="телефон" value="${requestScope.customer.phone}">
            </p>

            <p>
                <label for="email">Введите эл. почту:</label>
                <input type="email" name="email" id="email" placeholder="эл. почта"
                       value="${requestScope.customer.email}">
            </p>

            <p>
                <button type="submit" name="command" value="profile" class="buttonClass">
                    ОБНОВИТЬ
                </button>
                <button type="submit" name="command" value="main" class="buttonClass">ОТМЕНА</button>
            </p>

        </form>

    </div>
</div>
</body>

</html>
