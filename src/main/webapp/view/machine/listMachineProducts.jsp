<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scroll-table.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>
<c:set var="roleManager" value="Manager"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleAdministrator) or authority.contains(roleManager)}">
    <c:set var="access" value="${true}"/>
</c:if>

<c:set var="paramUrl" value="${requestScope.paramUrl}" scope="request"/>
<c:set var="products" value="${requestScope.products}" scope="request"/>

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
        <h3 class="h1">Список продукции</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <c:if test="${requestScope.machine ne null}">
                <p>
                        ${requestScope.machine.serialNumber}
                </p>
                <p>
                        ${requestScope.machine.model.brand}, ${requestScope.machine.model.nameModel}
                </p>

                <p>
                        ${requestScope.machine.address.city}, ${requestScope.machine.address.street}
                </p>
            </c:if>

            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <input type="hidden" name="prevUrlMachines" value="${requestScope.prevUrlMachines}"/>
                <input type="hidden" name="machineId" value="${requestScope.machine.id}"/>
                <button type="submit" name="command" value="newMachineProduct" class="buttonClass">ДОБАВИТЬ ПРОДУКЦИЮ
                </button>
                <a href="${pageContext.request.contextPath}${requestScope.prevUrlMachines}"
                   class="buttonClass">НАЗАД</a>
            </form>
            <br>

            <c:choose>
                <c:when test="${products eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${products ne null and products.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table-fix">
                        <tr>
                            <th>№</th>
                            <th>Название</th>
                            <th>Цена</th>
                            <c:if test="${access}">
                                <th>Действие</th>
                            </c:if>
                        </tr>
                    </table>

                    <div class="scroll-table-body">
                        <table class="main-table-scroll">
                            <c:forEach var="product" items="${products}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${product.name}</td>
                                    <td>${product.price}</td>
                                    <c:if test="${access}">
                                        <td>
                                            <form action="coffee-manage" method="post">
                                                <input type="hidden" name="prevURL"
                                                       value="/coffee-manage?${pageContext.request.queryString}"/>
                                                <input type="hidden" name="prevUrlMachines"
                                                       value="${requestScope.prevUrlMachines}"/>
                                                <input type="hidden" name="machineId"
                                                       value="${requestScope.machine.id}">
                                                <input type="hidden" name="productId" value="${product.id}">
                                                <button type="submit" name="command" value="deleteMachineProduct"
                                                        class="buttonClass">
                                                    УДАЛИТЬ
                                                </button>
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </c:otherwise>

            </c:choose>
        </c:if>
    </div>
</div>
</body>
</html>
