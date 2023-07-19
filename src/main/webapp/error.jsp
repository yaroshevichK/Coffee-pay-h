<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>

<h3>Страница ошибки</h3>

<br>
${message}
<br>

<br>
<a href="${pageContext.request.contextPath}/coffee-manage?command=login">Главная страница</a>
</body>
</html>
