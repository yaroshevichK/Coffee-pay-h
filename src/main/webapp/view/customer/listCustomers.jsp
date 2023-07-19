<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleAdministrator)}">
    <c:set var="access" value="${true}"/>
</c:if>

<c:set var="paramUrl" value="${requestScope.paramUrl}" scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPage" value="${requestScope.pageable.lastPage}" scope="request"/>
<c:set var="customers" value="${requestScope.pageable.records}" scope="request"/>

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
        <h3 class="h1">Список покупателей</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newCustomer" class="buttonClass">ДОБАВИТЬ ПОКУПАТЕЛЯ</button>
            </form>
            <br>

            <form action="coffee-manage">
                <label> <input name="searchName" placeholder="имя" value="${requestScope.searchName}"> </label>
                <label> <input name="searchSurname" placeholder="фамилия" value="${requestScope.searchSurname}">
                </label>
                <label> <input name="searchPhone" placeholder="телефон" value="${requestScope.searchPhone}"> </label>
                <label> <input name="searchEmail" placeholder="эл. почта" value="${requestScope.searchEmail}"> </label>
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="pageSize" value="${pageSize}">
                <button type="submit" name="command" value="customers" class="buttonClass">ПОИСК</button>
            </form>

            <c:choose>
                <c:when test="${customers eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${customers ne null and customers.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table">
                        <tr>
                            <th>№</th>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Телефон</th>
                            <th>Эл. почта</th>
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
                        <c:forEach var="customer" items="${customers}" varStatus="status">
                            <tr>
                                <td>${lastCount+status.count}</td>
                                <td>${customer.name}</td>
                                <td>${customer.surname}</td>
                                <td>${customer.phone}</td>
                                <td>${customer.email}</td>
                                <td>
                                    <form action="coffee-manage" method="get">
                                        <input type="hidden" name="customerId" value="${customer.id}">
                                        <input type="hidden" name="prevURL"
                                               value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <button type="submit" name="command" value="updateCustomer"
                                                class="buttonClass">
                                            РЕДАКТИРОВАТЬ
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="coffee-manage" method="post">
                                        <c:choose>
                                            <c:when test="${customers.size() eq 1}">
                                                <input type="hidden" name="prevURL"
                                                       value="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="prevURL"
                                                       value="/coffee-manage?${pageContext.request.queryString}"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <input type="hidden" name="customerId" value="${customer.id}">
                                        <button type="submit" name="command" value="deleteCustomer"
                                                class="buttonClass">
                                            УДАЛИТЬ
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
