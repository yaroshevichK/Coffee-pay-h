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
        <span class="span">Изменить продукцию</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="productId" value="${requestScope.product.id}">
            <p>
                <label for="name">Введите название:</label>
                <input name="name" id="name" placeholder="название" value="${requestScope.product.name}">
            </p>
            <p>
                <label for="price">Введите цену:</label>
                <input type="number" step="0.01" name="price" id="price" placeholder="цена" value="${requestScope.product.price}">
            </p>

            <p>
                <button type="submit" name="command" value="updateProduct" class="buttonClass">ОБНОВИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
