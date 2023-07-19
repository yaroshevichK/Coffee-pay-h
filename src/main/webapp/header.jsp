<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">

<c:set var="roles" value="${sessionScope.authority}"/>
<c:set var="roleCustomer" value="Customer"/>
<c:set var="prevURL" value="/coffee-manage?${pageContext.request.queryString}" scope="request"/>

<nav>
    <ul class="topmenu">
        <li class="item">
            <a class="item-point" href="${pageContext.request.contextPath}/coffee-manage?command=main">
                Главная страница
            </a>
        </li>

        <c:if test="${sessionScope.loggedUser eq null}">
            <li class="item">
                <a class="item-point" href="${pageContext.request.contextPath}/auth?command=registration">
                    Регистрация
                </a>
            </li>
        </c:if>


        <c:if test="${sessionScope.loggedUser eq null}">
            <li class="item">
                <a class="item-point" href="${pageContext.request.contextPath}/auth?command=login">
                    Авторизация
                </a>
            </li>
        </c:if>


        <c:if test="${sessionScope.loggedUser ne null}">
            <li class="item-right">
                <a class="item-point" href="${pageContext.request.contextPath}/auth?command=logout">
                    Выйти
                </a>
            </li>
        </c:if>

        <c:if test="${sessionScope.loggedUser ne null}">
            <li class="item-right">
                <a class="item-point" href="">
                    Hi, ${sessionScope.loggedUser.username}
                </a>
                <ul class="submenu-right">
                    <li>
                        <a class="item-point" href="${pageContext.request.contextPath}/coffee-manage?command=changePassword">
                            Сменить пароль
                        </a>
                    </li>
                    <c:if test="${roles.contains(roleCustomer)}">
                        <li>
                            <a class="item-point" href="${pageContext.request.contextPath}/coffee-manage?command=profile">
                                Профиль
                            </a>
                        </li>
                        <li>
                            <a class="item-point" href="${pageContext.request.contextPath}/coffee-manage?command=creditCards">
                                Банковские карты
                            </a>
                        </li>
                    </c:if>
                </ul>
            </li>

        </c:if>

    </ul>

</nav>
