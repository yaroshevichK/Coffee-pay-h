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
        <span class="span">Изменить данные по сидке</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="discountId" value="${requestScope.discount.id}">
            <p>
                <label for="amount">Введите сумму:</label>
                <input type="number" name="amount" id="amount" placeholder="сумма" value="${requestScope.discount.amount}">
            </p>
            <p>
                <label for="percent">Введите процент:</label>
                <input type="number" name="percent" id="percent" placeholder="процент" value="${requestScope.discount.percent}">
            </p>
            <p>
                <button type="submit" name="command" value="updateDiscount" class="buttonClass">ОБНОВИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
