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
        <span class="span">Изменить данные модели</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="modelId" value="${requestScope.model.id}">
            <p>
                <label for="brand">Введите брэнд:</label>
                <input name="brand" id="brand" placeholder="брэнд" value="${requestScope.model.brand}">
            </p>
            <p>
                <label for="nameModel">Введите модель:</label>
                <input name="nameModel" id="nameModel" placeholder="модель" value="${requestScope.model.nameModel}">
            </p>

            <p>
                <button type="submit" name="command" value="updateModel" class="buttonClass">ОБНОВИТЬ</button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
