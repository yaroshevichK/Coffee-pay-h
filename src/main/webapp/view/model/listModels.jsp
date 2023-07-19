<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>
<c:set var="roleManager" value="Manager"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleAdministrator) or authority.contains(roleManager)}">
    <c:set var="access" value="${true}"/>
</c:if>

<c:set var="paramUrl" value="${requestScope.paramUrl}" scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPage" value="${requestScope.pageable.lastPage}" scope="request"/>
<c:set var="models" value="${requestScope.pageable.records}" scope="request"/>

<html>
<head>
    <title>Coffee manage project</title>
</head>
<body>
<jsp:include page="../../header.jsp"/>

<div style="display:flex;">
    <c:if test="${sessionScope.loggedUser ne null}">
        <div>
            <jsp:include page="../../headerMenu.jsp"/>
        </div>
    </c:if>

    <div class="div-with-table">
        <h3 class="h1">Список моделей</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newModel" class="buttonClass">ДОБАВИТЬ МОДЕЛЬ</button>
            </form>
            <br>

            <form action="coffee-manage">
                <label> <input name="searchBrand" placeholder="брэнд" value="${requestScope.searchBrand}"> </label>
                <label> <input name="searchNameModel" placeholder="модель" value="${requestScope.searchNameModel}">
                </label>
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="pageSize" value="${pageSize}">
                <button type="submit" name="command" value="models" class="buttonClass">ПОИСК</button>
            </form>

            <c:choose>
                <c:when test="${models eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${models ne null and models.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table">
                        <tr>
                            <th>№</th>
                            <th>Брэнд</th>
                            <th>Модель</th>
                            <c:if test="${access}">
                                <th colspan="2">Действие</th>
                            </c:if>
                        </tr>

                        <c:choose>
                            <c:when test="${pageNumber ne 0}">
                                <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="lastCount" value="0"/>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="model" items="${models}" varStatus="status">
                            <tr>
                                <td>${lastCount+status.count}</td>
                                <td>${model.brand}</td>
                                <td>${model.nameModel}</td>
                                <c:if test="${access}">
                                    <td>
                                        <form action="coffee-manage" method="get">
                                            <input type="hidden" name="modelId" value="${model.id}">
                                            <input type="hidden" name="prevURL"
                                                   value="/coffee-manage?${pageContext.request.queryString}"/>
                                            <button type="submit" name="command" value="updateModel"
                                                    class="buttonClass">
                                                РЕДАКТИРОВАТЬ
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="coffee-manage" method="post">
                                            <c:choose>
                                                <c:when test="${models.size() eq 1}">
                                                    <input type="hidden" name="prevURL"
                                                           value="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="prevURL"
                                                           value="/coffee-manage?${pageContext.request.queryString}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="hidden" name="modelId" value="${model.id}">
                                            <button type="submit" name="command" value="deleteModel"
                                                    class="buttonClass">
                                                УДАЛИТЬ
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
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
