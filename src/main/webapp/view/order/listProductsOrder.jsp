<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scroll-table.css">

<c:set var="authority" value="${sessionScope.authority}"/>
<c:set var="roleCustomer" value="Customer"/>
<c:set var="access" value="${false}"/>
<c:if test="${authority.contains(roleCustomer)}">
    <c:set var="access" value="${true}"/>
</c:if>
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
        <h3 class="h1">Выбор продукции</h3>

        <c:if test="${not access}">
            <p>
                ДОСТУП ОГРАНИЧЕН
            </p>
        </c:if>

        <c:if test="${access}">

            <form action="coffee-manage">
                <input type="hidden" name="machineId" value="${requestScope.machine.id}">

                <c:if test="${requestScope.machine ne null}">
                    <p>${requestScope.machine.model.brand}, ${requestScope.machine.model.nameModel}</p>
                    <p>${requestScope.machine.address.city}, ${requestScope.machine.address.street}</p>
                </c:if>

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
                                <th>Продукция</th>
                                <th>Цена</th>
                                <th>Действие</th>
                            </tr>
                        </table>

                        <div class="scroll-table-body">
                            <table class="main-table-scroll">
                                <c:forEach var="product" items="${products}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                        <td>
                                            <form action="coffee-manage" method="get">
                                                <input type="hidden" name="machineId" value="${requestScope.machine.id}">
                                                <input type="hidden" name="productId" value="${product.id}">
                                                <button type="submit" name="command" value="chooseTypePay"
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
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
