<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scroll-table.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleCustomer" value="Customer"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleCustomer)}">
    <c:set var="access" value="${true}"/>
</c:if>
<c:set var="machines" value="${requestScope.machines}" scope="request"/>

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
        <h3 class="h1">Выбор аппарата</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">
            <c:choose>
                <c:when test="${machines eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${machines ne null and machines.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table-fix">
                        <tr>
                            <th>№</th>
                            <th>Модель</th>
                            <th>Адрес</th>
                            <th>Действие</th>
                        </tr>
                    </table>

                    <div class="scroll-table-body">
                        <table class="main-table-scroll">
                            <c:forEach var="machine" items="${machines}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
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
                                        <form action="coffee-manage" method="get">
                                            <input type="hidden" name="machineId" value="${machine.id}">
                                            <button type="submit" name="command" value="chooseProduct"
                                                    class="buttonClass">
                                                ВЫБРАТЬ
                                            </button>
                                        </form>
                                    </td>

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
