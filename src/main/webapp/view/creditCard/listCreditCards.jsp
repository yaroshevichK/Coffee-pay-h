<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scroll-table.css">

<c:set var="creditCards" value="${requestScope.creditCards}" scope="request"/>

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
        <h3 class="h1">Список банковских карт</h3>

        <form action="coffee-manage" method="get">
            <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
            <button type="submit" name="command" value="newCreditCard" class="buttonClass">ДОБАВИТЬ КАРТУ</button>
        </form>
        <br>

        <c:choose>
            <c:when test="${creditCards eq null}">
                <h2>Список пуст</h2>
            </c:when>

            <c:when test="${creditCards ne null and creditCards.size() eq 0}">
                <h2>Список пуст</h2>
            </c:when>

            <c:otherwise>
                <table class="main-table-fix">
                    <tr>
                        <th>№</th>
                        <th>Номер</th>
                        <th colspan="2">Действие</th>
                    </tr>
                </table>

                <div class="scroll-table-body">
                    <table class="main-table-scroll">
                        <c:forEach var="creditCard" items="${creditCards}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${creditCard.number}</td>
                                <td>
                                    <form action="coffee-manage" method="get">
                                        <input type="hidden" name="creditCardId" value="${creditCard.id}">
                                        <input type="hidden" name="prevURL"
                                               value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <button type="submit" name="command" value="updateCreditCard"
                                                class="buttonClass">
                                            РЕДАКТИРОВАТЬ
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="coffee-manage" method="post">
                                        <input type="hidden" name="prevURL"
                                               value="/coffee-manage?${pageContext.request.queryString}"/>
                                        <input type="hidden" name="creditCardId" value="${creditCard.id}">
                                        <button type="submit" name="command" value="deleteCreditCard"
                                                class="buttonClass">
                                            УДАЛИТЬ
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </c:otherwise>

        </c:choose>

    </div>
</div>
</body>
</html>
