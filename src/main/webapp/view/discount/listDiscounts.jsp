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
<c:set var="discounts" value="${requestScope.discounts}" scope="request"/>

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
        <h3 class="h1">Список скидок</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">
            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newDiscount" class="buttonClass">ДОБАВИТЬ СКИДКУ
                </button>
            </form>
            <br>

            <c:choose>
                <c:when test="${discounts eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${discounts ne null and discounts.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table-fix">
                        <tr>
                            <th>№</th>
                            <th>Сумма</th>
                            <th>Процент</th>
                            <c:if test="${access}">
                                <th colspan="2">Действие</th>
                            </c:if>
                        </tr>
                    </table>

                    <div class="scroll-table-body">
                        <table class="main-table-scroll">
                            <c:forEach var="discount" items="${discounts}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${discount.amount}</td>
                                    <td>${discount.percent}</td>
                                    <c:if test="${access}">
                                        <td>
                                            <form action="coffee-manage" method="get">
                                                <input type="hidden" name="discountId" value="${discount.id}">
                                                <input type="hidden" name="prevURL"
                                                       value="/coffee-manage?${pageContext.request.queryString}"/>
                                                <button type="submit" name="command" value="updateDiscount"
                                                        class="buttonClass">
                                                    РЕДАКТИРОВАТЬ
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="coffee-manage" method="post">
                                                <input type="hidden" name="prevURL"
                                                       value="/coffee-manage?${pageContext.request.queryString}"/>
                                                <input type="hidden" name="discountId" value="${discount.id}">
                                                <button type="submit" name="command" value="deleteDiscount"
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
