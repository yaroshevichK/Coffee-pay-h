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
        <span class="span">Изменить данные аппарата</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="machineId" value="${requestScope.machine.id}">

            <p>
                <label for="serialNumber">Введите серийный номер:</label>
                <input name="serialNumber" id="serialNumber" placeholder="серийный номер"
                       value="${requestScope.machine.serialNumber}">
            </p>

            <p>
                <select name="addressId">
                    <option value="">не выбрано</option>
                    <c:forEach var="address" items="${requestScope.addresses}">
                        <c:choose>
                            <c:when test="${address.id eq requestScope.machine.address.id}">
                                <option selected value="${address.id}">
                                        ${address.city}, ${address.street}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${address.id}">
                                        ${address.city}, ${address.street}
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <p>
                <select name="modelId">
                    <option value="">не выбрано</option>
                    <c:forEach var="model" items="${requestScope.models}">
                        <c:choose>
                            <c:when test="${model.id eq requestScope.machine.model.id}">
                                <option selected value="${model.id}">
                                        ${model.brand}, ${model.nameModel}
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${model.id}">
                                        ${model.brand}, ${model.nameModel}
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <p>
                <button type="submit" name="command" value="updateMachine" class="buttonClass">ОБНОВИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
