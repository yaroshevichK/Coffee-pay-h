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
        <span class="span">Изменить тип оплаты</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="typePaymentId" value="${requestScope.typePayment.id}">
            <p>
                <label for="name">Введите название:</label>
                <input name="name" id="name" placeholder="название" value="${requestScope.typePayment.name}">
            </p>
            <p>
                <label for="usePhoneNumber">Использовать номер телефона:</label>
                <c:choose>
                    <c:when test="${requestScope.typePayment.usePhoneNumber}">
                        <input type="checkbox" name="usePhoneNumber" id="usePhoneNumber" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="usePhoneNumber" id="usePhoneNumber">
                    </c:otherwise>
                </c:choose>
            </p>
            <p>
                <label for="useCreditCard">использовать банковские карты:</label>
                <c:choose>
                    <c:when test="${requestScope.typePayment.useCreditCard}">
                        <input type="checkbox" name="useCreditCard" id="useCreditCard" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="useCreditCard" id="useCreditCard">
                    </c:otherwise>
                </c:choose>
            </p>

            <p>
                <button type="submit" name="command" value="updateTypePayment" class="buttonClass">ОБНОВИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
