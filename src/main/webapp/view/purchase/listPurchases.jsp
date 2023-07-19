<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>
<c:set var="roleManager" value="Manager"/>
<c:set var="access" value="${false}"/>
<c:set var="accessAdmin" value="${false}"/>
<c:if test="${authority.contains(roleAdministrator) or authority.contains(roleManager)}">
    <c:set var="access" value="${true}"/>
</c:if>
<c:if test="${authority.contains(roleAdministrator)}">
    <c:set var="accessAdmin" value="${true}"/>
</c:if>

<c:set var="paramUrl" value="${requestScope.paramUrl}" scope="request"/>
<c:set var="pageNumber" value="${requestScope.pageable.pageNumber}" scope="request"/>
<c:set var="pageSize" value="${requestScope.pageable.pageSize}" scope="request"/>
<c:set var="lastPage" value="${requestScope.pageable.lastPage}" scope="request"/>
<c:set var="purchases" value="${requestScope.pageable.records}" scope="request"/>

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
        <h3 class="h1">Список покупок</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form action="coffee-manage" method="get">
                <input type="hidden" name="prevURL" value="/coffee-manage?${pageContext.request.queryString}"/>
                <button type="submit" name="command" value="newPurchase" class="buttonClass">ДОБАВИТЬ ПОКУПКУ</button>
            </form>
            <br>
            <c:choose>
                <c:when test="${purchases eq null}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:when test="${purchases ne null and purchases.size() eq 0}">
                    <h2>Список пуст</h2>
                </c:when>

                <c:otherwise>
                    <table class="main-table">
                        <tr>
                            <th>№</th>
                            <th>Дата покупки</th>
                            <th>Покупатель</th>
                            <th colspan="2">Аппарат</th>
                            <th>Продукция</th>
                            <th>Цена</th>
                            <th>Скидка</th>
                            <th>Сумма</th>
                            <th>Тип оплаты</th>
                            <th>Номер карты</th>
                            <c:if test="${accessAdmin}">
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
                        <c:forEach var="purchase" items="${purchases}" varStatus="status">
                            <tr>
                                <td>${lastCount+status.count}</td>
                                <td>
                                        ${purchase.createDate}
                                </td>
                                <td>
                                    <c:if test="${purchase.customer ne null}">
                                        ${purchase.customer.name}
                                        <br>
                                        ${purchase.customer.surname}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${purchase.machine ne null}">
                                        <c:if test="${purchase.machine.model ne null}">
                                            ${purchase.machine.model.brand}
                                            <br>
                                            ${purchase.machine.model.nameModel}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${purchase.machine ne null}">
                                        <c:if test="${purchase.machine.address ne null}">
                                            ${purchase.machine.address.city}
                                            <br>
                                            ${purchase.machine.address.street}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${purchase.product ne null}">
                                        ${purchase.product.name}
                                    </c:if>
                                </td>
                                <td>${purchase.price}</td>
                                <td>
                                    <c:if test="${purchase.discount ne null}">
                                        ${purchase.discount.percent}%
                                    </c:if>
                                <td>${purchase.amount}</td>
                                <td>${purchase.typePayment.name}</td>
                                <td>
                                    <c:if test="${purchase.creditCard ne null and not empty purchase.creditCard.number}">
                                        ${purchase.creditCard.number.charAt(0)}
                                        ***
                                        ${purchase.creditCard.number.charAt(purchase.creditCard.number.length()-1)}
                                    </c:if>

                                </td>
                                <c:if test="${accessAdmin}">
                                    <td>
                                        <form action="coffee-manage" method="get">
                                            <input type="hidden" name="purchaseId" value="${purchase.id}">
                                            <input type="hidden" name="prevURL"
                                                   value="/coffee-manage?${pageContext.request.queryString}"/>
                                            <button type="submit" name="command" value="updatePurchase"
                                                    class="buttonClass">
                                                РЕДАКТИРОВАТЬ
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="coffee-manage" method="post">
                                            <c:choose>
                                                <c:when test="${purchases.size() eq 1}">
                                                    <input type="hidden" name="prevURL"
                                                           value="${paramUrl}&pageSize=${pageSize}&pageNumber=${pageNumber-1}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="prevURL"
                                                           value="/coffee-manage?${pageContext.request.queryString}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="hidden" name="purchaseId" value="${purchase.id}">
                                            <button type="submit" name="command" value="deletePurchase"
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
