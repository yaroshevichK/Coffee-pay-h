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
        <span class="span">Добавить продукцию</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="prevUrlMachines" value="${requestScope.prevUrlMachines}"/>
            <input type="hidden" name="machineId" value="${requestScope.machineId}"/>

            <p>
                <label for="productId">Продукция</label>
                <select name="productId" id="productId">
                    <option value="">не выбрано</option>
                    <c:forEach var="product" items="${requestScope.products}">
                        <option value="${product.id}">
                                ${product.name}, ${product.price}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <button type="submit" name="command" value="newMachineProduct" class="buttonClass">
                    СОХРАНИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
