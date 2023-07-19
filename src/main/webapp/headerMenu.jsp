<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-menu.css">

<c:set var="roles" value="${sessionScope.authority}"/>
<c:set var="roleAdministrator" value="Administrator"/>
<c:set var="roleManager" value="Manager"/>
<c:set var="roleCustomer" value="Customer"/>

<nav>
    <div class="div">
        <ul class="menu">
            <li>
                <span class="item-point-v-empty"></span>
            </li>
            <c:if test="${roles.contains(roleAdministrator)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=roles">РОЛИ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator)}">
                <li>
                    <a class="item-point-v" href="${pageContext.request.contextPath}/coffee-manage?command=users">ПОЛЬЗОВАТЕЛИ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=customers">ПОКУПАТЕЛИ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v" href="${pageContext.request.contextPath}/coffee-manage?command=addresses">АДРЕСА</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=models">МОДЕЛИ АППАРАТОВ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=products">ПРОДУКЦИЯ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=machines">АППАРАТЫ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=discounts">СКИДКИ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=typePayments">ТИПЫ ОПЛАТ</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleAdministrator) or roles.contains(roleManager)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=purchases">СПИСОК ПОКУПОК</a>
                </li>
            </c:if>
            <c:if test="${roles.contains(roleCustomer)}">
                <li>
                    <a class="item-point-v"
                       href="${pageContext.request.contextPath}/coffee-manage?command=mainOrder">СОВЕРШИТЬ ПОКУПКУ</a>
                </li>
            </c:if>

            <%--            <li sec:authorize="hasAuthority('Customer')">--%>
            <%--                <a class="item-point-v" th:href="@{/orders/history}" data-th-text="#{panel.order.history}"></a>--%>
            <%--            </li>--%>
        </ul>
    </div>
</nav>
