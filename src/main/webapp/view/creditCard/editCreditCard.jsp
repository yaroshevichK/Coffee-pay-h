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
        <span class="span">Изменить данные банковской карты</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="customerId" value="${requestScope.creditCard.customer.id}"/>
            <input type="hidden" name="creditCardId" value="${requestScope.creditCard.id}"/>

            <p>
                <label for="number">Введите карту:</label>
                <input name="number" id="number" value="${requestScope.creditCard.number}" placeholder="номер карты">
            </p>

            <p>
                <button type="submit" name="command" value="updateCreditCard" class="buttonClass">
                    СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
