<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>

<c:set var="paramUrl" value="${requestScope.paramUrl}" scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPage" value="${requestScope.pageable.lastPage}" scope="request"/>
<c:set var="users" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../../header.jsp"/>

<div style="display:flex;">
    <c:if test="${sessionScope.loggedUser ne null}">
        <div >
            <jsp:include page="../../headerMenu.jsp"/>
        </div>
    </c:if>

    <div class="div-with-table">
        <h3 class="h1">Список пользователей</h3>

        <c:if test="${not authority.contains(roleAdministrator)}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${authority.contains(roleAdministrator)}">
            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newUser" class="buttonClass">ДОБАВИТЬ ПОЛЬЗОВАТЕЛЯ</button>
            </form>
            <br>

            <form action="coffee-manage">
                <label> <input name="searchUsername" placeholder="роль" value="${requestScope.searchUsername}"> </label>
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="pageSize" value="${pageSize}">
                <button type="submit" name="command" value="users" class="buttonClass">ПОИСК</button>
            </form>

            <c:choose>
                <c:when test="${users eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${users ne null and users.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table">
                        <tr>
                            <th>№</th>
                            <th>Логин</th>
                            <th>Роли</th>
                            <th colspan="2">Действие</th>
                        </tr>

                        <c:choose>
                            <c:when test="${pageNumber ne 0}">
                                <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="lastCount" value="0"/>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="user" items="${users}" varStatus="status">
                            <tr>
                                <td>${lastCount+status.count}</td>
                                <td>${user.username}</td>
                                <td>
                                    <c:forEach var="role" items="${user.roles}">
                                        ${role.name}
                                    </c:forEach>

                                </td>
                                <td>
                                    <form action="coffee-manage" method="get">
                                        <input type="hidden" name="username" value="${user.username}">
                                        <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <button type="submit" name="command" value="editUserPassword" class="buttonClass">
                                            СМЕНИТЬ ПАРОЛЬ
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="coffee-manage" method="get">
                                        <input type="hidden" name="username" value="${user.username}">
                                        <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <button type="submit" name="command" value="editRolesUser" class="buttonClass">
                                            КОРРЕКТИРОВАТЬ СПИСОК РОЛЕЙ
                                        </button>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>

                    </table>
                </c:otherwise>

            </c:choose>

            <br>

            <jsp:include page="../../pagination.jsp"/>

        </c:if>

    </div>
</div>
</body>
</html>
