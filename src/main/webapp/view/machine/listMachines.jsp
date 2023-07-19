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
<c:set var="machines" value="${requestScope.pageable.records}" scope="request"/>

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
        <h3 class="h1">Список аппаратов</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newMachine" class="buttonClass">ДОБАВИТЬ АППАРАТ</button>
            </form>
            <br>

            <form action="coffee-manage">
                <label> <input name="searchSerialNumber" placeholder="серийный номер"
                               value="${requestScope.searchSerialNumber}"> </label>
                <label> <input name="searchBrand" placeholder="брэнд" value="${requestScope.searchBrand}"> </label>
                <label> <input name="searchNameModel" placeholder="модель" value="${requestScope.searchNameModel}">
                </label>
                <label> <input name="searchCity" placeholder="город" value="${requestScope.searchCity}"> </label>
                <label> <input name="searchStreet" placeholder="улица" value="${requestScope.searchStreet}"> </label>
                <input type="hidden" name="pageNumber" value="${pageNumber}">
                <input type="hidden" name="pageSize" value="${pageSize}">
                <button type="submit" name="command" value="machines" class="buttonClass">ПОИСК</button>
            </form>

            <c:choose>
                <c:when test="${machines eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${machines ne null and machines.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table">
                        <tr>
                            <th>№</th>
                            <th>Серийный номер</th>
                            <th>Адрес</th>
                            <th>Модель</th>
                            <th colspan="3">Действие</th>
                        </tr>

                        <c:choose>
                            <c:when test="${pageNumber ne 0}">
                                <c:set var="lastCount" value="${(pageNumber-1)*pageSize}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="lastCount" value="0"/>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="machine" items="${machines}" varStatus="status">
                            <tr>
                                <td>${lastCount+status.count}</td>
                                <td>${machine.serialNumber}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${machine.address ne null}">
                                            ${machine.address.city}
                                            <br>
                                            ${machine.address.street}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${machine.model ne null}">
                                            ${machine.model.brand}
                                            <br>
                                            ${machine.model.nameModel}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form action="coffee-manage" method="get">
                                        <input type="hidden" name="machineId" value="${machine.id}">
                                        <input type="hidden" name="prevURL"
                                               value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <button type="submit" name="command" value="updateMachine"
                                                class="buttonClass">
                                            РЕДАКТИРОВАТЬ
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="coffee-manage" method="get">
                                        <input type="hidden" name="machineId" value="${machine.id}">
                                        <input type="hidden" name="prevUrlMachines"
                                               value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <button type="submit" name="command" value="updateProductsMachine"
                                                class="buttonClass">
                                            КОРРЕКТИРОВАТЬ СПИСОК ПРОДУКЦИИ
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="coffee-manage" method="post">
                                        <c:choose>
                                            <c:when test="${machines.size() eq 1}">
                                                <input type="hidden" name="prevURL"
                                                       value="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="prevURL"
                                                       value="/coffee-manage?${pageContext.request.queryString}"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <input type="hidden" name="machineId" value="${machine.id}">
                                        <button type="submit" name="command" value="deleteMachine"
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
