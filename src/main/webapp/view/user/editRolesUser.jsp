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
        <span class="span">Корректировка списка ролей</span>

        <form class="validate-form" action="coffee-manage" method="post">
            <input type="hidden" name="prevURL" value="${requestScope.prevURL}"/>
            <input type="hidden" name="username" value="${user.username}">
            <br>
            <p>
            <div class="select">
                <label for="checkRoles">Список ролей</label>
                <br>
                <c:forEach var="role" items="${requestScope.roles}">
                    <c:choose>
                        <c:when test="${user.roles.contains(role)}">
                            <input type="checkbox" name="checkRoles" id="checkRoles" value="${role.id}" checked>${role.name}
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="checkRoles" id="checkRoles" value="${role.id}">${role.name}
                        </c:otherwise>
                    </c:choose>


                </c:forEach>
            </div>
            </p>
            <p>
                <button type="submit" name="command" value="editRolesUser" class="buttonClass">
                    ОБНОВИТЬ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevURL}" class="buttonClass">ОТМЕНА</a>
            </p>
        </form>

    </div>
</div>
</body>

</html>
